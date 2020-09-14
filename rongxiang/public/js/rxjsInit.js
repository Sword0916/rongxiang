//获取招标公告的二级导航
function getZbggDh() {
    var url = baseUrl + 'api/dict/list';
    var params = 'dictType=bus_bid_type';
    getAjax(url,params,function (result){
        for (let i in result) {
            $("#zbggDH").append('<li> <a  target="_self" href="zbggArticle.html?bidType=' + result[i].dictValue + '">' + result[i].dictLabel + '</a></li>')

        }
    });
}

//获取建筑篇章的二级导航
function getJzpzDh() {
    var url = baseUrl + 'api/dict/list';
    var params = 'dictType=bus_construct_type';
    getAjax(url,params,function(result){
        for (let i in result) {
            $("#jzpzDH").append('<li><a  target="_self" href="jzpzArticle.html?conType=' + result[i].dictValue + '">' + result[i].dictLabel + '</a></li>')
        }
    });
}

//获取文章列表
function getArticleList(articleType) {
    var url = baseUrl + 'api/article/list';
    var params = 'pageNum=1&pageSize=10&articleType='+articleType;
    getAjax(url,params,function (result) {
        var code = result.code;
        var msg = result.msg;
        var rows = result.rows;
        if(code == 200){
            if(articleType == 'jtxw'){
                var str = "";
                rows.forEach((row)=>{
                    str += "<li style='padding:32px 0;border-bottom: 1px solid #dedede;'>" +
                        "<a href='articleDetail.html?articleType="+articleType+"&articleId="+ row.articleId+"'>" +
                        "<div style='display: flex; justify-content: space-between;'>" +
                        "<h2>"+ row.articleTitle +"</h2>" +
                        "<span>"+row.createTime +"</span>" +
                        "</div>" +
                        "<p style='margin-top:8px'>"+ row.articleSummary+"</p>" +
                        "</a>" +
                        "</li>";
                });
                $('#articleList').html(str);
            }else{
                window.location.href= 'articleDetail.html?articleType='+articleType+'&articleId='+rows[0].articleId;
            }
        }
    })
}

//获取文章详情
function getArticleDetail(articleId) {
    var url = baseUrl + 'api/article/' + articleId;
    getAjax(url,null,function(result){
        var code = result.code;
        var msg = result.msg;
        var res = result.data;
        if(code == 200){
            $('#articleTitle').append(res.articleTitle);
            $('#articleCont').html(res.articleContent);
        }
    })
}

//获取招标公告列表
function getBidList(bidType,showDetail) {
    var url = baseUrl + 'api/bid/list';
    var params = 'pageNum=1&pageSize=5&bidType='+ (bidType||'');
    getAjax(url,params,function (result) {
        var code = result.code;
        var msg = result.msg;
        var rows = result.rows;
        if(code == 200){
            if(showDetail){
                getBidDetail(rows[0].bidId);
            }else{
                var str = "";
                rows.forEach((row)=>{
                    str += "<div class='e_box e_ListBox-001 p_articles' data-dismoveable data-foreachitem>" +
                                "<div class='e_box e_box-000 p_content'>" +
                                    "<div class='e_box e_box-000 p_header'>" +
                                        "<div class='e_box e_box-000 p_TitleBox'>" +
                                            "<a href='zbggArticle.html?bidId="+row.bidId+"' class='e_link e_link-000 p_LinkC' target='_self'>" +
                                                "<h3 class='e_title e_DecTitle-001 p_title'>" +
                                                    "<div class='font'><i class='iconfont'></i>"+row.bidTitle+"</div>" +
                                                "</h3>" +
                                            "</a>" +
                                        "</div>" +
                                        "<div class='e_box e_box-000 p_assist'>" +
                                            "<div class='e_box e_box-000 p_TimeBox '>" +
                                                "<div class='e_title e_AssistName-001 p_AssistNameA '>" +
                                                    "<div class='font'><i class='iconfont'></i><span class='i_pubdate'>发布时间：</span></div>" +
                                                "</div>" +
                                                "<div class='e_title e_AssistInfo-001 p_time'>" +
                                                    "<div class='font'><i class='iconfont'></i> "+row.createTime+"</div>" +
                                                "</div>" +
                                            "</div>" +
                                        "</div>" +
                                    "</div>" +
                                    "<div class='e_box e_box-000 p_SummaryBox'>" +
                                        "<article class='e_article e_summary-001 p_summary'>" + (row.bidSummary||'') +"</article>" +
                                    "</div>" +
                                    "<div class='e_box e_box-000 p_about'></div>" +
                                    "<a class='e_link e_link-000 p_LinkF' href='news/647.html' title='点击查看详情' target='_self'></a>" +
                                "</div>" +
                            "</div>" +
                            "<div class='e_line e_ListHline-001 p_ListHline' data-dismoveable data-foreachitem></div>"
                });
                $('#zbggList').html(str);
            }
        }
    })
}
//获取招标详情
function getBidDetail(bidId){
    var url = baseUrl + 'api/bid/' + bidId;
    getAjax(url,null,function (result) {
        var code = result.code;
        var msg = result.msg;
        var res = result.data;
        if(code == 200){
            $('#bidId').val(res.bidId);
            $('#articleTitle').append(res.bidTitle)
            $('#articleCont').html(res.bidContent);
        }
    })
}
//获取建筑篇章列表
function getConstructList(conType,showDetail) {
    var url = baseUrl + 'api/construct/list';
    var params = 'pageNum=1&pageSize=6&conType=' + (conType||'');
    getAjax(url,params,function (result) {
        var code = result.code;
        var msg = result.msg;
        var rows = result.rows;
        if(code == 200){
            if(showDetail){
                getConstructDetail(rows[0].conId);
            }else{
                var str = "";
                rows.forEach((row)=>{
                    str += "<div class='new-list'>" +
                                "<a href='jzpzArticle.html?conId="+row.conId+"'>" +
                                    "<p>" +
                                        row.conTitle +
                                        "<span style='float: right;'>"+row.createTime+"</span>" +
                                    "</p>" +
                                "</a>" +
                            "</div>"
                });
                $('#w_rbox-1514382610431').html(str);
            }
        }
    })
}
//获取建筑篇章详情
function getConstructDetail(conId) {
    var url = baseUrl + 'api/construct/' + conId;
    getAjax(url,null,function (result) {
        var code = result.code;
        var msg = result.msg;
        var res = result.data;
        if(code == 200){
            $('#articleTitle').append(res.conTitle);
            $('#articleCont').html(res.conContent);
        }
    })
}

