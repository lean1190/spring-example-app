package ar.edu.unlp.info.controllers.validators;

import ar.edu.unlp.info.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component
public class UserRegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object userDTO, Errors errors) {
        UserDTO user = ((UserDTO) userDTO);
        if ("".equals(user.getName())) {
            errors.rejectValue("name", "errorMessage", null, "El atributo: 'Nombre' no puede estar vacio");
        }
        if ("".equals(user.getUsername())) {
            errors.rejectValue("username", "errorMessage", null, "El atributo: 'Usuario' no puede estar vacio");
        }
        if ("".equals(user.getPassword())) {
            errors.rejectValue("password", "errorMessage", null, "El atributo: 'Contrase�a' no puede estar vacio");
        }
    }

}
