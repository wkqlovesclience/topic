<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>Ʒ��������</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script type="text/javascript">
	//ȫѡ ��ȡ�� 
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
	
	function tunePage(num) {
		$("#page_index").val(num);
		$("#f1").submit();
	}
	
	//���س�ʵ������
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	document.getElementById("f1").submit();
	        }
	}
	
	function createCategoryNavigationHtml(){
		$.post("${ctx}/CategoryNavigation/publish.action",
			function(msg) {
				if (msg == "success") { 
					alert("�ļ����ɳɹ���");					 
				} else {
					alert("�ļ�����ʧ�ܣ�");
				}
		});
	}
	
	//��ӷ������
	function addItem(){
		var url = '${ctx}/CategoryNavigation/edit.action';
		window.location = url;
		return;
	}
	
	//ɾ��
	function delCategoryNavigation() {
		var cc = $('input:checked');
		var str = "";
		for ( var j = 0; j < cc.length; j++) {
			str = str + $('input:checked').get(j).value + ";";
		}
		if (str == "") {
			alert('������ѡ��һ�');
			return;
		}
		if(confirm('ȷ��ɾ����')){
		   window.location.href = "${ctx}/CategoryNavigation/delete.action?ids=" + str;
		}
	}
	$(function(){
		$("#reset").click(function() {
			$("#names").val("");
		});
	});
</script>
</head>
<body>
	<form action="${ctx}/CategoryNavigation/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>Ʒ��������</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  	�������ƣ�
				  	<input type="text" class="txt-5" id="names" name="names" value="${names}"/>  
				  	<input type="hidden" id="page_index" name="page_index" value="1"/>	
				  	<input type="submit" value="����" />&nbsp;&nbsp;<input type="button" value="���" id="reset"/><br/>
				</div>
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="ɾ��" onclick="javascript:delCategoryNavigation()"/>
									<input type="button" value="���" onclick="javascript:addItem()"/>
									<input type="button" value="ͳһ����" onclick="javascript:createCategoryNavigationHtml()"/>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" id="chkSelect" onclick="doSlectChk()"/></td>
								<td style="line-height: 1;">����</td>
								<td style="line-height: 1;">��������</td>
								<td style="line-height: 1;">״̬</td>
								<td style="line-height: 1;">������</td>
								<td style="line-height: 1;">����ʱ��</td>
								<td style="line-height: 1;">�޸�ʱ��</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="categoryList != null">
								<s:iterator value="categoryList"  status="st" var="bean">
									<tr>
										<td>
											<label><input type="checkbox" name="checkbox" value="<s:property value='id' />" /></label>
										</td>
										<td><s:property value='sorts' /></td>
										<td><s:property value='names' /></td>
										<td>
											<c:if test="${status == 1}">����</c:if>
											<c:if test="${status == 0}">����</c:if>
										</td>
										<td><s:property value='createdName' /></td>
										<td><s:property value='createdTime.substring(0,19)' /></td>
										<td><s:property value='updatedTime.substring(0,19)' /></td>
										<td>
											<a href="${ctx}/CategoryNavigation/edit.action?id=<s:property value='id' />">�༭</a>&nbsp;&nbsp;&nbsp;
											<a href="${ctx}/CategoryNavigation/delete.action?id=<s:property value='id' />">ɾ��</a>&nbsp;&nbsp;&nbsp;
											<a href="${ctx}/CategoryNavigation/firstList.action?groupId=<s:property value='id' />">��������</a>&nbsp;&nbsp;&nbsp;
											<a href="${ctx}/CategoryNavigation/thirdList.action?groupId=<s:property value='id' />">��������</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</tbody>
					</table>
					<table width="100%">
						<tfoot>
							<tr>
								<td style="border: 0 none;">
									<div class="numpage-box">
										<div class="numpage">
											<coo8:page name="categoryList" style="js" systemId="1" />
										</div>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>