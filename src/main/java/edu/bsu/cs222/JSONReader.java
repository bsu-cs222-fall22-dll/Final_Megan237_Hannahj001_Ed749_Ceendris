package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import netscape.javascript.JSObject;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;


public class JSONReader {

    public String parseName(Object name){
        Object result = JsonPath.read(name, "$..User");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }

    public String parseEmail(Object email) {
        Object result = JsonPath.read(email, "$..Email");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }

    public String parsePhoneNumber(Object phoneNumber) {
        Object result = JsonPath.read(phoneNumber, "$..PhoneNumber");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }

    public ArrayList<String> parseClasses(Object classes) {
        JSONArray result = JsonPath.read(classes, "$..Name");
        int resultLength = result.size();
        ArrayList<String> listOfClasses = new ArrayList<>();

        int i = 0;
        while (i < resultLength){
            listOfClasses.add(i,result.get(i).toString());
            i++;
        }
        return listOfClasses;
    }
}
