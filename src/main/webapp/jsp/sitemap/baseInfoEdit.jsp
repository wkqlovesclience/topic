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
    <title>��վ��ͼ������Ϣ�����޸�</title>
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
	        alert('����Ϊ�գ���������д');
	        $('#name').focus();
	        return false;
	    } 
	    if($.trim(keyword) == ''){
	        alert('�ؼ���Ϊ�գ���������д');
	        $('#keyword').focus();
	        return false;
	    }
	    if(keyword == "${sitemapBase.keyword}"){
           $("#hiddenValidateInput").val("1");
           $("#spanForKeywordValidate").css("color","blue");
           $("#spanForKeywordValidate").html("*����");
        }
        else{
		    var valFlag = false;
		    $.post("${ctx}/Sitemap/vaidateKeyword.action",{keyword:keyword},function (data){
	           if(data == "success"){
	               $("#hiddenValidateInput").val("1");
	               $("#spanForKeywordValidate").css("color","blue");
	               $("#spanForKeywordValidate").html("*����");
	               valFlag = true;
	           }
	           else{
	               $("#hiddenValidateInput").val("0");
	               $("#spanForKeywordValidate").css("color","red");
	               $("#spanForKeywordValidate").html("*������");
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
           $("#spanForKeywordValidate").html("*����");
           return false;
	   }
	   $.post(url,{keyword:keyword},function (data){
	       if(data == "success"){
	           $("#hiddenValidateInput").val("1");
	           $("#spanForKeywordValidate").css("color","blue");
	           $("#spanForKeywordValidate").html("*����");
	       }
	       else{
	           $("#hiddenValidateInput").val("0");
               $("#spanForKeywordValidate").css("color","red");
               $("#spanForKeywordValidate").html("*������");
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
            <h3>�޸���վ��ͼ������Ϣ����</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="baseInfoAddform" action="${ctx}/Sitemap/baseInfoSave.action" method="post" onreset="clearValidateMsg();">
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">���ƣ�</td>
                        <td>
                            <input type="text" id="name" name="sitemapBase.name" value="${sitemapBase.name }"/>
                            <input type="hidden" id="id" name="sitemapBase.id" value="${sitemapBase.id }"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">�ؼ��֣�</td>
                        <td>
                            <input type="text" id="keyword" name="sitemapBase.keyword" value="${sitemapBase.keyword }" onblur="vaidateKeyword()"/>
                            <span id="spanForKeywordValidate" style="color: red;">*</span>
                            <input type="hidden" value="0" id="hiddenValidateInput" />
                            <input type="button" id="validateBtn" value="�Ƿ����" onclick="vaidateKeyword()"/>
                        </td>
                    </tr>
                    <tr>
                        <td>���ͣ�</td>
                        <td>
                            <s:select id="type" name="sitemapBase.type" theme="simple" cssStyle="width:80px;" list="typeBaseInfoList" listKey="keyword" listValue="name" headerKey="0" headerValue="��ѡ��">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>�Ƿ�ɾ����</td>
                        <td>
                            <s:select id="isDelete" name="sitemapBase.isDelete" theme="simple" cssStyle="width:80px;" list="#{'0':'δɾ��','1':'��ɾ��' }">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">��չ��</td>
                        <td><input type="text" id="expand" name="sitemapBase.expand" value="${sitemapBase.expand }"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" value="����" /> <input type="button" value="�ύ" onclick="validateEdit();"/> </td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
    </div>
  </body>
</html>
