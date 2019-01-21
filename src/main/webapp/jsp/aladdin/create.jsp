<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="coo8" uri="/coo8-tag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>添加泛需求词</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript">
		function yxbegin() {
		if (window.ActiveXObject) {
			document.getElementById("createTimeBegin1").click();
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", true, true);
			document.getElementById("createTimeBegin1").dispatchEvent(evt);
		}
	}
		
		$(function(){$("form").submit(function(){
			var topic = $("#topic").val();
			var keywords = $("#keywords").val();
			var remark = $("#remark").val();
			var editorValue = $("#editorValue").val();
			if(topic==""){
				alert("请填写文章标题！");
				$('#topic').focus();
				return false;
			}
			if(editorValue==""){ 
				alert("请填写文章内容！");
				$('#editorValue').focus();
				return false;
			}
		});
		});
	</script>

</head>
<body>
	<div class="mod-1">
		<div class="hd">
			<c:if test="${akeywords.id == null }">
				<h3>添加泛需求词</h3>
			</c:if>
			<c:if test="${akeywords.id != null }">
				<h3>编辑泛需求词</h3>
			</c:if>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form action="${ctx}/Aladdin/save.action" method="post">
					<s:hidden name="command"></s:hidden>
					<input id="id" name="akeywords.id" value="${akeywords.id}" type="hidden" />
					<table class="tb-1">
						<tbody>
							<tr>
								<th>泛需求词：</th>
								<td><input id="names" name="akeywords.names" value="${akeywords.names}"
									type="text" class="txt-9" /> 
								</td>
							</tr>
							<tr>
								<th>泛需求词描述：</th>
								<td><input type="text" class="txt-9" id="descr"
									name="akeywords.descr" value="${akeywords.descr}"/>
								</td>
							</tr>
							<tr>
								<th>所属品牌：</th>
								<td>
								    <s:select id="catalogId" name="akeywords.catalogId" list="catalogList" listKey="cataid" listValue="cataname" theme="simple">
                                    </s:select>
								</td>
							</tr>
							<tr>
								<th></th>
								<td><input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="button" value="重置" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>