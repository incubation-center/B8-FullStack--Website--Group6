package com.polify.controller;

import java.util.*;

import javax.validation.Valid;

import com.polify.entity.PollOption;
import com.polify.model.VerifyUserDTO;
import com.polify.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;

import com.polify.entity.User;
import com.polify.entity.OTP;
import com.polify.exception.ResourceNotFoundException;
import com.polify.model.LoginHistoryDTO;
import com.polify.model.UserDTO;
import com.polify.service.LoginHistoryService;
import com.polify.service.UserAccountService;
import com.polify.utils.LoginHistoryConverter;
import com.polify.utils.ProjectUtils;
import com.polify.utils.UserConverter;
import com.polify.service.OtpService;
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
    public ResponseEntity<VerifyUserDTO> verifySignUp(@Valid @RequestBody VerifyUserDTO userDTO) throws MessagingException {
        String username = userDTO.getUsername();
        int code = userDTO.getCode();
        Optional<OTP> obj = otpService.findCodeByCreatedBy(username);

        if (obj.isPresent() && obj.get().getCode() == code) {

            User user_obj = userAccountService.getUserByUsername(username);

            user_obj.setVerified(true);
            userAccountService.save(user_obj);
            return ResponseEntity.ok(userDTO);
        } else {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Invalid code");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
