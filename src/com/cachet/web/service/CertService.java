package com.cachet.web.service;

import com.cachet.web.model.Cert;

public interface CertService {

	void saveCert(Cert cert);
	
	void updateCert(Cert cert);
	
	void deleteCert(int certId);
	
}
