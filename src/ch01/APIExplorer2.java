package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExplorer2 {

	public static void main(String[] args) throws IOException {

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/6310000/busstoplocation/getbusstoplocationList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=xa7Rh99jatgJXq7TYnfnsO7w7TDON7lXtmpBwSq%2BuxLimdvWK%2BDMUhM36PfZg4vUesojmbG0ipL%2FA1EFybNV1g%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 페이지 크기(기본20) */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 시작 페이지(기본1) */
		urlBuilder
				.append("&" + URLEncoder.encode("extent", "UTF-8") + "=" + URLEncoder.encode("경주시", "UTF-8")); /* 권역 */

		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
