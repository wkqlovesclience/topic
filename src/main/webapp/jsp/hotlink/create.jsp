<%@page import="com.coo8.hotlink.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>�����������</title>
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
				alert("����д������������!");
				$('#hotName').focus();
				return false;
			}
			 if(getByteLen(hotName)>12){
			  alert("��������������ֵĳ���!");
				$('#hotName').focus();
				return false;
			} 
			//alert(getByteLen(hotName));
			if(pcUrl==""){ 
				alert("����дWEB���ӣ�");
				$('#pcUrl').focus();
				return false;
			}
			
			if(pcUrl.indexOf("gome.com.cn") < 0 ){
			     alert("����д����gome.com.cn����url���ӣ�");
				$('#pcUrl').focus();
				return false;
			}
			if(pcUrl.indexOf("http://") < 0 ){
			     alert("����д��http://��ͷ�����ӣ�");
				$('#pcUrl').focus();
				return false;
			}
			
			if(wapUrl != ""){
				if(wapUrl.indexOf("gome.com.cn") < 0 ){
				     alert("����д����gome.com.cn����url���ӣ�");
					$('#wapUrl').focus();
					return false;
				}
				if(wapUrl.indexOf("http://") < 0 ){
				     alert("����д��http://��ͷ�����ӣ�");
					$('#wapUrl').focus();
					return false;
				}
			}
			if(!IsNum(sort)){
			  alert("����д��������");
				$('#sort').focus();
				return false;
			}
		    checkhotLinkName(hotName,pcUrl);
			var msg = $("#msg").val();
			if(msg == 'aaaa'){
			  	 alert('�����������Ѿ�����');
			  	  return false;
			}
			});
		});
      //��֤�Ƿ����ظ�����  ���ƺ�pcurlͬʱ���ھ�Ϊ�ظ�
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
					<h3>�༭��������</h3>
				</c:if>
				<c:if test="${hotLink.id == null}">
					<h3>�����������</h3>
				</c:if>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>������Ϣ</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>

							<tr>
								<th>�����������ƣ�</th>
								<td> 
								    <input type="hidden" name="hotLink.id"  id="id"
									value="${hotLink.id}" />
									<input type="hidden" name="msg"  id="msg"
									value="" />
								    <input type="text" class="txt-7" id="hotName"
									name="hotLink.hotName" value="${hotLink.hotName}"/><span style="color:red;">*(����)</span>
								</td>
							</tr>
                               <input id="titleId" name="moduleId" value="${hotLink.moduleId}"
						type="hidden"/>
					<input id="titleId" name="moduleType" value="${hotLink.moduleType}"
						type="hidden" />
							<tr>
								<th>WEB���ӣ�</th>
								<td>  <input type="text" class="txt-7" id="pcUrl" name="hotLink.pcUrl"
									value="${hotLink.pcUrl}" /><span style="color:red;">*(����)</span></td>
							</tr>

							<tr>
								<th>WAP���ӣ�</th>
								<td><input type="text" class="txt-7" id="wapUrl" name="hotLink.wapUrl"
									value="${hotLink.wapUrl}"  />
								</td>
							</tr>
							
							<!-- ������ʱ��ע���������ֵ  �ڿ��������ظ�����֤����ģ�����ͺͱ��-->
							 <input type="hidden" name="hotLink.moduleType"  id="moduleType"
									value="${hotLink.moduleType}" />
									
							 <input type="hidden" name="hotLink.moduleId"  id="moduleId"
									value="${hotLink.moduleId}" />	
					         
									
							<c:if test="${hotLink.id != null }">
					          <tr>
								<th>ģ�����ͣ�</th>
								<td>
									<c:if test="${hotLink.moduleType == 1}">
										<input type="text" class="txt-7" 
											value="��������"  disabled="disabled"/>
									</c:if>
									
									<c:if test="${hotLink.moduleType == 2}">
										<input type="text" class="txt-7" 
											value="��Ʒר��"  disabled="disabled"/>
									</c:if>
									
									<c:if test="${hotLink.moduleType == 3}">
										<input type="text" class="txt-7" 
											value="���а�"  disabled="disabled"/>
									</c:if>
								
								</td>
							</tr>
							<tr>
								<th>ģ��ID��</th>
								<td><input type="text" class="txt-7"  
									value="${hotLink.moduleId}"  disabled="disabled"/>
								</td>
							</tr>
				            </c:if>
							
							<tr>
								<th>����</th>
								<td><input type="text" class="txt-7" id="sort" name="hotLink.sort"
									value="${hotLink.sort}"  /><!-- <span style="color:red;">*(����)</span> -->
								</td>
							</tr>
							<c:if test="${hotLink.id == null}">
							<tr>
								<th>ģ��ID��</th>
								<td>
								<s:property  value="hotLink.moduleId"/>
								</td>
							</tr>
							<tr>
								<th>ģ�����ͣ�</th>
								<td>
								   <c:if test="${hotLink.moduleType == 1}">
										��������
									</c:if>
									
									<c:if test="${hotLink.moduleType == 2}">
										��Ʒר��
									</c:if>
									
									<c:if test="${hotLink.moduleType == 3}">
										���а�
									</c:if>
								</td>
							</tr>
							</c:if>
							
							
						</tbody>
					</table>
					<div class="But">
					       <input type="hidden" name="pageNumber" value="${pageNumber}"/>
						<input type="submit" value="����"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="����" />
							
					  <input type="button" value="������һ��"  onclick="goback(${pageNumber},${hotLink.moduleType});"/>
					  
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>