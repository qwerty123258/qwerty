package controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
	
	public GoogleAuthentication() {
		passAuth = 
			new PasswordAuthentication("jangeanove3","embwgsioufadwmpt");
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}
