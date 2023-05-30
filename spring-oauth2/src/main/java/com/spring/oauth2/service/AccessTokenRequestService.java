package com.spring.oauth2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

import com.spring.oauth2.model.UserResource;

public class AccessTokenRequestService {
	
	UserResource ur = new UserResource();
	String accessToken;
	
	public String AccessToken(String code) {
        try {
            // URL 객체 생성
            URL url = new URL("https://graph.facebook.com/v17.0/oauth/access_token"
            		+ "?client_id=828409408988555"
            		+ "&client_secret=8671694634fe7e625a0590a101c0c88c"
            		+ "&redirect_uri=http://localhost:8080/user/facebook"
            		+ "&code=" + code);

            // HttpURLConnection을 사용하여 연결 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 연결을 수행하고 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답을 읽어오기 위해 BufferedReader 사용
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }                
                String at[] = content.toString().split("\"");
                
                // 배열 길이로 정렬하기 위해 Comparator 구현(역순으로 정렬)
                Comparator<String> lengthComparator = new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.compare(s2.length(), s1.length());
                    }
                };
                
                // 배열 정렬
                Arrays.sort(at, lengthComparator);
                
                // 엑세스 토큰 저장
                ur.SetAccessToken(at[0]);
                
                in.close();
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
            
            // 연결 종료
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ur.GetAccessToken();
    }
}
