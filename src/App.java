import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
    }

    @Override
    public void start(Stage primaryStage) {

    }
    private void loginButtonOnClick() {
        boolean accountExists;
        String fileToReadFrom;
        if (radioEmployee.isSelected()) {
            fileToReadFrom = EMPLOYEES_FILE_NAME;
        } else {
            fileToReadFrom = ADMINS_FILE_NAME;
        }
        LoginDataValidator validator = new LoginDataValidator(textName.getText(), textPassword.getText());
        String validatorResult = validator.validateInput();
        if (!validatorResult.equals("OK")) {
            Alert a = new Alert(Alert.AlertType.WARNING, validatorResult);
            a.setTitle("Некоректни входни данни!");
            a.showAndWait();
        } else {
            AccountChecker accountChecker = new AccountChecker(textName.getText(), textPassword.getText(), fileToReadFrom);
            accountExists = accountChecker.fetchAccount();
            if (accountExists) {
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

    public void employeeScene() {
        Text textHeadingEmployee = new Text("Създайте протокол за деня");
        textHeadingEmployee.setFont(new Font("Verdana", 23));
        HBox paneTopEmployee = new HBox(textHeadingEmployee);
        paneTopEmployee.setAlignment(Pos.CENTER);
        paneTop.setPadding(new Insets(20, 10, 20, 10));

        Label labelComboClients = new Label("Избери клиент");
        comboClients = new ComboBox<String>();
        comboClients.setPromptText("Избери клиент");
        loader = new FXControlDataLoader();
        comboClients.setItems(FXCollections.observableArrayList(loader.setComboModel()));
        comboClients.setPrefWidth(140);
        HBox paneComboClients = new HBox(10, labelComboClients, comboClients);
        paneComboClients.setAlignment(Pos.CENTER);

        Label workedHours = new Label("Работено време");
        textWorkedHours = new TextField();
        textWorkedHours.setPromptText("h:mm");
        textWorkedHours.setPromptText("Изработени часове");
        HBox paneTextWorkedHours = new HBox(10, workedHours, textWorkedHours);
        paneTextWorkedHours.setAlignment(Pos.CENTER);
        VBox paneCenterEmployee = new VBox(30, paneComboClients, paneTextWorkedHours);
        paneCenterEmployee.setAlignment(Pos.CENTER);
        paneCenterEmployee.setPadding(new Insets(40, 10, 30, 10));

        saveProtocolButton = new Button("Запази");
        saveProtocolButton.setMinWidth(80);
        saveProtocolButton.setOnAction(e -> saveProtocolButtonOnClick());
        saveProtocolButton.setDefaultButton(true);
        toLogginScreenButton = new Button("Към логин екрана");
        toLogginScreenButton.setMinWidth(80);
        toLogginScreenButton.setOnAction(e -> toLogginScreenButtonOnClick());
        HBox paneBottomEmployee = new HBox(10, saveProtocolButton, toLogginScreenButton);
        paneBottomEmployee.setAlignment(Pos.CENTER);
        date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        dateString = formatter.format(date);

        BorderPane paneMainEmployee = new BorderPane();
        paneMainEmployee.setTop(paneTopEmployee);
        paneMainEmployee.setCenter(paneCenterEmployee);
        paneMainEmployee.setBottom(paneBottomEmployee);
        paneMainEmployee.setPadding(new Insets(80, 10, 180, 10));
        employeeScene = new Scene(paneMainEmployee, 900, 500);
    }
}
