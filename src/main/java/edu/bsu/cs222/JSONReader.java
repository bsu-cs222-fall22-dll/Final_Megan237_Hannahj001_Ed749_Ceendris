package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class JSONReader {

    public Object getJsonObject(String email) throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        URL resource = getClass().getClassLoader().getResource(email.replace(".", "") + ".json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        return jsonParser.parse(reader);
    }
    public String parseName(Object name){
        Object result = JsonPath.read(name, "$..User");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }
    public String getName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseName(object);
    }

    public String parsePassword(Object password){
        Object result = JsonPath.read(password, "$..Password");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }
    public String getPassword(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parsePassword(object);
    }

    public String parseEmail(Object email) {
        Object result = JsonPath.read(email, "$..Email");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }

    public String getEmail(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEmail(object);
    }
    public String parsePhoneNumber(Object phoneNumber) {
        Object result = JsonPath.read(phoneNumber, "$..PhoneNumber");
        String finalResult = result.toString().replace("[", "");
        String finalR = finalResult.replace("\"", "");

        return finalR.replace("]", "");
    }
    public String getPhoneNumber(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parsePhoneNumber(object);
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
    public ArrayList<String> getClasses(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseClasses(object);
    }

    public ArrayList<String> parseDays(Object classes) {
        JSONArray result = JsonPath.read(classes, "$..Meetings..Day");
        int resultLength = result.size();
        ArrayList<String> listOfDays = new ArrayList<>();

        int i = 0;
        while (i < resultLength){
            listOfDays.add(i,result.get(i).toString());
            i++;
        }
        return listOfDays;
    }

    public ArrayList<String> getDays(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseDays(object);
    }

    public ArrayList<String> mondayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> monday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while(day < days.size()){
            if (days.get(day).equals("F") || days.get(day).equals("R")){
                classesCounter+=1;
            }
            if (days.get(day).equals("M")){
                monday.add(classes.get(classesCounter));
            }
            day+=1;
        }
        return monday;

    }

    public ArrayList<String> tuesdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> tuesday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while(day < days.size()){
            if (days.get(day).equals("F") || days.get(day).equals("R")){
                classesCounter+=1;
            }
            if (days.get(day).equals("T")){
                tuesday.add(classes.get(classesCounter));
            }
            day+=1;
        }
        return tuesday;
    }

    public ArrayList<String> wednesdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> wednesday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while(day < days.size()){
            if (days.get(day).equals("F") || days.get(day).equals("R")){
                classesCounter+=1;
            }
            if (days.get(day).equals("W")){
                wednesday.add(classes.get(classesCounter));
            }
            day+=1;
        }
        return wednesday;
    }

    public ArrayList<String> thursdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> thursday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while(day < days.size()){
            if (days.get(day).equals("F")){
                classesCounter+=1;
            }
            if (days.get(day).equals("R")){
                thursday.add(classes.get(classesCounter));
                classesCounter+=1;
            }
            day+=1;
        }
        return thursday;
    }

    public ArrayList<String> fridayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> friday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while(day < days.size()){
            if (days.get(day).equals("F")){
                friday.add(classes.get(classesCounter));
                classesCounter+=1;
            }
            if (days.get(day).equals("R")){
                classesCounter+=1;
            }
            day+=1;
        }
        return friday;
    }
}
