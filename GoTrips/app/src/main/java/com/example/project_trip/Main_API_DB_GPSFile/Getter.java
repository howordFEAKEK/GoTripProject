package com.example.project_trip.Main_API_DB_GPSFile;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
public class Getter {

    String apiGetter(String sido, String gungu) throws IOException {
        //도시의 이름과 군구를 입력 받고 그에 해당하는 관광지를 전송함
        StringBuilder urlBuilder = new StringBuilder("http://openapi.tour.go.kr/openapi/service/TourismResourceService/getTourResourceList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HljdApr9dt8GOT0E0H87Ii2Ah7buUCv6SHh5AmjZJLwWZvRO9Ww4hgPgXehtDS3Ytn3hxKWk9J6sVS4MHdURJA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("SIDO","UTF-8") + "=" + URLEncoder.encode(sido, "UTF-8")); /*시도*/
        urlBuilder.append("&" + URLEncoder.encode("GUNGU","UTF-8") + "=" + URLEncoder.encode(gungu, "UTF-8")); /*시군구*/
        urlBuilder.append("&" + URLEncoder.encode("RES_NM","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관광지*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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


        return sb.toString();
    }

    String apiGetterName(String sido, String gungu, String resnm) throws IOException {
        //시도, 군구, 관광지 이름을 입력받고, 해당되는 관광지 관련 정보를 출력함

        StringBuilder urlBuilder = new StringBuilder("http://openapi.tour.go.kr/openapi/service/TourismResourceService/getTourResourceList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HljdApr9dt8GOT0E0H87Ii2Ah7buUCv6SHh5AmjZJLwWZvRO9Ww4hgPgXehtDS3Ytn3hxKWk9J6sVS4MHdURJA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("SIDO","UTF-8") + "=" + URLEncoder.encode(sido, "UTF-8")); /*시도*/
        urlBuilder.append("&" + URLEncoder.encode("GUNGU","UTF-8") + "=" + URLEncoder.encode(gungu, "UTF-8")); /*시군구*/
        urlBuilder.append("&" + URLEncoder.encode("RES_NM","UTF-8") + "=" + URLEncoder.encode(resnm, "UTF-8")); /*관광지*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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


        return sb.toString();
    }

}
