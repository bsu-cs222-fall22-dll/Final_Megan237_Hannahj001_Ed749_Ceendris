package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginGUI {
    public TextField emailInput;
    public Button enterButton;



    @FXML
    public void getEmailFromInput(){
        String email = emailInput.getText();
    }

    @FXML
    public void loginUser(){

    }





}
