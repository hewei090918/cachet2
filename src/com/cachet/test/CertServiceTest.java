package com.cachet.test;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;


public class CertServiceTest {

	public static void main(String[] args) {

		//创建spring容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/resources/applicationContext.xml");
		//获取certService实例
		CertService certService = (CertService) ctx.getBean("certService");
		Cert cert = new Cert();
		cert.setCertId(2);
		cert.setCertName("xxx");
		cert.setCertType(2);
		cert.setCreateTime(new Date());
		cert.setScbz("0");
		certService.saveCert(cert);
		ctx.destroy();
		
	}

}
