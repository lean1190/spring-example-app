package ar.edu.unlp.info.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.edu.unlp.info.model.exceptions.InsufficientMoneyToInvestException;

public class User extends PersistentEntity {
	
	private Boolean administrator;
	
	private String name, username, password;
	private Double money;
	private Integer successPoints;
	private Set<UserRole> userRoles;
	
	public User(){}
	
	public User(Boolean administrator) {
		this.administrator = administrator;
	}
	
	public User(String name, String username, String password, Double money, Integer successPoints, Set<UserRole> userRoles, Boolean administrator) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.money = money;
		this.successPoints = successPoints;
		this.userRoles = userRoles;
		this.administrator = administrator;
	}
	
	public Boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Integer getSuccessPoints() {
		return successPoints;
	}

	public void setSuccessPoints(Integer successPoints) {
		this.successPoints = successPoints;
	}
	
	public void incrementSuccessPoints(int successPoints) {
		this.successPoints += successPoints;
	}
	
	public void decrementSuccessPoints(int successPoints) {
		this.successPoints -= successPoints;
	}
	
	public void addUserRole(UserRole userRole){
		this.getUserRoles().add(userRole);
	}
	
	public void incrementMoney(Double amount){
		this.money += amount;		
	}
	
	public void decrementMoney(Double amount){
		this.money -= amount;		
	}
	
	/**
	 * Calculates the total invested amount in every project
	 * 
	 * @return The total invested amount
	 */
	public Double getTotalInvestedAmount() {
		double totalInvested = 0;
		for (InvestorRole currentInvestorRole : this.getInvestorRoles()) {
			for (Investment currentInvestment : currentInvestorRole.getInvestments()) {
				totalInvested += currentInvestment.getAmount();
			}
		}
		
		return totalInvested;
	}
	
	/**
	 * Returns a set of every investor role
	 * 
	 * @return The set with the investor roles
	 */
	public Set<InvestorRole> getInvestorRoles(){
		Set<InvestorRole> investorRolesList = new HashSet<InvestorRole>();
		
		for (UserRole currentUserRole : this.getUserRoles()){
			if(currentUserRole.isInvestorRole()){
				investorRolesList.add((InvestorRole) currentUserRole);
			}
		}
		
		return investorRolesList;
	}
	
	/**
	 * Returns the investor role for a passing project
	 * 
	 * @param project
	 * @return The found investor role, or null
	 */
 	public InvestorRole findInvestorRoleToProject(Project project){
		Iterator<InvestorRole> investorRolesIterator = this.getInvestorRoles().iterator();
		InvestorRole actualInvestorRole = null, resultInvestorRole = null;
		boolean investorRoleFound = false;
		
		while (investorRolesIterator.hasNext() && investorRoleFound == false){
			actualInvestorRole = investorRolesIterator.next();
			if (actualInvestorRole.getProject() == project){
				investorRoleFound = true;
				resultInvestorRole = actualInvestorRole;
			}
		}
		
		return resultInvestorRole;
	}
	
	public void invest(Double amount, Project project) throws InsufficientMoneyToInvestException{
		project.invest(amount, this);
	}
	
	/**
	 * Administrator methods
	 */
	
	public void markProjectAsSuspect(Project project){
		project.markAsSuspect();
	}
	
	public void cancelProjectByFraudulent(Project project){
		project.cancelByFraudulent();
	}
	
}
