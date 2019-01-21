<%@page import="com.coo8.hotlink.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>添加热门链接</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />

<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<script type="text/javascript">

  function getByteLen(val) {
      var len = 0;
      for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
          len += 2;
        }
        else {
          len += 1;
        }
      }
      return len;
    }
    
	$(function(){$("form").submit(function(){
			var hotName = $("#hotName").val();
			var pcUrl = $("#pcUrl").val();
			var wapUrl = $("#wapUrl").val();
			var sort = $("#sort").val();
			if(hotName==""){
				alert("请填写热门链接名称!");
				$('#hotName').focus();
				return false;
			}
			 if(getByteLen(hotName)>12){
			  alert("最多输入六个汉字的长度!");
				$('#hotName').focus();
				return false;
			} 
			//alert(getByteLen(hotName));
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
			if(pcUrl.indexOf("http://") < 0 ){
			     alert("请填写以http://开头的链接！");
				$('#pcUrl').focus();
				return false;
			}
			
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
			if(!IsNum(sort)){
			  alert("请填写正整数！");
				$('#sort').focus();
				return false;
			}
		    checkhotLinkName(hotName,pcUrl);
			var msg = $("#msg").val();
			if(msg == 'aaaa'){
			  	 alert('此热门链接已经存在');
			  	  return false;
			}
			});
		});
      //验证是否有重复数据  名称和pcurl同时存在就为重复
      function checkhotLinkName(v,j){ 
		if (v == '') {
			return;
		}
		if(j == ''){
		   return;
		}
	   $.post("${ctx}/HotLink/checkChongFu.action?hotName="+ encodeURI(encodeURI(v))+'&pcUrl='+j, function(msg) {
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
       
       function goback(qparent_pageNumer,moduleType){
    	   if(moduleType == 3){
			  window.location = "${ctx}/Category/children.action?pageNumber="+qparent_pageNumer;
    	   }
    	   if(moduleType == 2){
 			  window.location = "${ctx}/Titles/list.action?page_index="+qparent_pageNumer;
     	   }
    	   if(moduleType == 1){
  			  window.location = "${ctx}/HotKeyword/list.action?pageNumber="+qparent_pageNumer;
      	   }
		}
</script>
</head>
<body>
	<form name="form" action="${ctx}/HotLink/save.action" method="post">
	   	<s:hidden name="command"></s:hidden>
		<div class="mod-1">
			<div class="hd">
				<c:if test="${hotLink.id != null }">
					<h3>编辑热门链接</h3>
				</c:if>
				<c:if test="${hotLink.id == null}">
					<h3>添加热门链接</h3>
				</c:if>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>基本信息</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>

							<tr>
								<th>热门链接名称：</th>
								<td> 
								    <input type="hidden" name="hotLink.id"  id="id"
									value="${hotLink.id}" />
									<input type="hidden" name="msg"  id="msg"
									value="" />
								    <input type="text" class="txt-7" id="hotName"
									name="hotLink.hotName" value="${hotLink.hotName}"/><span style="color:red;">*(必填)</span>
								</td>
							</tr>
                               <input id="titleId" name="moduleId" value="${hotLink.moduleId}"
						type="hidden"/>
					<input id="titleId" name="moduleType" value="${hotLink.moduleType}"
						type="hidden" />
							<tr>
								<th>WEB链接：</th>
								<td>  <input type="text" class="txt-7" id="pcUrl" name="hotLink.pcUrl"
									value="${hotLink.pcUrl}" /><span style="color:red;">*(必填)</span></td>
							</tr>

							<tr>
								<th>WAP链接：</th>
								<td><input type="text" class="txt-7" id="wapUrl" name="hotLink.wapUrl"
									value="${hotLink.wapUrl}"  />
								</td>
							</tr>
							
							<!-- 新增的时候注意给参数赋值  在考虑新增重复的验证加入模块类型和编号-->
							 <input type="hidden" name="hotLink.moduleType"  id="moduleType"
									value="${hotLink.moduleType}" />
									
							 <input type="hidden" name="hotLink.moduleId"  id="moduleId"
									value="${hotLink.moduleId}" />	
					         
									
							<c:if test="${hotLink.id != null }">
					          <tr>
								<th>模块类型：</th>
								<td>
									<c:if test="${hotLink.moduleType == 1}">
										<input type="text" class="txt-7" 
											value="热门搜索"  disabled="disabled"/>
									</c:if>
									
									<c:if test="${hotLink.moduleType == 2}">
										<input type="text" class="txt-7" 
											value="商品专题"  disabled="disabled"/>
									</c:if>
									
									<c:if test="${hotLink.moduleType == 3}">
										<input type="text" class="txt-7" 
											value="排行榜"  disabled="disabled"/>
									</c:if>
								
								</td>
							</tr>
							<tr>
								<th>模块ID：</th>
								<td><input type="text" class="txt-7"  
									value="${hotLink.moduleId}"  disabled="disabled"/>
								</td>
							</tr>
				            </c:if>
							
							<tr>
								<th>排序：</th>
								<td><input type="text" class="txt-7" id="sort" name="hotLink.sort"
									value="${hotLink.sort}"  /><!-- <span style="color:red;">*(必填)</span> -->
								</td>
							</tr>
							<c:if test="${hotLink.id == null}">
							<tr>
								<th>模块ID：</th>
								<td>
								<s:property  value="hotLink.moduleId"/>
								</td>
							</tr>
							<tr>
								<th>模块类型：</th>
								<td>
								   <c:if test="${hotLink.moduleType == 1}">
										热门搜索
									</c:if>
									
									<c:if test="${hotLink.moduleType == 2}">
										商品专题
									</c:if>
									
									<c:if test="${hotLink.moduleType == 3}">
										排行榜
									</c:if>
								</td>
							</tr>
							</c:if>
							
							
						</tbody>
					</table>
					<div class="But">
					       <input type="hidden" name="pageNumber" value="${pageNumber}"/>
						<input type="submit" value="保存"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="重置" />
							
					  <input type="button" value="返回上一级"  onclick="goback(${pageNumber},${hotLink.moduleType});"/>
					  
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>