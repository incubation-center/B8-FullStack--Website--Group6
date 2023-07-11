package com.polify.utils;

import java.io.IOException;
import java.util.Properties;

import com.polify.entity.OTP;
import com.polify.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utils {
    private static final String UPLOAD_URL = ProjectUtils.FILE_URL;

    public static String uploadFile(byte[] fileBytes, String fileName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadRequest = new HttpPost(UPLOAD_URL + "/upload");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", fileBytes, ContentType.APPLICATION_OCTET_STREAM, fileName);
        builder.addTextBody("description", "File uploaded from my server");

        HttpEntity multipart = builder.build();
        uploadRequest.setEntity(multipart);

        CloseableHttpResponse uploadResponse = httpClient.execute(uploadRequest);
        HttpEntity responseEntity = uploadResponse.getEntity();
        String responseString = EntityUtils.toString(responseEntity, "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseString);
        return responseJson.get("file_name").asText();
    }

    public static void SendOtp(int code, User user, int optKey) throws MessagingException {

        String recipientEmail = user.getEmail();
        String senderEmail = ProjectUtils.SENDER_EMAIL;
        String senderPassword = ProjectUtils.MAIL_PASSWORD;
        String body;
        String subject;

        switch (optKey) {
            case 1:
                subject = "Your OTP for registration";
                body = "Hello " + user.getUsername() + ",\n\nYour OTP for registration is: " + code;
                break;
            case 2:
                subject = "Reset password";
                body = "Hello " + user.getUsername() + ",\n\nYour OTP for reset password is: " + code;
                break;
            default:
                throw new IllegalArgumentException("Invalid optKey value: " + optKey);
        }

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
    }
}