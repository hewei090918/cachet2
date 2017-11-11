package com.cachet.web.service.impl;

import java.util.List;

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
	public void deleteCert(Cert cert) {
		certDao.delete(cert);
	}

	@Override
	public Cert get(int certId) {
		return certDao.get(certId);
	}

	@Override
	public List<Cert> findAll() {
		return certDao.findAll();
	}
	
	

}
