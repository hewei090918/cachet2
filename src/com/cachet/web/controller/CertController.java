package com.cachet.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cachet.web.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
    @Autowired
	private CertService certService;
	
}
