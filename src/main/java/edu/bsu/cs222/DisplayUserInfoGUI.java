package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class DisplayUserInfoGUI {
    @FXML
    public Label nameBox;
    public Label phoneNumberBox;
    public Label emailBox;


    @FXML
    public void updateUserInformation(String name, String phoneNumber, String email){

        nameBox.setText(name);
        phoneNumberBox.setText(phoneNumber);
        emailBox.setText(email);
    }









}

