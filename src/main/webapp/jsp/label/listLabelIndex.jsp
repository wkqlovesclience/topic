<%@page import="com.coo8.topic.labelart.modal.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<title>文章索引管理</title>
<script type="text/javascript">
function sel_all(checked){ 
    var check_obj = $("input[name='checkbox']"); 
    for(var i=0; i<check_obj.length;i++){ 
        if(checked){ 
            check_obj.get(i).checked = true; 
        }
        else{ 
         check_obj.get(i).checked = false; 
        } 
    } 
    return; 
} 
function changeBlock(){
	var cc=$('input:checked');
	var str="";
	for(var j=0;j<cc.length;j++){
		str=str+$('input:checked').get(j).value+";";
	}	
	if(str == ""){
		 alert('请至少选择一项！'); 
		 return;
	} 
	if(confirm('确认删除？')){
	window.location.href="${ctx}/Label/labelIndexDelete.action?paramIds="+str;
	}
}
function tunePage(num) {
	var url = '${ctx}/Label/listLabelIndex.action?page_index='+num;
	var cindex = $('#cindex').val();
	var titleId = $('titleId').val();
	if(cindex != null && cindex != ''){
		url += '&titleIndex.cindex='+cindex;
	}
	if(titleId != null && titleId != ''){
		url += '&titleIndex.titleId='+titleId; 
	}
	window.location = url;
}
	//专题首页发布
	function publishHomePage(){
		if(confirm('确认发布？')){
			$.post('${ctx}/Label/publishLabelHomePage.action',
					function(msg){	
						if(msg == '0'){
							alert("文章首页发布成功,请访问：http://www.gome.com.cn/topic");
						}
					}
			);
		}
	}
</script>
</head>
<body>
<form action="${ctx}/Label/listLabelIndex.action" method="post" id="titleIndexForm">
	<div class="mod-1">
		<div class="hd">
			<h3>文章索引管理</h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
				专题索引：<s:select id="cindex" name="labelIndex.cindex" value="titleIndex.cindex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
				'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','_':'0~9'}">
				</s:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				专题编号：<input type="text" id="titleId" name="labelIndex.labelId" value="${labelIndex.labelId }" class="txt-5"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="搜索"/>
			</div>
			<div style="font-size: 20px;" class="container-1">
				<input type="button" value="全选" onclick="sel_all('true')" />
				<input type="button" value="取消" onclick="sel_all()" />
				<!-- <input type="button" value="删除" onclick="changeBlock()" /> -->
				<input type="button" value="新增" onclick="location.href='${ctx}/jsp/label/labelIndexAdd.jsp'"  />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="首页发布" onclick="publishHomePage()"  />
			</div>
			<div class="container-1">
				<table style="width: 100%;" class="tb-zebra tmp-class" border="0">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 21%;"/>
						<col style="width: 15%;"/>
						<col style="width: 5%;" />
						<col style="width: 15%;"/>
						<col style="width: 5%;" />
						<col style="width: 10%;"/>
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;">&nbsp;</td>
						<td style="line-height: 1;">文章索引</td>
						<td style="line-height: 1;">优先级</td>
						<td style="line-height: 1;">专题编号</td>
						<td style="line-height: 1;">专题名称</td>
						<td style="line-height: 1;">操作者</td>
						<td style="line-height: 1;">状态</td>
						<td style="line-height: 1;">操作时间</td>
						<td style="line-height: 1;">操作</td>
					</tr>
					</thead>
					<tbody>
					<s:if test="labelIndexList!= null">
						<s:iterator value="labelIndexList"  status="til">
						<tr style="height:15px;">
							<td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
							<td><s:property value="cindex"/></td>
							<td>
								<c:choose>
									<c:when test="${priority == 0 }">低</c:when>
									<c:when test="${priority == 1 }">中</c:when>
									<c:otherwise>高</c:otherwise>
								</c:choose>
							</td>
							<td><s:property value="labelId" /></td>
							<td><s:property value="title" /></td>
							<td><s:property value="operator" /></td>
							<td>
								<c:choose>
									<c:when test="${status eq '1'}">有效</c:when>
									<c:otherwise>失效</c:otherwise>
								</c:choose>
							</td>
							<td><s:date name="updatetime" format="yyyy.MM.dd HH:mm:ss" /></td>
							<td><a href="javascript:void(0)" onclick="location.href='${ctx}/Label/labelIndexEdit.action?id=${id}'">修改</a></td>
						</tr>
						</s:iterator>
						<tr>
							<td colspan="10" style="border: 0 none;"><div class="numpage"><coo8:page name="labelIndexList" style="js" systemId="1" /></div></td>
						</tr>
					</s:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>
