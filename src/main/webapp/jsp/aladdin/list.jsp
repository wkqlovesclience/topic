<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>����������</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
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
	function delTitles() {
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
		   window.location.href = "${ctx}/Aladdin/delete.action?tags=" + str;
		 }
	}
	
	function tunePage(num) {
		var names = document.getElementById("names").value;
		var related = document.getElementById("related").value;
		var url = '${ctx}/Aladdin/list.action?' + 'page_index=' + num;
		
		if(null != names && '' != names){
			url += '&names=' + names;
		}
		if(null != related && '' != related){
			url += '&related=' + related;
		}
		window.location = url;
	}
	
	//���س�ʵ������
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	//document.form[0].submit();
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
	
	function createAladdinXml(){
		
		$.post("${ctx}/Aladdin/sendMessage.action",
				function(msg) {
					if (msg == "yes") { 
						alert("�ļ����ɳɹ�,����� http://www.gome.com.cn/aladdin/xmlForBaiduAladdin.xml �鿴��");					 
					} else {
						alert("�ļ�����ʧ�ܡ�");
					}
				});
		return ;
	}
	
	//��ӷ������
	function addItem(){
		var url = '${ctx}/Aladdin/edit.action';
		window.location = url;
		return;
	}
	
	//ɾ��
	function delAkeywords() {
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
		   window.location.href = "${ctx}/Aladdin/delete.action?tags=" + str;
		}
	}
</script>
</head>
<body>
	<form action="${ctx}/Aladdin/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>����������</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  	������ʣ�
				  	<input type="text" class="txt-5" id="names" name="names" value="${names}"/>  	
				  	Ʒ�ƹ���״̬��
				  	<s:select id="related" name="related" list="#{'':'ȫ��','Y':'�ѹ���','N':'δ����'}"/> 
				  <input type="submit" value="����" /><br/>
				</div>
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="ȫѡ" onclick="sel_all('true')" /> 
									<input type="button" value="ȡ��" onclick="sel_all()"/>
									<input type="button" value="ɾ��" onclick="javascript:delAkeywords()"/>
									<input type="button" value="���" onclick="javascript:addItem()"/>
									<input type="button" value="���ɰ�����ģ���ļ�" onclick="javascript:createAladdinXml()"/>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">�������</td>
								<td style="line-height: 1;">����Ʒ��</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="keywordsList != null">
								<s:iterator value="keywordsList"  status="st" var="bean">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<td><s:property value='names' /></td>
										<td>
											<c:if test="${catalogName != null }"><s:property value='catalogName' /></c:if>
											<c:if test="${catalogName == null }">δ����Ʒ��</c:if>
										</td>
										<td>
											<a
											href="${ctx}/Aladdin/edit.action?id=<s:property value='id' />&forwards=0">�༭</a>
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
											<coo8:page name="keywordsList" style="js" systemId="1" />
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