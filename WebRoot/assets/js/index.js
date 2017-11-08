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
    
    //上传框初始页面
    var initHtml = getParentHtml('idCard_upload');
    
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

        $('input[name="cert.certType"]').on('click', function () {
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
            case "1":
                $('#addArea').find('input[name="cert.certName"]').attr('disabled',true);
                $('#police_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#idCard_upload').find('input[name="cert.certName"]').removeAttr('disabled');

                $('#idCard_upload').show();
                break;
            case "2":
                $('#addArea').find('input[name="cert.certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#signature_upload').hide();
                $('#cachet_upload').hide();

                $('#police_upload').find('input[name="cert.certName"]').removeAttr('disabled');

                $('#police_upload').show();
                break;
            case "3":
                $('#addArea').find('input[name="cert.certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#cachet_upload').hide();

                $('#signature_upload').find('input[name="cert.certName"]').removeAttr('disabled');

                $('#signature_upload').show();
                break;
            case "4":
                $('#addArea').find('input[name="cert.certName"]').attr('disabled',true);
                $('#idCard_upload').hide();
                $('#police_upload').hide();
                $('#signature_upload').hide();

                $('#cachet_upload').find('input[name="cert.certName"]').removeAttr('disabled');

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
	
});

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

function getParentHtml(id){
    var uploadDiv = $('#' + id).parent().clone(true);
    return uploadDiv.html();
}