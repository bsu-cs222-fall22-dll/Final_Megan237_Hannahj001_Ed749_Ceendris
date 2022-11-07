package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainScreenGUI {
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public JSONReader jsonReader = new JSONReader();
    public Button profileButton;

    public String userEmail;

//    public void setEmail(String email){
//        userEmail = email;
//      }

    @FXML
    public void displayName(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        String name = jsonReader.getName(email);
        nameBox.setText(name);
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


    public void goToProfile(ActionEvent actionEvent) throws IOException, URISyntaxException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUserInfoGUI.fxml"));
        Parent root = loader.load();
        JSONReader jsonReader = loader.getController();
        jsonReader.getEmail(userEmail);
        jsonReader.getName(userEmail);
        jsonReader.getPhoneNumber(userEmail);
        jsonReader.getDays(userEmail);

        profileButton.getScene().setRoot(root);

    }
}
