package ar.edu.unlp.info.lifia.grupo1.repository.interfaces;

import java.util.List;

import ar.edu.unlp.info.lifia.grupo1.model.User;
import org.springframework.dao.DataAccessException;

public interface UserDAO extends GenericDAO<User, Long> {

	/**
	 * Retrieves a list of users who had created at least one project,
	 * ordered by their success points, greater to lower 
	 * 
	 * @return The list of users whit one or more projects in charge
	 */
	public List<User> findCreatorsRank() throws DataAccessException;
        
        public List<User> findByUsername(String username) throws DataAccessException;
}
