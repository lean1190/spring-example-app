package ar.edu.unlp.info.lifia.grupo1.model;

import java.util.Set;

import ar.edu.unlp.info.lifia.grupo1.model.Project;

public class MovieProject extends Project {

	public MovieProject() {}
	
	public MovieProject(CreatorRole creator, Set<InvestorRole> investors, ProjectState projectState, Set<Section> sections) {
		super(creator, investors, projectState, sections);
	}
	
	public MovieProject(ProjectState projectState, Set<Section> sections) {
		super(projectState,sections);
	}

	@Override
	public boolean isMovieProject() {
		return true;
	}
	
	

}
