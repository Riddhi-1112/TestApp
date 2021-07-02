/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Riddhi
 */
public class CalculateTollCost {

    public static double CostOfQEWToHighway400() {
        double distance = 68.93;
        double cost = distance * .25;
        return cost;
    }

    public static double CalculateCost(String from, String to) throws IOException, FileNotFoundException, ParseException {
        double distance = calculatedistance(from, to);
        double cost = distance * .25;
        return cost;
    }

    public static double calculatedistance(String from, String to) throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject interchanges = (JSONObject) parser.parse(new FileReader("D:/Projects/TestApp/interchanges.json"));

        JSONObject location = (JSONObject) interchanges.get("locations");
        Map<String, String> map = new HashMap<String, String>();
        map = location;
        Set<String> keyset = map.keySet();
        Iterator<String> it = keyset.iterator();
        String keyvalfrom = null;
        String keyvalto = null;
        while (it.hasNext()) {
            String key = it.next();

            JSONObject values = (JSONObject) location.get(key);
            String name = (String) values.get("name");

            if (name.equals(from)) {
                keyvalfrom = key;
            }
            if (name.equals(to)) {
                keyvalto = key;
            }
        }
        int keyvalf = 0, keyvalt = 0;
        if (keyvalfrom != null) {
            keyvalf = Integer.parseInt(keyvalfrom);
        }
        if (keyvalfrom != null) {
            keyvalt = Integer.parseInt(keyvalto);
        }
        double distance = 0.0;
        if (keyvalt > keyvalf) {
            for (int i = keyvalf; i < keyvalt; i++) {
                if (i != 21 && i != 27) {

                    JSONObject values = (JSONObject) location.get(String.valueOf(i));
                   
                    JSONArray routes = (JSONArray) values.get("routes");
                    JSONObject routesobject = null;
                    if (!routes.isEmpty()) {
                        routesobject = (JSONObject) routes.get(0);
                    }
                    double distance1 = 0.0;
                    if (routesobject != null) {
                        if (!routesobject.get("distance").toString().equalsIgnoreCase("0")) {
                            distance1 = (double) routesobject.get("distance");
                        }
                        // double distance1 = Double.parseDouble(distancestr);

                        distance = distance + distance1;
                    }
                }

            }
        }
        if (keyvalf > keyvalt) {
            for (int i = keyvalf; i > keyvalt; i--) {
                if (i != 21 && i != 27) {
                    JSONObject values = (JSONObject) location.get(String.valueOf(i));
                    JSONArray routes = (JSONArray) values.get("routes");
                    JSONObject routesobject = null;
                    if (!routes.isEmpty()) {
                        routesobject = (JSONObject) routes.get(1);
                    }
                    double distance1 = 0.0;
                    if (routesobject != null) {
                        if (!routesobject.get("distance").toString().equalsIgnoreCase("0")) {
                            distance1 = (double) routesobject.get("distance");
                        }
                        distance = distance + distance1;

                        //System.out.println("routes" + routes);
                    }
                }

            }
        }
        return distance;
    }

    public static void main(String args[]) throws IOException, FileNotFoundException, ParseException, org.json.simple.parser.ParseException {
        double distanceofqewtohighway400 = calculatedistance("QEW", "Highway 400");
        double costofqewtohighway400 = CalculateCost("QEW", "Highway 400");
        double distanceoftest2 = calculatedistance("Salem Road", "QEW");
        double costoftest2 = CalculateCost("Salem Road", "QEW");
        double distanceoftest3 = calculatedistance("QEW", "Salem Road");
        double costoftest3 = CalculateCost("QEW", "Salem Road");
        
        System.out.println("distance:" + distanceofqewtohighway400 + "cost:" + costofqewtohighway400);

    }

}
