package com.nic.mail.common;

public class Enums {

    public static enum Encode{
    	UTF8;
        public String getValue(){
        	switch (this.ordinal()) {
	            case 0: return "UTF-8";
        	}
        	return name();
        }
    }

    public static enum ContentType{
    	UTF8;
        public String getValue(){
        	switch (this.ordinal()) {
	            case 0: return "text/html;charset=UTF-8";
        	}
        	return name();
        }
    }

    public static enum Mail{
    	host,port,from,username,password,auth,attachfd,attachfdsent;
        public String getValue(){
        	switch (this.ordinal()) {
	            case 0: return "mail.smtp.host";
	            case 1: return "mail.smtp.port";
	            case 2: return "mail.from";
	            case 3: return "mail.username";
	            case 4: return "mail.password";
	            case 5: return "mail.auth";
	            case 6: return "mail.attach.folder";
	            case 7: return "mail.attach.sent.folder";
        	}
        	return name();
        }
    }

    public static enum FileCSV{
    	name,encode;
        public String getValue(){
        	switch (this.ordinal()) {
	            case 0: return "file.csv.name";
	            case 1: return "file.csv.encode";
        	}
        	return name();
        }
    }
}