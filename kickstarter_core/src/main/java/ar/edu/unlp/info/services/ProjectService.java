package ar.edu.unlp.info.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dto.ProjectDTO;
import ar.edu.unlp.info.dto.SectionDTO;
import ar.edu.unlp.info.model.CreatorRole;
import ar.edu.unlp.info.model.GameProject;
import ar.edu.unlp.info.model.MovieProject;
import ar.edu.unlp.info.model.Project;
import ar.edu.unlp.info.model.ProjectPrototypeFactory;
import ar.edu.unlp.info.model.Section;
import ar.edu.unlp.info.model.User;
import ar.edu.unlp.info.model.exceptions.InsufficientMoneyToInvestException;
import ar.edu.unlp.info.repository.interfaces.ProjectDAO;
import ar.edu.unlp.info.repository.interfaces.UserDAO;
import ar.edu.unlp.info.services.exceptions.DatabaseException;
import ar.edu.unlp.info.services.interfaces.IProjectService;

public class ProjectService implements IProjectService{
	
	private ProjectDAO projectDAO;
	private UserDAO userDAO;
		
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	private void setProjectCreator(Project newProject, User projectCreator) {
		CreatorRole newCreatorRole = new CreatorRole(projectCreator, newProject);
		
		newProject.setCreator(newCreatorRole);
		projectCreator.addUserRole(newCreatorRole);
	}
	
