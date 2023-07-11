package com.polify.utils;

import java.io.IOException;
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
}