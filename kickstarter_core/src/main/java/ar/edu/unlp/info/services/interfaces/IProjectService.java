package ar.edu.unlp.info.services.interfaces;

import java.util.List;
import java.util.Set;

import ar.edu.unlp.info.dto.ProjectDTO;
import ar.edu.unlp.info.dto.SectionDTO;
import ar.edu.unlp.info.model.exceptions.InsufficientMoneyToInvestException;
import ar.edu.unlp.info.services.exceptions.DatabaseException;

public interface IProjectService {

        /**
         * Retrieves a project by it's id
         * 
         * @param projectId
         * @return An object with the project's information
         * @throws DatabaseException 
         */
        public ProjectDTO findProjectById(long projectId) throws DatabaseException;
        
	/**
	 * Creates a new movie project and sets it's owner
	 * 
	 * @param ownerID
	 * @param name
	 * @param genre
	 * @param target
	 * @param description
	 * @param script
	 * @param requiredMoney
	 * @return An object with the created movie project's information
	 * @throws DatabaseException
	 */
	public ProjectDTO createMovieProject(long ownerID,String name,String genre,String target,String description,String script,String requiredMoney) throws DatabaseException;
	
	/**
	 * Creates a new game project and sets it's owner
	 * 
	 * @param ownerID
	 * @param name
	 * @param codename
	 * @param target
	 * @param description
	 * @param requiredMoney
	 * @return An object with the created game project's information
	 * @throws DatabaseException
	 */
	public ProjectDTO createGameProject(long ownerID,String name,String codename,String target,String description,String requiredMoney) throws DatabaseException;
	
	/**
	 * Adds a new invest to a project
	 * Increments the total financed amount
	 * 
	 * @param userID
	 * @param projectID
	 * @param amount
	 * @throws UnsupportedOperationException
	 * @throws InsufficientMoneyToInvestException
	 * @throws DatabaseException
	 */
	public void investInProject(long userID, long projectID, Double amount) throws UnsupportedOperationException, InsufficientMoneyToInvestException, DatabaseException;
	
	/**
	 * Retrieves every project with a total or 
	 * partial coincidence on it's sections content
	 * 
	 * @param sectionName
	 * @param sectionContent
	 * @return The list of projects with coincidence
	 */
	public List<ProjectDTO> searchProjects(String sectionName, String sectionContent) throws DatabaseException;
	
	/**
	 * Changes a project's state to suspected
	 * 
	 * @param ProjectID
	 * @throws UnsupportedOperationException
	 * @throws DatabaseException
	 */
	public void markProjectAsSuspect(long ProjectID) throws UnsupportedOperationException, DatabaseException;
	
	/**
	 * Changes a project's state to finished
	 * 
	 * @param ProjectID
	 * @throws UnsupportedOperationException
	 * @throws DatabaseException
	 */
	public void markProjectAsFinished(long ProjectID) throws UnsupportedOperationException, DatabaseException;
	
	/**
	 * Changes a project's state to canceled
	 * The project will not be available any longer
	 * 
	 * @param ProjectID
	 * @throws UnsupportedOperationException
	 * @throws DatabaseException
	 */
	public void cancelProjectByFraudulent(long ProjectID) throws UnsupportedOperationException, DatabaseException;
	
        /**
	 * Changes a project's state to active
	 * 
	 * @param ProjectID
	 * @throws UnsupportedOperationException
	 * @throws DatabaseException
	 */
        public void resumeProject(long ProjectID) throws UnsupportedOperationException, DatabaseException;
                
	/**
	 * Retrieves those projects marked as supected
	 * 
	 * @return The list of projects in suspect state
	 * @throws DatabaseException
	 */
	public List<ProjectDTO> listOfSuspectProjects() throws DatabaseException;
	
        /**
         * Retrieves all projects
         * 
         * @return The list of projects
         * @throws DatabaseException 
         */
	public List<ProjectDTO> listAllProjects() throws DatabaseException;
	
	/**
	 * Retrieves the set of default sections
	 * 
	 * @return The list with the default sections
	 */
	public Set<SectionDTO> getDefaultSections();
	
	/**
	 * Retrieves the list of all Movie sections
	 * 
	 * @return The list with all Movie sections
	 */
	public List<SectionDTO> getAllMovieSections();
	
	/**
	 * Retrieves the list of all Game sections
	 * 
	 * @return The list with all Game sections
	 */
	public List<SectionDTO> getAllGameSections();
}
