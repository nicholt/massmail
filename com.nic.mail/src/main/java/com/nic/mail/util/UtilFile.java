package com.nic.mail.util;

import java.io.File;

public class UtilFile {

    public static String getFileName(String fullFileName) {
    	return fullFileName.substring(fullFileName.lastIndexOf(File.separator) + 1);
    }

    public static String getLocalizedFullFileName(String fileName) {
        String fileNameSeparator = "\\".equals(File.separator)? "\\" + File.separator: File.separator;
        String fullFileName = fileName.replaceAll("/+|\\\\+", fileNameSeparator);
        return fullFileName;
    }

    public static String getLocalizedFullFileName(String path, String fileName) {
        String fileNameSeparator = "\\".equals(File.separator)? "\\" + File.separator: File.separator;
        String fullFileName = path.replaceAll("/+|\\\\+", fileNameSeparator) + fileName;
        return fullFileName;
    }
}