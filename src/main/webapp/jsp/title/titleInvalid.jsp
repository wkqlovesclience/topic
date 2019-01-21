<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>无效商品专题管理</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
<script src="${ctx}/js/singleCalendar/WdatePicker.js"  type="text/javascript" ></script>
<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
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

</script>
</head>
<body>
	<form action="${ctx}/Titles/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>无效商品专题管理</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">

					<table style="width: 100%;">
						<tbody>


						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 30%;" />
							<col style="width: 30%;" />
							<col style="width: 40%;" />

						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;">时间</td>
								<td style="line-height: 1;">数量</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="titleInvalidPaginatedList!= null">
								<s:iterator value="titleInvalidPaginatedList"  status="st">
									<tr>
										<td><s:property value='createTime'/></td>
										<td><s:property value='count' /></td>

										<td>
											<a href="${ctx}/Titles/downloadTitleInvalid.action?createTime=<s:property value='createTime'/>">下载</a>
											&nbsp;
											<a href="${ctx}/Titles/viewOnLine.action?createTime=<s:property value='createTime'/>" target="_blank" >在线预览</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</form>
</body>
</html>