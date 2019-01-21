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
		.mask {
			width: 100%;
			height: 100%;
			position: fixed;
			left: 0;
			top: 0;
			z-index: 100000;

			background-color: #000;
			opacity: 0.6;
			display: none;
		}
		.maskTip {
			padding: 10px 20px;
			border-radius: 5px;
			background-color: #fff;
			color: #000;
			font: 24px/30px '微软雅黑';
			max-width: 200px;
			text-align: center;
			z-index: 100001;
			border: 1px solid #ccc;
			position: fixed;
			left: 50%;
			top: 50%;
			display: none;
		}
	</style>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>
	<title>热词任务管理</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mask" id="mask"></div>
	<div class="maskTip" id="maskTip">正在加载中……</div>
	<div class="mod-1">
		<div class="hd">
			<h3>修改热词任务</h3>
			<h3><a href="${ctx}/HotKeyword/listTask.action"  >返回上一级</a></h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="./updateHotWordTask.action" 
					method="post" name="hotwordTaskForm" id="hotwordTaskForm" onreset="clearInfo()">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">说明：</td>
								<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
                                    <td>
                                    	<p>定时发布 全量数据</p>
                                    </td>
                                </tr>
								</table>
								</td>
							</tr>
							<tr>
								<td width="100">发布开始序列：</td>
								<td>
									<input type="text" value ="${numSat}" id ="numSat" name = "numSat" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></input>
									<font color="red">已发布热词总量:${num}</font>
								</td>
							</tr>
							<tr>
								<td width="100">发布数量/日：</td>
								<td>
									<input type="text" value ="${numEnd}" id ="numEnd" name = "numEnd" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></input>
								</td>
							</tr>
							<tr>
								<td width="100">&nbsp;</td>
								<td>
									<input type="button" onclick="sub()" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
	function sub(){
		var numSat = $("#numSat").val();
		var numEnd = $("#numEnd").val();
		var checkNum = ${num};
		if(numSat==null || numSat=="")
		{
			alert("请添加正确的序列");
			return;
		}
		if(numEnd==null || numEnd=="")
		{
			alert("请添加正确的当日更新数量");
			return;
		}
		if(numSat >= checkNum){
			alert("开始序列不能大于热词总量");
			return;
		}
		if(numEnd > 40000){
			alert("本项目由异步调度器组成，经测试，并不建议添加大于40,000的更新量,如有必要，请联系管理员");
			return;
		}
		showLoadding()
		$("#hotwordTaskForm").submit();
	}
	
	function showLoadding() {
		var width = $(document).width();
		var height = $(document).height();
		$('#mask').css({
            width: width,
            height: height
        }).show();
		$('#maskTip').css({
            left: (width-$('#maskTip').outerWidth())/2,
            top: (height-$('#maskTip').outerHeight())/2,
            margin: 0
        }).show();
	}

	function hideLoadding() {
		$('#mask,#maskTip').hide();
	}
</script>
</body>
</html>