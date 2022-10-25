package edu.bsu.cs222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    @Override

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LoginGUI.fxml")));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root, 450, 700));
        primaryStage.show();
//        FlowPane rootNode = new FlowPane(10, 10);
//        rootNode.setAlignment(Pos.CENTER);
//        Scene myScene = new Scene(rootNode, 250, 450);
        };


    public static void main(String[] args) {
        launch(args);

    }

}
