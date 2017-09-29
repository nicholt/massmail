package com.nic.mail.util;

import java.io.InputStream;

import com.nic.mail.common.Constants;

public class UtilClassLoader {

	public static InputStream getResourceAsStream(String resource) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			return loader.getResourceAsStream(Constants.Resource_Home+resource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}