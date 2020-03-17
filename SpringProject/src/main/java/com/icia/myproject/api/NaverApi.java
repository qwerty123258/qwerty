package com.icia.myproject.api;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverApi extends DefaultApi20 {

	protected NaverApi(){
	}

	private static class InstanceHolder{
	    private static final NaverApi INSTANCE = new NaverApi();
	}


	public static NaverApi instance(){
	    return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
	    return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}                   

	@Override
	protected String getAuthorizationBaseUrl() {
	    return "https://nid.naver.com/oauth2.0/authorize";
	}   
}
