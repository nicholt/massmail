package com.nic.mail.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.nic.mail.common.Enums;
import com.nic.mail.util.UtilClassLoader;

public class UtilProperties {

	public static String getPropertyValue(String resource, String name) throws Exception {
		if (resource == null || resource.length() <= 0) return "";
        if (name == null || name.length() <= 0) return "";
        Properties env = getProperties(resource);
        if (env == null) return "";
        String value = null;
        try {
        	value = env.getProperty(name);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return value == null ? "" : value.trim();
	}

	public static Properties getProperties(String resource) throws Exception {
		if (resource == null || resource.length() <= 0) return null;
		Properties properties = new Properties();
		InputStream is = null;
		InputStreamReader reader = null;
		try {
			is = UtilClassLoader.getResourceAsStream(resource);
			if (is != null) {
				reader = new InputStreamReader(is, Enums.Encode.UTF8.getValue());
		        properties.load(reader);
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if (reader != null) {
        		try {
        			reader.close();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }
        	}
        	if (is != null) {
        		try {
        			is.close();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }
        	}
        }
        return properties;
    }
}