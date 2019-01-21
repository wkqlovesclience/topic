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
<title>�������</title>
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
    				alert('·���Ѿ�����!');
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
					alert("�Բ������ϴ����ļ����Ϸ����ļ�����ӦΪͼƬ��ʽ��");
					$('#pic').focus();
					return false;
				}
			}
			if(newsId == ""){
				if(!fileName){
				alert("�������Ҫ�ϴ����ļ����ļ�����ӦΪͼƬ��ʽ��");
				$('#pic').focus();
				return false;
				}
			}
			
			
			if(topic==""){
				alert("����д���±��⣡");
				$('#topic').focus();
				return false;
			}
			if(editorValue==""){ 
				alert("����д�������ݣ�");
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
				<h3>�������</h3>
			</c:if>
			<c:if test="${news.id != null }">
				<h3>�༭����</h3>	
			</c:if>
			<h3><a href="${ctx}/Titles/list.action?page_index=${pageNumber}"  >������һ��</a></h3>
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
								<th>�ϴ�����ͷͼ��</th>
								<td>
								<input type="file" id="pic" name="pic"/>								
								<input type="text" class="txt-9" value="${news.picUrl}"   disabled="disabled" "/>								
								</td>
							</tr>
							
							<tr>
								<th>���±��⣺</th>
								<td><input id="topic" name="news.topic" value="${news.topic}"
									type="text" class="txt-9" /> &nbsp;�Ƽ�&nbsp;<input id="isTop"
									name="news.isTop"  
									checked="checked"
									value="1" type="checkbox"  />
									 
									<br><span class="gray">��ҳtitleͨ�������������ע���ص㣬����ж���ؼ��֣������� "|"  "��"  ","  "_" (��������) �ȷ��ŷָ�  </span>
								
								</td>
							</tr>

							<tr>
								<th>�ؼ��ʣ�</th>
								<td><input type="text" class="txt-9" id="keywords"
									name="news.keywords" value="${news.keywords}"/>
									<br><span class="gray">Keywords �������ҳ��ͷ���� Meta ��ǩ�У����ڼ�¼��ҳ��Ĺؼ��֣�����ؼ��ּ����ð�Ƕ��� "," ����</span>
								
								</td>
							</tr>
							
							<tr>
								<th>��ǩ��</th>
								<td><input type="text" class="txt-7" id="tags" name="tags"
									value="${tags}"  /><span
									class="gray">�������ǩ�á�,������,������5����ǩ,ÿ����ǩ�������4���֡���</span>
								</td>
							</tr>

							<tr>
								<th>������</th>
								<td><textarea class="txtarea-1" id="remark" name="news.remark">${news.remark}</textarea>
								  <br><span class="gray">Description ������ҳ��ͷ���� Meta ��ǩ�У����ڼ�¼��ҳ��ĸ�Ҫ������</span>
								
								</td>
							</tr>
							<tr>
								<th>������Դ��</th>
								<td><input type="text" class="txt-5" name="news.sourceUrl" value="${news.sourceUrl}"/></td>
							</tr>
							<tr>
								<th>����ʱ�䣺</th>
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
								<th>���ģ�</th>
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
								<!-- <input type="button" value="ƥ��ؼ���" onclick="getAnchorKeywords()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
								<input type="submit" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="button" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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