package ar.edu.unlp.info.lifia.grupo4.controllers;

import ar.edu.unlp.info.lifia.grupo1.services.exceptions.DatabaseException;
import ar.edu.unlp.info.lifia.grupo1.services.interfaces.IProjectService;
import ar.edu.unlp.info.lifia.grupo1.services.interfaces.IUserService;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private MessageSource messageSource;
    
    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IProjectService projectService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
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
        
    @RequestMapping(value = "/rankingUsers", method = RequestMethod.GET)
    public String rankingUsers(ModelMap model) throws DatabaseException {
        model.put("users", this.getUserService().projectCreatorsRank());

        return "rankingUsers";
    }

    @RequestMapping(value = "/inversions", method = RequestMethod.GET)
    public String inversions(ModelMap model) throws DatabaseException {
        model.put("users", this.getUserService().listOfUsersInvestmentData());

        return "inversions";
    }
    
    @RequestMapping(value = "/project/finish/{projectId}", method = RequestMethod.GET)
    public String finish(@PathVariable String projectId, ModelMap model, Locale locale) throws DatabaseException {
        this.getProjectService().cancelProjectByFraudulent(Long.parseLong(projectId));
        
        model.addAttribute("message", this.messageSource.getMessage("info.canceled", new Object[0], locale));
        
        return "infoPage";
    }
     
    @RequestMapping(value = "/project/suspected/{projectId}", method = RequestMethod.GET)
    public String suspected(@PathVariable String projectId, ModelMap model, Locale locale) throws DatabaseException {
        this.getProjectService().markProjectAsSuspect(Long.parseLong(projectId));
        
        model.addAttribute("message", this.messageSource.getMessage("info.suspected", new Object[0], locale));
        
        return "infoPage";
    }    
    
    @RequestMapping(value = "/project/resume/{projectId}", method = RequestMethod.GET)
    public String resume(@PathVariable String projectId, ModelMap model, Locale locale) throws DatabaseException {
        this.getProjectService().resumeProject(Long.parseLong(projectId));
        
        model.addAttribute("message", this.messageSource.getMessage("info.resumed", new Object[0], locale));
        
        return "infoPage";
    }  
    
}
