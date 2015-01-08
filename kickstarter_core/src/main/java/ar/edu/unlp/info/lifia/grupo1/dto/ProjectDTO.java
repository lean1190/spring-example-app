package ar.edu.unlp.info.lifia.grupo1.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.edu.unlp.info.lifia.grupo1.model.Project;
import ar.edu.unlp.info.lifia.grupo1.model.Section;

public class ProjectDTO {
	
	private long id;
	private long creatorId;
	private Double totalInvestments;
	private String creator;
	private String state;
	private String type;
	private List<SectionDTO> sections;
	
	public ProjectDTO(){}
	
	public ProjectDTO(Project project) {
		this.id = project.getId();
		this.creatorId = project.getCreator().getUser().getId();
		this.totalInvestments = project.getTotalInvestments();
		this.creator = project.getCreator().getUser().getName();
		this.state = project.getProjectState().toString();
				
		if (project.isMovieProject()){
			this.type = "Pelicula";
		}else{
			this.type = "Videojuego";
		}
		
		this.sections = new ArrayList<SectionDTO>();
		for(Section currentSection : project.getSections()){ 
                    this.sections.add(new SectionDTO(currentSection));
		}
			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotalInvestments() {
		return totalInvestments;
	}

	public void setTotalInvestments(Double totalInvestments) {
		this.totalInvestments = totalInvestments;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public List<SectionDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}
	
	public SectionDTO getSectionByName(String sectionName){
		SectionDTO resultSection = null;
		Boolean sectionFound = false;
		
		Iterator<SectionDTO> sectionsIterator = this.getSections().iterator();
		while(sectionsIterator.hasNext() && !sectionFound) {
			SectionDTO currentSection = sectionsIterator.next(); 
			if(currentSection.getName().toLowerCase().equals(sectionName.toLowerCase())) {
				sectionFound = true;
				resultSection = currentSection;
			}
		}
		
		return resultSection;
	}
	
}
