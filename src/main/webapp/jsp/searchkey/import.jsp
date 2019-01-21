<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<link href="http://css.gomein.net.cn/topics/css/b2c_backstage.css" rel="stylesheet" type="text/css" />
	<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
	<style>
		html{*overflow-y:scroll;*overflow-x:hidden}
	</style>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>
	<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
	<title>�����ȴ�</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>����ȴ�</h3>
			<h3><a href="${ctx}/SearchKeyword/listSearchwords.action?pageNumber=${pageNumber}"  >������һ��</a></h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="<c:if test="${hotSearchword == null}">./updateSearchword.action</c:if><c:if test="${hotSearchword != null}">./updateSearchword.action?id=${hotSearchword.id}</c:if>" 
					method="post" name="hotwordForm" id="hotwordForm" onreset="clearInfo()">
					<table class="tb-1">
						<tbody>
							
							<tr>
								<td width="100">�����ʱ��⣺</td>
								<td>
									<input type="hidden" id="hotId" name="id" value="${hotSearchword.id}"/>
									<input type="text" value="${hotSearchword.title}" id="title" class="inbox" name="title"/>
									<font color="red">*</font>
									
									
									<input type="button" id="check_product" value="ȷ��" onclick="check()"/>
									<br />
									<span style="color: red;" id="error_info"></span>
									
								</td>
						    </tr>
							<tr>
								<td width="100">�����ʷִʣ�</td>
								<td>
									<input type="text" value="${hotSearchword.tag}" id="tag" class="inbox" name="tag"/>
								</td>
							
							</tr>
							
							<tr>
								<td width="100">&nbsp;</td>
								<td>
								    <input type="hidden" name="pageNumber" value="${pageNumber}"/>
									<input type="button" onclick="sub()" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="����" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		// �����ȴ�
		var isRightProductFlag = 1;
		function sub()
		{
			
			var title = $.trim($("#title").val());
			if(!title)
			{
				alert("����������ʱ���");
				return;
			}
			
			var id=$("#hotId").val();
			$.post("./check.action", {title:title,id:id}, function(result){
				if($.trim(result) == 'File')
				{   
					return;
				}
			});
			
			$("#hotwordForm").submit();
		}
		
		//����Ʒ�Ƿ����
		function check()
		{
			var title = $("#title").val();
			if(!title)
			{
				alert("����������ʱ���");
				return;
			}
			var id=$("#hotId").val();
			$.post("./check.action", {title:title,id:id}, function(result){
				if($.trim(result) == 'File')
				{   
				    $("#error_info").css('color','red');
					$("#error_info").html("�Բ��𣬸���Ʒ���Ʋ�����");
				}else{
				    $("#title").css('border','');
				    $("#error_info").css('color','blue');
					$("#error_info").html("����Ʒ����"+",��Ʒ���ƣ�"+title);
				}
			});
		}
		
		// ��ȡ�ӱ�ǩ����
		/**
		function getSubTags(parent_id)
		{
			$.post("./soptions.action", {parent_id: parent_id}, function(result){
				$("#secondTagId").html("");
				$("#secondTagId").html(result);
			});
		}
		*/
		
		function clearInfo (){
		    $("#title").css('border','');
		    $("#error_info").html("");
		};
	</script>
</body>
</html>