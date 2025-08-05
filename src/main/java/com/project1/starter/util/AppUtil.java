package com.project1.starter.util;

import java.io.File;
import java.nio.file.Paths;

public class AppUtil {
    public static String getUploadPath(String fileName) {
        return new File("src//main//resources//static//uploads//**").getAbsolutePath() + "//" + fileName; // Save to ./uploads/
    }
}

