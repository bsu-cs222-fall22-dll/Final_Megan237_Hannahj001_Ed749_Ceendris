package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
        roommate.put("Roommate", roommateEmail);
        fullFile.add(roommate);
        Files.writeString(path, fullFile.toJSONString());

    }

    public void writeToFile(String email) throws IOException {
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        Files.createFile(path);
        Files.writeString(path, user.toJSONString());
    }

    public void writeToFileImage(String name,File filePath) throws IOException {
        Path path = Paths.get("src/main/resources/" + name);
        BufferedImage bImage;
        try{
            File initialImage = new File(String.valueOf(filePath));
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "gif", new File(path.toString()));
            ImageIO.write(bImage, "jpg", new File(path.toString()));
            ImageIO.write(bImage, "bmp", new File(path.toString()));

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
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

    public void removeRoommate(String email, String roommateEmail) throws IOException{
        String file = "src/main/resources/" + email.replace(".","") + ".json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        Path path = Paths.get(file);
        Files.writeString(path, json.replace(roommateEmail, ""));
    }

    public void writeImage(String email, String fileName) throws IOException, URISyntaxException, ParseException {
        String filePath = "src/main/resources/" + fileName;
        Object originalFile = reader.getJsonObject(email);
        JSONObject image = new JSONObject();
        JSONArray fullFile = new JSONArray();
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        fullFile.add(originalFile);
        image.put("ProfileImage", filePath);
        fullFile.add(image);
        Files.writeString(path, fullFile.toJSONString());

    }
}