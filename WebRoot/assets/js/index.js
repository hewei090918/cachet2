$(function() {
	
	var uploadForm = '#uploadForm';

    //设置首页边框样式（宽度和高度）
    var outerWidth = parseFloat($('.outer').css("width"));
    $('.outer').css("height", 0.41*outerWidth);

    var innerWidth = parseFloat($('.inner').css("width"));
    var innerHeight = 0.62*innerWidth;
    $('.inner').css("height", innerHeight);

    //设置首页默认图片显示位置
    $('.showFront').css("top", 0.25*innerHeight);
    $('.showBack').css("top", 0.25*innerHeight);
    $('.showOne').css("top", 0.25*innerHeight);
    
    var $img;
    var ifEdit = false;
    
    //初始化主界面
    $.ajax({
        type: 'POST',
        url: base + '/cert/queryAll.html',
        success: function (data) {
        	var result = eval('(' + data + ')');
            if(result.status == 1){
                var data = result.data;
                if(data.length > 0){
                    var arr = new Array();
                    //先整合首页需要展示的所有框框
                    for(var i in data){
                        var type = data[i].certType;
                        switch (type){
                            case 1:
                                arr.push(createObj(data[i].certId, data[i].certType, data[i].certUrl1, data[i].certUrl2, data[i].certName));
                                $('#showDiv').append(showDiv('showDouble', data[i].certId));
                                if(i == data.length-1) $('#showDouble').find('.outer').removeAttr('id');
                                break;
                            case 2:
                                arr.push(createObj(data[i].certId, data[i].certType, data[i].certUrl1, data[i].certUrl2, data[i].certName));
                                $('#showDiv').append(showDiv('showDouble', data[i].certId));
                                if(i == data.length-1) $('#showDouble').find('.outer').removeAttr('id');
                                break;
                            case 3:
                                arr.push(createObj(data[i].certId, data[i].certType, data[i].certUrl1, data[i].certUrl2, data[i].certName));
                                $('#showDiv').append(showDiv('showSingle', data[i].certId));
                                if(i == data.length-1) $('#showSingle').find('.outer').removeAttr('id');
                                break;
                            case 4:
                                arr.push(createObj(data[i].certId, data[i].certType, data[i].certUrl1, data[i].certUrl2, data[i].certName));
                                $('#showDiv').append(showDiv('showSingle', data[i].certId));
                                if(i == data.length-1) $('#showSingle').find('.outer').removeAttr('id');
                                break;
                        }
                    }

                    $('#showDouble').remove();
                    $('#showSingle').remove();

                    //在每个框框里填充原始图片
                    for(var i=0; i < arr.length; i++){
                        var type = arr[i].type;
                        if(type == 1 || type == 2){
                            if(arr[i].url1){
                                $('#targetId_' + arr[i].id).find('.showFront').replaceWith("<img name='aa'/>");
                                var src = '/' + arr[i].url1;
                                $img = $('#targetId_' + arr[i].id).find('img[name="aa"]');
                                $img.attr('src', src);
                            }
                            if(arr[i].url2){
                                $('#targetId_' + arr[i].id).find('.showBack').replaceWith("<img name='bb'/>");
                                var src = '/' + arr[i].url2;
                                $img = $('#targetId_' + arr[i].id).find('img[name="bb"]');
                                $img.attr('src', src);
                            }
                        } else if(type == 3 || type == 4){
                            if(arr[i].url1){
                                $('#targetId_' + arr[i].id).find('.showOne').replaceWith("<img name='cc'/>")
                                var src = '/' + arr[i].url1;
                                $img = $('#targetId_' + arr[i].id).find('img[name="cc"]');
                                $img.attr('src', src);
                            }
                        }

                        $('#targetId_' + arr[i].id).find('.spanText').text(arr[i].name?arr[i].name:'（无名称展示）');
                    }

                    //延迟一点时间后，调整每个框框中图片的大小和位置
                    setTimeout(function () {
                        for(var i=0; i < arr.length; i++){
                            var type = arr[i].type;
                            if(type == 1 || type == 2){
                                if(arr[i].url1){
                                    $img = $('#targetId_' + arr[i].id).find('img[name="aa"]');
                                    editActive($img.parent(), arr[i].id);//显示悬浮编辑栏
                                    ifEdit = false;
                                    autoScaling();

                                }
                                if(arr[i].url2){
                                    $img = $('#targetId_' + arr[i].id).find('img[name="bb"]');
                                    editActive($img.parent(), arr[i].id);//显示悬浮编辑栏
                                    ifEdit = false;
                                    autoScaling();

                                }

                            }else if(type == 3 || type == 4){
                                if(arr[i].url1){
                                    $img = $('#targetId_' + arr[i].id).find('img[name="cc"]');
                                    editActive($img.parent(), arr[i].id);//显示悬浮编辑栏
                                    ifEdit = false;
                                    autoScaling();

                                }
                            }

                        }
                    }, 300);

                }

            }
        },
        error: function () {

        }
    });
    
    //上传框初始页面
    var initHtml = getParentHtml('idCard_upload');
    var editMap = {};

    function editActive(parent, targetId){
        parent.append('<div class="file_bar" style="width:' + (innerWidth-2) + 'px;left: 32px;"><div style="padding:5px;"><span class="file_edit" data-target="' + targetId + '" title="编辑"></span></div></div>');
        parent.hover(
            function (e) {
                $(this).find(".file_bar").addClass("file_hover");
                //绑定编辑按钮
                if($(".file_edit").length>0){
                    // 编辑方法
                    $(".file_edit").click(function() {
                        var editCertId = $(this).attr("data-target");
                        $.ajax({
                            type: 'POST',
                            url: base + "/cert/load.html",
                            data: {certId: editCertId},
                            success: function (data) {
                            	var result = eval('(' + data + ')');
                                if(result.status == 1){
                                    var data = result.data;
                                    var type = data.certType;

                                    $('#addArea').html(initHtml);

                                    //初始化单选按钮的状态（默认将无关的单选按钮设置为禁选）
                                    var radioBtns = $('#uploadForm').find('input[type="radio"]');
                                    for(var i in radioBtns){
                                        var typeVal = radioBtns[i].value;
                                        var _this = $('#uploadForm').find('input[type="radio"][value="' + typeVal + '"]');
                                        if(typeVal == type)
                                            _this.prop("checked", true);
                                        else
                                            _this.attr("disabled", true);
                                    }
                                    $('#uploadForm').find('input[type="radio"][value="' + type + '"]').removeAttr("disabled");

                                    //加载图片
                                    loadImg(type, data);

                                    $("#cachet_modal").modal({show:true, backdrop: 'static'});

                                }
                            },
                            error: function () {

                            }
                        });
                    });
                }
            },function (e) {
                $(this).find(".file_bar").removeClass("file_hover");
            }
        );
    }

    function loadImg(type, data){
        setUploadArea(850, 355);

        switch(type){
            case 1:
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#police_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#idCard_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#idCard_upload').show();
                var divIds = ["idCard_front", "idCard_back"];
                var fileIds = ["fileImage_idCard_front", "fileImage_idCard_back"];
                initEdit(divIds, fileIds, "idCard_upload", data);
                break;
            case 2:
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#police_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#police_upload').show();
                var divIds = ["police_front", "police_back"];
                var fileIds = ["fileImage_police_front", "fileImage_police_back"];
                initEdit(divIds, fileIds, "police_upload", data);
                break;
            case 3:
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#cachet_upload').hide();

                $('#signature_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#signature_upload').show();
                var divIds = ["signature_all"];
                var fileIds = ["fileImage_signature"];
                initEdit(divIds, fileIds, "signature_upload", data);
                break;
            case 4:
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#signature_upload').hide();

                $('#cachet_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#cachet_upload').show();

                var divIds = ["cachet_all"];
                var fileIds = ["fileImage_cachet"];
                initEdit(divIds, fileIds, "cachet_upload", data);
                break;
        }
    }

    function initEdit(divIds, fileIds, parentId, data){
        for(var i in divIds){
            var divId = divIds[i];
            editMap[divId] = getParentHtml(divId);
        }

        if(divIds.length == 2){
            if(data.certUrl1){
                $('#' + divIds[0]).replaceWith("<img name='aa'/>");
                var url1 = base + '/' + data.certUrl1;
                $('#' + parentId).find('img[name="aa"]').attr('src', url1);
            }
            if(data.certUrl2){
                $('#' + divIds[1]).replaceWith("<img name='bb'/>");
                var url2 = base + '/' + data.certUrl2;
                // console.log("url2 = " + data.cachetUrl2);
                $('#' + parentId).find('img[name="bb"]').attr('src', url2);
            }
        }else if(divIds.length == 1){
            if(data.certUrl1){
                $('#' + divIds[0]).replaceWith("<img name='cc'/>");
                var url1 = base + '/' + data.certUrl1;
                $('#' + parentId).find('img[name="cc"]').attr('src', url1);
            }

        }

        for(var i in fileIds){
            var imgUrl = $('#' + fileIds[i]).parent().children('img').attr('src');
            if(imgUrl){
                $('#' + fileIds[i]).parent().append('<div class="file_bar" style="width: 358px;left: 40px;"><div style="padding:5px;"><span class="file_del" data-target="' + fileIds[i] + '" data-key="' + divIds[i] + '"title="删除"></span></div></div>');
                $('#' + fileIds[i]).parent().hover(function (e) {
                    $(this).find(".file_bar").addClass("file_hover");
                    //绑定删除按钮
                    if($(".file_del").length>0){
                        // 删除方法
                        $(".file_del").click(function() {
                            var delFileId = $(this).attr("data-target");
                            // console.log("targetId = " + targetId);
                            var key = $(this).attr("data-key");
                            var defaultHtml = editMap[key];
                            $('#' + delFileId).parent().html(defaultHtml);
                        });
                    }
                },function (e) {
                    $(this).find(".file_bar").removeClass("file_hover");
                });
            }

        }

        //调整图片位置和大小
        setTimeout(function () {
            $img = $('#' + parentId).find('img');
            ifEdit = true;
            autoScaling();
        }, 300);

        $('#certId').val(data.certId);
        $('#' + parentId).find('input[name="certName"]').val(data.certName);
    }

    function autoScaling(){
        if (!$.support.leadingWhitespace)//检查是否为IE6-8
            $img.get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = "image";
        var img_width = $img.width();
        var img_height = $img.height();
        if(img_width > 0 && img_height > 0){
            var div_width = ifEdit?355:innerWidth;
            var div_height = ifEdit?218:innerHeight;
            var rate = (div_width / img_width < div_height / img_height) ? div_width / img_width : div_height / img_height;
            if(rate < 1){
                if (!$.support.leadingWhitespace)
                    $img.get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = "scale";
                $img.width(img_width * rate);
                $img.height(img_height * rate);
            }else{
                $img.width(img_width);
                $img.height(img_height);
            }
            var left = (div_width - $img.width()) * 0.5;
            var top = (div_height - $img.height()) * 0.5;
            $img.css({ "margin-left": left, "margin-top": top });
            $img.show();
        }

    }

    $('#delete_button').on('click', function() {
        var ids = [];
        var cbox = $(':checkbox:checked');
        for(var i=0; i<cbox.length; i++){
            ids.push(cbox[i].id);
        }
        var certIds = ids.join();
        loading('body');
        $.ajax({
            type: "POST",
            url: base + "/cert/delete.html",
            data: {certIds: certIds},
            success: function (data) {
            	var result = eval('(' + data + ')');
                if(result.status == 1){
                    removeLoading('waiting');
                    parent.location.reload();
                }
            }
        });
    });
    
    $('#create_button').on('click', function () {
        setTimeout(function () {
            var outerUploadWidth = parseFloat($('.outer_upload').css("width"));
            $('.outer_upload').css("height", 0.41*outerUploadWidth);

            var innerUploadWidth = parseFloat($('.inner_upload').css("width"));
            var innerUploadHeight = 0.62*innerUploadWidth;
            $('.inner_upload').css("height", innerUploadHeight);

            $('.front').css("top", 0.25*innerUploadHeight);
            $('.back').css("top", 0.25*innerUploadHeight);
            $('.both').css("top", 0.25*innerUploadHeight);

            $('.title').css("top", 0.32*innerUploadHeight);
            $('.title').css("left", 0.35*innerUploadWidth);
        }, 300);

        $('input[name="certType"]').on('click', function () {
            $('#addArea').html(initHtml);

            var type = $(this).attr('value');
            typeChecked(type);

            //初始化单选按钮的状态（默认全部可选）
            var radioBtns = $('#uploadForm').find('input[type="radio"]');
            for(var i in radioBtns){
                var typeVal = radioBtns[i].value;
                if(radioBtns[i].disabled)
                    $('#uploadForm').find('input[type="radio"][value="' + typeVal + '"]').removeAttr("disabled");
            }
        });

        $('#idCard').click();//默认选中身份证

        $("#cachet_modal").modal({show:true, backdrop: 'static'});
    });
    
    function typeChecked(type){
        var outerUploadWidth = parseFloat($('.outer_upload').css("width"));
        var innerUploadWidth = parseFloat($('.inner_upload').css("width"));
        setTimeout(setUploadArea(outerUploadWidth, innerUploadWidth), 100);
        switch(type){
            case '1':
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#police_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#idCard_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#idCard_upload').show();
                break;
            case '2':
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#police_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#police_upload').show();
                break;
            case '3':
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#cachet_upload').hide();

                $('#signature_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#signature_upload').show();
                break;
            case '4':
                $('#addArea').find('input[name="certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#signature_upload').hide();

                $('#cachet_upload').find('input[name="certName"]').removeAttr('disabled');

                $('#cachet_upload').show();
                break;
        }
    }

    //设置证件上传边框宽度、高度，调整默认图片和文字显示位置
    function setUploadArea(outerUploadWidth, innerUploadWidth) {

        $('.outer_upload').css("height", 0.41*outerUploadWidth);

        var innerUploadHeight = 0.62*innerUploadWidth;
        $('.inner_upload').css("height", innerUploadHeight);

        $('.front').css("top", 0.25*innerUploadHeight);
        $('.back').css("top", 0.25*innerUploadHeight);
        $('.both').css("top", 0.25*innerUploadHeight);

        $('.title').css("top", 0.32*innerUploadHeight);
        $('.title').css("left", 0.35*innerUploadWidth);

    }
    
    //摸态框关闭事件
    $("#cachet_modal").on('hide.bs.modal', function () {
        if(!$('#fileSubmit').attr('disabled')) $('#fileSubmit').attr('disabled', 'disabled');
    });
	
});

