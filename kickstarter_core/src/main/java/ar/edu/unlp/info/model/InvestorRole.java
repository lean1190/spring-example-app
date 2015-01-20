package ar.edu.unlp.info.model;

import java.util.Set;

public class InvestorRole extends UserRole {

	private Set<Investment> investments;

	public InvestorRole() {}
	
	public InvestorRole(User user, Project project, Set<Investment> investments) {
		super(user, project);		
		this.investments = investments;
	}

	public Set<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(Set<Investment> investments) {
		this.investments = investments;
	}
	
	public void addInvestment(Investment investment){
		this.getInvestments().add(investment);
	}

	@Override
	public boolean isInvestorRole() {
		return true;
	}
	
	

}
