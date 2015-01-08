package ar.edu.unlp.info.lifia.grupo1.model;

import java.util.Date;
import java.util.HashSet;

import ar.edu.unlp.info.lifia.grupo1.model.exceptions.InsufficientMoneyToInvestException;

public class Active extends ProjectState {
	
	public Active(){}

	@Override
	public void invest(Double amount, User user, Project project) throws InsufficientMoneyToInvestException  {
		InvestorRole investorRole; 
		
		if(user.getMoney() < amount){
			throw new InsufficientMoneyToInvestException();
		}
		
		/**
		 * Searchs for an existing InvestorRole in the project
		 * If it doesn't exist, creates it
		 */
		investorRole = user.findInvestorRoleToProject(project);
		if (investorRole == null ){
			investorRole = new InvestorRole(user, project, new HashSet<Investment>());
		}
		
		Investment investment = new Investment(amount, new Date());
		investorRole.addInvestment(investment);
		user.decrementMoney(amount);
		user.addUserRole(investorRole);
		project.addInvestor(investorRole);						
	}

	@Override
	public void markProjectAsSuspect(Project project) {
		project.setProjectState(new Suspect());
	}
	
	@Override
	public void markAsFinished(Project project) {
		project.getCreator().getUser().incrementSuccessPoints(10);
		project.setProjectState(new Finished());
	}
	
	@Override
	public String toString() {
		return "Activo";
	}
	
}
