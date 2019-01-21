<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
   function clearAll()
	{
	    $("input:text,select").not(":button,:submit").val("");
	}
	function doSlectChk(){
        var check_obj = $("input[name='checkbox']");
        var checked = $("#chkSelect").is(":checked");
        for(var i=0; i<check_obj.length;i++){ 
            if(checked){ 
                check_obj.get(i).checked = true; 
            }
            else{ 
                check_obj.get(i).checked = false; 
            } 
        }
    }
    function deleteItem(paramId){
        if(window.confirm("ȷ��ɾ����")){
            window.location.href='${ctx}/Sitemap/deleteUpload.action?id='+paramId;
        }
    }
    function deleteBatch(){
        var cc = $(':checkbox[name=checkbox][checked=true]');
        var str = "";
        for(var j=0;j<cc.length;j++){
            str += cc.get(j).value+";";
        }   
        if(str == ""){
             alert('������ѡ��һ�'); 
             return;
        } 
        if(confirm('ȷ��ɾ����')){
            window.location.href="${ctx}/Sitemap/deleteBatchUpload.action?paramIds="+str;
        }
    }
    function tunePage(num) {
        var url = '${ctx}/Sitemap/uploadList.action?page_index='+num;
        var filename = $('#filename').val();
        var uploadUser = $('#uploadUser').val();
        if($.trim(filename)!=""){
            filename = $.trim(filename);
            url += '&filename='+filename;
        }
        if($.trim(uploadUser)!=""){
            uploadUser = encodeURIComponent(encodeURIComponent($.trim(uploadUser)));
            url += '&uploadUser='+uploadUser;
        }
        window.location = url;
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
		    <form action="${ctx}/Sitemap/uploadList.action" method="post" id="sitemapUploadForm">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                �ļ�����<input type="text" id="filename" name="filename" value="${filename}"/>&nbsp;&nbsp;
                �ϴ��ˣ�<input type="text" id="uploadUser" name="uploadUser" value="${uploadUser}"/>&nbsp;&nbsp;
                <input type="submit" value="�� ��"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="clearAll()" value="�� ��"/>
            </div>
            </form>
            <div style="font-size: 20px;" class="container-1">
                <input type="button" value="����ɾ��" onclick="deleteBatch()" />
                <input type="button" value="�� ��" onclick="window.location.href='${ctx}/Sitemap/doUpload.action'"/>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 5%;" />
                        <col style="width: 20%;" />
                        <col style="width: 30%;" />
                        <col style="width: 8%;"/>
                        <col style="width: 15%;"/>
                        <col style="width: 7%;" />
                        <col style="width: 5%;" />
                        <col style="width: 5%;" />
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;"><input type="checkbox" id="chkSelect" onclick="doSlectChk()"/></td>
                        <td style="line-height: 1;">���</td>
                        <td style="line-height: 1;">�ļ���</td>
                        <td style="line-height: 1;">����·��</td>
                        <td style="line-height: 1;">�ϴ���</td>
                        <td style="line-height: 1;">�ϴ�ʱ��</td>
                        <td style="line-height: 1;">�Ƿ�ɾ��</td>
                        <td style="line-height: 1;">վ��</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="sitemapUploadList != null">
                        <s:iterator value="sitemapUploadList" status="til" var="item">
                        <tr style="height:15px;">
                            <td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
                            <td>${id }</td>
                            <td>${filename }</td>
                            <td>
                                <s:if test="isDelete eq 0 && path != ''">
                                    <a href="${path }" target="_blank">${path }</a>
                                </s:if>
                                <s:else>${path }</s:else>
                            </td>
                            <td>${uploadUser }</td>
                            <td><s:date name="uploadTime" format="yyyy.MM.dd HH:mm:ss" /></td>
                            <td>
                                <c:choose>
                                    <c:when test="${isDelete == 0}">δɾ��</c:when>
                                    <c:when test="${isDelete == 1}">��ɾ��</c:when>
                                    <c:otherwise>${isDelete }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${site == 'gome'}">����</c:when>
                                    <c:when test="${site == 'coo8'}">���</c:when>
                                    <c:otherwise>${site }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="javascript:void(0)" onclick="deleteItem(${id})">ɾ��</a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="10" style="border: 0 none;">
                                <div class="numpage"><coo8:page name="sitemapUploadList" style="js" systemId="1" /></div>
                            </td>
                        </tr>
                    </s:if>
                    </tbody>
                </table>
            </div>
        </div>
      </div>
  </body>
</html>
