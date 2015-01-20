package ar.edu.unlp.info.model;

import ar.edu.unlp.info.model.exceptions.InsufficientMoneyToInvestException;

/**
 * Defines default behavior for concrete classes
 */
public abstract class ProjectState extends PersistentEntity implements Cloneable {
	
	/**
	 * Overriden by Active class
	 * 
	 * @param amount
	 * @param user
	 * @param project
	 * @throws UnsupportedOperationException
	 * @throws InsufficientMoneyToInvestException
	 */
	public void invest(Double amount, User user, Project project) throws UnsupportedOperationException, InsufficientMoneyToInvestException{
            throw new UnsupportedOperationException();
	}
	
	/**
	 * Overriden by Active class
	 * 
	 * @param project
	 * @throws UnsupportedOperationException
	 */
	public void markProjectAsSuspect(Project project) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();			
	}
	
	/**
	 * Overriden by Active class
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void markAsFinished(Project project) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
	}
	
	/**
	 * Overriden by Suspect class
	 * 
	 * @param project
	 * @throws UnsupportedOperationException
	 */
	public void cancelProjectByFraudulent(Project project) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
	}
        
        /**
	 * Overriden by Suspect class
	 * 
	 * @param project
	 * @throws UnsupportedOperationException
	 */
        public void resumeProject(Project project) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
	
	public Object clone() {
		ProjectState projectStateClone = null;
		
	    try {
	    	projectStateClone = (ProjectState)super.clone();
	    } 
	    catch(CloneNotSupportedException e) {
	        e.printStackTrace();
	    }
	    
	    return projectStateClone;
	}
	
}
