package ar.edu.unlp.info.dto;

import ar.edu.unlp.info.model.Section;

public class SectionDTO {
	
	private String content;
	private String name;
	
	public SectionDTO(){}
	
	public SectionDTO(Section section){
		this.name = section.getName();
		this.content = section.getContent();
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
