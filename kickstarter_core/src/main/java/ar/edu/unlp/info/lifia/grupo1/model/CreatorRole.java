package ar.edu.unlp.info.lifia.grupo1.model;

public class CreatorRole extends UserRole implements Cloneable{

	public CreatorRole() {}
	public CreatorRole(User user, Project project) {
		super(user, project);
	}
}
