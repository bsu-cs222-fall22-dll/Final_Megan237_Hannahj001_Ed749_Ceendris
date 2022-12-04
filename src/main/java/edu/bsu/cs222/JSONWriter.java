package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONWriter {
    JSONObject user = new JSONObject();
    JSONReader reader = new JSONReader();

    public void writeEmail(String email) {
        user.put("Email", email);
    }

    public void writeName(String name) {
        user.put("User", name);
    }

    public void writePhoneNumber(String phoneNumber) {
        user.put("PhoneNumber", phoneNumber);
    }

    public void writePassword(String password) {
        user.put("Password", password);
    }

    public void writeRoommate(String userEmail, String roommateEmail) throws IOException, URISyntaxException, ParseException {
        Object originalFile = reader.getJsonObject(userEmail);
        JSONObject roommate = new JSONObject();
        JSONArray fullFile = new JSONArray();
        Path path = Paths.get("src/main/resources/" + userEmail.replace(".","") + ".json");
        fullFile.add(originalFile);
        int i = 0;

        while (true){
            String checkRoommate = "Roommate" + i;
            if (originalFile.toString().contains(checkRoommate)){
                i+=1;
            }
            else {
                break;
            }
        }
        String key = "Roommate"+i;
        roommate.put(key, roommateEmail);
        fullFile.add(roommate);
        Files.writeString(path, fullFile.toJSONString());

    }

    public void writeToFile(String email) throws IOException {
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        Files.createFile(path);
        Files.writeString(path, user.toJSONString());
    }

    public void writeEvent(String email, String name, ArrayList<String> days, String startTime, String endTime) throws IOException, URISyntaxException, ParseException {
        Object originalFile = reader.getJsonObject(email);
        JSONObject eventInProgress = new JSONObject();
        JSONArray event = new JSONArray();
        JSONArray fullFile = new JSONArray();
        JSONObject object = new JSONObject();
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        eventInProgress.put("EventName", name);
        eventInProgress.put("StartTime", startTime);
        eventInProgress.put("EndTime", endTime);
        int i = 0;
        while (true){
            if (originalFile.toString().contains("Days"+i)){
                i++;
            }
            else {
                break;
            }
        }
        eventInProgress.put("Days"+i, days);

        event.add(eventInProgress);
        fullFile.add(originalFile);
        object.put("Event", event);
        fullFile.add(object);
        Files.writeString(path, fullFile.toJSONString());
    }
}