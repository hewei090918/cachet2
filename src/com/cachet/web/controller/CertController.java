package com.cachet.web.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cachet.common.bean.Result;
import com.cachet.common.properies.PropertiesItems;
import com.cachet.common.util.StringUtil;
import com.cachet.web.model.Cert;
import com.cachet.web.service.CertService;

@Controller
@RequestMapping("/cert")
public class CertController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	//文件保存目录路径
    public String uploadFilePath = "upload";
	
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
    @ResponseBody String upload(HttpServletRequest request,HttpServletResponse response) {
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
        Map<String, String> map = new HashMap<>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查输入请求是否为multipart表单数据
        if (isMultipart == true) {   // 如果是二进制流形式的表单
        	logger.info("---上传证件开始---");
        	FileItemFactory factory = new DiskFileItemFactory();// 通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = null;
            File savedFile=null;
            try {
                items = upload.parseRequest(request);
                Iterator<FileItem> itr = items.iterator();
                while (itr.hasNext()) {
                	FileItem item = (FileItem) itr.next();
                	if (!item.isFormField()) { //如果提交的是图片
                		if(StringUtil.isNotNullOrEmpty(item.getName())){
                			System.out.println("item name = " + item.getName());
                			File fullFile = new File(item.getName()); //获取提交的文件
                       	 	String originName = fullFile.getName();
                            fileName = rename(originName);
                            savedFile = new File(savePath, fileName); //在项目下新建该文件
                            
                            if(StringUtil.equals(item.getName(), "blob")) {//获取截图路径
                            	String fName = item.getFieldName();
                            	System.out.println("fieldName = " + fName);
                            	if(StringUtil.equals(fName, "\'idCardBackFile\'") || StringUtil.equals(fName, "\'policeBackFile\'")) {
                            		url2 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                            	}else {
                            		url1 = "/" + uploadFilePath + "/" + dateName + "/" + fileName;
                            	}
                            }
                    		
                            try {
                            	item.write(savedFile); //写入文件
                            } catch (Exception e) {
                            	logger.error("---上传证件失败---");
                            	e.printStackTrace();
                            }
                		}
                        
                    }else { //检测是否为普通表单 如果是普通表单项目，将其名字与对应的value值放入map中
                    	 try {
                    		String fieldName = item.getFieldName();
							map.put(fieldName, item.getString("UTF-8")); //获取表单value时确定数据格式，如果不写，有中文的话会乱码
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
                    } 
                }
                
                
            } catch (FileUploadException e) {
                e.printStackTrace();
            } 
            logger.info("---上传证件结束---");
        }
        String certId = map.get("certId");
        if(StringUtil.isNotNullOrEmpty(certId)) {
        	this.updateRecord(map, url1, url2);
        }else {
        	this.saveRecord(map, url1, url2);
        }
    	result.setStatus(1);
    	String ss = JSONObject.fromObject(result).toString();
    	return ss;
    }
    
//    public String uploadFile(String savePath, MultipartFile uploadFile){
//        //文件名转换
//        String originName = uploadFile.getName();
//        String fileName = rename(originName);
//        //文件所在的路径
//        String source = savePath + "/" + fileName;
//        
//        try{
//        	logger.info("---上传证件开始---");
//            InputStream inputStream = uploadFile.getInputStream();
//            OutputStream outputStream = new FileOutputStream(new File(source));
//
//            byte[] bt = new byte[1024];
//            int len = 0;
//            while ((len = inputStream.read(bt)) != -1) {
//                outputStream.write(bt, 0, len);
//            }
//            // 关闭流
//            outputStream.close();
//            inputStream.close();
//            logger.info("---上传证件结束---");
//        }catch (IOException e){
//            e.printStackTrace();
//            logger.error("---上传证件失败---");
//        }
//
//        return fileName;
//    }
    
    public void saveRecord(Map<String, String> certMap, String url1, String url2){
    	Cert cert = new Cert();
    	cert.setCertName(certMap.get("certName"));
    	cert.setCertType(Integer.parseInt(certMap.get("certType")));
    	cert.setCertUrl1(url1);
    	cert.setCertUrl2(url2);
    	cert.setCreateTime(new Date());
    	cert.setScbz("0");
        certService.saveCert(cert);
    }

    public void updateRecord(Map<String, String> certMap, String url1, String url2){
        Cert record = certService.get(Integer.parseInt(certMap.get("certId")));
        record.setCertName(certMap.get("certName"));
        if(StringUtil.isNotNullOrEmpty(url1))
            record.setCertUrl1(url1);
        if(StringUtil.isNotNullOrEmpty(url2))
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
