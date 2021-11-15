/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Zarin Tasninm
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application  {

    public static void main(String[] args) {

        launch(); //lauches the scene
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("App-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 633, 420);  //cfreates a scene for the app
        stage.setScene(scene);
        stage.show();
    }

}