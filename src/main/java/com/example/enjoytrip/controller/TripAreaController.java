package com.example.enjoytrip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class TripAreaController {

    private String getServiceURL(String areaCode) {
        String serviceKey = "euAYup8VHKXrkrAglwBW1BsRkftXXCRhRyG2JTrFxIRQiwnOJ425JgNtNXNfvWuh0JHjfFNzr%2FZHPN4%2FDCjHXQ%3D%3D";
        String serviceId = "areaCode1";
        String mobileOS = "ETC";
        String mobileApp = "TripApp";
        String _type = "json";
        String numOfRows = "10";
        String pageNo = "1";

        return "https://apis.data.go.kr/B551011/KorService1/" + serviceId + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows +
                "&pageNo=" + pageNo + "&MobileOS=" + mobileOS + "&MobileOS=" +
                mobileOS + "&MobileApp=" + mobileApp + "&_type=" + _type +
                "&areaCode=" + areaCode;
    }


    @GetMapping("/apitest")
    public String callApiWithJson(@RequestParam String areaCode) {
        String jsonPrintString;

        try {
            if (areaCode == null || "".equals(areaCode)) areaCode = "";
            String serviceUrl = getServiceURL(areaCode);

            // data request
            URL url = new URL(serviceUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            BufferedReader br;
            if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();
            urlConnection.disconnect();

            System.out.println(result);

            jsonPrintString = String.valueOf(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonPrintString;
    }
}
