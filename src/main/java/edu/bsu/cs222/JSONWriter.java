package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import net.minidev.json.reader.JsonWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONWriter {
    JSONObject user = new JSONObject();
    JSONObject eventInProgress = new JSONObject();
    JSONArray event = new JSONArray();
    JSONArray fullFile = new JSONArray();

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

    public void writeRoommate(String email){
        user.put("Roommate", email);
    }

    public void writeToFile(String email) throws IOException {
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        Files.createFile(path);
        Files.writeString(path, user.toJSONString());
    }

    public void writeEvent(String email, String name, ArrayList<String> days) throws IOException, URISyntaxException, ParseException {
        JSONReader reader = new JSONReader();
        JSONObject file = reader.fullFile(email);
        JSONObject object = new JSONObject();
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        eventInProgress.put("EventName", name);
        eventInProgress.put("Days", days);
        event.add(eventInProgress);
        object.put("Event", event);
        fullFile.add(file);
        fullFile.add(object);
        Files.writeString(path, fullFile.toJSONString());


    }


}