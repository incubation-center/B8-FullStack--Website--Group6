package com.polify.controller;

import java.util.*;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polify.entity.PollOption;
import com.polify.model.VerifyUserDTO;
import com.polify.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.polify.entity.User;
import com.polify.entity.OTP;
import com.polify.exception.ResourceNotFoundException;
import com.polify.model.LoginHistoryDTO;
import com.polify.model.UserDTO;
import com.polify.model.ProfileDTO;
import com.polify.service.LoginHistoryService;
import com.polify.service.UserAccountService;
import com.polify.utils.LoginHistoryConverter;
import com.polify.utils.ProjectUtils;
import com.polify.utils.UserConverter;
import com.polify.service.OtpService;
import com.polify.utils.Utils;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type User controller.
 *
 */
@RestController
@RequestMapping(ProjectUtils.API_URL)
public class UserLoginController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private LoginHistoryService loginHistoryService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private LoginHistoryConverter loginHistoryConverter;

    @Autowired
    private OtpService otpService;

	/**
	 * Register user.
	 *
	 * @param userDTO
	 * @return the user
	 */
    @PostMapping(ProjectUtils.REGISTER_USER_URL)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws MessagingException {
        // Generate 4-digit OTP
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        User user = userAccountService.save(userConverter.convertFromDto(userDTO));

        OTP Obj = new OTP();
        Obj.setUser(user);
        Obj.setCode(code);
        Obj.setCreatedBy(user.getUsername());
        Obj.setUpdatedBy(user.getUsername());

        otpService.addOtp(Obj);

        String recipientEmail = userDTO.getEmail();
        String senderEmail = ProjectUtils.SENDER_EMAIL;
        String senderPassword = ProjectUtils.MAIL_PASSWORD;
        String subject = "Your OTP for registration";
        String body = "Hello " + userDTO.getUsername() + ",\n\nYour OTP for registration is: " + code;

        // Create properties object for SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        return ResponseEntity.status(HttpStatus.CREATED).body(userConverter.convertFromEntity(user));
    }
	/**
	 * Get login history by username
	 *
	 * @param username
	 * @return login history
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/login-history/{username}")
	public ResponseEntity<List<LoginHistoryDTO>> getLoginHistoryByUser(
			@PathVariable(value = "username") String username) throws ResourceNotFoundException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(loginHistoryConverter.createFromEntities(loginHistoryService.findByUsername(username)));
	}

    @PostMapping(ProjectUtils.VERIFY_USER_URL)
    public ResponseEntity<Map> verifySignUp(@Valid @RequestBody VerifyUserDTO userDTO) throws MessagingException {
        String username = userDTO.getUsername();
        int code = userDTO.getCode();
        Optional<OTP> obj = otpService.findCodeByCreatedBy(username);

        if (obj.isPresent() && obj.get().getCode() == code) {

            User user_obj = userAccountService.getUserByUsername(username);

            user_obj.setVerified(true);
            userAccountService.save(user_obj);

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "SUCCESS");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.OK);

        } else {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Invalid code");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "/update-profile", consumes = "multipart/form-data")
    public ResponseEntity<Map> updateProfile(ProfileDTO profileDTO,
                                             @RequestPart(name = "file", required = false) MultipartFile file) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user_obj = userAccountService.getUserByUsername(authentication.getName());

        String uploadUrl = null;
        String file_name;
        if (profileDTO != null) {

            file_name = null;
            if (file != null) {
                file_name = null;

                byte[] fileBytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                file_name = Utils.uploadFile(fileBytes, fileName);
            }
            user_obj.setImage(file_name);
            userAccountService.save(user_obj);


        } else {
            throw new IllegalArgumentException("Required request part 'profileDTO' is not present");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("file_name", uploadUrl + "/files/" + file_name);

        return ResponseEntity.ok(response);
    }

	@Bean
	public LoginHistoryConverter loginHistoryConverter() {
		return new LoginHistoryConverter();
	}

	@Bean
	public UserConverter userConverter() {
		return new UserConverter();
	}
}
