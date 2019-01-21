<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
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
            window.location.href='${ctx}/Sitemap/delete.action?id='+paramId;
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
            window.location.href="${ctx}/Sitemap/deleteBatch.action?paramIds="+str;
        }
    }
    function tunePage(num) {
        var url = '${ctx}/Sitemap/list.action?page_index='+num;
        var sitemapId = $('#sitemapId').val();
        var name = $('#name').val();
        if($.trim(sitemapId)!=""){
            url += '&id='+$.trim(sitemapId);
        }
        if($.trim(name)!=""){
            url += '&name='+$.trim(name); 
        }
        window.location = url;
    }
    function exportData(searchId){
        window.location.href = "${ctx}/Sitemap/exportData.action?searchId="+searchId;
    }
    function clearAll()
    {
        $("input:text,select").not(":button,:submit").val("");
    }
    </script>
    <title>��վ��ͼ����</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>��վ��ͼ����</h3>
        </div>
        <div class="bd clearfix">
	        <form action="${ctx}/Sitemap/list.action" method="post" id="sitemapForm">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                ��ţ�<input type="text" id="sitemapId" name="id" value="${id }"/>&nbsp;&nbsp;
                ���ƣ�<input type="text" id="name" name="name" value="${name}"/>&nbsp;&nbsp;
                <input type="submit" value="�� ��"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="�� ��" onclick="clearAll()"/>
            </div>
            </form>
            <div style="font-size: 20px;" class="container-1">
                <input type="button" value="����ɾ��" onclick="deleteBatch()" />
                <input type="button" value="�� ��" onclick="location.href='${ctx}/Sitemap/add.action'"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <s:iterator value="searchBaseInfoList">
                    <input type="button" value="${name }��ͼ�ļ�����" onclick="exportData('${id}')"/>
                </s:iterator>
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 4%;" />
                        <col style="width: 6%;" />
                        <col style="width: 15%;" />
                        <col style="width: 8%;" />
                        <col style="width: 8%;"/>
                        <col style="width: 12%;"/>
                        <col style="width: 20%;" />
                        <col style="width: 6%;"/>
                        <col style="width: 6%;"/>
                        <col style="width: 6%;"/>
                        <col style="width: 8%;" />
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;"><input type="checkbox" id="chkSelect" onclick="doSlectChk()"/></td>
                        <td style="line-height: 1;">���</td>
                        <td style="line-height: 1;">����</td>
                        <td style="line-height: 1;">��Ǵ�</td>
                        <td style="line-height: 1;">�����ļ�����</td>
                        <td style="line-height: 1;">������</td>
                        <td style="line-height: 1;">����ʱ��</td>
                        <td style="line-height: 1;">վ��</td>
                        <td style="line-height: 1;">ʹ��״̬</td>
                        <td style="line-height: 1;">ɾ��״̬</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="sitemapList != null">
                        <s:iterator value="sitemapList" status="til" var="item">
                        <tr style="height:15px;">
                            <td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
                            <td>${id }</td>
                            <td>${name }</td>
                            <td>${urlCatalog }</td>
                            <td>${count }</td>
                            <td>${creator }/${updator }</td>
                            <td><s:date name="createDate" format="yyyy.MM.dd HH:mm:ss" />/<s:date name="updateDate" format="yyyy.MM.dd HH:mm:ss" /></td>
                            <td>
                                <s:if test="site eq 'gome'">����</s:if>
                                <s:elseif test="site eq 'coo8'">���</s:elseif>
                                <s:else>${site }&nbsp;</s:else>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${status == 'Y'}">����</c:when>
                                    <c:when test="${status == 'N'}">����</c:when>
                                    <c:otherwise>${status }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${isDelete == 0}">δɾ��</c:when>
                                    <c:when test="${isDelete == 1}">��ɾ��</c:when>
                                    <c:otherwise>${isDelete }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="javascript:void(0)" onclick="location.href='${ctx}/Sitemap/edit.action?id=${id}&page_index=${page_index}'">�޸�</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="deleteItem(${id})">ɾ��</a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="10" style="border: 0 none;">
                                <div class="numpage"><coo8:page name="sitemapList" style="js" systemId="1" /></div>
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
