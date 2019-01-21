<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>关键词库</title>
		<link rel="stylesheet" href="${ctx}/styles/cui.css" />
		<style>
			html{*overflow-y:scroll;*overflow-x:hidden;}
			.fixed{
			    position:absolute;
			    width:400px;
			    height:130px;
			    background:#A8A8A8;
			    border:1px solid black;
			    text-align:center;
			    z-index: 9999;
			}
			a {
			    color: #5075a3;
			    text-decoration: none;
			}
			a:hover{
			    color: #5075a3;
			}
		</style>
		<script type="text/javascript" src="${ctx}/js/ueditor/editor_config.js"></script>
		<script type="text/javascript"  src="${ctx}/js/jquery-1.6.js"></script>
		<script type="text/javascript"  src="${ctx}/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
		<script type="text/javascript">
			$().ready(function(){
				
				$("#floatBox").hide();
				
				
				
			});
			// 分页方法
			function tunePage(num)
			{
				var url = "./listAllHotword.action?pageNumber=" + num;
				
			
				var qtitle = $("#qtitle").val();
				if(qtitle != null && qtitle != "" )
				{
					url += "&title=" + encodeURIComponent(encodeURIComponent(qtitle));
				}
				var createTime = $("#qcreateTime").val();
				if(createTime != null && createTime != "" )
				{
					url += "&createTime=" + createTime;
				}
				
				window.location = url;
				return;
			}
			
		</script>
	</head>
	<body>
		<form action="./listAllHotword.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>关键词库管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					
					搜索词名称： <input type="text" class="txt-5" id="qtitle" name="title" value="${param.title}"/> 
					
					<input type="button" onclick="clearAll()" value="重置"/>
					<input type="submit" value="搜索"/><p>&nbsp;</p>
					创建时间：
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="createTime" id="qcreateTime" class="txt-5" value="${param.createTime}"  />
					<img onclick="yxbegin()" src="http://app.gomein.net.cn/topics/images/images_3.gif" />
				</div>
				<div class="container-1" style="overflow: auto;">
					
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">序号</td>
								<td style="line-height: 1;">搜索词标题</td>
								<td style="line-height: 1;">搜索词URL</td>
								<td style="line-height: 1;">搜索词来源</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">创建者/修改者</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listAllHotword}" var="allHotWord">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${allHotWord.id}" /> </label>
								</td>
								<td>${allHotWord.id}</td>
								<td>
								    ${allHotWord.title}
								</td>
								<td>
								    ${allHotWord.url}
								</td>
								
								
								<td>
									<c:if test="${allHotWord.sourceType == 1}">热门</c:if>
									<c:if test="${allHotWord.sourceType == 2}">热词</c:if> 
									<c:if test="${allHotWord.sourceType == 3}">专题</c:if> 
								</td>
								<td>${allHotWord.createTime} / ${allHotWord.updateTime}</td>
								<td>${allHotWord.creator} / ${allHotWord.updater}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<table width="100%">
						<tfoot>
							<tr>
								<td style="border: 0 none; padding-top: 10px">
									<div class="numpage">
										<coo8:page name="listAllHotword" style="js" systemId="1" />
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