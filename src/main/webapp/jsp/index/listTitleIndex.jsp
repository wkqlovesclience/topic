<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<title>ר����������</title>
<script type="text/javascript">
function sel_all(checked){ 
    var check_obj = $("input[name='checkbox']"); 
    for(var i=0; i<check_obj.length;i++){ 
        if(checked){ 
            check_obj.get(i).checked = true; 
        }
        else{ 
         check_obj.get(i).checked = false; 
        } 
    } 
    return; 
} 
function changeBlock(){
	var cc=$('input:checked');
	var str="";
	for(var j=0;j<cc.length;j++){
		str=str+$('input:checked').get(j).value+";";
	}	
	if(str == ""){
		 alert('������ѡ��һ�'); 
		 return;
	} 
	if(confirm('ȷ��ɾ����')){
	window.location.href="${ctx}/Titles/titleIndexDelete.action?paramIds="+str;
	}
}
function tunePage(num) {
	var url = '${ctx}/Titles/listTitleIndex.action?page_index='+num;
	var cindex = $('#cindex').val();
	var titleId = $('titleId').val();
	if(cindex != null && cindex != ''){
		url += '&titleIndex.cindex='+cindex;
	}
	if(titleId != null && titleId != ''){
		url += '&titleIndex.titleId='+titleId; 
	}
	window.location = url;
}
	/*//ר����ҳ����
	function publishHomePage(){
		if(confirm('ȷ�Ϸ�����')){
			$.post('${ctx}/Titles/publishTitleHomePage.action',
					function(msg){	
						if(msg == '0'){
							alert("ר����ҳ�����ɹ�,����ʣ�http://www.gome.com.cn/topic");
						}
					}
			);
		}
	}
	//wapר����ҳ����
	function publishWapHomePage(){
		if(confirm('ȷ�Ϸ�����')){
			$.post('${ctx}/Titles/publishWapTitleHomePage.action',
					function(msg){	
						if(msg == '0'){
							alert("�ƶ���ר����ҳ�����ɹ�,����ʣ�http://m.gome.com.cn/topic");
						}
					}
			);
		}
	}*/
</script>
</head>
<body>
<form action="${ctx}/Titles/listTitleIndex.action" method="post" id="titleIndexForm">
	<div class="mod-1">
		<div class="hd">
			<h3>ר����������</h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
				ר��������<s:select id="cindex" name="titleIndex.cindex" value="titleIndex.cindex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
				'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','_':'0~9'}">
				</s:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				ר���ţ�<input type="text" id="titleId" name="titleIndex.titleId" value="${titleIndex.titleId }" class="txt-5"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="����"/>
			</div>
			<div style="font-size: 20px;" class="container-1">
				<input type="button" value="ȫѡ" onclick="sel_all('true')" />
				<input type="button" value="ȡ��" onclick="sel_all()" />
				<input type="button" value="ɾ��" onclick="changeBlock()" />
				<input type="button" value="����" onclick="location.href='/jsp/index/titleIndexAdd.jsp'"  />
				<%--&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="��ҳ����" onclick="publishHomePage()"  />
				<input type="button" value="�ƶ�����ҳ����" onclick="publishWapHomePage()"  />--%>
			</div>
			<div class="container-1">
				<table style="width: 100%;" class="tb-zebra tmp-class" border="0">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 21%;"/>
						<col style="width: 15%;"/>
						<col style="width: 5%;" />
						<col style="width: 15%;"/>
						<col style="width: 5%;" />
						<col style="width: 10%;"/>
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;">&nbsp;</td>
						<td style="line-height: 1;">ר������</td>
						<td style="line-height: 1;">���ȼ�</td>
						<td style="line-height: 1;">ר����</td>
						<td style="line-height: 1;">ר������</td>
						<td style="line-height: 1;">������</td>
						<td style="line-height: 1;">״̬</td>
						<td style="line-height: 1;">����ʱ��</td>
						<td style="line-height: 1;">վ��</td>
						<td style="line-height: 1;">����</td>
					</tr>
					</thead>
					<tbody>
					<s:if test="titleIndexList!= null">
						<s:iterator value="titleIndexList"  status="til">
						<tr style="height:15px;">
							<td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
							<td><s:property value="cindex"/></td>
							<td>
								<c:choose>
									<c:when test="${priority == 0 }">��</c:when>
									<c:when test="${priority == 1 }">��</c:when>
									<c:otherwise>��</c:otherwise>
								</c:choose>
							</td>
							<td><s:property value="titleId" /></td>
							<td><s:property value="title" /></td>
							<td><s:property value="operator" /></td>
							<td>
								<c:choose>
									<c:when test="${status eq '1'}">��Ч</c:when>
									<c:otherwise>ʧЧ</c:otherwise>
								</c:choose>
							</td>
							<td><s:date name="updatetime" format="yyyy.MM.dd HH:mm:ss" /></td>
							<td>
								<c:choose>
									<c:when test="${site eq 'gome'}">����</c:when>
									<c:otherwise>���</c:otherwise>
								</c:choose>
							</td>
							<td><a href="javascript:void(0)" onclick="location.href='${ctx}/Titles/titleIndexEdit.action?id=${id}'">�޸�</a></td>
						</tr>
						</s:iterator>
						<tr>
							<td colspan="10" style="border: 0 none;"><div class="numpage"><coo8:page name="titleIndexList" style="js" systemId="1" /></div></td>
						</tr>
					</s:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>
