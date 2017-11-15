package com.cachet.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cachet.common.bean.Result;
import com.cachet.common.properies.PropertiesItems;
import com.cachet.common.util.StringUtils;
import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	//文件保存目录路径
    public String uploadFilePath = "cert_img";
	
    @Autowired
	private CertService certService;
    
    @Autowired
    private PropertiesItems propertiesItems;
    
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryAll() {
    	Result result = new Result();
    	result.setStatus(1);
    	List<Cert> certList = certService.findAll();
    	result.setData(certList);
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public @ResponseBody String load(int certId) {
    	Result result = new Result();
    	Cert cert = certService.get(certId);
    	if(cert != null){
    		result.setStatus(1);
    		result.setData(cert);
    	}else {
    		result.setStatus(0);
    	}
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody String upload(@RequestParam MultipartFile idCardFrontFile, @RequestParam MultipartFile idCardBackFile,
    		@RequestParam MultipartFile policeFrontFile, @RequestParam MultipartFile policeBackFile,
    		@RequestParam MultipartFile signatureFile, @RequestParam MultipartFile cachetFile,
    		@ModelAttribute Cert cert, HttpServletRequest request,HttpServletResponse response) {
    	Result result = new Result();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    	String dateName = formatter.format(Calendar.getInstance().getTime());
    	String realPath = request.getServletContext().getRealPath("/");
//    	String savePath = propertiesItems.getImgPath() + "/" + dateName;
    	String savePath = realPath + uploadFilePath + "/" + dateName;
    	System.out.println("savePath = " + savePath);
    	//判断上传文件的保存目录是否存在
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        
        String fileName = "", url1 = "", url2 = "";
        
        int certType = cert.getCertType();
    	switch(certType) {
    		case 1:
    			if(idCardFrontFile != null){
                    fileName = uploadFile(savePath, idCardFrontFile);
                    url1 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(idCardBackFile != null){
                    fileName = uploadFile(savePath, idCardBackFile);
                    url2 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(cert.getCertId() != null)
                    updateRecord(cert, url1, url2);
                else
                    saveRecord(cert, url1, url2);
    			break;
    		case 2:
    			if(policeFrontFile != null){
                    fileName = uploadFile(savePath, policeFrontFile);
                    url1 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(policeBackFile != null){
                    fileName = uploadFile(savePath, policeBackFile);
                    url2 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(cert.getCertId() != null)
                    updateRecord(cert, url1, url2);
                else
                    saveRecord(cert, url1, url2);
    			break;
    		case 3:
    			if(signatureFile != null){
                    fileName = uploadFile(savePath, signatureFile);
                    url1 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(cert.getCertId() != null)
                    updateRecord(cert, url1, url2);
                else
                    saveRecord(cert, url1, url2);
    			break;
    		case 4:
    			if(cachetFile != null){
                    fileName = uploadFile(savePath, cachetFile);
                    url1 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                }
                if(cert.getCertId() != null)
                    updateRecord(cert, url1, url2);
                else
                    saveRecord(cert, url1, url2);
    			break;
    	}
    	result.setStatus(1);
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
    public String uploadFile(String savePath, MultipartFile uploadFile){
        //文件名转换
        String originName = uploadFile.getName();
        String fileName = rename(originName);
        //文件所在的路径
        String source = savePath + "/" + fileName;
        
        try{
        	logger.info("---上传证件开始---");
            InputStream inputStream = uploadFile.getInputStream();
            OutputStream outputStream = new FileOutputStream(new File(source));

            byte[] bt = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bt)) != -1) {
                outputStream.write(bt, 0, len);
            }
            // 关闭流
            outputStream.close();
            inputStream.close();
            logger.info("---上传证件结束---");
        }catch (IOException e){
            e.printStackTrace();
            logger.error("---上传证件失败---");
        }

        return fileName;
    }
    
    public void saveRecord(Cert cert, String url1, String url2){
    	cert.setCertUrl1(url1);
    	cert.setCertUrl2(url2);
    	cert.setCreateTime(new Date());
    	cert.setScbz("0");
        certService.saveCert(cert);
    }

    public void updateRecord(Cert cert, String url1, String url2){
        Cert record = certService.get(cert.getCertId());
        record.setCertName(cert.getCertName());
        if(StringUtils.isNotNullOrEmpty(url1))
            record.setCertUrl1(url1);
        if(StringUtils.isNotNullOrEmpty(url2))
            record.setCertUrl2(url2);
        certService.updateCert(record);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody String delete(String certIds) {
    	Result result = new Result();
    	String[] ids = certIds.split(",");
    	if(ids != null && ids.length > 0){
            for(String id: ids){
                int certId = Integer.parseInt(id);
                Cert cert = certService.get(certId);
                certService.deleteCert(cert);
            }
        }
    	result.setStatus(1);
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
    /*
     * 将上传的文件进行重命名
     */
    private static String rename(String name) {
        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;
        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }
        return fileName;
    }
	
}
