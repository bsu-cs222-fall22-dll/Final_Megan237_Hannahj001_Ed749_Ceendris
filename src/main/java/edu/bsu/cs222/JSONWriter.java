package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
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

    public void writeRoommate(String email){
        user.put("Roommate", email);
    }

    public void writeToFile(String email) throws IOException {
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        Files.createFile(path);
        Files.writeString(path, user.toJSONString());
    }

    public void writeEvent(String email, String name, ArrayList<String> days) throws IOException, URISyntaxException, ParseException {
        Object testFile = reader.getJsonObject(email);
        JSONObject eventInProgress = new JSONObject();
        JSONArray event = new JSONArray();
        JSONArray fullFile = new JSONArray();
        JSONObject object = new JSONObject();
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        eventInProgress.put("Days", days);
        eventInProgress.put("EventName", name);
        event.add(eventInProgress);
        fullFile.add(testFile);
        int i = 0;

        while (true){
            String checkEvent = "Event" + i;
            if (testFile.toString().contains(checkEvent)){
                i+=1;
            }
            else {
                break;
            }
        }
        String key = "Event"+i;
        object.put(key, event);
        fullFile.add(object);
        Files.writeString(path, fullFile.toJSONString());
//        file.clear();
//        eventInProgress.clear();
//        event.clear();
//        fullFile.clear();
//        object.clear();


    }


}