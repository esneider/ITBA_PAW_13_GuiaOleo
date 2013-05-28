package ar.edu.itba.it.paw.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public abstract class AbstractHibernateRepo {

	private final SessionFactory sessionFactory;

	public AbstractHibernateRepo(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type, Serializable id) {

		return (T) getSession().get(type, id);
	}

	public <T> List<T> find(String hql, Object... params) {

		return limitedFind(hql, null, params);
	}

	public <T> List<T> limitedFind(String hql, Integer limit, Object... params) {

		Query query = getSession().createQuery(hql);

		return generateList(query, limit, params);
	} 

	protected org.hibernate.Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public Serializable save(Object o) {

		return getSession().save(o);
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> generateList(Query query, Integer limit, Object... params) {
		
		if (limit != null) {
			query.setMaxResults(limit);
		}

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

		return query.list();
	}
}
