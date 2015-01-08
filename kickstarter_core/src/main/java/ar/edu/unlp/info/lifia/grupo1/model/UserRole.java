package ar.edu.unlp.info.lifia.grupo1.model;

public abstract class UserRole extends PersistentEntity implements Cloneable{

	protected User user;
	protected Project project;
	
	public UserRole() {}
	
	public UserRole(User user, Project project) {
		this.user = user;
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public boolean isInvestorRole(){
		return false;
	}
	
	public Object clone() {
		UserRole userRoleClone = null;
		
	    try {
	    	userRoleClone = (UserRole)super.clone();
	    } 
	    catch(CloneNotSupportedException e) {
	        e.printStackTrace();
	    }
	    
	    return userRoleClone;
	}

}
