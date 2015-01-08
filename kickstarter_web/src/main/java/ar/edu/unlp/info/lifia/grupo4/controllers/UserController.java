package ar.edu.unlp.info.lifia.grupo4.controllers;

import ar.edu.unlp.info.lifia.grupo1.dto.UserDTO;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.DatabaseException;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.ExistingUserException;
import ar.edu.unlp.info.lifia.grupo1.services.interfaces.IUserService;
import ar.edu.unlp.info.lifia.grupo4.controllers.validators.UserRegisterValidator;
import java.util.Locale;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
//@SessionAttributes
public class UserController {

    @Autowired
    private MessageSource messageSource;
    
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRegisterValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Locale locale, Exception ex) {
        ModelAndView model = new ModelAndView("maintenance");
        model.addObject("enable", this.messageSource.getMessage("info.maintenance", new Object[0], locale));
        String str = "";
        for (StackTraceElement element : ex.getStackTrace()) {
            str += element.toString();
        }
        logger.error(str);
        
        return model;
    }

    @RequestMapping(value = "/register")
    public String registerUser(ModelMap model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);

        return "registerUser";
    }

    @RequestMapping(value = "/register/save")
    public String registerUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult result, ModelMap model,
            final RedirectAttributes redirectAttributes, Locale locale) throws DatabaseException {
        try {
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "registerUser";
            }
            this.getUserService().registerUser(user.getName(), user.getUsername(), user.getPassword());

            model.addAttribute("message", this.messageSource.getMessage("info.register.success", new Object[0], locale));
            
            return "infoPage";
        } catch (ExistingUserException ex) {
            model.addAttribute("error", this.messageSource.getMessage("error.user.exists", new Object[0], locale));
            
            return "registerUser";
        }
    }

    @RequestMapping(value = "/login/failed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        
        return "loginfailed";
    }

}
