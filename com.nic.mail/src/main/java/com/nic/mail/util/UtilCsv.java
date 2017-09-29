package com.nic.mail.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nic.mail.common.Constants;
import com.nic.mail.common.Enums;

public class UtilCsv {

	public static List<String> loadCsvLineString(String resource) {
		if (resource == null || resource.length() <= 0) return null;
		List<String> list = new ArrayList<String>();
		InputStream is = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		try {
			is = UtilClassLoader.getResourceAsStream(resource);
			if (is != null) {
				reader = new InputStreamReader(is, UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.FileCSV.encode.getValue()));
		        if (reader != null) {
		        	br = new BufferedReader(reader);
		        	if (br != null) {
		        		String line;
		        		while ((line = br.readLine()) != null) {
		        			list.add(line);
		        		}
		        	}
		        }
			}
		} catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
            		br.close();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }
        	}
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
		return list;
	}
}