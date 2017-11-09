package com.cachet.web.service;

import java.util.List;

import com.cachet.web.model.Cert;

public interface CertService {

	void saveCert(Cert cert);
	
	void updateCert(Cert cert);
	
	void deleteCert(int certId);
	
	Cert get(int certId);
	
	List<Cert> findAll();
	
}
