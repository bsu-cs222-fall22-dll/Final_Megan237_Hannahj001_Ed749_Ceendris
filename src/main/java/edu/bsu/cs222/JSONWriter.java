package edu.bsu.cs222;

import net.minidev.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
    JSONObject user = new JSONObject();
    String email;
    String name;
    String phoneNumber;
    String password;

    public void writeEmail() {
        user.put("Email", email);
    }

    public void writeName() {
        user.put("User", name);
    }

    public void writePhoneNumber() {
        user.put("PhoneNumber", phoneNumber);
    }

    public void writePassword() {
        user.put("Password", password);
    }

    public void writeToFile() throws IOException {


    FileWriter fileWriter = new FileWriter(email + ".json");
    fileWriter.write(user.toJSONString());
    fileWriter.flush();
    }
}
