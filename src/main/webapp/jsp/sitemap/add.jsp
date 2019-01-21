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
    <title>网站地图数据添加</title>
    <script type="text/javascript">
	function validateAdd(){
	    var name = $('#name').val();
	    var urlCatalog = $('#urlCatalog').val();
	    var status = $('#status').val();
	    var site = $('#site').val();
	    
	    if($.trim(name) == ''){
	        alert('名称为空，请重新填写');
	        $('#name').focus();
	        return false;
	    } 
	    if($.trim(urlCatalog) == ''){
	        alert('标记词为空，请重新填写');
	        $('#urlCatalog').focus();
	        return false;
	    }
	    if($.trim(status) == ''){
            alert('状态为空，请重新填写');
            $('#status').focus();
            return false;
        }
        if($.trim(site) == ''){
            alert('站点为空，请重新填写');
            $('#site').focus();
            return false;
        }
	}
    </script>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>添加网站地图数据</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="sitemapAddform" action="${ctx}/Sitemap/insert.action" method="post" onsubmit="return validateAdd();">
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">名称：</td>
                        <td><input type="text" id="name" name="sitemap.name" value=""/></td>
                    </tr>
                    <tr>
                        <td width="60">标记词：</td>
                        <td><input type="text" id="urlCatalog" name="sitemap.urlCatalog" value=""/></td>
                    </tr>
                    <tr>
                        <td width="60">扩展：</td>
                        <td><input type="text" id="expand" name="sitemap.expand" value=""/></td>
                    </tr>
                    <tr>
                        <td>使用状态：</td>
                        <td>
	                        <s:select id="status" name="sitemap.status" theme="simple" cssStyle="width:80px;" list="#{'':'请选择','Y':'启用','N':'弃用'}">
	                        </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>站点：</td>
                        <td>
                            <s:select id="site" name="sitemap.site" theme="simple" cssStyle="width:80px;" list="siteBaseInfoList" listKey="keyword" listValue="name" headerKey="" headerValue="请选择">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" value="重置" /> <input type="submit" value="提交" /> </td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
    </div>
  </body>
</html>
