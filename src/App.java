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
}
