package com.icia.airandroom.util;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.icia.airandroom.dto.AirlinebkDTO;
import com.icia.airandroom.dto.RoombkDTO;

import lombok.extern.java.Log;

@Log
@Service
public class KakaoPay {
	 
    private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    
    public String kakaoPayReady(RoombkDTO roombk, String roomid) {
 
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "b4c169226849224d9256e46e22af7531");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", roomid);
        params.add("item_name", roombk.getRname());
        params.add("quantity", "1");
        params.add("total_amount", roombk.getPrice());
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://icia.kro.kr:8090/kakaoPaySuccess?roomid="+roomid +"&rname="+roombk.getRname()+"&price="+roombk.getPrice()+"&checkindate="+roombk.getCheckindate()+"&checkoutdate="+roombk.getCheckoutdate()+"&rno=" + roombk.getRno());
        params.add("cancel_url", "http://icia.kro.kr:8090/kakaoPayCancel");
        params.add("fail_url", "http://icia.kro.kr:8090/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
        
    }
    
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token,String roomid) {
    	 
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "b4c169226849224d9256e46e22af7531");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", roomid);
        params.add("pg_token", pg_token);
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);
          
            return kakaoPayApprovalVO;
        
        }catch(HttpClientErrorException e) {
        	e.printStackTrace();
        	System.out.println(e.getResponseBodyAsString());
        }
        
        catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

	public String kakaoPayReady(AirlinebkDTO airlinebk, String airlineid) {
        RestTemplate restTemplate = new RestTemplate();
        
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "b4c169226849224d9256e46e22af7531");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", airlineid);
        params.add("item_name", airlinebk.getAname());
        params.add("quantity", "1");
        params.add("total_amount", airlinebk.getPrice());
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://icia.kro.kr:8090/AirlineKakaoPaySuccess?airlineid="+airlineid + "&ano=" + airlinebk.getAno() 
        + "&aname=" + airlinebk.getAname()+ "&price=" + airlinebk.getPrice()+ "&startdate=" + airlinebk.getStartdate()+ "&sctime=" + airlinebk.getSctime() +"&startpoint="+airlinebk.getStartpoint() + "&endpoint="+airlinebk.getEndpoint() );
        params.add("cancel_url", "http://icia.kro.kr:8090/kakaoPayCancel");
        params.add("fail_url", "http://icia.kro.kr:8090/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        }
        catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
	}
    
}
