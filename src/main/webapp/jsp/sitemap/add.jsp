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
    <title>��վ��ͼ�������</title>
    <script type="text/javascript">
	function validateAdd(){
	    var name = $('#name').val();
	    var urlCatalog = $('#urlCatalog').val();
	    var status = $('#status').val();
	    var site = $('#site').val();
	    
	    if($.trim(name) == ''){
	        alert('����Ϊ�գ���������д');
	        $('#name').focus();
	        return false;
	    } 
	    if($.trim(urlCatalog) == ''){
	        alert('��Ǵ�Ϊ�գ���������д');
	        $('#urlCatalog').focus();
	        return false;
	    }
	    if($.trim(status) == ''){
            alert('״̬Ϊ�գ���������д');
            $('#status').focus();
            return false;
        }
        if($.trim(site) == ''){
            alert('վ��Ϊ�գ���������д');
            $('#site').focus();
            return false;
        }
	}
    </script>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>�����վ��ͼ����</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="sitemapAddform" action="${ctx}/Sitemap/insert.action" method="post" onsubmit="return validateAdd();">
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">���ƣ�</td>
                        <td><input type="text" id="name" name="sitemap.name" value=""/></td>
                    </tr>
                    <tr>
                        <td width="60">��Ǵʣ�</td>
                        <td><input type="text" id="urlCatalog" name="sitemap.urlCatalog" value=""/></td>
                    </tr>
                    <tr>
                        <td width="60">��չ��</td>
                        <td><input type="text" id="expand" name="sitemap.expand" value=""/></td>
                    </tr>
                    <tr>
                        <td>ʹ��״̬��</td>
                        <td>
	                        <s:select id="status" name="sitemap.status" theme="simple" cssStyle="width:80px;" list="#{'':'��ѡ��','Y':'����','N':'����'}">
	                        </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>վ�㣺</td>
                        <td>
                            <s:select id="site" name="sitemap.site" theme="simple" cssStyle="width:80px;" list="siteBaseInfoList" listKey="keyword" listValue="name" headerKey="" headerValue="��ѡ��">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" value="����" /> <input type="submit" value="�ύ" /> </td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
    </div>
  </body>
</html>
