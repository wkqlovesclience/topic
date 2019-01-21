<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>�ٶȺ������</title>
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
	
	//ɾ��
	function delBaiDuCards() {
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
		   window.location.href = "${ctx}/BaiDuBlackFridayCard/delete.action?tags=" + str;
		 }
	}
	
	function tunePage(num) {
		var id  = document.getElementById("id").value;
		var isEditor = document.getElementById("isEditor").value;
		var isInvalid = document.getElementById("isInvalid").value;
		var url = '${ctx}/BaiDuBlackFridayCard/list.action?' + 'page_index=' + num;

		if(null != id && '' != id){
			url += '&id=' + id;
		}
		if(null != isEditor && '' != isEditor){
			url += '&isEditor=' + encodeURIComponent(encodeURIComponent(isEditor));
		}
		if(null != isInvalid && '' != isInvalid){
			url += '&isInvalid=' + encodeURIComponent(encodeURIComponent(isInvalid));
		}
		window.location = url;
	}
	
	
	//���س�ʵ������
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	document.getElementById("f1").submit();
	        }
	}
		
	//У�������Ƿ�Ϊ����
	function numValidate(num){
		var reg = /^\d+$/;
		if(null != num && "" != num){
			if(!reg.test(num)){
				alert(num + "�������֣����������֣�")
				return false;
			}
		}
		return true;
	}
	//�Ƚ�����ֵ��С
	function compareNum(num1,num2){
		if(null != num1 && num2 != null && "" != num1 && "" != num2 && (num1 > num2)){
			alert(num2 + " ֵ���� " + num1 + "  ���������룡");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="${ctx}/BaiDuBlackFridayCard/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>�ٶȺ������</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">				 
				  &nbsp;&nbsp;SKUID��
				  <input type="text" class="txt-5" id="id" name="id" value="${id}"/>  
				   &nbsp;&nbsp;�༭״̬��
				  <s:select id="isEditor" name="isEditor" list="#{3:'ȫ��',0:'δ�༭',1:'�ѱ༭'}" />
				  &nbsp;&nbsp;ʧЧ״̬��
				  <s:select id="isInvalid" name="isInvalid" list="#{3:'ȫ��',0:'δʧЧ',1:'��ʧЧ'}" />
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;	
				  <input type="submit" value="����" />
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;	
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				  </div>
				<div class="container-1">

					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
								<input type="button" value="ȫѡ" onclick="sel_all('true')" /> 
									
									<input type="button" value="ɾ��"
									onclick="delBaiDuCards()"/>
						
									<input type="button" value="ȡ��"
									onclick="sel_all(false)" />							
								</td>									
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 15%;" />
							<col style="width: 13%;" />
							<col style="width: 10%;" />
							<col style="width: 8%;" />
							<col style="width: 8%;" />
							<col style="width: 8%;" />
							<col style="width: 8%;" />
							<col style="width: 8%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;">ѡ��</td>
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">��Ʒ����</td>
								<td style="line-height: 1;">��ƷͼƬ</td>
								<td style="line-height: 1;">��Ʒ״̬</td>
								<td style="line-height: 1;">skuId</td>
								<td style="line-height: 1;">productId</td>
								<td style="line-height: 1;">��Ʒ����</td>
								<td style="line-height: 1;">������Ϣ</td>
								<td style="line-height: 1;">������/�޸���</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">�Ƿ�༭</td>
								<td style="line-height: 1;">�Ƿ�ʧЧ</td>									
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="baiDuBlackFridayCardlist!= null">
								<s:iterator value="baiDuBlackFridayCardlist"  status="st">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<s:if test="customUrl == null || customUrl == '' ">
										   <td ><s:property value='productName' /></td>
										</s:if>
										<s:else>
										   <td style="color:red"><s:property value='productName' /></td>
										</s:else> 
										<td ><img align="top" src="${imgUrl}_200.jpg" width="60" height="50" /></td>
										<td >${onSale==true ?'�ϼ�':'<spean style="color:red;">�¼�</spean>' }</td>
										<td ><s:property value='skuId' /></td>
										<td ><s:property value='productId' /></td>
										<td ><s:property value='type' /></td>
										<td ><s:property value='tag' /></td>											
										<td><s:property value='creator' />&nbsp;/&nbsp;<s:property value="modifier"/></td>
										<td><s:date name="createDate"
												format="yyyy.MM.dd HH:mm:ss" />/<s:date name="updateDate"
												format="yyyy.MM.dd HH:mm:ss" /></td>
										
										<td>${isEditor==1 ?'�ѱ༭':'δ�༭' }</td>							
									    <td>${isInvalid==1 ?'��ʧЧ':'δʧЧ' }</td>
										<td> 										
											 <a href="${ctx}/BaiDuBlackFridayCard/edit.action?id=<s:property value='id'/>">�༭</a>
											 &nbsp;&nbsp;&nbsp;
											 &nbsp;&nbsp;&nbsp;
											 <a href="${ctx}/BaiDuBlackFridayCard/delete.action?tags=<s:property value='id'/>">ɾ��</a>
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
												<td
									style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="ȫѡ" onclick="sel_all('true')" /> 
								
									<input type="button" value="ɾ��"
									onclick="delBaiDuCards()"/>
									
									<input type="button" value="ȡ��"
									onclick="sel_all(false)" />  
									</td>
											</tr>
										</tbody>
									</table></td>
								<td style="border: 0 none;">
									<div class="numpage-box">
										<div class="numpage">
											<coo8:page name="baiDuBlackFridayCardlist" style="js" systemId="1" />
										</div>
									</div></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>