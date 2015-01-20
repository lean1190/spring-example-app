package ar.edu.unlp.info.controllers;

import ar.edu.unlp.info.dto.ProjectDTO;
import ar.edu.unlp.info.model.exceptions.InsufficientMoneyToInvestException;
import ar.edu.unlp.info.services.exceptions.DatabaseException;
import ar.edu.unlp.info.services.interfaces.IProjectService;
import ar.edu.unlp.info.controllers.validators.ProjectValidator;
import ar.edu.unlp.info.dto.UserSession;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
//@SessionAttributes("user")
public class ProjectController {

    @Autowired
    private MessageSource messageSource;
      
    private static final Logger logger = Logger.getLogger(ProjectController.class);

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectValidator projectValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(projectValidator);
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
   
    private UserSession getAuthenticatedUser() {
        return (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws DatabaseException {
        model.put("projects", this.getProjectService().listAllProjects());
        
        return "index";
    }

    @RequestMapping(value = "project/create/movie", method = RequestMethod.GET)
    public String createMovieProject(ModelMap model) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setSections(this.getProjectService().getAllMovieSections());
        model.addAttribute("projectDTO", projectDTO);
        model.addAttribute("type", "movie");

        return "createProject";
    }

    @RequestMapping(value = "project/create/game", method = RequestMethod.GET)
    public String createGameProject(ModelMap model) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setSections(this.getProjectService().getAllGameSections());
        model.addAttribute("projectDTO", projectDTO);
        model.addAttribute("type", "game");

        return "createProject";
    }

    @RequestMapping(value = "project/save/game", method = RequestMethod.POST)
    public String saveGameProject(@Valid ProjectDTO projectDTO, BindingResult result, ModelMap model,
            final RedirectAttributes redirectAttributes, Locale locale) throws DatabaseException {

        if (result.hasErrors()) {
            model.addAttribute("projectDTO", projectDTO);
            model.addAttribute("type", "game");

            return "createProject";
        }
        
        UserSession user = this.getAuthenticatedUser();
        this.getProjectService().createGameProject(user.getId(), projectDTO.getSectionByName("Nombre").getContent(), projectDTO.getSectionByName("Nombre en codigo").getContent(), projectDTO.getSectionByName("Objetivo").getContent(), projectDTO.getSectionByName("Descripcion").getContent(), projectDTO.getSectionByName("Dinero requerido").getContent());

        model.addAttribute("message",this.messageSource.getMessage("info.project.created", new Object[0], locale));
        
        return "infoPage";
    }

    @RequestMapping(value = "project/save/movie", method = RequestMethod.POST)
    public String saveMovieProject(@Valid ProjectDTO projectDTO, BindingResult result, ModelMap model,
            final RedirectAttributes redirectAttributes, Locale locale) throws DatabaseException {

        if (result.hasErrors()) {
            model.addAttribute("projectDTO", projectDTO);
            model.addAttribute("type", "movie");
            
            return "createProject";
        }
        
        UserSession user = this.getAuthenticatedUser();
        this.getProjectService().createMovieProject(user.getId(), projectDTO.getSectionByName("Nombre").getContent(), projectDTO.getSectionByName("Genero").getContent(), projectDTO.getSectionByName("Objetivo").getContent(), projectDTO.getSectionByName("Descripcion").getContent(), projectDTO.getSectionByName("Guion").getContent(), projectDTO.getSectionByName("Dinero requerido").getContent());
        
        model.addAttribute("message",this.messageSource.getMessage("info.project.created", new Object[0], locale));
        
        return "infoPage";
    }

    @RequestMapping(value = "show/{projectId}", method = RequestMethod.GET)
    public String showProject(@PathVariable String projectId, ModelMap model) throws DatabaseException {

        UserSession user;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // If the user is logged in
        if (principal instanceof UserDetails) {
            user = (UserSession) principal;
            model.addAttribute("userId", user.getId());
        }

        ProjectDTO projectDTO = this.getProjectService().findProjectById(Long.parseLong(projectId));
        model.addAttribute("project", projectDTO);

        return "showProject";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchProject(@RequestParam("search") String search, ModelMap model) throws DatabaseException {
        List<ProjectDTO> projectDtoResultList = this.getProjectService().searchProjects("%", search);
        model.addAttribute("projects", projectDtoResultList);
        
        return "searchResults";
    }
    
    @RequestMapping(value = "project/invest/{projectId}", method = RequestMethod.POST)
    public String invest(@PathVariable String projectId, @RequestParam("amount") String amount, ModelMap model, Locale locale) throws DatabaseException {
        try {
            Double realAmount = Double.parseDouble(amount);
            Long userId = this.getAuthenticatedUser().getId();
            this.getProjectService().investInProject(userId, Long.parseLong(projectId), realAmount);
            
            model.addAttribute("message", this.messageSource.getMessage("info.invest.success", new Object[0], locale));
        } catch (UnsupportedOperationException ex) {
            model.addAttribute("message", this.messageSource.getMessage("info.unsupported", new Object[0], locale));
        } catch (InsufficientMoneyToInvestException ex) {
            model.addAttribute("message", this.messageSource.getMessage("info.nomoney", new Object[0], locale));
        } catch (NumberFormatException e) {
            model.addAttribute("message", this.messageSource.getMessage("info.wrongnumber", new Object[0], locale));
        } finally {
            return "infoPage";
        }

    }

    @RequestMapping(value = "project/finish/{projectId}", method = RequestMethod.GET)
    public String finish(@PathVariable String projectId, ModelMap model, Locale locale) throws DatabaseException {        
        Long userId = this.getAuthenticatedUser().getId();
        ProjectDTO projectDTO = this.getProjectService().findProjectById(Long.parseLong(projectId));

        // If the current user is the owner of the project
        if(projectDTO.getCreatorId() == userId) {
            this.getProjectService().markProjectAsFinished(projectDTO.getId());
            model.addAttribute("message", this.messageSource.getMessage("info.finished", new Object[0], locale));
        }
        else
            model.addAttribute("message", this.messageSource.getMessage("info.notyours", new Object[0], locale));
        
        return "infoPage";
    }
         
}
