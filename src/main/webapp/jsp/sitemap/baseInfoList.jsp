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
            window.location.href='${ctx}/Sitemap/deleteBaseInfo.action?id='+paramId;
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
            window.location.href="${ctx}/Sitemap/deleteBatchBaseInfo.action?paramIds="+str;
        }
    }
    function tunePage(num) {
        var url = '${ctx}/Sitemap/baseInfoList.action?page_index='+num;
        var baseInfoId = $('#baseInfoId').val();
        var name = $('#name').val();
        var keyword = $('#keyword').val();
        var type = $('#type').val();
        var isDelete = $('#isDelete').val();
        if($.trim(baseInfoId)!=""){
            url += '&id='+$.trim(baseInfoId);
        }
        if($.trim(name)!=""){
            url += '&name='+$.trim(name); 
        }
        if($.trim(keyword)!=""){
            url += '&keyword='+$.trim(keyword); 
        }
        if($.trim(type)!=""){
            url += '&type='+$.trim(type); 
        }
        if($.trim(isDelete)!=""){
            url += '&isDelete='+$.trim(isDelete); 
        }
        window.location = url;
    }
    function clearAll()
	{
	    $("input:text,select").not(":button,:submit").val("");
	}
	function createNew(){
	   var url = "${ctx}/Sitemap/baseInfoAdd.action";
	   var dialogLeft = ($(window).width() - 600)/2;
       var dialogTop = ($(window).height() - 300)/2;
       if($.browser.msie){
           centerDialog = "";
       }
       else{
	       var centerDialog = "dialogLeft:"+dialogLeft+"px;"+"dialogTop:"+dialogTop+"px;";
       }
	   window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:300px;center:yes;resizable:yes;status:no;help:no;scroll:yes;"+centerDialog);
	}
	function doModify(paramId){
	   var url = "${ctx}/Sitemap/baseInfoEdit.action?id="+paramId;
       var dialogLeft = ($(window).width() - 600)/2;
       var dialogTop = ($(window).height() - 300)/2;
       if($.browser.msie){
           centerDialog = "";
       }
       else{
           var centerDialog = "dialogLeft:"+dialogLeft+"px;"+"dialogTop:"+dialogTop+"px;";
       }
       window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;center:yes;resizable:yes;status:no;help:no;scroll:yes;"+centerDialog);
	}
    </script>
    <title>��վ��ͼ������Ϣ����</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>��վ��ͼ������Ϣ����</h3>
        </div>
        <div class="bd clearfix">
		    <form action="${ctx}/Sitemap/baseInfoList.action" method="post" id="baseInfoForm">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                ��ţ�<input type="text" name="id" id="baseInfoId" value="${id }"/>&nbsp;&nbsp;
                ���ƣ�<input type="text" id="name" name="name" value="${name}"/>&nbsp;&nbsp;
                �ؼ��֣�<input type="text" id="keyword" name="keyword" value="${keyword}"/>&nbsp;&nbsp;
                ���ͣ�
                <s:select name="type" id="type" cssStyle="width:80px;" list="typeBaseInfoList" headerKey="" headerValue="��ѡ��" listKey="keyword" listValue="name" theme="simple">
                </s:select>&nbsp;&nbsp;
                �Ƿ�ɾ����<s:select list="#{'':'��ѡ��','0':'δɾ��','1':'��ɾ��' }" theme="simple" name="isDelete" id="isDelete" cssStyle="width:80px;"></s:select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="�� ��"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="clearAll()" value="�� ��"/>
            </div>
            </form>
            <div style="font-size: 20px;" class="container-1">
                <input type="button" value="����ɾ��" onclick="deleteBatch()" />
                <input type="button" value="�� ��" onclick="createNew()"/>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 15%;" />
                        <col style="width: 30%;" />
                        <col style="width: 20%;" />
                        <col style="width: 10%;"/>
                        <col style="width: 10%;"/>
                        <col style="width: 10%;" />
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;"><input type="checkbox" id="chkSelect" onclick="doSlectChk()"/></td>
                        <td style="line-height: 1;">���</td>
                        <td style="line-height: 1;">����</td>
                        <td style="line-height: 1;">�ؼ���</td>
                        <td style="line-height: 1;">����</td>
                        <td style="line-height: 1;">ɾ��״̬</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="baseInfoList != null">
                        <s:iterator value="baseInfoList" status="til" var="item">
                        <tr style="height:15px;">
                            <td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
                            <td>${id }</td>
                            <td>${name }</td>
                            <td>${keyword }</td>
                            <td>${type }</td>
                            <td>
                                <c:choose>
                                    <c:when test="${isDelete == 0}">δɾ��</c:when>
                                    <c:when test="${isDelete == 1}">��ɾ��</c:when>
                                    <c:otherwise>${isDelete }&nbsp;</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="javascript:void(0)" onclick="doModify('${id}')">�޸�</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="deleteItem(${id})">ɾ��</a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="10" style="border: 0 none;">
                                <div class="numpage"><coo8:page name="baseInfoList" style="js" systemId="1" /></div>
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
