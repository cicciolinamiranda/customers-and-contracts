package com.g4s.javelin.api.custom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.enums.ObjectTypeEnum;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Sharlyn Mae Pandes
 * @since 4/18/2016
 *
 * 4/20/16 - Renamed from TaskQueueController
 * */

@RestController
@RequestMapping("/tasks")
public class TaskQueueApi {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueApi.class.getName());
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = "/saveAuditLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveAuditLog(@RequestBody final AuditLogDTO auditLog) throws IOException {
        HttpURLConnection connection = null;
        String logURL = "";

        LOGGER.info(MAPPER.writeValueAsString(auditLog));
        try {
            // Create connection
            if (auditLog.getObjectType().equals(ObjectTypeEnum.CUSTOMERLOCATION.getCode())) {
                logURL = "https://reports-dot-javelin-dev.appspot.com/reports/v1/auditlog/location/log";
            } else if (auditLog.getObjectType().equals(ObjectTypeEnum.POST.getCode())) {
                logURL = "https://reports-dot-javelin-dev.appspot.com/reports/v1/auditlog/post/log";
            }

            URL url = new URL(logURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Content-Length",
                    "" + Integer.toString(MAPPER.writeValueAsString(auditLog).getBytes().length));
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
    }

}
