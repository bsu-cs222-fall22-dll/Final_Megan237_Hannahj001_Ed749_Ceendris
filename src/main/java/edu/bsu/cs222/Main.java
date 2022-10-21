package edu.bsu.cs222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DisplayUserInfoGUI.fxml")));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root,450,700));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

}
