package ar.edu.unlp.info.lifia.grupo1.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.edu.unlp.info.lifia.grupo1.model.exceptions.InsufficientMoneyToInvestException;

/**
 * Design patterns: Prototype
 */
public abstract class Project extends PersistentEntity implements Cloneable {

	protected CreatorRole creator;
	protected Set<InvestorRole> investors;
	protected ProjectState projectState;
	protected Set<Section> sections;

	public Project() {}
	
	public Project(CreatorRole creator, Set<InvestorRole> investors, ProjectState projectState, Set<Section> sections) {
		this.creator = creator;
		this.investors = investors;
		this.projectState = projectState;
		this.sections = sections;
	}
	
	public Project(ProjectState projectState, Set<Section> sections) {
		this.projectState = projectState;
		this.sections = sections;
	}

	public CreatorRole getCreator() {
		return creator;
	}

	public void setCreator(CreatorRole creator) {
		this.creator = creator;
	}
	
	public Set<InvestorRole> getInvestors() {
		return investors;
	}

	public void setInvestors(Set<InvestorRole> investors) {
		this.investors = investors;
	}
		
	public ProjectState getProjectState() {
		return projectState;
	}

	public void setProjectState(ProjectState projectState) {
		this.projectState = projectState;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	/**
	 * Returns a section which name's attribute is equal to the passing section name 
	 * 
	 * @param sectionName
	 * @return The found section, or null
	 */
	public Section getSectionByName(String sectionName){
		Section resultSection = null;
		Boolean sectionFound = false;
		
		Iterator<Section> sectionsIterator = this.getSections().iterator();
		while(sectionsIterator.hasNext() && !sectionFound) {
			Section currentSection = sectionsIterator.next(); 
			if(currentSection.getName().toLowerCase().equals(sectionName.toLowerCase())) {
				sectionFound = true;
				resultSection = currentSection;
			}
		}
		
		return resultSection;
	}
	
	/**
	 * Calculates the total amount invested in all projects
	 * 
	 * @return The total support money amount
	 */
	public Double getTotalInvestments() {
		Double totalInvestments = 0.0;
		for (InvestorRole currentInvestorRole : this.getInvestors()) {
			for (Investment currentInvestment : currentInvestorRole.getInvestments()) {
				totalInvestments += currentInvestment.getAmount();
			}
		}
		return totalInvestments;
	}
	
	public void addSection(Section section) {
		this.getSections().add(section);
	}
	
	public void addInvestor(InvestorRole investor){
		this.getInvestors().add(investor);
	}
	
	/**
	 * Increments the support money for a project
	 * 
	 * @param amount
	 * @param user
	 * @throws UnsupportedOperationException
	 * @throws InsufficientMoneyToInvestException
	 */
	public void invest(Double amount, User user) throws UnsupportedOperationException, InsufficientMoneyToInvestException{
		this.getProjectState().invest(amount, user, this);
	}
	
	/**
	 * Changes the project state to Suspect
	 */
	public void markAsSuspect() throws UnsupportedOperationException {
		this.getProjectState().markProjectAsSuspect(this);
	}
	
	/**
	 * Changes the project state to Finished
	 */
	public void markAsFinished() throws UnsupportedOperationException {
		this.getProjectState().markAsFinished(this);
	}
	
	/**
	 * Changes the project state to Cancelled
	 */
	public void cancelByFraudulent() throws UnsupportedOperationException{
            this.getProjectState().cancelProjectByFraudulent(this);
	}
        
        public void resumeProject() throws UnsupportedOperationException {
            this.getProjectState().resumeProject(this);
        }
	
	public Object clone() {
		Project projectClone = null;
		
	    try {
	    	projectClone = (Project)super.clone();
	    	
	    	/**
	    	 * Deep copy, objects cloning
	    	 */
	    	Set<Section> sectionsClone = new HashSet<Section>();	  
	    	
	    	for(Section currentSection : projectClone.getSections()) {
	    		sectionsClone.add((Section) currentSection.clone());
	    	}
	    	
	    	projectClone.setProjectState((ProjectState)projectClone.getProjectState().clone());	    		    	
	    	projectClone.setInvestors(new HashSet<InvestorRole>());
	    	projectClone.setSections(sectionsClone);

	    } 
	    catch(CloneNotSupportedException e) {
	        e.printStackTrace();
	    }
	    
	    return projectClone;
	}
	
	public boolean isVideoGameProject(){
		return false;
	}
	
	public boolean isMovieProject(){
		return false;
	}
	
	public String toString(){
		return (this.getSectionByName("nombre")).getContent() + (this.getSectionByName("descripcion")).getContent();
	}
	
}
