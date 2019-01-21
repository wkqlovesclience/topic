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
        if(window.confirm("确认删除吗？")){
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
             alert('请至少选择一项！'); 
             return;
        } 
        if(confirm('确认删除？')){
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
    <title>手动上传管理</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>手动上传管理</h3>
        </div>
        <div class="bd clearfix">
		    <form action="${ctx}/Sitemap/uploadList.action" method="post" id="sitemapUploadForm">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                文件名：<input type="text" id="filename" name="filename" value="${filename}"/>&nbsp;&nbsp;
                上传人：<input type="text" id="uploadUser" name="uploadUser" value="${uploadUser}"/>&nbsp;&nbsp;
                <input type="submit" value="搜 索"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="clearAll()" value="清 空"/>
            </div>
            </form>
            <div style="font-size: 20px;" class="container-1">
                <input type="button" value="批量删除" onclick="deleteBatch()" />
                <input type="button" value="上 传" onclick="window.location.href='${ctx}/Sitemap/doUpload.action'"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
                        <td style="line-height: 1;">编号</td>
                        <td style="line-height: 1;">文件名</td>
                        <td style="line-height: 1;">访问路径</td>
                        <td style="line-height: 1;">上传者</td>
                        <td style="line-height: 1;">上传时间</td>
                        <td style="line-height: 1;">是否删除</td>
                        <td style="line-height: 1;">站点</td>
                        <td style="line-height: 1;">操作</td>
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
                                    <c:when test="${isDelete == 0}">未删除</c:when>
                                    <c:when test="${isDelete == 1}">已删除</c:when>
                                    <c:otherwise>${isDelete }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${site == 'gome'}">国美</c:when>
                                    <c:when test="${site == 'coo8'}">库巴</c:when>
                                    <c:otherwise>${site }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="javascript:void(0)" onclick="deleteItem(${id})">删除</a>
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
