package view;

public class LoginDataValidator implements IValidator{
    private String name;
    private String password;

    public LoginDataValidator(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String validateInput() {
        String message = "";
        if (name.trim() == "") {
            message += "Моля въведете трите си имена! ";
        }
        if (!name.matches("[А-Яа-я]{2,10}[\\s]{1}[А-Яа-я]{2,15}[\\s]{1}[А-Яа-я]{2,15}")) {
            message += "Неправилен формат на името! Въведете име, презиме и фамилия разделени с интервал. ";
        }
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$")) {
            message += "Невалиден формат на паролата! ";
        }
        if (message.length() != 0) {
            return message;
        } else {
            return "OK";
        }
    }
}
