package ar.edu.unlp.info.lifia.grupo1.model;

public class Suspect extends ProjectState {

	public Suspect() {}
	
	@Override
	public void cancelProjectByFraudulent(Project project) throws UnsupportedOperationException {
		double totalInvestments;
			
		for (InvestorRole currentInvestorRole : project.getInvestors()){
			totalInvestments = 0;
			for (Investment currentInvestment : currentInvestorRole.getInvestments()){
				totalInvestments += currentInvestment.getAmount(); 
			}
			currentInvestorRole.getUser().incrementMoney(totalInvestments);
		}
		
		project.getCreator().getUser().decrementSuccessPoints(15);
		
		project.setProjectState(new Cancelled());
	}
	
        @Override
        public void resumeProject(Project project) throws UnsupportedOperationException {
            project.setProjectState(new Active());
        }
        
	@Override
	public String toString() {
		return "Sospechado";
	}
}
