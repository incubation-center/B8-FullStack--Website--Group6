package com.polify.controller;

import java.util.*;

import javax.security.auth.kerberos.KerberosKey;
import javax.validation.Valid;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
    public static final String SECRET = "FG^723fhhQW12~123qwert123#@$fr!67DSWa";

    public static final long EXPIRATION_TIME = 100000000;
	/**
	 * Register user.
	 *
	 * @param userDTO
	 * @return the user
	 */
    @PostMapping(ProjectUtils.REGISTER_USER_URL)
    public ResponseEntity<Map> createUser(@Valid @RequestBody UserDTO userDTO) throws MessagingException {
        // Generate 4-digit OTP
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        User existing_username = userAccountService.getUserByUsername(userDTO.getUsername());
        User existing_email =  userAccountService.getByEmail(userDTO.getEmail());

        if (existing_username != null || existing_email !=null) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "User is already exist");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.BAD_REQUEST);
        }

        User user = userAccountService.save(userConverter.convertFromDto(userDTO));

        OTP Obj = new OTP();
        Obj.setUser(user);
        Obj.setCode(code);
        Obj.setCreatedBy(user.getUsername());
        Obj.setUpdatedBy(user.getUsername());

        otpService.addOtp(Obj);
        Utils.SendOtp(code, user, 1);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Register successfully");
        return new ResponseEntity<>(
            responseMap,
            HttpStatus.CREATED);
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

    @PostMapping(ProjectUtils.VERIFY_FORGOT_PASSWORD)
    public ResponseEntity<Map> verifyForgotPassword(@Valid @RequestBody VerifyUserDTO userDTO) throws MessagingException {
        String email = userDTO.getEmail();
        int code = userDTO.getCode();
        Optional<OTP> obj = otpService.findCodeByCodeAndCreatedBy(code, email);

        if (obj.isPresent() && obj.get().getCode() == code) {

            Authentication authResult = null;

            String token = JWT.create().withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // JWT token validity time
                .sign(Algorithm.HMAC512(SECRET.getBytes())); // JWT Signature

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("token", token);
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

    @PostMapping(path = "reset-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map> resetForgotPassword(@RequestParam(required = false) String token, @RequestBody Map<String, String> body ){
        try {
            DecodedJWT decodedToken = JWT.decode(token);

            String email = decodedToken.getSubject();
            User user_obj = userAccountService.getByEmail(email);

            String password = body.get("password");
            user_obj.setPassword(bCryptPasswordEncoder.encode(password));
            userAccountService.save(user_obj);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "SUCCESS");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Error   ");
            return new ResponseEntity<>(
                HttpStatus.BAD_GATEWAY);
        }
    }
    @PostMapping(ProjectUtils.FORGOT_PASSWORD_URL)
    public ResponseEntity<Map> forgotPassowrd(@Valid @RequestBody ProfileDTO profileDTO) throws MessagingException {
        String email = profileDTO.getEmail();
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);

        User user = userAccountService.getByEmail(email);


        if (user !=null) {

            OTP Obj = new OTP();
            Obj.setUser(user);
            Obj.setCode(code);
            Obj.setCreatedBy(email);
            Obj.setUpdatedBy(user.getUsername());
            Obj.setForgotPassword(true);

            otpService.addOtp(Obj);

            Utils.SendOtp(code, user, 2);

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "SUCCESS");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.OK);

        } else {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Email does not exist");
            return new ResponseEntity<>(
                responseMap,
                HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "/update-profile", consumes = "multipart/form-data")
    public ResponseEntity<Map> updateProfile(ProfileDTO profileDTO,
                                             @RequestPart(name = "file", required = true) MultipartFile file) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user_obj = userAccountService.getUserByUsername(authentication.getName());

        String uploadUrl = ProjectUtils.FILE_URL;
        String file_name;
        if (profileDTO != null) {

            file_name = null;
            if (file != null) {
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