var fns = new Array();
var blobMap = {};
var arr = new Array(6);

function fileUpload(div) {

    $('input[name="certType"]').on('click', function () {
        fns = new Array();
        blobMap = {};
    });

    var type = $(div).attr('data-type');
    switch(type){
        case 'idCard_front'://身份证正面
            $('#fileImage_idCard_front').click();
            preview('idCard_front', 'fileImage_idCard_front', 0);
            break;
        case 'idCard_back'://身份证反面
            $('#fileImage_idCard_back').click();
            preview('idCard_back', 'fileImage_idCard_back', 1);
            break;
        case 'police_front'://警官证正面
            $('#fileImage_police_front').click();
            preview('police_front', 'fileImage_police_front', 2);
            break;
        case 'police_back'://警官证反面
            $('#fileImage_police_back').click();
            preview('police_back', 'fileImage_police_back', 3);
            break;
        case 'signature_all'://电子签名
            $('#fileImage_signature').click();
            preview('signature_all', 'fileImage_signature', 4);
            break;
        case 'cachet_all'://电子公章
            $('#fileImage_cachet').click();
            preview('cachet_all', 'fileImage_cachet', 5);
            break;
    }
}

function preview(divId, fileId, insertIndex){
    var fileInput = "#" + fileId;
    var $uploadDiv = $(fileInput).parent('.inner_upload');
    arr.splice(insertIndex, 1, getParentHtml(divId));

    // Preview image
    var imgDiv = '#' + divId;
    $(fileInput).uploadPreview({
        width: 354,
        height: 218,
        imgDiv: imgDiv
    });

    // Import image
    $(fileInput).change(function () {
        var files = this.files;
        var _this = $(this);
        // var index = _this.attr("data-index");
        var fn = _this.attr("name");
        //剔除重复后保存（避免出现多次保存同一类型同一照面证件的情况）
        var flag = false;
        if(fns.length > 0){
            for(var i in fns){
                if(fn == fns[i]){
                    flag = true;//说明存在重复值!
                    break;
                }
            }
        }
        if(!flag) fns.push(fn);

        loading($uploadDiv);

        //加载完图片后显示裁剪框
        setTimeout(function () {
            removeLoading('waiting');
            var $image = $uploadDiv.children('img');
            $image.cropper({
                checkImageOrigin: true,
                aspectRatio: 177 / 109,
                autoCropArea: 0.5,
                viewMode: 1,
                modal: false
            });

            if(files && files.length){
                var cutBtn = $uploadDiv.siblings('.jy-up-ch').children('a[data-ng-click="cutBtn"]');
                //给裁剪按钮注册点击事件
                cutBtn.on('click', function () {
                    var canvas = $image.cropper("getCroppedCanvas");
                    //这里转成base64 image，img的src可直接使用
                    var imgUrl = canvas.toDataURL("image/png", 1.0);
                    // var imgUrl = canvas.toDataURL("image/jpeg", 1.0);
                    // console.log(imgUrl);
                    var blob = convertBase64UrlToBlob(imgUrl);
                    blobMap[fn] = blob;

                    loading($uploadDiv);

                    setTimeout(function(){
                        removeLoading('waiting');

                        //显示悬浮删除栏
                        if(_this.val()){
                            $image.cropper("destroy");
                            //裁剪后预览图片
                            $image.attr("src", imgUrl);
                            $image.css("width", 355);
                            $image.css("height", 218);
                            $image.css("margin-left", 0);
                            $image.css("margin-top", 0);

                            $uploadDiv.append('<div class="file_bar" style="width: 358px;left: 40px;"><div style="padding:5px;"><span class="file_del" data-target="' + fileId + '" data-index="' + insertIndex + '" title="删除"></span></div></div>');
                            $uploadDiv.hover(
                                function (e) {
                                    $(this).find(".file_bar").addClass("file_hover");
                                    //绑定删除按钮
                                    if($(".file_del").length > 0){
                                        // 删除方法
                                        $(".file_del").click(function() {
                                            var delFileId = $(this).attr("data-target");
                                            var index = $(this).attr("data-index");
                                            // console.log("insertIndex = " + index);
                                            var defaultHtml = arr[index];
                                            // console.log(defaultHtml);
                                            $('#' + delFileId).parent().html(defaultHtml);

                                            if($(".file_del").length == 0) $('#fileSubmit').attr('disabled', true);
                                        });
                                    }
                                },function (e) {
                                    $(this).find(".file_bar").removeClass("file_hover");
                                }
                            );

                            //激活上传按钮并绑定点击事件
                            if($('#fileSubmit').attr('disabled') == 'disabled'){
                                $('#fileSubmit').removeAttr('disabled');
                                // $('#fileSubmit').click(function () {
                                //
                                // });
                            }

                        }

                    },3000);
                });
            }
        }, 3500);
    });

}

