package com.cachet.web.dao;

import java.util.List;

import com.cachet.web.model.Cert;

public interface CertDao {
	
	void save(Cert cert);
	
	void update(Cert cert);
	
	void delete(int certId);
	
	Cert get(int certId);
	
	List<Cert> findAll();
	
}
