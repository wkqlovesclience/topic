<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>块管理</title>
<%@ include file="/jsp/admin/common/css.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<%@ include file="/jsp/admin/common/common_admin_css.jsp" %>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
<table cellpadding="0" cellspacing="0">
    <tr>
      <td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
	    <table cellspacing="0" cellpadding="0">
          <tr>
		     <td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" style="width:11" height="7" /></td>
             <td style="width:97%" class="NavText DText">块管理</td>		
			        			</tr>
		      			</table>
		      		</td>
    			</tr>   
				<tr>
      				<td>
      					<div class="RightBg">
							<div class="RightTab">
								<form action="${ctx}/admin/block/list.action" method="post" id="blockFormId" enctype="application/x-www-form-urlencoded" onsubmit="YUTIL.trimFormVal(this.id)">
	    							<input type="hidden" value="<s:property value='blockList.pageIndex' />" name="block.pageIndex" id="page-index" />
							    	<table cellspacing="0" cellpadding="1">
						        		<tr>
								        	<td style="width:67px" class="TextRig">块名字</td>
									        <td style="width:160px" class="TextLeft">
											&nbsp;<input type="text" name="block.name" style="width:140px; height:16px;" value="<s:property value='block.name'/>" /></td>
									        <td style="width:67px" class="TextRig">块描述</td>
									        <td style="width:160px" class="TextLeft">
											&nbsp;<input type="text" name="block.description" style="width:140px; height:16px;" value="<s:property value='block.description'/>"/></td>
									        <td style="width:67px" class="TextRig">显示名字</td>
									        <td style="width:160px" class="TextLeft">
											&nbsp;<input type="text" name="block.displayName" style="width:140px; height:16px;" value="<s:property value='block.displayName'/>"/></td>
									        <td class="TextRig">类型</td>
									        <td class="TextLeft">
									       		&nbsp;<select name="block.type" style="width:100px;">
													<option value="">全部</option>
													<option <s:property value="%{block.isCommonBlock() ? 'selected' : ''}" /> value="2" >通用手动块</option>
													<option <s:property value="%{block.isTemplateRelatedBlock() ? 'selected' : ''}" /> value="1" >模板关联手动块</option>
													<option <s:property value="%{block.isAutoBlock() ? 'selected' : ''}" /> value="0" >自动块</option>
												</select>											</td>
						          		</tr>
							       		<tr>
							       			<td class="TextRig">数据源编号</td>
											<td class="TextLeft">&nbsp;<input type="text" name="block.resourceId" value="<s:property value='block.resourceId'/>" style="width:140px; height:16px;" /></td>
											<td class="TextRig">修改人</td>
											<td class="TextLeft">&nbsp;<input type="text" name="block.updator" value="<s:property value='block.updator'/>" style="width:140px; height:16px;" /></td>
											<td class="TextRig">创建人</td>
											<td class="TextLeft">&nbsp;<input type="text" name="block.creator" value="<s:property value='block.creator'/>" style="width:140px; height:16px;"/>
											</td>
											<td class="TextRig">状态</td>
									        <td class="TextLeft">&nbsp;<select name="block.status" style="width:100px;">
													<option value="">全部</option>
													<option <s:property value="%{block.status == 0 ? 'selected' : ''}" /> value="0" >启用</option>
													<option <s:property value="%{block.status == 1 ? 'selected' : ''}" /> value="1" >停用</option>
												</select>&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit222" value="查询" style="width:48px; height:25px; color:#485058" />
												</td>
										</tr>
									</table>
								</form>
						  </div>
						  <div class="H10"></div>
						  <div class="RightTabTags">
						    <table height="25" cellspacing="0" cellpadding="0">
						    	<tbody>
						         	<tr>
							           <td style="width:16px"></td>
							           <td style="width:110px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="color:#FFFFFF " href="${ctx}/admin/block/list.action">所有块</a>
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:80px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           	   <a href="${ctx}/jsp/admin/block/admin_common_block_create.jsp">新建通用块</a>
							           	</td>
							           	<td style="width:5px"></td>
							            <td style="width:80px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a href="${ctx}/admin/block/autoCreateRequest.action">新建自动块</a>
							           	</td>
							           	<td>&nbsp;</td>
						           	</tr>
						       	</tbody>
						    </table>
						  </div>
						  <div class="RightTab">
						  	<table cellpadding="0" cellspacing="0" class="LBoder"> 
								<tr>
									<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">块编号</td> 
									<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">模板编号</td> 
									<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">数据源编号</td> 
									<td class="TextCen fB GDDray LBodRit DText" style="width:120px;">块名字</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">类型</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:100px;">块描述</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">显示名字</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">块状态</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">数据行</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">发布位置</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">创建者/修改人</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">创建/修改时间</td>
									<td  class="TextCen fB GDDray DText" style="width:50px;">操作</td>
								</tr> 
							<s:iterator value="blockList" status="t" var="ct">
								<tr cellpadding="0" cellspacing="0" class="<s:property value="#t.Even ? 't-even' :''"/>" tid="<s:property value="id"/>"> 
									<td class="TextCen DText LBodTop LBodRit">
										<s:property value="id"/>
									</td> 
									<td class="TextCen DText LBodTop LBodRit"><s:property value="templateId"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="resourceId"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="name"/></td> 
									<td class="TextCen DText LBodTop LBodRit">
										<s:if test="%{#ct.isAutoBlock()}">
											自动块
										</s:if><s:elseif test="%{#ct.isCommonBlock()}">
											通用手动块
										</s:elseif><s:elseif test="%{#ct.isTemplateRelatedBlock()}">
											模板关联手动块
										</s:elseif>
									</td>
									<td class="TextCen DText LBodTop LBodRit" title="<s:property value="description"/>">
										<s:property value="description"/>
									</td>
									
									<td class="TextCen DText LBodTop LBodRit"><s:property value="displayName"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="%{status == 0 ? '启用' : '停用'}"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="dataNum"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="location"/></td>
									<td class="TextCen DText LBodTop LBodRit"><s:property value="creator" />/<s:property value="updator"/></td> 
									<td class="TextCen DText LBodTop LBodRit" title="<s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss"/>/<s:date name="updatedTime" format="yyyy-MM-dd HH:mm:ss"/>">
										<s:date name="createdTime" format="yyyy-MM-dd"/>
											/
										<s:date name="updatedTime" format="yyyy-MM-dd"/>
									</td> 
									<td class="TextCen DText LBodTop">
									<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/block/autoBlockUpdateRequest.action')>=0}"  -->
										<s:if test="%{#ct.isCommonBlock()}">
											<a href="${ctx}/admin/block/commonBlockUpdateRequest.action?blockId=<s:property value="id"/>">编辑</a>
										</s:if>
										<s:elseif test="%{#ct.isAutoBlock() && #ct.templateId != null}">
											<a href="${ctx}/admin/block/updateRequest.action?blockId=<s:property value="id"/>&templateId=<s:property value='templateId' />">编辑</a>
										</s:elseif>
										<s:elseif test="%{#ct.isAutoBlock() && #ct.templateId == null}">
											<a href="${ctx}/admin/block/autoBlockUpdateRequest.action?blockId=<s:property value="id"/>">编辑</a>
										</s:elseif>
									<!--/c:if -->
									</td>
								</tr>
							</s:iterator>
							<tr>
							  <td colspan="13" class="LBodTop">
							<s:if test="! blockList.isEmpty()">
								<div class="numpage-box FloRig"> 
									<div class="numpage">
										<s:if test="blockList.firstPage">
										<h6>共<s:property value='%{blockList.totalRec}' />条记录：</h6> 
											<span class="numpage1" >
											上一页</span>
										</s:if> <s:else>
											<a target="_self" href="javascript:submitPgaeSplit(<s:property value='%{blockList.pageIndex-1}' />);">上一页</a>
										</s:else> 
										<s:iterator var="index" begin="blockList.begin" end="blockList.end">
											<s:if test="%{blockList.pageIndex==#index}">
												<span><s:property value="#index" /></span>
											</s:if><s:else>
												<a target="_self" href="javascript:submitPgaeSplit(<s:property value='#index' />);"><s:property value="#index" /></a>
											</s:else>
										</s:iterator> 
										<s:if test="blockList.lastPage">
											<span class="numpage1">下一页</span>
										</s:if> <s:else>
											<a target="_self" href="javascript:submitPgaeSplit(<s:property value='%{blockList.pageIndex+1}' />);">下一页</a>
										</s:else>
										<s:if test="blockList.end > 1">
										<label>到第 <input name="" type="text" /> 页&nbsp;</label>
										</s:if> 
									</div>
								</div> 
								</s:if>
								</td>
								</tr>
							
  	</table>
  	</div>
  	</div>
  	</td>
  	</tr>
  	</table>
  	
  	
  	
  	
<script type="text/javascript">
	function submitPgaeSplit(num){
		$('#page-index').val(num);
		document.getElementById("blockFormId").submit();			
	}
</script>
</body>
</html>

