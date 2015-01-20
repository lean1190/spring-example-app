package ar.edu.unlp.info.model;

public class CreatorRole extends UserRole implements Cloneable{

	public CreatorRole() {}
	public CreatorRole(User user, Project project) {
		super(user, project);
	}
}
