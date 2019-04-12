import controller.AccountChecker;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.FXControlDataLoader;
import view.DataValidator;

import java.util.Date;

public class App extends Application {
    public static final String EMPLOYEES_FILE_NAME = "employees.txt";
    public static final String ADMINS_FILE_NAME = "administrators.txt";

    public static void main(String[] args) {
        launch(args);
    }

    String userName;
    Stage stage;
    Scene loginScene;
    Scene employeeScene;
    TextField textName;
    PasswordField textPassword;
    RadioButton radioEmployee;
    RadioButton radioAdmin;
    Button loginButton;
    Button clearButton;
    ComboBox<String> comboClients;
    TextField textWorkedHours;
    Button saveProtocolButton;
    Button toLogginScreenButton;
    FXControlDataLoader loader;
    Date date;
    String dateString;
    String userFullName;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        //loginScene
        Text textHeading = new Text("Влезте в системата като служител или администратор.");
        textHeading.setFont(new Font("Verdana", 23));
        HBox paneTop = new HBox(textHeading);
        paneTop.setAlignment(Pos.CENTER);
        paneTop.setPadding(new Insets(20, 10, 20, 10));

        radioEmployee = new RadioButton("Служител");
        radioAdmin = new RadioButton("Администратор");
        HBox paneChecks = new HBox(10, radioEmployee, radioAdmin);
        paneChecks.setAlignment(Pos.CENTER);
        ToggleGroup employeeOrAdmin = new ToggleGroup();
        radioEmployee.setToggleGroup(employeeOrAdmin);
        radioAdmin.setToggleGroup(employeeOrAdmin);
        radioEmployee.setSelected(true);

        Label labelName = new Label("Име");
        labelName.setPrefWidth(100);
        textName = new TextField();
        textName.setText("");
        textName.setPrefColumnCount(30);
        textName.setPromptText("Въведете трите си имена.");
        HBox paneName = new HBox(-50, labelName, textName);
        paneName.setAlignment(Pos.CENTER);

        Label labelPassword = new Label("Парола");
        labelPassword.setPrefWidth(100);
        textPassword = new PasswordField();
        textPassword.setPrefColumnCount(30);
        textPassword.setText("");
        textPassword.setPromptText("Поне 6 знака, малка и голяма буква, число, специален сивол.");
        HBox panePassword = new HBox(-50, labelPassword, textPassword);
        panePassword.setAlignment(Pos.CENTER);
        VBox paneCenter = new VBox(20, paneChecks, paneName, panePassword);
        paneCenter.setAlignment(Pos.CENTER);

        loginButton = new Button("ОК");
        loginButton.setMinWidth(80);
        loginButton.setOnAction(e -> loginButtonOnClick());
        loginButton.setDefaultButton(true);
        clearButton = new Button("Изчисти");
        clearButton.setMinWidth(80);
        clearButton.setOnAction(e -> clearButtonOnClick());
        HBox paneBottom = new HBox(20, loginButton, clearButton);
        paneBottom.setAlignment(Pos.CENTER);

        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        paneMain.setPadding(new Insets(50, 10, 90, 10));

        loginScene = new Scene(paneMain, 900, 500);
        stage.setScene(loginScene);
        stage.setTitle("Система за следене на работното време");
        stage.show();
    }

    private void loginButtonOnClick() {
        boolean accountExists;
        String fileToReadFrom;
        String userType;
        if (radioEmployee.isSelected()) {
            fileToReadFrom = EMPLOYEES_FILE_NAME;
            userType = "empolyee";
        } else {
            fileToReadFrom = ADMINS_FILE_NAME;
            userType = "administrator";
        }
        DataValidator validator = new DataValidator(textName.getText(), textPassword.getText());
        String validatorResult = validator.validateLoginInput();
        if (!validatorResult.equals("OK")) {
            Alert a = new Alert(Alert.AlertType.WARNING, validatorResult);
            a.setTitle("Некоректни входни данни!");
            a.showAndWait();
        } else {
            AccountChecker accountChecker = new AccountChecker(textName.getText(), textPassword.getText(), fileToReadFrom);
            accountExists = accountChecker.fetchAccount();
            if (accountExists) {
                userFullName = textName.getText();
                stage.setScene(employeeScene);
                stage.setTitle("Система за следене на работното време");
                stage.show();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Акаунтът не е намерен.");
                a.setTitle("Търсене на акаунт");
                a.showAndWait();
            }
        }
    }


    private void clearButtonOnClick() {
        textName.setText("");
        textPassword.setText("");
    }
}
