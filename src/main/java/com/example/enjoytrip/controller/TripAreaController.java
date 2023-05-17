package com.example.enjoytrip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/trip")
public class TripAreaController {

    @GetMapping("/area")
    public String callArea(@RequestParam(value = "areaCode", required = false) String areaCode) {
        String jsonPrintString;

        try {
            if (areaCode == null || "".equals(areaCode)) areaCode = "";
            String serviceUrl = getServiceURL(areaCode);

            // data request
            jsonPrintString = getString(serviceUrl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonPrintString;
    }

    @GetMapping("/category")
    public String callCategory(@RequestParam(value = "cat1", required = false) String cat1, @RequestParam(value = "cat2", required = false) String cat2) {
        String jsonPrintString;

        try {
            if (cat1 == null || "".equals(cat1)) cat1 = "";
            if (cat2 == null || "".equals(cat2)) cat2 = "";
            String serviceUrl = getServiceURL(cat1, cat2);

            // data request
            jsonPrintString = getString(serviceUrl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonPrintString;
    }

    @GetMapping("/list")
    public String callList(@RequestParam(value = "areaCode", required = false) String areaCode, @RequestParam(value = "sigunguCode", required = false) String sigunguCode, @RequestParam(value = "cat1", required = false) String cat1, @RequestParam(value = "cat2", required = false) String cat2, @RequestParam(value = "cat3", required = false) String cat3) {
        String jsonPrintString;

        try {
            if (areaCode == null || "".equals(areaCode)) areaCode = "";
            if (sigunguCode == null || "".equals(sigunguCode)) sigunguCode = "";
            if (cat1 == null || "".equals(cat1)) cat1 = "";
            if (cat2 == null || "".equals(cat2)) cat2 = "";
            if (cat3 == null || "".equals(cat3)) cat3 = "";
            String serviceUrl = getServiceURL(areaCode, sigunguCode, cat1, cat2, cat3);

            // data request
            jsonPrintString = getString(serviceUrl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonPrintString;
    }

    private String getServiceURL(String areaCode) {
        String serviceId = "areaCode1";
        String serviceKey = "euAYup8VHKXrkrAglwBW1BsRkftXXCRhRyG2JTrFxIRQiwnOJ425JgNtNXNfvWuh0JHjfFNzr%2FZHPN4%2FDCjHXQ%3D%3D";
        String numOfRows = "10";
        String pageNo = "1";
        String mobileOS = "ETC";
        String mobileApp = "TripApp";
        String _type = "json";

        return "https://apis.data.go.kr/B551011/KorService1/" + serviceId + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows +
                "&pageNo=" + pageNo + "&MobileOS=" + mobileOS + "&MobileOS=" +
                mobileOS + "&MobileApp=" + mobileApp + "&_type=" + _type +
                "&areaCode=" + areaCode;
    }

    private String getServiceURL(String cat1, String cat2) {
        String serviceId = "categoryCode1";
        String serviceKey = "euAYup8VHKXrkrAglwBW1BsRkftXXCRhRyG2JTrFxIRQiwnOJ425JgNtNXNfvWuh0JHjfFNzr%2FZHPN4%2FDCjHXQ%3D%3D";
        String numOfRows = "10";
        String pageNo = "1";
        String mobileOS = "ETC";
        String mobileApp = "TripApp";
        String _type = "json";

        return "https://apis.data.go.kr/B551011/KorService1/" + serviceId + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows +
                "&pageNo=" + pageNo + "&MobileOS=" + mobileOS + "&MobileOS=" +
                mobileOS + "&MobileApp=" + mobileApp + "&_type=" + _type +
                "&cat1=" + cat1 + "&cat2=" + cat2;
    }

    private String getServiceURL(String areaCode, String sigunguCode, String cat1, String cat2, String cat3) {
        String serviceId = "areaBasedList1";
        String serviceKey = "euAYup8VHKXrkrAglwBW1BsRkftXXCRhRyG2JTrFxIRQiwnOJ425JgNtNXNfvWuh0JHjfFNzr%2FZHPN4%2FDCjHXQ%3D%3D";
        String numOfRows = "10";
        String pageNo = "1";
        String mobileOS = "ETC";
        String mobileApp = "TripApp";
        String _type = "json";

        return "https://apis.data.go.kr/B551011/KorService1/" + serviceId + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows +
                "&pageNo=" + pageNo + "&MobileOS=" + mobileOS + "&MobileOS=" +
                mobileOS + "&MobileApp=" + mobileApp + "&_type=" + _type + "&areaCode=" + areaCode + "&sigunguCode=" + sigunguCode +
                "&cat1=" + cat1 + "&cat2=" + cat2 + "&cat3=" + cat3;
    }

    private String getString(String serviceUrl) throws IOException {
        String jsonPrintString;
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

        jsonPrintString = String.valueOf(result);
        System.out.println(jsonPrintString);
        return jsonPrintString;
    }
}
