package com.spring.oauth2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

import com.spring.oauth2.model.UserResource;

public class UserInfoRequestService {
	
	UserResource ur = new UserResource();
	
	public String Name(String accessToken) {
		// 이름 가져오기
		try {
            // URL 객체 생성
            URL url = new URL("https://graph.facebook.com/me"
            		+ "?access_token=" + accessToken
            		+ "&fields=name");

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
                String ni[] = content.toString().split("\"");
                
                // 배열 길이로 정렬하기 위해 Comparator 구현(역순으로 정렬)
                Comparator<String> lengthComparator = new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.compare(s2.length(), s1.length());
                    }
                };
                
                // 배열 정렬
                Arrays.sort(ni, lengthComparator);
                
                // 이름 저장
                ur.SetName(ni[0]);
                
                in.close();
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
            
            // 연결 종료
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
		return ur.GetName();
    }
	
	public String Email(String accessToken) {
		
		// 이메일 가져오기
		try {
            // URL 객체 생성(URL1은 이름 URL2는 이메일)
            URL url = new URL("https://graph.facebook.com/me"
            		+ "?access_token=" + accessToken
            		+ "&fields=email");

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
                String ni[] = content.toString().split("\"");
                
                // 배열 길이로 정렬하기 위해 Comparator 구현(역순으로 정렬)
                Comparator<String> lengthComparator = new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.compare(s2.length(), s1.length());
                    }
                };
                
                // 배열 정렬
                Arrays.sort(ni, lengthComparator);
                
                // 이메일 저장
                ur.SetEmail(ni[0]);
                
                in.close();
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
            
            // 연결 종료
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
		return ur.GetEmail();
	}
}