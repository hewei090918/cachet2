<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>公章管理平台</title>
    <jsp:include page="common/common.jsp" />
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/css/index.css">
	<!-- JS -->
	<script type="text/javascript" src="<%=basePath%>assets/js/index.js"></script>
	
  </head>
  <body>
  	<nav class="navbar navbar-masthead navbar-inverse tour-step" data-placement="bottom">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">Brand</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
	        <li><a href="#">Link</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="navbar-form navbar-left">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">Submit</button>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="#">Link</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
  	<div class="page-header tour-step" data-placement="bottom">
  	</div>
  	<div class="container-fluid tour-step">
	  	 <div class="panel panel-default">
	         <div class="panel-body">
	             <span style="float:right;margin-right: 10px;">
	                 <button class="btn btn-primary" data-toggle="modal" id="create_button"><i class="fa fa-plus"></i> 新增证件</button>&nbsp;&nbsp;
	                 <button class="btn btn-default" data-toggle="modal" id="delete_button" disabled><i class="fa fa-remove"></i> 删除</button>
	             </span>
	         </div>
	         <div class="panel-body" id="showDiv">
	             <div id="showDouble">
	                 <div class="col-sm-6 col-md-6 col-lg-4">
	                     <div class="outer">
	                         <input type="checkbox" class="cb" name="item" onclick="selectForDelete(this)" style="zoom:180%;" disabled/>
	                         <div class="row">
	                             <div class="col-sm-6 col-md-6 col-lg-6">
	                                 <div class="inner">
	                                     <div class="showFront"></div>
	                                 </div>
	                             </div>
	                             <div class="col-sm-6 col-md-6 col-lg-6">
	                                 <div class="inner">
	                                     <div class="showBack"></div>
	                                 </div>
	                             </div>
	                         </div>
	                         <div class="row">
	                             <div class="col-sm-12 col-md-12 col-lg-12 text-center">
	                                 <span class="spanText">（无名称展示）</span>
	                             </div>
	                         </div>
	                     </div>
	                 </div>
	             </div>
	             <div id="showSingle">
	                 <div class="col-sm-6 col-md-6 col-lg-4" >
	                     <div class="outer">
	                         <input type="checkbox" class="cb" name="item" onclick="selectForDelete(this)" style="zoom:180%;" disabled/>
	                         <div class="row">
	                             <div class="col-sm-3 col-md-3 col-lg-3"></div>
	                             <div class="col-sm-6 col-md-6 col-lg-6">
	                                 <div class="inner">
	                                     <div class="showOne"></div>
	                                 </div>
	                             </div>
	                             <div class="col-sm-3 col-md-3 col-lg-3"></div>
	                         </div>
	                         <div class="row">
	                             <div class="col-sm-12 col-md-12 col-lg-12 text-center">
	                                 <span class="spanText">（无名称展示）</span>
	                             </div>
	                         </div>
	                     </div>
	                 </div>
	             </div>
	         </div>
	     </div>
  	</div>
  	<footer class="footer">
       <div class="container">
           <p class="text-muted">Copyright&copy;2017 github.com</p>
       </div>
    </footer>
  	
    <!-- modal -->
    <div class="modal fade bs-example-modal-lg" id="cachet_modal" tabindex="-1" role="dialog"
         aria-labelledby="cachet_modal_label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form id="uploadForm" method="post" action="" enctype="multipart/form-data">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">证件上传</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <label class="col-sm-2 col-md-2 control-label">证件类型:</label>
                            <div class="col-sm-10 col-md-10">
                                <label class="radio-inline col-sm-2 col-md-2">
                                    <input type="radio" name="cert.certType" id="idCard" value="1"/> 身份证
                                </label>
                                <label class="radio-inline col-sm-2 col-md-2">
                                    <input type="radio" name="cert.certType" id="police" value="2"/> 警官证
                                </label>
                                <label class="radio-inline col-sm-2 col-md-2">
                                    <input type="radio" name="cert.certType" id="signature" value="3"/> 电子签名
                                </label>
                                <label class="radio-inline col-sm-2 col-md-2">
                                    <input type="radio" name="cert.certType" id="cachet" value="4"/> 电子公章
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 col-md-12">
                                <div class="outer_upload" id="addArea">
                                    <input type="hidden" class="form-control" name="cert.certId" id="certId">
                                    <div class="row" id="idCard_upload">
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="idCard_front">
                                                    <div class="front" data-type="idCard_front" onclick="fileUpload(this);"></div>
                                                    <span class="title">身份证正面</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_idCard_front" size="30" name="idCardFrontFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="idCard_back">
                                                    <div class="back" data-type="idCard_back" onclick="fileUpload(this);"></div>
                                                    <span class="title">身份证反面</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_idCard_back" size="30" name="idCardBackFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-md-12 text-center">
                                            <div class="col-sm-2 col-md-2"></div>
                                            <div class="form-group col-sm-6 col-md-6">
                                                <label class="col-sm-4 col-md-4 control-label">证件名称:</label>
                                                <div class="col-sm-8 col-md-8">
                                                    <input type="text" class="form-control" name="cert.certName">
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-md-4"></div>
                                        </div>
                                    </div>
                                    <div class="row" id="police_upload" style="display:none;">
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="police_front">
                                                    <div class="front" data-type="police_front" onclick="fileUpload(this);"></div>
                                                    <span class="title">警官证正面</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_police_front" size="30" name="policeFrontFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="police_back">
                                                    <div class="back" data-type="police_back" onclick="fileUpload(this);"></div>
                                                    <span class="title">警官证反面</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_police_back" size="30" name="policeBackFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-md-12 text-center">
                                            <div class="col-sm-2 col-md-2"></div>
                                            <div class="form-group col-sm-6 col-md-6">
                                                <label class="col-sm-4 col-md-4 control-label">证件名称:</label>
                                                <div class="col-sm-8 col-md-8">
                                                    <input type="text" class="form-control" name="cert.certName">
                                                </div>
                                            </div>
                                            <div class="col-sm-4 col-md-4"></div>
                                        </div>
                                    </div>
                                    <div class="row" id="signature_upload" style="display:none;">
                                        <div class="col-sm-3 col-md-3"></div>
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="signature_all">
                                                    <div class="both" data-type="signature_all" onclick="fileUpload(this);"></div>
                                                    <span class="title">电子签名</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_signature" size="30" name="signatureFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 col-md-3"></div>
                                        <div class="col-sm-12 col-md-12 text-center">
                                            <div class="col-sm-3 col-md-3"></div>
                                            <div class="form-group col-sm-6 col-md-6">
                                                <label class="col-sm-3 col-md-3 control-label">证件名称:</label>
                                                <div class="col-sm-8 col-md-8">
                                                    <input type="text" class="form-control" name="cert.certName">
                                                </div>
                                            </div>
                                            <div class="col-sm-3 col-md-3"></div>
                                        </div>
                                    </div>
                                    <div class="row" id="cachet_upload" style="display:none;">
                                        <div class="col-sm-3 col-md-3"></div>
                                        <div class="col-sm-6 col-md-6">
                                            <div class="inner_upload">
                                                <div id="cachet_all">
                                                    <div class="both" data-type="cachet_all" onclick="fileUpload(this);"></div>
                                                    <span class="title">电子公章</span>
                                                </div>
                                                <input type="file" class="form-control" id="fileImage_cachet" size="30" name="cachetFile">
                                            </div>
                                            <div class="jy-up-ch">
                                                <a href="javascript:void(0);" class="bch bch1" onclick="rotateLeft(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch2" onclick="zoomOut(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch3" data-ng-click="cutBtn"> </a>
                                                <a href="javascript:void(0);" class="bch bch4" onclick="zoomIn(this);"> </a>
                                                <a href="javascript:void(0);" class="bch bch5" onclick="rotateRight(this)"> </a>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 col-md-3"></div>
                                        <div class="col-sm-12 col-md-12 text-center">
                                            <div class="col-sm-3 col-md-3"></div>
                                            <div class="form-group col-sm-6 col-md-6">
                                                <label class="col-sm-3 col-md-3 control-label">证件名称:</label>
                                                <div class="col-sm-8 col-md-8">
                                                    <input type="text" class="form-control" name="cert.certName">
                                                </div>
                                            </div>
                                            <div class="col-sm-3 col-md-3"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="fileSubmit" class="btn btn-success" onclick="return beginUpload();" disabled>开始上传</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消选择</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div>
    </div>

    <style type="text/css">
        .outer{
            border:1px solid #486860;
            width: 98%;
            margin: 10px;
        }
        .inner{
            border:1px dashed #00B7EE;
            width: 88%;
            margin: 10px auto;
        }
        .outer_upload{
            border:2px solid #486860;
            width: 98%;
            margin: 10px;
            border-radius: 5px;
        }
        .inner_upload{
            border:2px dashed #38b2bf;
            width: 88%;
            margin: 10px auto;
        }
        .cb{
		    position:absolute;
		    top: -2px;
		    left: 0;
		    z-index:99;
		}
        .jy-up-ch{
            position: relative;
            width: 88%;
            height: 38px;
            margin: -8px auto 10px auto;
            border: 1px dashed #38b2bf;
            border-radius: 10px;
        }
    </style>
   </body>
</html>
