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

    public String parseName(Object name) {
        Object result = JsonPath.read(name, "$..User");
        return result.toString().replace("[", "").replace("\"", "").replace("]", "");
    }

    public String getName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseName(object);
    }

    public ArrayList<String> parseRoommates(Object roommates){
        ArrayList<String> listOfRoommates = new ArrayList<>();
        int num = 0;
        while (true){
            String parsedResult = JsonPath.read(roommates, "$..Roommate" + num).toString();
            String newResult = parsedResult.replace("\"", "").replace("[", "").replace("]", "");
            if (newResult.contains("@")){
                listOfRoommates.add(newResult);
                num+=1;
            }
            else {
                break;
            }
        }
        return listOfRoommates;
    }

    public ArrayList<String> getRoommates(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseRoommates(object);

    }

    public String parsePassword(Object password) {
        Object result = JsonPath.read(password, "$..Password");
        return result.toString().replace("[", "").replace("\"", "").replace("]", "");
    }

    public String getPassword(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parsePassword(object);
    }

    public String parseEmail(Object email) {
        Object result = JsonPath.read(email, "$..Email");
        return result.toString().replace("[", "").replace("\"", "").replace("]", "");
    }

    public String getEmail(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEmail(object);
    }

    public String parsePhoneNumber(Object phoneNumber) {
        Object result = JsonPath.read(phoneNumber, "$..PhoneNumber");
        return result.toString().replace("[", "").replace("\"", "").replace("]", "");
    }

    public String getPhoneNumber(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parsePhoneNumber(object);
    }

    public ArrayList<ArrayList<String>> parseEvent(Object event){
        ArrayList<ArrayList<String>> eventInfo = new ArrayList<>();
        ArrayList<String> eventNames = JsonPath.read(event, "$..Event..EventName");
        int lengthOfEvents = eventNames.size();
        ArrayList<String> monday = new ArrayList<>();
        ArrayList<String> tuesday = new ArrayList<>();
        ArrayList<String> wednesday = new ArrayList<>();
        ArrayList<String> thursday = new ArrayList<>();
        ArrayList<String> friday = new ArrayList<>();
        for (int i = 0; i<lengthOfEvents; i++){
            for (int j = 0; j<7;j++) {
                ArrayList<String> daysResult = JsonPath.read(event, "$..Event..Days"+i+"["+j+"]");
                if (daysResult.contains("monday")) {
                    monday.add(eventNames.get(i));
                }
                if (daysResult.contains("tuesday")) {
                    tuesday.add(eventNames.get(i));
                }
                if (daysResult.contains("wednesday")) {
                    wednesday.add(eventNames.get(i));
                }
                if (daysResult.contains("thursday")) {
                    thursday.add(eventNames.get(i));
                }
                if (daysResult.contains("friday")) {
                    friday.add(eventNames.get(i));
                }
            }
        }
        eventInfo.add(monday);
        eventInfo.add(tuesday);
        eventInfo.add(wednesday);
        eventInfo.add(thursday);
        eventInfo.add(friday);
        return eventInfo;

    }
    public ArrayList<ArrayList<String>> getEvent(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEvent(object);
    }

    public ArrayList<String> parseClasses(Object classes) {
        JSONArray result = JsonPath.read(classes, "$..Name");
        int resultLength = result.size();
        ArrayList<String> listOfClasses = new ArrayList<>();

        int i = 0;
        while (i < resultLength) {
            listOfClasses.add(i, result.get(i).toString());
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
        while (i < resultLength) {
            listOfDays.add(i, result.get(i).toString());
            i++;
        }
        return listOfDays;
    }

    public ArrayList<String> getDays(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseDays(object);
    }

    public ArrayList<String> parseStartTimes(Object classes) {
        JSONArray result = JsonPath.read(classes, "$..Meetings..Start");
        int resultLength = result.size();
        ArrayList<String> listOfStartTimes = new ArrayList<>();

        int i = 0;
        while (i < resultLength) {
            listOfStartTimes.add(i, result.get(i).toString());
            i++;
        }
        return listOfStartTimes;
    }

    public ArrayList<String> getStartTimes(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseStartTimes(object);
    }

    public ArrayList<String> parseEndTimes(Object classes) {
        JSONArray result = JsonPath.read(classes, "$..Meetings..End");
        int resultLength = result.size();
        ArrayList<String> listOfEndTimes = new ArrayList<>();

        int i = 0;
        while (i < resultLength) {
            listOfEndTimes.add(i, result.get(i).toString());
            i++;
        }
        return listOfEndTimes;
    }

    public ArrayList<String> getEndTimes(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEndTimes(object);
    }

    public ArrayList<String> mondayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> monday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while (day < days.size()) {
            if (days.get(day).equals("F") || days.get(day).equals("R")) {
                classesCounter += 1;
            }
            if (days.get(day).equals("M")) {
                monday.add(classes.get(classesCounter));
            }
            day += 1;
        }
        return monday;

    }

    public ArrayList<String> tuesdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> tuesday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while (day < days.size()) {
            if (days.get(day).equals("F") || days.get(day).equals("R")) {
                classesCounter += 1;
            }
            if (days.get(day).equals("T")) {
                tuesday.add(classes.get(classesCounter));
            }
            day += 1;
        }
        return tuesday;
    }

    public ArrayList<String> wednesdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> wednesday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while (day < days.size()) {
            if (days.get(day).equals("F") || days.get(day).equals("R")) {
                classesCounter += 1;
            }
            if (days.get(day).equals("W")) {
                wednesday.add(classes.get(classesCounter));
            }
            day += 1;
        }
        return wednesday;
    }

    public ArrayList<String> thursdayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> thursday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while (day < days.size()) {
            if (days.get(day).equals("F")) {
                classesCounter += 1;
            }
            if (days.get(day).equals("R")) {
                thursday.add(classes.get(classesCounter));
                classesCounter += 1;
            }
            day += 1;
        }
        return thursday;
    }

    public ArrayList<String> fridayArray(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> classes = getClasses(email);
        ArrayList<String> days = getDays(email);
        ArrayList<String> friday = new ArrayList<>();

        int classesCounter = 0;
        int day = 0;
        while (day < days.size()) {
            if (days.get(day).equals("F")) {
                friday.add(classes.get(classesCounter));
                classesCounter += 1;
            }
            if (days.get(day).equals("R")) {
                classesCounter += 1;
            }
            day += 1;
        }
        return friday;
    }
}