function beginUpload(){
    $('#fileSubmit').removeAttr('disabled');
    var fd = new FormData($('#uploadForm')[0]);
    var certId = fd.get("certId");
    var certType = fd.get("certType");
    if(!certId || certId == ''){// 新增
        if(certType == 4){// 上传公章（一个用户只能上传一次公章）
            var flag = ifExist();
            if(flag) {
                $.notify("<em class='fa fa-check'></em> 公章只能上传一次!", {status: "warning"});
                return false;
            }
        }
    }

    for(var i in fns){
        var key = fns[i];
        fd.append("'" + key + "'", blobMap[key]);
    }
    
    var xhr = new XMLHttpRequest();
    xhr.open('POST', base + "/cert/upload.html", true);
    xhr.send(fd);

    $("#cachet_modal").modal('hide');
    parent.location.reload();
}

function ifExist(){
    var count = 0;
    $.ajax({
        type: 'POST',
        async : false,//必选
        url: "",
        success: function (data) {
            if(data.status == 1){
                var data = data.data;
                for(var i in data){
                    if(data[i].certType == 4){
                        count++;
                    }
                }
            }
        }
    });
    if(count > 0)
        return true;
    else
        return false;

}

function loading(uploadDiv){
    $(uploadDiv).loading({
        loadingWidth:120,
        title:'',
        name:'waiting',
        discription:'',
        direction:'column',
        type:'origin',
        originBg:'#71EA71',
        originDivWidth:80,
        originDivHeight:80,
        originWidth:10,
        originHeight:10,
        smallLoading:false,
        loadingBg:'transparent',
        flexCenter: true
    });
}
//base64转成blob
function convertBase64UrlToBlob(urlData){
    var bytes = window.atob(urlData.split(',')[1]);//去掉url的头，并转换为byte
    //处理异常,将ascii码小于0的转换为大于0
    var ab = new ArrayBuffer(bytes.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }
    return new Blob( [ia], {type : 'image/png'});

}

