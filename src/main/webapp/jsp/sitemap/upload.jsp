<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
    function validateSubmit(){
        var uploadVal = $("#upload").val();
        var substrLocation = uploadVal.lastIndexOf(".");
        if(uploadVal == "" || substrLocation == -1){
            alert("��ѡ���ļ�");
            return false;
        }
        var extension = uploadVal.substr(substrLocation+1);
        if(extension!="xml" && extension!="txt"){
            alert("��ѡ��xml,txt��ʽ�ļ�");
            return false;
        }
    }
    </script>
    <title>�ֶ��ϴ�����</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>�ֶ��ϴ�����</h3>
        </div>
        <div class="bd clearfix">
		    <form action="${ctx}/Sitemap/uploadXML.action" method="post" id="sitemapUploadForm" enctype="multipart/form-data" onsubmit="return validateSubmit()">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                �ļ�����<input type="file" id="upload" name="upload" value=""/>&nbsp;&nbsp;
                <input type="submit" value="�� ��"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset"  value="�� ��"/>
            </div>
            </form>
        </div>
      </div>
  </body>
</html>
