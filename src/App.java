import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;

import java.awt.*;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Stage stage;
    TextField textName;
    PasswordField textPassword;
    CheckBox checkEmployee;
    CheckBox checkAdmin;
    Button loginButton;
    Button clearButton;


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        //loginScene
        Text textHeading = new Text("Влезте в системата като служител или администратор.");
        textHeading.setFont(new Font("Arial", 25));
        HBox paneTop = new HBox(textHeading);
        paneTop.setAlignment(Pos.CENTER);
        paneTop.setPadding(new Insets(20, 10, 20, 10));

        checkEmployee = new CheckBox("Служител");
        checkAdmin = new CheckBox("Администратор");
        HBox paneChecks = new HBox(10, checkEmployee, checkAdmin);
        paneChecks.setAlignment(Pos.CENTER);

        Label labelName = new Label("Име");
        labelName.setPrefWidth(100);
        textName = new TextField();
        textName.setPrefColumnCount(20);
        textName.setPromptText("Въведете вашето име.");
        HBox paneName = new HBox(-50,labelName, textName);
        paneName.setAlignment(Pos.CENTER);

        Label labelPassword = new Label("Парола");
        labelPassword.setPrefWidth(100);
        textPassword = new PasswordField();
        textPassword.setPrefColumnCount(20);
        textPassword.setPromptText("Въведете вашата парола.");
        HBox panePassword = new HBox(-50,labelPassword, textPassword);
        panePassword.setAlignment(Pos.CENTER);
        VBox paneCenter = new VBox(20, paneChecks, paneName, panePassword);
        paneCenter.setAlignment(Pos.CENTER);

        loginButton = new Button("ОК");
        loginButton.setMinWidth(80);
        loginButton.setOnAction(e -> loginButtonOnClick() );
        clearButton = new Button("Изчисти");
        loginButton.setMinWidth(80);
        loginButton.setOnAction(e -> clearButtonOnClick() );
        HBox paneBottom = new HBox(20, loginButton, clearButton);
        paneBottom.setAlignment(Pos.CENTER);

        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        paneMain.setPadding(new Insets(40, 10, 90, 10));

        Scene scene = new Scene(paneMain);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Система за следене на работното време");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(900);
        primaryStage.show();



    }


    private void loginButtonOnClick() {
        //TODO
    }
    private void clearButtonOnClick() {
        //TODO
    }

}
