const baseUrl = 'http://992443.ichengyun.net/prod-api/';

//公用get请求
function getAjax(url, params, callback) {
    $.ajax({
        url: url + (params ? '?'+params:''),
        type: "GET",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        },
        success: function (result, status, request) {
            callback(result);
        }
    });
}


//获取url参数
function getUrlParam(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}