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
    <div class="panel panel-default">
            <div class="panel-body bb">
                <span style="float:right;margin-right: 10px;">
                    <button class="btn btn-primary" data-toggle="modal" id="create_button"><i class="fa fa-plus"></i> 新增证件</button>&nbsp;&nbsp;
                    <button class="btn btn-default" data-toggle="modal" id="delete_button" disabled><i class="fa fa-remove"></i> 删除</button>
                </span>
            </div>
            <div class="panel-body" id="showDiv">
                <div id="showDouble">
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="outer">
                            <input type="checkbox" class="cb" name="item" onclick="" style="zoom:180%;" disabled/>
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
                            <input type="checkbox" class="cb" name="item" onclick="" style="zoom:180%;" disabled/>
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
                                                        <div class="front" data-type="idCard_front" onclick=""></div>
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
                                                        <div class="back" data-type="idCard_back" onclick=""></div>
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
                                                        <div class="front" data-type="police_front" onclick=""></div>
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
                                                        <div class="back" data-type="police_back" onclick=""></div>
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
                                                        <div class="both" data-type="signature_all" onclick=""></div>
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
                                                        <div class="both" data-type="cachet_all" onclick=""></div>
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
                            <button type="button" id="fileSubmit" class="btn btn-success" onclick="" disabled>开始上传</button>
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