//左旋转
function rotateLeft(btn){
    var $image = $(btn).parent().siblings('.inner_upload').children('img');
    $image.cropper('rotate', -90);
}

//右旋转
function rotateRight(btn){
    var $image = $(btn).parent().siblings('.inner_upload').children('img');
    $image.cropper('rotate', 90);
}

//缩小
function zoomOut(btn){
    var $image = $(btn).parent().siblings('.inner_upload').children('img');
    $image.cropper('zoom', -0.5);
}

//放大
function zoomIn(btn) {
    var $image = $(btn).parent().siblings('.inner_upload').children('img');
    $image.cropper('zoom', 0.5);
}

function createObj(id, type, url1, url2, name){
    var obj = new Object();
    obj.id = id;
    obj.type = type;
    obj.url1 = url1;
    obj.url2 = url2;
    obj.name = name;
    return obj;
}

function showDiv(divId, certId){
    switch (divId){
        case "showDouble":
            $('#' + divId).find('.outer').attr('id', 'targetId_' + certId);
            var item = $('#' + divId).find('.outer').children('input[name="item"]');
            item.attr('id', certId);
            item.removeAttr("disabled");
            break;
        case "showSingle":
            $('#' + divId).find('.outer').attr('id', 'targetId_' + certId);
            var item = $('#' + divId).find('.outer').children('input[name="item"]');
            item.attr('id', certId);
            item.removeAttr("disabled");
            break;
    }
    var tempDiv = $('#' + divId).clone(true);
    return tempDiv.html();
}

function selectForDelete(cb){
    if($(cb).is(':checked')){
        $('#delete_button').removeAttr('disabled');
        $('#delete_button').removeClass('btn btn-default').addClass('btn btn-success');
    }else{
        var count = 0;
        var items = $('.cb');
        for(var i=0; i<items.length; i++){
            if(items[i].checked == true) count++;
        }
        if(count == 0) {
            $('#delete_button').attr('disabled', 'disabled');
            $('#delete_button').removeClass('btn btn-success').addClass('btn btn-default');
        }
    }
}

function getParentHtml(id){
    var uploadDiv = $('#' + id).parent().clone(true);
    return uploadDiv.html();
}