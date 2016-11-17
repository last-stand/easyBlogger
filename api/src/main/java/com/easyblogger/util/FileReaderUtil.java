package com.easyblogger.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderUtil.class);

    public static String getFileContent(final String fileName) {

        BufferedReader br = null;
        try {
            InputStream in = new FileInputStream(new File(fileName));
            br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            return sb.toString();
        } catch (IOException e) {
            LOGGER.error("File" + fileName + "not found", e);
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public static String getFileContent(String filePath, boolean isAbsolutePath) {
        if (!isAbsolutePath) {
            return getFileContent(filePath);
        }
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            LOGGER.error("File at location " + filePath + " not found", e);
        }
        return null;
    }
}
