<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="coo8" uri="/coo8-tag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>${news.topic} -库巴购物网</title>
<meta name="keywords" content="${news.keywords}" />
<meta name="description" content="${news.remark}" />
<!--# include virtual="/mblock/0/229/b_201107_base_css.html" -->
<!--# include virtual="/mblock/0/240/b_201107_coo8_css.html" -->
<!--# include virtual="/mblock/0/244/b_201107_zhai_pin_css.html" -->
<link rel="stylesheet" href="http://css.gomein.net.cn/topics/css/201107/zt.css" />
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/201107/base.js"></script>
 <script type="text/javascript" src="http://js.gomein.net.cn/topics/js/cookie.js"></script>
<!--# include virtual="/mblock/0/199/b_201107_header_js.html" --> 
<style type="text/css">body{text-align:center;font-family:\5B8B\4F53,Arial Narrow,arial,serif;}strong{font-weight:700;}th{font-weight:bolder;text-align:center;}table{border-collapse:separate;border-spacing:1px;}.menuBar{width: 960px;text-algin:left;}.header,.footer{width: 980px;text-align:left;}.footer{clear:both;padding-top:10px;}.footerNav .footerItems{width: 120px;padding-right: 20px;}.hotSrch{width: 235px;}</style>
<script type="text/javascript">

</script>
</head>

<body class="ListPage">
<!--# include virtual="/mblock/0/194/b_201107_menu_header.html" -->
<!--# include virtual="/mblock/0/197/b_201107_conmon_header.html" -->
<div class="main clearfix">
        
       <div class="crumb"><a href="http://www.gome.com.cn/">首页</a><span>&gt;</span><a href="javascript:void(0)">商品专题</a><span>&gt;</span><a href="http://www.gome.com.cn/${title_key.id}/">${title_key.title}</a></div>
        
        <div class="colMain">
		<div class="Zt border">
		<div class="ZtDetailhd">
		<h1
			style="background: url(http://app.gomein.net.cn/topics/images/201107/icons201107.png) repeat-x scroll 0 -2271 transparent">${news.topic}</h1>
		<p>
			<span class="Time">${news.createTimeString}</span> 
	<span>
         分享到：  <a class="sina" href="javascript:share('1');"> </a>
			<a class="qq" href="javascript:share('2');" ></a> 
			<a class="kaixin" href="javascript:share('3');" ></a>
			<a class="sohu" href="javascript:share('4');" ></a> 
			<a class="renren" href="javascript:share('5');" ></a>
	</span>
		</p>
		
<script charset="utf-8" type="text/javascript">
	function share(v) {
		if(v=='1'){
			window.open("http://service.t.sina.com.cn/share/share.php", "_blank",
			"width=615,height=505");
		}else if(v=='2'){
			window.open("http://v.t.qq.com/share/share.php", "_blank",
			"width=615,height=505");
		}else if(v=='3'){
			window.open("http://www.kaixin001.com/repaste/share.php", "_blank",
			"width=615,height=505");
		}else if(v=='4'){
			window.open("http://t.sohu.com/third/post.jsp", "_blank",
			"width=615,height=505");
		}else if(v=='5'){
			window.open("http://share.renren.com/share/buttonshare", "_blank",
			"width=615,height=505");
		}
		
	}
</script>
			</div>
                <div class="ZtDetailZt">${news.contents}</div>
</div>
        </div><!-- mainContent END -->
        
        <div class="colSub">
                    <div class="mod brand">
						<div class="hd"><h2>相关知识库</h2><a href="http://www.gome.com.cn/catalog/${title_key.id}/know.html" target="_blank">更多&gt;&gt;</a></div>
						
				<div class="bd">
					
                <!--# include virtual="/pblock/0/${title_key.id}/b_topic_relknowList.html" -->
				</div>
					</div>
					
					 <!--# include virtual="/cblock/0/483/b_brand_zone.html" -->
					
					<div style="border:1px #E4E4E4 solid" class="mod productRank">
                        <div class="hd"><h2>${catalogname}销售排行榜</h2></div>
                        <div class="bd">
                            <ul class="smNav" id="product_rank">
                                <li class="cur" tid="rank_one"><span >同类别</span></li>
                                <li tid="rank_two"><span >同品牌</span></li>
                                <li tid="rank_three"><span >同价位</span></li>
                            </ul>
							<!--# include virtual="/cblock/0/112/b_201107_zhanti_product_rank.html" -->                          
							<!--# include virtual="/pblock/0/125/b_topic_same_brand.html" -->
							<!--# include virtual="/pblock/0/125/b_topic_same_price.html" -->
                            <script type="text/javascript">
                            	(function(){
                            		$("#product_rank li").mouseover(function(){
                            			$("#" + $(this).attr("tid")).show().siblings(".smCon").hide();
                            			$(this).addClass("cur").siblings().removeClass("cur");
                            		});
                            	})();
                            </script>
                        </div><div class="clear"></div> 
                    </div><!-- brand END -->
            
            <div class="mod recentItems">
                <div class="hd"><h2>浏览过的商品</h2></div>
                <div class="bd">
                   <ul id="dataItems">
                   </ul>
                </div>
            </div><!-- recentItems END -->
        </div><!-- aside END -->
        
    </div>
<!--# include virtual="/mblock/0/196/b_201107_all_catalog.html" -->
<!--# include virtual="/mblock/0/195/b_201107_hot_search.html" -->
<!--# include virtual="/mblock/0/200/b_201107_common_footer.html" -->
<!--# include virtual="/mblock/0/121/b_ga_common_block.html" -->

    <script type="text/javascript">
    loadBrowseHistory("dataItems");
        $(function(){
            $("#friendLogos").cooMarquee();
            $("#tmp").cooMarquee();
            $("#ANWrap").cooMarquee();
            $("#slider").cooSlider();
            $(".Actnew .hd li").mouseover(function(){
                var index = $(this).index();
                $(this).addClass("cur").siblings().removeClass("cur");
                $(".Actnew .bd ul").eq(index).show().siblings().hide();
            });
            $("#Rush .pagesMenu li").mouseover(function(){
                var index = $(this).index();
                $(this).addClass("cur").siblings().removeClass("cur");
                $("#Rush .bd .items ").eq(index).show().siblings().hide();
            });
            $(".productRank .smCon li").mouseover(function(){
                $(this).addClass("on").siblings().removeClass("on");
            });
            
        });
    </script>
</body>
</html>	