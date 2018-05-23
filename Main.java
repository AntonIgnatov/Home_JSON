package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public class Curensy {
        private HashMap<String, Double> rates;

        public Curensy(HashMap<String, Double> rates) {
            this.rates = rates;
        }

        private HashMap<String, Double> getRates() {
            return rates;
        }

        public void setRates(HashMap<String, Double> rates) {
            this.rates = rates;
        }
    }

    public static void main(String[] args) {
        String request = "http://data.fixer.io/api/latest?access_key=2f09b70e4b7f77b4cf0901246f459e96&format=1";
        String result = null;
        try {
            result = performRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().create();
//        Type type = new TypeToken<Map<String, Double>>() {}.getType();
       Curensy cur = gson.fromJson(result, Curensy.class);
       double curUAH = cur.getRates().get("UAH");
       String curConvert = currensykChec(cur);
       double selectedCur = cur.getRates().get(curConvert);
       double div = curUAH/selectedCur;
//       System.out.println("UAH/"+curConvert+" - "+div);
        System.out.format("UAH/%s - %.2f грн", curConvert, div);


    }

    private static String performRequest(String urlAddress) throws IOException {
        String result ="";
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String temp = "";
                for (; (temp = br.readLine()) != null;) {
                    result+=temp;
                    result+=System.lineSeparator();
                    }
            }
        } catch (IOException e) {
            throw e;
        }
        return result;
    }

    private static String currensyGetType() {
        System.out.println("Введите валюту для конвертации:");
        Scanner sc = new Scanner(System.in);
        String type = sc.next();

        return type;
    }

    private static String currensykChec(Curensy o){
        boolean f = false;
        String temp = "";
        while (!f){
            temp = currensyGetType().toUpperCase();
            f = o.getRates().containsKey(temp);
        }
        return temp;
    }


}
