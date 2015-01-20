package ar.edu.unlp.info.model;

import java.util.Set;

public class GameProject extends Project {

	public GameProject() {}
	
	public GameProject(CreatorRole creator, Set<InvestorRole> investors, ProjectState projectState, Set<Section> sections) {
		super(creator, investors, projectState, sections);
	}
	
	public GameProject(ProjectState projectState, Set<Section> sections) {
		super(projectState,sections);
	}

	@Override
	public boolean isVideoGameProject() {
		return true;
	}
	
	

}
