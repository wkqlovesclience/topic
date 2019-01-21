<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<link href="http://css.gome.com.cn/topics/css/b2c_backstage.css"
	rel="stylesheet" type="text/css" />
<link href="http://css.gome.com.cn/topics/css/OPTBG.css"
	rel="stylesheet" type="text/css" />
<style>
html {
	*overflow-y: scroll;
	*overflow-x: hidden
}
</style>
<script type="text/javascript" charset="GBK"
	src="${ctx}/js/jquery-1.6.js"></script>
<script type="text/javascript"
	src="${ctx}/js/singleCalendar/WdatePicker.js"></script>

<title>新增商品黑名单</title>
</head>
<body
	style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; *overflow-y: hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>新增黑名单</h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="../../ProductBlack/add.action" method="post" name="addBlack"
					id="addBlack">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">商品ID：</td>
								<td><input type="text" value="" class="inbox" name="proId" />
									<font color="red">*</font></td>
							</tr>
							<tr>
								<td width="100"></td>
								<td><input type="button" onclick="sub()" value="保存" /> <input
									type="button" value="返回上一级" onclick="goback(${pageNumber});" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
		function sub()
		{
			document.forms["addBlack"].submit();
		}
	</script>
</body>
</html>