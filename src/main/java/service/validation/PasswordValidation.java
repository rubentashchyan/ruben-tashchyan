package service.validation;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidation {



       public List<String> validate(String value) {
              List<String> passwordErrors = new ArrayList<>();
              if (value.length() < 5){
                     passwordErrors.add("пароль должен содержать от 5 символов");
              }
              if (value.length() > 16){
                     passwordErrors.add("пароль слишком длинный");
              }
              return passwordErrors;
       }

}