//初始化页面
function initHtml(){
    var url = window.location.href;
    var urlIndex = url.lastIndexOf("?");
    var urlWithoutParams = urlIndex > 0 ? url.substring(0,url.lastIndexOf("?")):url;
    var routeArr = urlWithoutParams.split('/');
    var currentRoute = routeArr[routeArr.length - 1];

    if(currentRoute == 'articleList.html'){//文章列表
        var articleType = getUrlParam('articleType');
        changeBanner();
        getArticleList(articleType);
    }else if(currentRoute == 'articleDetail.html'){//文章详情
        var articleId = getUrlParam('articleId');
        changeBanner();
        getArticleDetail(articleId);
    }else if(currentRoute == 'zbggArticle.html'){//招标详情
        var bidType = getUrlParam('bidType');
        var bidId = getUrlParam('bidId');
        if(bidType){
            getBidList(bidType,true);
        }else if(bidId){
            getBidDetail(bidId);
        }
    }else if(currentRoute == 'jzpzArticle.html'){//建筑篇章详情
        var conType = getUrlParam('conType');
        var conId = getUrlParam('conId');
        if(conType){
            getConstructList(conType,true);
        }else if(conId){
            getConstructDetail(conId);
        }
    }else if(currentRoute == 'zhaopin.html'){//招聘

    }else if(currentRoute == 'jianjie.html'){//简介

    }else{ //首页
        getConstructList(null,false);
        getBidList(null,false);
    }
}
//设置对应文章banner
function changeBanner() {
    var articleType = getUrlParam('articleType');
    if(articleType == 'qyjs' || articleType == 'ygfc'){
        $('#bannerTitle').html('企业文化');
        $('#bannerTitleEn').html('COMPANY CULTURE');
        $('.js_thumb').attr("src", 'img/1920X400-qywh.jpg');
    }else if(articleType == 'jtxw'){
        $('#bannerTitle').html('集团新闻');
        $('#bannerTitleEn').html('GROUP NEWS');
        $('.js_thumb').attr("src", 'img/1920X400-jzpz.jpg');
    }
}

//保存投标信息
function saveMessage(){
    var bidId = $('#bidId').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
    var price = $('#price').val();
    var address = $('#address').val();
    var remark = $('#remark').val();

    if(!bidId){
        alert('系统异常，请刷新！');
        return;
    }
    if(!name){
        alert('请输入“姓名”！');
        return;
    }
    if(!phone){
        alert('请输入“手机”！');
        return;
    }
    if(!price){
        alert('请输入“报价”！');
        return;
    }
    if(!address){
        alert('请输入“地址”！');
        return;
    }
    var data = {bidId:bidId,messageName:name,messagePhone:phone,messagePrice:price,messageAddress:address,remark:remark};
    $.ajax({
        url: baseUrl + 'api/bid/',
        type: "POST",
        contentType:'application/json;charset=UTF-8',
        data:JSON.stringify(data),
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        },
        success: function (result, status, request) {
            var code = result.code;
            if(code == 200){
                alert('投标成功！');
                resetFrom();
            }
        }
    });
}
//重置表单
function resetFrom(){
    $('#bidId').val('');
    $('#name').val('');
    $('#phone').val('');
    $('#price').val('');
    $('#address').val('');
    $('#remark').val('');
}




$(function () {
    getZbggDh();
    getJzpzDh();
    //初始化页面
    initHtml();
})





