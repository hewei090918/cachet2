package com.cachet.web.dao;

import com.cachet.web.model.Cert;

public interface CertDao {
	void save(Cert cert);
	void update(Cert cert);
	void delete(int certId);
	Cert findById(int certId);
}
