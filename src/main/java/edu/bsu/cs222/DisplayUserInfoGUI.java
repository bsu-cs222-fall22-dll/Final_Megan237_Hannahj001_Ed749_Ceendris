package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class DisplayUserInfoGUI {
    @FXML
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public VBox displaySchedule;
    public JSONReader jsonReader = new JSONReader();

    @FXML
    public void updateUserInformation(String name, String phoneNumber, String email) throws FileNotFoundException, URISyntaxException, ParseException {

        nameBox.setText(name);
        phoneNumberBox.setText(phoneNumber);
        emailBox.setText("no");
    }


    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(email);
    }

    @FXML
    public void displayEmail(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String Displayedemail = jsonReader.getEmail(email);
        emailBox.setText(Displayedemail);
    }

    @FXML
    public void displayPhoneNumber(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String phoneNumber = jsonReader.getPhoneNumber(email);
        phoneNumberBox.setText(phoneNumber);
    }

//    @FXML
//    public void setDisplaySchedule(String email){
//
//    }
}

