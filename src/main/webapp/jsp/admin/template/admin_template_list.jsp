<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>ģ�����</title>
<%@ include file="/jsp/admin/common/css.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<%@ include file="/jsp/admin/common/common_admin_css.jsp" %>
<style>html{*overflow-y:scroll;*overflow-x:hidden}</style>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
<table cellpadding="0" cellspacing="0">
    <tr>
	<td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
	    <table cellspacing="0" cellpadding="0">
          <tr>						
		    <td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" width="11" height="7" /></td>						
		    <td style="width:97%" class="NavText DText">ģ�����</td>	
		  </tr>
		 </table>
	</td>
    </tr>   
	<tr>
    <td>
      <div class="RightBg">
	    <div class="RightTab">
								<form action="${ctx}/admin/template/list.action" method="get" id="templateFormId" onsubmit="YUTIL.trimFormVal(this.id)">
									<input type="hidden" value="<s:property value='templateList.pageIndex' />" name="template.pageIndex" id="page-index"/>
									<table cellspacing="0" cellpadding="0" >
						        		<tr>
								        	<td style="width:67px" class="TextRig">ģ������</td>
									        <td style="width:148px" class="TextLeft">
									            &nbsp;<input type="text" name="template.name" style=" height:16px;" value="<s:property value='template.name'/>" /></td>
									        <td style="width:67px" class="TextRig">ģ������</td>
									        <td style="width:148px" class="TextLeft">
											   &nbsp;<input type="text" name="template.description" style=" height:16px;" value="<s:property value='template.description'/>"/></td>
									      	<td style="width:67px" class="TextRig">�޸���</td>
											<td style="width:148px" class="TextLeft">
											   &nbsp;<input type="text" name="template.updator" value="<s:property value='template.updator'/>" style=" height:16px;" /></td>
											<td style="width:67px" class="TextRig">������</td>
											<td style="width:148px" class="TextLeft">
											    &nbsp;<input type="text" name="template.creator" value="<s:property value='template.creator'/>" style="height:16px;"/></td>
											<td style="width:67px" class="TextRig">״̬</td>
									        <td colspan="7" style="width:112px" class="TextLeft">
									       		&nbsp;<select name="template.status" style="width:60px">
													<option value="">ȫ��</option>
													<option <s:property value="%{template.status == 0 ? 'selected' : ''}" /> value="0" >����</option>
													<option <s:property value="%{template.status == 1 ? 'selected' : ''}" /> value="1" >ͣ��</option>
												</select>
											</td>
											<td style="width:77px" class="TextCen">
											   <input type="submit" name="Submit222" value="��ѯ" style="width:48px; height:25px; color:#485058" />
											</td>
										</tr>
									</table>
								</form>
						  </div>
						  <div class="H10"></div>
						  <div class="RightTabTags">
						    <table cellspacing="0" cellpadding="0" class="DText">
						         	<tr>
							           <td style="width:26px"></td>
							           <td style="width:77px; color:white; border-bottom:none;" class="TextCen GDDDray Boder fB">							           		
							           	 <!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/template/list.action')>=0}"-->	
							           		<a style="color:#FFFFFF " href="${ctx}/admin/template/list.action">����ģ��</a>
							           	 <!--/c:if-->	
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:88px;border-bottom:none;" class="TextCen GDray LBoder fB">							           		
							           		<a href="${ctx}/jsp/admin/template/admin_template_create.jsp">�½�ģ��</a>
							           	</td>
							           	<td>&nbsp;</td>
						           	</tr>
						       	</tbody>
						  	</table>
						  </div>
						  <div class="RightTab">
						  	<table cellpadding="0" cellspacing="0" class="LBoder"> 
								<tr>
									<td class="TextCen fB GDDray LBodRit DText">ģ���</td> 
									<td class="TextCen fB GDDray LBodRit DText">ģ������</td>
									<td class="TextCen fB GDDray LBodRit DText">ģ������</td>
									<td class="TextCen fB GDDray LBodRit DText">ģ��״̬</td>
									<td class="TextCen fB GDDray LBodRit DText">������</td>
									<td class="TextCen fB GDDray LBodRit DText">����ʱ��</td>
									<td class="TextCen fB GDDray LBodRit DText">�޸���</td>
									<td class="TextCen fB GDDray LBodRit DText">�޸�ʱ��</td>
									<td class="TextCen fB GDDray DText">����</td>
								</tr> 						
							<s:iterator value="templateList" status="t" var="ct">
								<tr bgcolor="<s:property value="%{#t.count%2==0?'#e7eaee':'#f9f9f9'}"/>" tid="<s:property value="id"/>"> 
									<td class="TextCen DText LBodTop LBodRit">
									<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/template/updateRequest.action')>=0}"-->
									      <a href="${ctx}/admin/template/updateRequest.action?id=<s:property value="id"/>"><s:property value="id"/></a>
									      <!--/c:if-->
									      </td> 
									<td class="TextCen DText LBodTop LBodRit">
									      <a href="${ctx}/admin/template/updateRequest.action?id=<s:property value="id"/>"><s:property value="name"/></a></td> 
									<td class="TextCen DText LBodTop LBodRit">
									       <s:property value="description"/></td>
									<td class="TextCen DText LBodTop LBodRit">
									       <s:property value="%{enabled ? '����' : 'ͣ��'}"/></td> 
									<td class="TextCen DText LBodTop LBodRit">
									       <s:property value="creator" /></td> 
									<td class="TextCen DText LBodTop LBodRit">
									       <s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss"/></td> 
									<td class="TextCen DText LBodTop LBodRit">
									       <s:property value="updator"/></td> 
									<td class="TextCen DText LBodTop LBodRit">
									       <s:date name="updatedTime" format="yyyy-MM-dd HH:mm:ss"/></td> 
									<td class="TextCen DText LBodTop" style="white-space:normal; line-height:22px;">
										<a href="${ctx}/admin/template/updateRequest.action?id=<s:property value="id"/>">�༭</a>
										<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/block/updateRequest.action')>=0}"-->
										|
										<a href="${ctx}/admin/block/updateRequest.action?templateId=<s:property value="id"/>">��༭</a>
										<!--/c:if-->
										<s:if test="%{loadRequest}">
											|
										<a href="${ctx}/admin/template/load.action?catalogId=<s:property value='catalogId' />&type=<s:property value='type' />&templateId=<s:property value='id' />">����</a>
										</s:if>
										<s:if test="%{#parameters.htmlId != null}">
											<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/html/loadTemplate.action')>=0}"-->
											|
											<a href="${ctx}/admin/html/loadTemplate.action?html.id=<s:property value='%{#parameters.htmlId}' />&html.templateId=<s:property value='id' />">����</a>
											<!--/c:if-->
										</s:if>
										<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/template/apply.action')>=0}"-->
										|
										<a href="${ctx}/admin/template/apply.action?template.id=<s:property value='id' />">Ӧ��</a>
										<!--/c:if-->
									</td>
								</tr>								
							</s:iterator>
							
							<s:if test="! templateList.isEmpty()">
							 <tr>
							  <td colspan="9" class="LBodTop">
								<div class="numpage-box FloRig"> 
									<div class="numpage">
										<s:if test="templateList.firstPage">
										<h6>��<s:property value='%{templateList.totalRec}' />����¼��</h6> 
											<span class="numpage1" >
											��һҳ</span>
										</s:if> 
										<s:else>
											<a target="_self" href="javascript:submitPgaeSplit(<s:property value='%{templateList.pageIndex-1}' />);">��һҳ</a>
										</s:else> 
										<s:iterator var="index" begin="templateList.begin" end="templateList.end">
											<s:if test="%{templateList.pageIndex==#index}">
												<span><s:property value="#index" /></span>
											</s:if><s:else>
												<a target="_self" href="javascript:submitPgaeSplit(<s:property value='#index' />);"><s:property value="#index" /></a>
											</s:else>
										</s:iterator> 
										<s:if test="templateList.lastPage">
											<span class="numpage1">��һҳ</span>
										</s:if> <s:else>
											<a target="_self" href="javascript:submitPgaeSplit(<s:property value='%{templateList.pageIndex+1}' />);">��һҳ</a>
										</s:else>
										<s:if test="templateList.end > 1">
										<label>���� <input name="" type="text" /> ҳ</label>
										</s:if> 
									</div>
								</div>
								</td>
								</tr>
							</s:if>
						 </div>
     </div>     			
 </td>
</tr>
</table>
<script type="text/javascript">
	function submitPgaeSplit(num){
		$('#page-index').val(num);
		$('#templateFormId').submit();				
	}
</script>
</body>
</html>

