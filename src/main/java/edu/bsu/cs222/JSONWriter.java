package edu.bsu.cs222;

import net.minidev.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONWriter {
    JSONObject user = new JSONObject();

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

    public void writeToFile(String email) throws IOException {
        Path path = Paths.get("src/main/resources/" + email.replace(".","") + ".json");
        Files.createFile(path);
        Files.writeString(path, user.toJSONString());
    }
}