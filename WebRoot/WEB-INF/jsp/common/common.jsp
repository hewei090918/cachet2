<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!-- 以图标的方式区别网站，图标会在浏览器标题前面展示 -->
  	<link rel="icon" href="<%=basePath%>assets/favicon.ico">
  	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/plugins/bootstrap/3.3.6/css/bootstrap.min.css">
	<!-- Bootstrap Validator CSS -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/plugins/bootstrapValidator/dist/css/bootstrapValidator.min.css">
	<!-- cropper css-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/plugins/jquery-cropper/dist/css/cropper.min.css">
	<!-- loading css -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/plugins/jquery-loading/dist/css/loading.css">
	
	<!-- jQuery -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap JS -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<!-- Bootstrap Validator JS -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
	<!-- cropper js -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/jquery-cropper/dist/js/cropper.js"></script>
	<!-- uploadPreview -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/jquery-uploadPreview/js/uploadPreview.js"></script>
	<!-- loading js -->
	<script type="text/javascript" src="<%=basePath%>assets/plugins/jquery-loading/dist/js/loading.js"></script>
	
  </head>
  
  <body>
  </body>
</html>
