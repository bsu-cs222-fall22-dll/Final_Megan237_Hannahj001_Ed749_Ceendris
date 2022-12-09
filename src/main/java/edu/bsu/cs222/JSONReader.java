package edu.bsu.cs222;

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
        ArrayList<String> listOfRoommates = JsonPath.read(roommates, "$..Roommate");
        ArrayList<String> finalListOfRoommates = new ArrayList<>();
        for (String roommate : listOfRoommates){
            if (roommate.contains("@")){
                finalListOfRoommates.add(roommate);
            }
        }
        return finalListOfRoommates;
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
        ArrayList<String> startTimes = JsonPath.read(event, "$..Event..StartTime");
        ArrayList<String> endTime = JsonPath.read(event, "$..Event..EndTime");
        int lengthOfEvents = eventNames.size();
        ArrayList<String> monday = new ArrayList<>();
        ArrayList<String> mondayStartAndEndTimes = new ArrayList<>();
        ArrayList<String> tuesday = new ArrayList<>();
        ArrayList<String> tuesdayStartAndEndTimes = new ArrayList<>();
        ArrayList<String> wednesday = new ArrayList<>();
        ArrayList<String> wednesdayStartAndEndTimes = new ArrayList<>();
        ArrayList<String> thursday = new ArrayList<>();
        ArrayList<String> thursdayStartAndEndTimes = new ArrayList<>();
        ArrayList<String> friday = new ArrayList<>();
        ArrayList<String> fridayStartAndEndTimes = new ArrayList<>();
        for (int i = 0; i<lengthOfEvents; i++){
            for (int j = 0; j<7;j++) {
                ArrayList<String> daysResult = JsonPath.read(event, "$..Event..Days"+i+"["+j+"]");
                if (daysResult.contains("monday")) {
                    monday.add(eventNames.get(i));
                    mondayStartAndEndTimes.add(startTimes.get(i));
                    mondayStartAndEndTimes.add(endTime.get(i));
                }
                if (daysResult.contains("tuesday")) {
                    tuesday.add(eventNames.get(i));
                    tuesdayStartAndEndTimes.add(startTimes.get(i));
                    tuesdayStartAndEndTimes.add(endTime.get(i));
                }
                if (daysResult.contains("wednesday")) {
                    wednesday.add(eventNames.get(i));
                    wednesdayStartAndEndTimes.add(startTimes.get(i));
                    wednesdayStartAndEndTimes.add(endTime.get(i));
                }
                if (daysResult.contains("thursday")) {
                    thursday.add(eventNames.get(i));
                    thursdayStartAndEndTimes.add(startTimes.get(i));
                    thursdayStartAndEndTimes.add(endTime.get(i));
                }
                if (daysResult.contains("friday")) {
                    friday.add(eventNames.get(i));
                    fridayStartAndEndTimes.add(startTimes.get(i));
                    fridayStartAndEndTimes.add(endTime.get(i));
                }
            }
        }
        eventInfo.add(monday);
        eventInfo.add(tuesday);
        eventInfo.add(wednesday);
        eventInfo.add(thursday);
        eventInfo.add(friday);
        eventInfo.add(mondayStartAndEndTimes);
        eventInfo.add(tuesdayStartAndEndTimes);
        eventInfo.add(wednesdayStartAndEndTimes);
        eventInfo.add(thursdayStartAndEndTimes);
        eventInfo.add(fridayStartAndEndTimes);
        return eventInfo;

    }
    public ArrayList<ArrayList<String>> getEvent(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEvent(object);
    }

    public ArrayList<String> parseStartTime(Object startTime){
        return JsonPath.read(startTime, "$..Event..StartTime");
    }

    public ArrayList<String> getStartTime(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseStartTime(object);
    }

    public ArrayList<String> parseEndTime(Object endTime){
        return JsonPath.read(endTime, "$..Event..EndTime");
    }

    public ArrayList<String> getEndTime(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseEndTime(object);
    }

    public ArrayList<String> eventNames(Object event){
        return JsonPath.read(event, "$..Event..EventName");
    }

    public ArrayList<String> getEventNames(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return eventNames(object);
    }
    public ArrayList<String> parseDaysBasedOnEventName(Object object, String eventName, String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> eventNameArray = getEventNames(email);
        int indexNumber = eventNameArray.indexOf(eventName);
        return JsonPath.read(object, "$..Event..Days"+indexNumber+"[*]");
    }

    public ArrayList<String> getDaysBasesOnEventName(String email, String eventName) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        return parseDaysBasedOnEventName(object, eventName, email);
    }

    public ArrayList<String> parseImagePath(Object image){
        return JsonPath.read(image, "$..ProfileImage");
    }

    public String getImagePath(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        Object object = getJsonObject(email);
        int size = parseImagePath(object).size();
        if (size != 0){
            return parseImagePath(object).get(size-1);
        }
        else {
            return "src/main/resources/REG-378623.png";
        }
    }

}