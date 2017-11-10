package com.cachet.web.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cachet.common.bean.Result;
import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
    @Autowired
	private CertService certService;
    
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryAll() {
    	Result result = new Result();
    	result.setStatus(1);
    	List<Cert> certList = certService.findAll();
    	result.setData(certList);
    	String ss = JSONObject.fromObject(result).toString();
//    	System.out.println(ss);
    	return ss;
    }
    
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load() {
    	
    	return "";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete() {
    	return "";
    }
	
}
