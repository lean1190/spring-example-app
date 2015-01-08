package ar.edu.unlp.info.lifia.grupo1.repository;


import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlp.info.lifia.grupo1.model.CreatorRole;
import ar.edu.unlp.info.lifia.grupo1.model.User;
import ar.edu.unlp.info.lifia.grupo1.repository.interfaces.UserDAO;
import java.util.List;

@SuppressWarnings("unchecked")
public class HibernateUserDAO extends GenericHibernateDAO<User, Long> implements UserDAO {

	public List<User> findCreatorsRank(){
		
		return (List<User>) this.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(User.class, "user")
                    .createAlias("userRoles", "ur")
                    .add(Restrictions.eq("ur.class", CreatorRole.class))
                    .addOrder(Order.desc("user.successPoints"))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
	}	
        
        public List<User> findByUsername(String username) {
            User exampleToFind = new User(null, username, null, null, null, null, null); //null values are ignored in the search
            
            return this.findByExample(exampleToFind);           
        }

}
