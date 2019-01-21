<%@page import="com.coo8.item.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>¥�����</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
<script src="${ctx}/js/singleCalendar/WdatePicker.js"  type="text/javascript" ></script>
<style type="text/css">
.tmp-class thead td,.tmp-class tbody td {
	padding: 5px 5px;
}

.tmp-class1 {
	text-align: center;
}

.tmp-class1 th {
	width: 63px;
	height: 29px;
	border: 1px solid #C4C8CC;
	background: #E9EBED;
	text-align: center;
}

.tmp-class1 td {
	width: 63px;
	border: 1px solid #C4C8CC;
	height: 29px;
	text-align: center;
}
</style>
<script type="text/javascript">
	//ȫѡ ��ȡ�� 
	function sel_all(checked){ 
	    var check_obj = $("input[id='cbs']"); 
	   for(var i=0; i<check_obj.length;i++){ 
	       if(checked){ 
	            check_obj.get(i).checked = true; 
	        }else{ 
	            check_obj.get(i).checked = false; 
	        } 
	    } 
	    return; 
	}
	
	//����
	function addFloor(){
		window.location.href = "${ctx}/Item/createFloor.action?pageNumber" + ${pageNumber};
	}
	
	//ɾ��
	function delFloors() {
		var cc = $('input:checked');
		var ids = "";
		for ( var j = 0; j < cc.length; j++) {
			ids = ids + $('input:checked').get(j).value + ";";
		}
		if (ids == "") {
			alert('������ѡ��һ�');
			return;
		}
		delFloorIds(ids);
	}
	
	function delFloorIds(ids) {
		if(confirm('ȷ��ɾ����')){
		   window.location.href = "${ctx}/Item/deleteFloors.action?ids=" + ids;
		 }
	}
	
	function delFloor(id) {
		delFloorIds(id);
	}
	
	function tunePage(num) {
		var url = '${ctx}/Item/listFloors.action?pageNumber=' + num;
		window.location = url;
	}
</script>
</head>
<body>
	<form action="${ctx}/Item/listFloors.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>¥�����</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
						
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="ȫѡ" onclick="sel_all('true')" /> 
									<input type="button" value="ɾ��" onclick="delFloors()" /> 
									<input type="button" value="ȡ��" onclick="sel_all()" /> 
									<input type="button" value="����" onclick="addFloor()" /> 
								</td>
								<td style="padding-right: 10px;" align="right" >
	 							</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">¥������</td>
								<td style="line-height: 1;">���ȼ�</td>
								<td style="line-height: 1;">�Ƿ�����</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="listFloor!= null">
								<s:iterator value="listFloor" status="st">
									<tr>
										<td><label><input type="checkbox" name="cbs" id="cbs" value="<s:property value='id' />" /></label></td>
										<td><s:property value='id' /></td>
										<td><s:property value='floorName' /></td>
										<td><s:property value='priority' /></td>
										<td>${isValid==true?'����':'ͣ��'}</td>
										<td><s:date name="createTime"
												format="yyyy.MM.dd" />/<s:date name="updateTime"
												format="yyyy.MM.dd" /></td>
										<td>
											<a href="${ctx}/Item/listCategories.action?floor.id=<s:property value='id'/>">�������</a>
											<a href="${ctx}/Item/editFloor.action?floor.id=<s:property value='id'/>&pageNumber=${pageNumber}">�༭</a>
											<a href="javascript:delFloor(<s:property value='id' />)">ɾ��</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</tbody>
					</table>

					<table width="100%">
						<tfoot>
							<tr>
								<td style="padding-left: 0; text-align: left; border: none;">
									<table>
										<tbody>
											<tr>
												<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
													<input type="button" value="ȫѡ" onclick="sel_all('true')" />
													<input type="button" value="ɾ��" onclick="delFloors()"/>
													<input type="button" value="ȡ��" onclick="sel_all(false)" />
													<input type="button" value="����" onclick="location.href='./createFloor.action?pageNumber=${pageNumber}'" />
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td style="border: 0 none;">
									<div class="numpage-box">
										<div class="numpage">
											<coo8:page name="listFloor" style="js" systemId="1" />
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