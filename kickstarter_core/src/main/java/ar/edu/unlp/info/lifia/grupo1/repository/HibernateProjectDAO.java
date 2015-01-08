package ar.edu.unlp.info.lifia.grupo1.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlp.info.lifia.grupo1.model.Project;
import ar.edu.unlp.info.lifia.grupo1.model.Suspect;
import ar.edu.unlp.info.lifia.grupo1.repository.interfaces.ProjectDAO;

@SuppressWarnings("unchecked")
public class HibernateProjectDAO extends GenericHibernateDAO<Project, Long> implements ProjectDAO {
	
	
	public List<Project> findProjectsBySection(String sectionName, String sectionContent) {
		
		DetachedCriteria projectCriteria = DetachedCriteria.forClass(Project.class);
		DetachedCriteria sectionsCriteria = projectCriteria.createCriteria("sections");
		
		sectionsCriteria
			.add(Restrictions.ilike("name", sectionName))
			.add(Restrictions.ilike("content",sectionContent, MatchMode.ANYWHERE));
		
		projectCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (List<Project>) this.getHibernateTemplate().findByCriteria(projectCriteria);
		
	}	
		
	public List<Project> findSuspectProjects(){
		
		return (List<Project>) this.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Project.class)
				.createAlias("projectState", "ps")
				.add(Restrictions.eq("ps.class", Suspect.class)));
	}

}
