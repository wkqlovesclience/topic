<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.AnchorAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>添加关键词</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />

<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<script type="text/javascript">
	$(function(){$("form").submit(function(){
			var keyName = $("#keyName").val();
			var pcUrl = $("#pcUrl").val();
			var wapUrl = $("#wapUrl").val();
			var rate = $("#rate").val();
			var youxianji = $("#youxianji").val();
			if(keyName==""){
				alert("请填写关键词！");
				$('#keyName').focus();
				return false;
			}
			if(pcUrl==""){ 
				alert("请填写WEB链接！");
				$('#pcUrl').focus();
				return false;
			}
			
			if(pcUrl.indexOf("gome.com.cn") < 0 ){
			     alert("请填写包含gome.com.cn域名url链接！");
				$('#pcUrl').focus();
				return false;
			}
		/* 	if(pcUrl.indexOf("http://") < 0 ){
			     alert("请填写以http://开头的链接！");
				$('#pcUrl').focus();
				return false;
			} */
			
			if(wapUrl != ""){
				if(wapUrl.indexOf("gome.com.cn") < 0 ){
				     alert("请填写包含gome.com.cn域名url链接！");
					$('#wapUrl').focus();
					return false;
				}
				if(wapUrl.indexOf("http://") < 0 ){
				     alert("请填写以http://开头的链接！");
					$('#wapUrl').focus();
					return false;
				}
			}
			if(rate==""){ 
				alert("请填写频率！");
				$('#rate').focus();
				return false;
			}
			if(!IsNum(rate)){
			  alert("请填写正整数！");
				$('#rate').focus();
				return false;
			}
			if(youxianji==""){ 
				alert("请填写优先级！");
				$('#youxianji').focus();
				return false;
			}
			if(!IsNum(youxianji)){
			  alert("请填写正整数！");
				$('#youxianji').focus();
				return false;
			}
			if(parseInt(rate)<0 || parseInt(rate)>3){
			 alert("输入频率范围为1-3！");
				$('#rate').focus();
				return false;
			}
		    
			var id = ${akeywords.id};
			if(id == null){
				checkTitleName(keyName,pcUrl);
				var msg = $("#msg").val();
				if(msg == 'aaaa'){
				  	 alert('此关键词已经存在');
				  	  return false;
				}
				}
			});
		});
      //验证是否有重复数据  名称和pcurl同时存在就为重复
      function checkTitleName(v,j){ 
		if (v == '') {
			return;
		}
		if(j == ''){
		   return;
		}
	   $.post("${ctx}/AnchorKeywords/checkChongFu.action?keyName="+ encodeURI(encodeURI(v))+'&pcUrl='+j, function(msg) {
		    if (msg != "yes") {
		  	    $("#msg").val("aaaa");
			}else{
			  $("#msg").val("bbbb");
			}
		}); 
	 }
	 
       function IsNum(num){
	    var reNum=/^\d*$/;
	    return(reNum.test(num));
	   } 
	   
	 
       $(document).ready(function() {
           var id = $("#id").val();
       if(id == null || id == ""){
          $("#rate").val(1);
       }
       $.ajaxSetup({  
       async : false  
       });
     });

</script>
</head>
<body>
	<form name="form" action="${ctx}/AnchorKeywords/save.action" method="post">
	   	<s:hidden name="command"></s:hidden>
		<div class="mod-1">
			<div class="hd">
				<c:if test="${akeywords.id != null }">
					<h3>编辑关键词</h3>
				</c:if>
				<c:if test="${akeywords.id == null}">
					<h3>添加关键词</h3>
				</c:if>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>基本信息</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>

							<tr>
								<th>关键词：</th>
								<td> 
								    <input type="hidden" name="akeywords.id"  id="id"
									value="${akeywords.id}" />
									<input type="hidden" name="msg"  id="msg"
									value="" />
								    <input type="text" class="txt-7" id="keyName"
									name="akeywords.keyName" value="${akeywords.keyName}"/><span style="color:red;">*(必填)</span>
								</td>
							</tr>

							<tr>
								<th>WEB链接：</th>
								<td>  <input type="text" class="txt-7" id="pcUrl" name="akeywords.pcUrl"
									value="${akeywords.pcUrl}" /><span style="color:red;">*(必填)</span></td>
							</tr>

							<tr>
								<th>WAP链接：</th>
								<td><input type="text" class="txt-7" id="wapUrl" name="akeywords.wapUrl"
									value="${akeywords.wapUrl}"  /></span>
								</td>
							</tr>
							
							<tr>
								<th>频率：</th>
								<td><input type="text" class="txt-7" id="rate" name="akeywords.rate"
									value="${akeywords.rate}"  /><span style="color:red;">*(必填)</span>
								</td>
							</tr>
							
							<tr>
								<th>优先级：</th>
								<td><input type="text" class="txt-7" id="youxianji" name="akeywords.youxianji"
									value="${akeywords.youxianji}"  /><span style="color:red;">*(必填)</span>
								</td>
							</tr>
							
						</tbody>
					</table>
					<div class="But">
						<input type="submit" value="保存"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="重置" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>