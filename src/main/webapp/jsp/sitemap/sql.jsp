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
    <title>网站地图数据ID编辑</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>编辑网站地图数据ID</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="sitemapEditForm" action="${ctx}/Sitemap/doSql.action" method="post" >
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">老ID：</td>
                        <td>
                            <input type="text" id="id" name="id" "/>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">新ID：</td>
                        <td><input type="text" id="nid" name="nid" /></td>
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
