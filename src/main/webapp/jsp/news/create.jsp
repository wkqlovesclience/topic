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
<title>添加文章</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   

<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript">
       /*  var flagAttr = 0;
        var flagButton = false; */
		function yxbegin() {
		if (window.ActiveXObject) {
			document.getElementById("createTimeBegin1").click();
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", true, true);
			document.getElementById("createTimeBegin1").dispatchEvent(evt);
		}
	}
     
           

        
        
        function checkUrl(v){
    		if(v!=""){
    			var str= new Array();  
    			str=v.split("//");
    			if(str[0]!="http:"){
    				alert('路径已经存在!');
    			}
    		}
    	}
              
		$(function(){
			$("form").submit(function(){				
			var topic = $("#topic").val();			
			var keywords = $("#keywords").val();
			var tags = $("#tags").val();
			var remark = $("#remark").val();
			var editorValue = $("#editorValue").val();			
			var fileName = $.trim($("#pic").val());
			var newsId = $("#id").val();
			checkPaths();
			fileName = fileName.substring(fileName.lastIndexOf("."));
			
			if(fileName)
			{
				if(fileName == ".png" || fileName == ".jpg" || fileName == ".gif" || fileName == ".bmp")
				{
					document.forms["addForm"].submit();
				}
				else
				{
					alert("对不起，您上传的文件不合法，文件类型应为图片格式。");
					$('#pic').focus();
					return false;
				}
			}
			if(newsId == ""){
				if(!fileName){
				alert("请您添加要上传的文件，文件类型应为图片格式。");
				$('#pic').focus();
				return false;
				}
			}
			
			
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
	<div id="content_A" style="display:none">
	</div>
	<div class="mod-1">
		<div class="hd">
			<c:if test="${news.id == null }">
				<h3>添加文章</h3>
			</c:if>
			<c:if test="${news.id != null }">
				<h3>编辑文章</h3>	
			</c:if>
			<h3><a href="${ctx}/Titles/list.action?page_index=${pageNumber}"  >返回上一级</a></h3>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form action="${ctx}/News/save.action" method="post" id="addForm" enctype="multipart/form-data">
					<s:hidden name="command"></s:hidden>					
					<s:hidden name="news.creator"></s:hidden>	
					<c:if test="${news.id == null }">
						<input id="id" name="news.id" value="" type="hidden" />
					</c:if>
					<c:if test="${news.id != null }">
						<input id="id" name="news.id" value="${news.id}" type="hidden" />
					</c:if>				
					<input id="anchorContent" name="anchorContent" value="" type="hidden" />
					<input id="titleId" name="news.titleId" value="${news.titleId}"
						type="hidden" />
					<input id="anchorIds" name="anchorIds" value=""
						type="hidden" />
					<table class="tb-1" style="width:100%">
						<tbody>
							<tr>
								<th>上传文章头图：</th>
								<td>
								<input type="file" id="pic" name="pic"/>								
								<input type="text" class="txt-9" value="${news.picUrl}"   disabled="disabled" "/>								
								</td>
							</tr>
							
							<tr>
								<th>文章标题：</th>
								<td><input id="topic" name="news.topic" value="${news.topic}"
									type="text" class="txt-9" /> &nbsp;推荐&nbsp;<input id="isTop"
									name="news.isTop"  
									checked="checked"
									value="1" type="checkbox"  />
									 
									<br><span class="gray">网页title通常是搜索引擎关注的重点，如果有多个关键字，建议用 "|"  "、"  ","  "_" (不含引号) 等符号分隔  </span>
								
								</td>
							</tr>

							<tr>
								<th>关键词：</th>
								<td><input type="text" class="txt-9" id="keywords"
									name="news.keywords" value="${news.keywords}"/>
									<br><span class="gray">Keywords 项出现在页面头部的 Meta 标签中，用于记录本页面的关键字，多个关键字间请用半角逗号 "," 隔开</span>
								
								</td>
							</tr>
							
							<tr>
								<th>标签：</th>
								<td><input type="text" class="txt-7" id="tags" name="tags"
									value="${tags}"  /><span
									class="gray">（多个标签用“,”隔开,最多添加5个标签,每个标签字数最多4个字。）</span>
								</td>
							</tr>

							<tr>
								<th>描述：</th>
								<td><textarea class="txtarea-1" id="remark" name="news.remark">${news.remark}</textarea>
								  <br><span class="gray">Description 出现在页面头部的 Meta 标签中，用于记录本页面的概要与描述</span>
								
								</td>
							</tr>
							<tr>
								<th>文章来源：</th>
								<td><input type="text" class="txt-5" name="news.sourceUrl" value="${news.sourceUrl}"/></td>
							</tr>
							<tr>
								<th>发布时间：</th>
								<td><input readonly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text"
									 id="createTimeBegin1" name="pubTime" class="txt-5" 
									value="<s:date name="news.PublicTime"
												format="yyyy-MM-dd HH:mm:ss" />"  /><img onclick="yxbegin()"
									src="http://app.gomein.net.cn/topics/images/images_3.gif" /> 
<!-- 									<input -->
<%-- 									type="text" class="txt-5" value="${model.createTimeString}" --%>
<!-- 									id="createTimeString" name="createTimeString" /><img -->
<!-- 									src="http://app.gomein.net.cn/topics/images/images_3.gif" /> -->
								</td>
							</tr>
							<tr>
								<th>正文：</th>
								<td>
									<div>
									<textarea id="editor">${news.contents}</textarea>
									<script type="text/javascript">
										var editor = new baidu.editor.ui.Editor();
										editor.render('editor');
									</script>
									</div>
								</td>
							</tr>
							<tr>
							 <th></th>
							 <td><table id="anthorKeyword"></table></td>
							</tr>
							<tr>
								<th></th>
								<td>
								<!-- <input type="button" value="匹配关键词" onclick="getAnchorKeywords()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
								<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="button" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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