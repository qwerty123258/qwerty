package controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class NaverAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
	
	public NaverAuthentication() {
		passAuth = 
			new PasswordAuthentication("qwerty123258","628D6MSGK4GH");
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}
