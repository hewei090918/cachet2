package com.cachet.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cachet.web.dao.CertDao;
import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;

@Service("certService")
public class CertServiceImpl implements CertService {

	@Autowired
	private CertDao certDao;
	
	@Override
	public void saveCert(Cert cert) {
		certDao.save(cert);
	}

	@Override
	public void updateCert(Cert cert) {
		certDao.update(cert);
	}

	@Override
	public void deleteCert(int certId) {
		certDao.delete(certId);
	}

}
