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
    <title>��վ��ͼ���ݱ༭</title>
    <script type="text/javascript">
	function validateEdit(){
	    var name = $('#name').val();
	    var urlCatalog = $('#urlCatalog').val();
	    var status = $('#status').val();
	    var isDelete = $('#isDelete').val();
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
            alert('ʹ��״̬Ϊ�գ���������д');
            $('#status').focus();
            return false;
        }
        if($.trim(isDelete) == ''){
            alert('ɾ��״̬Ϊ�գ���������д');
            $('#isDelete').focus();
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
            <h3>�༭��վ��ͼ����</h3>
            <h3><a href="${ctx}/Sitemap/list.action?page_index=${page_index}"  >������һ��</a></h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <form name="sitemapEditForm" action="${ctx}/Sitemap/save.action" method="post" onsubmit="return validateEdit();">
                <table class="tb-1" style="margin-left: 20px;" width="100%">
                    <tbody>
                    <tr>
                        <td width="60">���ƣ�</td>
                        <td>
                            <input type="hidden" id="sitemapId" name="sitemap.id" value="${sitemap.id }"/>
                            <input type="text" id="name" name="sitemap.name" value="${sitemap.name }"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="60">��Ǵʣ�</td>
                        <td><input type="text" id="urlCatalog" name="sitemap.urlCatalog" value="${sitemap.urlCatalog }"/></td>
                    </tr>
                    <tr>
                        <td width="60">��չ��</td>
                        <td><input type="text" id="expand" name="sitemap.expand" value="${sitemap.expand }"/></td>
                    </tr>
                    <tr>
                        <td>ʹ��״̬��</td>
                        <td>
	                        <s:select id="status" name="sitemap.status" theme="simple" cssStyle="width:80px;" list="#{'':'��ѡ��','Y':'����','N':'����'}" value="sitemap.status">
	                        </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>ɾ��״̬��</td>
                        <td>
                            <s:select id="isDelete" name="sitemap.isDelete" theme="simple" cssStyle="width:80px;" list="#{'':'��ѡ��','0':'δɾ��','1':'��ɾ��'}" value="sitemap.isDelete">
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>վ�㣺</td>
                        <td>
                            <s:select id="site" name="sitemap.site" theme="simple" cssStyle="width:80px;" list="siteBaseInfoList" listKey="keyword" listValue="name" headerKey="" headerValue="��ѡ��" value="sitemap.site">
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
