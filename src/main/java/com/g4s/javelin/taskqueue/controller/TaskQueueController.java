package com.g4s.javelin.taskqueue.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;

@RestController
@RequestMapping("/tasks")
public class TaskQueueController {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueController.class.getName());
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping("/saveAuditLog")
    public void saveAuditLog(final AuditLogDTO auditLog) throws IOException {
        HttpURLConnection connection = null;
        try {
             // Create connection
            URL url = new URL("https://reports-dot-javelin-dev.appspot.com/reports/v1/auditlog/location/log");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Content-Length", ""
                + Integer.toString(MAPPER.writeValueAsString(auditLog).getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(MAPPER.writeValueAsString(auditLog));
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            LOGGER.info(response.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        LOGGER.info("Saving Audit Logs");
    }

}
