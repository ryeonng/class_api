package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExplorer {

	public static void main(String[] args) throws IOException {

		// 순수 자바코드로 클라이언트 측 코딩
		// 준비물
		// 1. 서버 측 주소 (경로)
		// http://localhost:8080/test?name=홍길동&age=20
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20 ← url 인코딩으로 바뀐 형태
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
		// StringBuilder : 단일스레드에 안정적 , StringBuffer : 멀티스레드에 안정적
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=xa7Rh99jatgJXq7TYnfnsO7w7TDON7lXtmpBwSq%2BuxLimdvWK%2BDMUhM36PfZg4vUesojmbG0ipL%2FA1EFybNV1g%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("202", "UTF-8")); /*측정 연도*/
        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
	
        // URL 객체에 문자열 경로를 넣어서 객체 생성
        // url.openConnection() 데이터 요청 보내기 (설정하고)
	
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        conn.setRequestMethod("GET"); // 서버에게 자원 요청
        conn.setRequestProperty("Content-type", "application/json");
        // 200, 실패 404, 405
        System.out.println("Response code: " + conn.getResponseCode());
        
        // 100 ~ 500 의미 (약속)
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    

} // end of main

} // end of class
