package com.cachet.web.dao.impl;

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
	
	@Override
	public void save(Cert cert){
		 Session session=sessionFactory.getCurrentSession();
	     session.save(cert);
	}
	
	@Override
	public void update(Cert cert){
		Session session=sessionFactory.getCurrentSession();
		session.update(cert);
	}

	@Override
	public void delete(int certId) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(certId);
	}

	@Override
	public Cert findById(int certId) {
		return null;
	}
}
