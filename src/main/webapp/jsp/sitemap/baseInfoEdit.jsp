<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <title>网站地图基础信息数据修改</title>
    <script type="text/javascript">
    jQuery.ajaxSettings.async = false;  
	function validateEdit(){
	    var id = $('#id').val();
	    var name = $('#name').val();
	    var keyword = $('#keyword').val();
	    var type = $("#type").val();
	    var isDelete = $("#isDelete").val();
	    var expand = $("#expand").val();
	    
	    if($.trim(name) == ''){
	        alert('名称为空，请重新填写');
	        $('#name').focus();
	        return false;
	    } 
	    if($.trim(keyword) == ''){
	        alert('关键字为空，请重新填写');
	        $('#keyword').focus();
	        return false;
	    }
	    if(keyword == "${sitemapBase.keyword}"){
           $("#hiddenValidateInput").val("1");
           $("#spanForKeywordValidate").css("color","blue");
           $("#spanForKeywordValidate").html("*可用");
        }
        else{
		    var valFlag = false;
		    $.post("${ctx}/Sitemap/vaidateKeyword.action",{keyword:keyword},function (data){
	           if(data == "success"){
	               $("#hiddenValidateInput").val("1");
	               $("#spanForKeywordValidate").css("color","blue");
	               $("#spanForKeywordValidate").html("*可用");
	               valFlag = true;
	           }
	           else{
	               $("#hiddenValidateInput").val("0");
	               $("#spanForKeywordValidate").css("color","red");
	               $("#spanForKeywordValidate").html("*不可用");
	           }
	       });
	       if(!valFlag){
	           return false;
	       }
        }
	   $.post("${ctx}/Sitemap/baseInfoSave.action",{"sitemapBase.id":id,"sitemapBase.name":name,"sitemapBase.keyword":keyword,"sitemapBase.type":type,"sitemapBase.isDelete":isDelete,"sitemapBase.expand":expand},function (data){
	       if(data == "success"){
	           window.dialogArguments.location.href=window.dialogArguments.location.href;
	           window.focus();
	           window.close();
	       }
	    });
	}
	function vaidateKeyword(){
	   var url = "${ctx}/Sitemap/vaidateKeyword.action";
	   var keyword = $("#keyword").val();
	   if(keyword == "${sitemapBase.keyword}"){
	       $("#hiddenValidateInput").val("1");
           $("#spanForKeywordValidate").css("color","blue");
           $("#spanForKeywordValidate").html("*可用");
           return false;
	   }
	   $.post(url,{keyword:keyword},function (data){
	       if(data == "success"){
	           $("#hiddenValidateInput").val("1");
	           $("#spanForKeywordValidate").css("color","blue");
	           $("#spanForKeywordValidate").html("*可用");
	       }
	       else{
	           $("#hiddenValidateInput").val("0");
               $("#spanForKeywordValidate").css("color","red");
               $("#spanForKeywordValidate").html("*不可用");
	       }
	   });
	}
	function clearValidateMsg(){
	   $("#spanForKeywordValidate").css("color","red");
	   $("#spanForKeywordValidate").html("*");
	   $("#hiddenValidateInput").val("0");
	}
    </script>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>修改网站地图基础信息数据</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="baseInfoAddform" action="${ctx}/Sitemap/baseInfoSave.action" method="post" onreset="clearValidateMsg();">
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">名称：</td>
                        <td>
                            <input type="text" id="name" name="sitemapBase.name" value="${sitemapBase.name }"/>
                            <input type="hidden" id="id" name="sitemapBase.id" value="${sitemapBase.id }"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">关键字：</td>
                        <td>
                            <input type="text" id="keyword" name="sitemapBase.keyword" value="${sitemapBase.keyword }" onblur="vaidateKeyword()"/>
                            <span id="spanForKeywordValidate" style="color: red;">*</span>
                            <input type="hidden" value="0" id="hiddenValidateInput" />
                            <input type="button" id="validateBtn" value="是否可用" onclick="vaidateKeyword()"/>
                        </td>
                    </tr>
                    <tr>
                        <td>类型：</td>
                        <td>
                            <s:select id="type" name="sitemapBase.type" theme="simple" cssStyle="width:80px;" list="typeBaseInfoList" listKey="keyword" listValue="name" headerKey="0" headerValue="请选择">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否删除：</td>
                        <td>
                            <s:select id="isDelete" name="sitemapBase.isDelete" theme="simple" cssStyle="width:80px;" list="#{'0':'未删除','1':'已删除' }">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">扩展：</td>
                        <td><input type="text" id="expand" name="sitemapBase.expand" value="${sitemapBase.expand }"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" value="重置" /> <input type="button" value="提交" onclick="validateEdit();"/> </td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
    </div>
  </body>
</html>
