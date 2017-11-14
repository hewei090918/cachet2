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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cachet.common.bean.Result;
import com.cachet.common.util.StringUtils;
import com.cachet.web.bean.FileBean;
import com.cachet.web.bean.upload.CertUpload;
import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
	//文件保存目录路径
    public static final String UPLOAD_FILEPATH = "upload";
	
    @Autowired
	private CertService certService;
    
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
    public String upload(HttpServletRequest request,HttpServletResponse response,@ModelAttribute CertUpload certUpload) {
    	Result result = new Result();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    	String dateName = formatter.format(Calendar.getInstance().getTime());
    	String realPath = request.getServletContext().getRealPath("/");
    	String savePath = realPath + UPLOAD_FILEPATH + "/" + dateName;
    	System.out.println("savePath = " + savePath);
    	//判断上传文件的保存目录是否存在
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        
        String fileName = "", url1 = "", url2 = "";
    	switch(certUpload.getCertType()) {
    		case 1:
    			if(certUpload.getIdCardFrontFile() != null){
                    fileName = uploadFile(savePath, certUpload.getIdCardFrontFile());
                    url1 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getIdCardBackFile() != null){
                    fileName = uploadFile(savePath, certUpload.getIdCardBackFile());
                    url2 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getCertId() != null)
                    updateRecord(certUpload, url1, url2);
                else
                    saveRecord(certUpload, url1, url2);
    			break;
    		case 2:
    			if(certUpload.getPoliceFrontFile() != null){
                    fileName = uploadFile(savePath, certUpload.getPoliceFrontFile());
                    url1 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getPoliceBackFile() != null){
                    fileName = uploadFile(savePath, certUpload.getPoliceBackFile());
                    url2 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getCertId() != null)
                    updateRecord(certUpload, url1, url2);
                else
                    saveRecord(certUpload, url1, url2);
    			break;
    		case 3:
    			if(certUpload.getSignatureFile() != null){
                    fileName = uploadFile(savePath, certUpload.getSignatureFile());
                    url1 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getCertId() != null)
                    updateRecord(certUpload, url1, url2);
                else
                    saveRecord(certUpload, url1, url2);
    			break;
    		case 4:
    			if(certUpload.getCachetFile() != null){
                    fileName = uploadFile(savePath, certUpload.getCachetFile());
                    url1 = "/" + UPLOAD_FILEPATH + "/" + dateName + "/" + fileName;
                }
                if(certUpload.getCertId() != null)
                    updateRecord(certUpload, url1, url2);
                else
                    saveRecord(certUpload, url1, url2);
    			break;
    	}
    	result.setStatus(1);
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
    public String uploadFile(String savePath, FileBean uploadFile){
        //文件名转换
        String originName = uploadFile.getFileName();
        String fileName = rename(originName);
        //文件所在的路径
        String source = savePath + "/" + fileName;
        try{
            System.out.println("---上传证件开始---");
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
            System.out.println("---上传证件结束---");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("---上传证件失败---");
        }

        return fileName;
    }
    
    public void saveRecord(CertUpload certUpload, String url1, String url2){
    	Cert cert = new Cert();
    	cert.setCertName(certUpload.getCertName());
    	cert.setCertType(certUpload.getCertType());
    	cert.setCertUrl1(url1);
    	cert.setCertUrl2(url2);
    	cert.setCreateTime(new Date());
    	cert.setScbz("0");
        certService.saveCert(cert);
    }

    public void updateRecord(CertUpload certUpload, String url1, String url2){
        Cert record = certService.get(certUpload.getCertId());
        record.setCertName(certUpload.getCertName());
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
