package com.spring.oauth2.model;

import org.apache.commons.lang3.StringEscapeUtils;

public class UserResource {
	String code;
	String accessToken;
	String name;
    String email;
    String id;
    
    public void SetCode(String code) {
    	this.code = code;
    }
    public void SetAccessToken(String accessToken) {
    	this.accessToken = accessToken;
    }
    public void SetName(String name) {
    	String a[] = name.split("\\\\");
    	String newName = "";
    	for (int i=0; i < a.length; i++) {
    		// name을 유니코드에서 변경
    		newName += StringEscapeUtils.unescapeJava("\\" + a[i]);
    	}
    	this.name = newName;
    }
    public void SetEmail(String email) {
    	// email을 유니코드에서 변경
    	String newEmail = email.replace("\\u0040", "@");
    	this.email = newEmail;
    }
 
    
    public String GetCode() {
    	return code;
    }
    public String GetAccessToken() {
    	return accessToken;
    }
    public String GetName() {
    	return name;
    }
    public String GetEmail() {
    	return email;
    }
}