	@Transactional
	public ProjectDTO createMovieProject(long ownerID,String name,String genre,String target,String description,String script,String requiredMoney) throws DatabaseException {
		try{
			MovieProject newMovieProject = ProjectPrototypeFactory.getInstance().createMovieProjectFromPrototype();		
			User projectCreator = this.getUserDAO().findById(ownerID);
			this.setProjectCreator(newMovieProject, projectCreator);
			
			// Sections setting
			newMovieProject.getSectionByName("Nombre").setContent(name);
			newMovieProject.getSectionByName("Genero").setContent(genre);
			newMovieProject.getSectionByName("Objetivo").setContent(target);
			newMovieProject.getSectionByName("Descripcion").setContent(description);
			newMovieProject.getSectionByName("Guion").setContent(script);
			newMovieProject.getSectionByName("Dinero requerido").setContent(requiredMoney);
			
			this.getProjectDAO().save(newMovieProject);
			
			return new ProjectDTO(newMovieProject);
			
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
	}
	
	@Transactional
	public ProjectDTO createGameProject(long ownerID,String name,String codename,String target,String description,String requiredMoney) throws DatabaseException {
		try{
			GameProject newGameProject = ProjectPrototypeFactory.getInstance().createGameProjectFromPrototype();		
			User projectCreator = this.getUserDAO().findById(ownerID);
			this.setProjectCreator(newGameProject, projectCreator);
			
			// Sections setting
			newGameProject.getSectionByName("Nombre").setContent(name);
			newGameProject.getSectionByName("Nombre en codigo").setContent(codename);
			newGameProject.getSectionByName("Objetivo").setContent(target);
			newGameProject.getSectionByName("Descripcion").setContent(description);
			newGameProject.getSectionByName("Dinero requerido").setContent(requiredMoney);
			
			this.getProjectDAO().save(newGameProject);
			
			return new ProjectDTO(newGameProject);
			
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
	}
	
	@Transactional
	public void investInProject(long userID, long projectID, Double amount) throws UnsupportedOperationException, InsufficientMoneyToInvestException, DatabaseException {
		try{
			Project project = this.getProjectDAO().findById(projectID);
			User user = this.getUserDAO().findById(userID);
			project.invest(amount, user);
			
			this.getProjectDAO().save(project);
			
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
	}
	
	@Transactional
	public List<ProjectDTO> searchProjects(String sectionName, String sectionContent) throws DatabaseException{
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		try{
			List<Project> projectsWithCoincidence = this.getProjectDAO().findProjectsBySection(sectionName,sectionContent);
			for (Project currentProject : projectsWithCoincidence) {
				projectsDTO.add(new ProjectDTO(currentProject));
			}
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
		
		return projectsDTO;
	}
	
	@Transactional
	public void markProjectAsSuspect(long projectID) throws UnsupportedOperationException, DatabaseException {
		try{
			Project project = this.getProjectDAO().findById(projectID);
			project.markAsSuspect();
			this.getProjectDAO().save(project);
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
	}
	
	@Transactional
	public void markProjectAsFinished(long projectID) throws UnsupportedOperationException, DatabaseException {
		try{
			Project project = this.getProjectDAO().findById(projectID);
			project.markAsFinished();
			this.getProjectDAO().save(project);
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
	}
	
	@Transactional
	public void cancelProjectByFraudulent(long projectID) throws UnsupportedOperationException, DatabaseException {
            try{
                Project project = this.getProjectDAO().findById(projectID);
                project.cancelByFraudulent();
                this.getProjectDAO().save(project);
            }catch(DataAccessException dataAccessException){
                throw new DatabaseException();
            }
	}
        
        @Transactional
        public void resumeProject(long projectID) throws UnsupportedOperationException, DatabaseException {
            try{
                Project project = this.getProjectDAO().findById(projectID);
                project.resumeProject();
                this.getProjectDAO().save(project);
            }catch(DataAccessException dataAccessException){
                throw new DatabaseException();
            }
        }
	
	@Transactional
	public List<ProjectDTO> listOfSuspectProjects() throws DatabaseException{
            List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
            try{
                List<Project> projectsFound = this.getProjectDAO().findSuspectProjects();
                for (Project currentProject : projectsFound){
                        projectsDTO.add(new ProjectDTO(currentProject));
                }
            }catch(DataAccessException dataAccessException){
                throw new DatabaseException();
            }

            return projectsDTO;
	}

        @Transactional
	public List<ProjectDTO> listAllProjects() throws DatabaseException{
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		try{
			List<Project> projectsFound = this.getProjectDAO().findAll();
			for (Project currentProject : projectsFound){
				projectsDTO.add(new ProjectDTO(currentProject));
			}
		}catch(DataAccessException dataAccessException){
			throw new DatabaseException();
		}
		
		return projectsDTO;		
	}      
	
	public List<SectionDTO> getAllMovieSections() {
		List<SectionDTO> sectionDTOList = new ArrayList<SectionDTO>();
		Set<Section> sectionsSet = ProjectPrototypeFactory.getInstance().getDefaultSections();
		sectionsSet.addAll(ProjectPrototypeFactory.getInstance().getMovieSections());
		
		for (Section currentSection : sectionsSet) {
			sectionDTOList.add(new SectionDTO(currentSection));
		}
		
		return sectionDTOList;
	}
	
	public List<SectionDTO> getAllGameSections() {
		List<SectionDTO> sectionDTOList = new ArrayList<SectionDTO>();
		Set<Section> sectionsSet = ProjectPrototypeFactory.getInstance().getDefaultSections();
		sectionsSet.addAll(ProjectPrototypeFactory.getInstance().getGameSections());
		
		for (Section currentSection : sectionsSet) {
			sectionDTOList.add(new SectionDTO(currentSection));
		}
		
		return sectionDTOList;
	}	
	
	public Set<SectionDTO> getDefaultSections() {
		Set<SectionDTO> sectionDTOSet = new HashSet<SectionDTO>();
		Set<Section> sectionsSet = ProjectPrototypeFactory.getInstance().getDefaultSections();
		
		for (Section currentSection : sectionsSet) {
			sectionDTOSet.add(new SectionDTO(currentSection));
		}
		
		return sectionDTOSet;
	}
	
        @Transactional
        public ProjectDTO findProjectById(long projectId) throws DatabaseException {
            try {
                Project project = this.getProjectDAO().findById(projectId);
                return new ProjectDTO(project);
            }catch(DataAccessException dataAccessException){
                throw new DatabaseException();
            }
        }
        
}
