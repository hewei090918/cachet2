package com.cachet.web.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cachet.web.dao.CertDao;
import com.cachet.web.model.Cert;

@Repository
public class CertDaoImpl implements CertDao {

	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		Session session=sessionFactory.getCurrentSession();
	    return session;
	}
	
	@Override
	public void save(Cert cert){
	     getSession().save(cert);
	}
	
	@Override
	public void update(Cert cert){
		getSession().update(cert);
	}

	@Override
	public void delete(int certId) {
		getSession().delete(certId);
	}

	@Override
	public Cert get(int certId) {
		return (Cert) getSession().get(Cert.class, certId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cert> findAll() {
		String hql = "from Cert";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
