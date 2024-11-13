package service.validation;

import entity.User;
import lombok.RequiredArgsConstructor;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoginValdation {
    private final UserService userService;

    public List<String> validate(String value) {
        List<String> loginErrors = new ArrayList<>();
        boolean isExist = userService.getAllUsers()
                .stream()
                .map(User::getLogin)
                .anyMatch(login -> login.equals(value));
        if (isExist) {
            loginErrors.add("такой пользователь уже существует");
        }
        if (value.length() < 5){
           loginErrors.add("длинна должна быть больще 5 символов");
        }
        if (value.contains("")){
            loginErrors.add("введён некорректный символ");
        }
        return loginErrors;
    }
}
