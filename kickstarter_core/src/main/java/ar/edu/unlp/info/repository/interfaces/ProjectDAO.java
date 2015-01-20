package ar.edu.unlp.info.repository.interfaces;

import java.util.List;

import ar.edu.unlp.info.model.Project;

public interface ProjectDAO extends GenericDAO<Project, Long>{

	/**
	 * Returns a list with those projects wich sections name or content
	 * have a total or parcial coincidence with the passing parameters
	 * 
	 * @param sectionName
	 * @param sectionContent
	 * @return The list of projects with coincidences
	 */
	public List<Project> findProjectsBySection(String sectionName, String sectionContent);
		
	/**
	 * Returns a list of projects marked as suspect
	 * 
	 * @return The list of projects in suspect state
	 */
	public List<Project> findSuspectProjects();
}
