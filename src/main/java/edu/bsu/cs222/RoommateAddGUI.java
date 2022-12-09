package edu.bsu.cs222;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class RoommateAddGUI {
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;
    public TextField roommatesEmailBox;
    public Button addRoommateButton;
    public Button goBackButton;
    public JSONReader jsonReader = new JSONReader();
    public JSONWriter writer = new JSONWriter();
    public ChoiceBox<String> roommateRemoveTabBox;
    public Button removeButton;


    @FXML
    public void addRoommate() throws IOException, URISyntaxException, ParseException {
        String roommateEmail = roommatesEmailBox.getText();
        String emailJSON = jsonReader.getEmail(roommateEmail);
        if (roommateEmail.equals(emailJSON) & !roommateEmail.equals(emailBox.getText())) {
            writer.writeRoommate(emailBox.getText(), roommateEmail);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateAddGUI.fxml"));
            Parent root = loader.load();
            RoommateAddGUI roommateAddGUI = loader.getController();
            roommateAddGUI.displayEmail(emailBox.getText());
            roommateAddGUI.displayName(emailBox.getText());
            roommateAddGUI.displayPhoneNumber(emailBox.getText());
            addRoommateButton.getScene().setRoot(root);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conformation");
            alert.setHeaderText("Roommate Added");
            alert.setContentText("You have successfully added a new roommate");
            alert.showAndWait();

        }else if (roommateEmail.equals(emailBox.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Roommate Add Error");
            alert.setContentText("You cannot add yourself a roommate.");
            alert.showAndWait();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("That user does not exist.");
            alert.setContentText("Please enter a different email.");
            alert.showAndWait();
        }

    }

    public void goBackToSettingsPage() throws IOException, URISyntaxException, ParseException {
        String email = emailBox.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsGUI.fxml"));
        Parent root = loader.load();
        SettingsGUI settingsGUI = loader.getController();
        settingsGUI.displayEmail(email);
        settingsGUI.displayName(email);
        settingsGUI.displayPhoneNumber(email);
        goBackButton.getScene().setRoot(root);
    }

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
    @FXML
    public void displayRemoveRoommates(String email) throws FileNotFoundException, URISyntaxException, ParseException {
        ArrayList<String> roommatesEmails = jsonReader.getRoommates(email);
        ObservableList<String> roommatesList = FXCollections.observableArrayList("Select Roommate");
        roommatesList.setAll(roommatesEmails);
        roommateRemoveTabBox.setItems(roommatesList);

    }
    @FXML
    public void removeRoommateButton() throws IOException, URISyntaxException, ParseException {
        String selection = roommateRemoveTabBox.getValue();
        if (selection == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("A Selection Error Occurred");
            alert.setContentText("You need to select a roommate to remove it.");
            alert.showAndWait();
        }else{
            //NEEDS TO REMOVE EMAIL OF ROOMMATE FROM JSON\
            writer.removeRoommate(emailBox.getText(), selection);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoommateAddGUI.fxml"));
            Parent root = loader.load();
            RoommateAddGUI roommateAddGUI = loader.getController();
            roommateAddGUI.displayEmail(emailBox.getText());
            roommateAddGUI.displayName(emailBox.getText());
            roommateAddGUI.displayPhoneNumber(emailBox.getText());
            removeButton.getScene().setRoot(root);
        }
    }
}