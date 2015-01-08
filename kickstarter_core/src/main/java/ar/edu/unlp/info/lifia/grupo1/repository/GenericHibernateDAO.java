package ar.edu.unlp.info.lifia.grupo1.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.edu.unlp.info.lifia.grupo1.repository.interfaces.GenericDAO;

@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}
	
	private DetachedCriteria createCriteria(Criterion... criterion) {
		DetachedCriteria crit = DetachedCriteria.forClass(this.getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit;
	}
	
	public T findById(ID id) {		
		return (T) this.getHibernateTemplate().load(getPersistentClass(), id);
	}
	
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().loadAll(this.getPersistentClass());
	}
	
	public List<T> findByCriteria(Criterion... criterion) {
		DetachedCriteria crit = this.createCriteria(criterion);
		return (List<T>) this.getHibernateTemplate().findByCriteria(crit);
	}
	
	public List<T> findByExample(T exampleInstance) {
		return (List<T>) this.getHibernateTemplate().findByExample(exampleInstance);
	}
	
	public T save(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}
	
	public void remove(T entity) {
		this.getHibernateTemplate().delete(entity);
	}
	
	public void flush() {
		this.getHibernateTemplate().flush();
	}
	
	public void clear() {
		this.getHibernateTemplate().clear();
	}

}
