<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>��������</title>
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
	
	//��ӷ������
	function addItem(){
		var url = '${ctx}/CategoryNavigation/thirdEdit.action?groupId=' + $("#groupId").val();
		window.location = url;
		return;
	}
	
	//ɾ��
	function delCategoryNavigationThird() {
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
		   window.location.href = "${ctx}/CategoryNavigation/thirdDelete.action?thirdIds=" + str + "&groupId=" + $("#groupId").val();
		}
	}
	
	$(function(){
		$("#reset").click(function() {
			$("#extendName").val("");
		});
	});
</script>
</head>
<body>
	<form action="${ctx}/CategoryNavigation/thirdList.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>��������</h3>
				<h3><a href="${ctx}/CategoryNavigation/list.action">������һ��</a></h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  	�����������ݣ�
				  	<input type="text" class="txt-9" id="extendName" name="extendName" value="${extendName}"/>
					<input type="hidden" id="groupId" name="groupId" value="${groupId}"/> 
					<input type="hidden" id="page_index" name="page_index" value="1"/>	
				  	<input type="submit" value="����" />&nbsp;&nbsp;<input type="button" value="���" id="reset"/><br/>
				</div>
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="ɾ��" onclick="javascript:delCategoryNavigationThird()"/>
									<input type="button" value="���" onclick="javascript:addItem()"/>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 6%;" />
							<col style="width: 8%;" />
							<col style="width: 6%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" id="chkSelect" onclick="doSlectChk()"/></td>
								<td style="line-height: 1;">����</td>
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">��������</td>
								<td style="line-height: 1;">������������</td>
								<td style="line-height: 1;">״̬</td>
								<td style="line-height: 1;">������</td>
								<td style="line-height: 1;">����ʱ��</td>
								<td style="line-height: 1;">�޸�ʱ��</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="categoryListThird != null">
								<s:iterator value="categoryListThird" status="st" var="bean">
									<tr>
										<td>
											<label><input type="checkbox" name="checkbox" value="<s:property value='id' />" /></label>
										</td>
										<td><s:property value='sorts' /></td>
										<td>
											<c:if test="${markRed == 1}">Y</c:if>
											<c:if test="${markRed == 0}">N</c:if>
										</td>
										<td><s:property value='categoryName' /></td>
										<td><s:property value='extendName' /></td>
										<td>
											<c:if test="${status == 1}">����</c:if>
											<c:if test="${status == 0}">����</c:if>
										</td>
										<td><s:property value='createdName' /></td>
										<td><s:property value='createdTime.substring(0,19)' /></td>
										<td><s:property value='updatedTime.substring(0,19)' /></td>
										<td>
											<a href="${ctx}/CategoryNavigation/thirdEdit.action?thirdId=<s:property value='id' />&groupId=${groupId}">�༭</a>
											&nbsp;&nbsp;&nbsp;
											<a href="${ctx}/CategoryNavigation/thirdDelete.action?thirdId=<s:property value='id' />&groupId=${groupId}">ɾ��</a>
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
											<coo8:page name="categoryListThird" style="js" systemId="1" />
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