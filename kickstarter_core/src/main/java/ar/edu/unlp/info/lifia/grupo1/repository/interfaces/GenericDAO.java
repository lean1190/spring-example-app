package ar.edu.unlp.info.lifia.grupo1.repository.interfaces;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;

import org.springframework.dao.DataAccessException;

/**
 * Generic Repository, providing basic CRUD operations
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
 */
public interface GenericDAO<T, ID extends Serializable> {
	 
   /**
    * Find an entity by its primary key
    * 
    * @param id
    * @return the entity
    */
    T findById(ID id) throws DataAccessException;
 
    /**
     * Load all entities
     * 
     * @return the list of entities
     */
    List<T> findAll() throws DataAccessException;
 
    /**
     * Find entities based on a collection of restrictions
     * 
     * @param criterion a collection of restrictions
     * @return the list of entities found
     */
    public List<T> findByCriteria(Criterion... criterion);
    
    /**
     * Find entities based on an example
     * 
     * @param exampleInstance the example
     * @return the list of entities
     */    
    List<T> findByExample(T exampleInstance) throws DataAccessException;
    
    /**
     * Save an entity
     * 
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity) throws DataAccessException;
 
    
    /**
     * Remove an entity
     * 
     * @param entity the entity to delete
     */
    void remove(T entity) throws DataAccessException;
    
}
