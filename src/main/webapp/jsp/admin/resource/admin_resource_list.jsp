<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>����Դ����</title>
<link href="http://css.gomein.net.cn/topics/css/b2c_backstage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
<%@ include file="/jsp/admin/common/css.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<%@ include file="/jsp/admin/common/common_admin_css.jsp" %>
<script type="text/javascript">
function tunePage(num) {
	var url = '${ctx}/admin/resource/list.action?resource.pageIndex='+num;
	window.location = url;
}
</script>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
<table cellpadding="0" cellspacing="0">
    <tr>
	<td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
	    <table cellspacing="0" cellpadding="0">
          <tr>						
		     <td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" width="11" height="7" /></td>						
		     <td style="width:97%" class="NavText DText">����Դ����</td>	
		  </tr>
		</table>
	</td>
    </tr>   
	<tr>
    <td>
      <div class="RightBg">
			<div class="RightTab">
				<form action="${ctx}/admin/resource/list.action" method="post" id="resourceFormId" onsubmit="YUTIL.trimFormVal(this.id)">
					<input type="hidden" value="<s:property value='resourceList.pageIndex' />" name="resource.pageIndex" id="page_index"/>
					<table cellspacing="0" cellpadding="0">
						<tr>
							<td style="width:80px" class="TextRig">����Դ����&nbsp;</td>
							<td style="width:100px;color:#009933" class="TextLeft">
								<input type="text" id="name" name="resource.name" style="width:140px; height:16px;" value="${resource.name }" />
							</td>
					        <td style="width:80px" class="TextRig">����Դ����&nbsp;</td>
							<td style="width:100px" class="TextLeft">
								<input type="text" id="description" name="resource.description" style="width:140px; height:16px;" value="${resource.description}" /></td>
					        <td style="width:80px" class="TextRig">����Դ����&nbsp;</td>
					        <td style="width:100px" class="TextLeft" >	
					       	    <s:select id="type" name="resource.type" value="resource.type" theme="simple" list="#{'':'ȫ��','4':'ATG��','2':'�����','1':'��Ʒ��','3':'�ⲿ��'}">
							    </s:select>
							</td>
							<td>&nbsp;</td>
						 </tr>
						 
						 <tr class="H10"></tr>
					     <tr>
							<td class="TextRig">�޸���&nbsp;</td>
							<td class="TextLeft"><input type="text" id="updator" name="resource.updator" value="${resource.updator}" style="width:140px; height:16px;" /></td>
							<td class="TextRig">������&nbsp;</td>
							<td class="TextLeft"><input type="text" id="creator" name="resource.creator" value="${resource.creator}" style="width:140px; height:16px;"/></td>
							<td  class="TextRig">����Դ״̬&nbsp;</td>
					        <td class="TextLeft">
					        	<s:select id="status" name="resource.status" value="resource.status" theme="simple" list="#{'':'ȫ��','0':'����','1':'ͣ��' }"></s:select>
							</td>
							<td class="TextLeft">
								<input type="submit" name="Submit222" value="��ѯ" style="width:48px; height:25px; color:#485058" />
							</td>
						 </tr>
						</table>
					</form>
				</div>
			<div class="H10"></div>
			<div class="RightTabTags">
				<table cellspacing="0" cellpadding="0">
				   <tr>
				      <td style="width:16px"></td>
				      <td style="width:108px; border-bottom:none;" class="TextCen GDDDray Boder fB">							
			           <a href="${ctx}/admin/resource/list.action" style="color:white;">��������Դ</a>
			          </td>
			          
			           <td style="width:5px"></td>
			           <td style="width:108px;border-bottom:none;" class="TextCen GDray LBoder fB">
		           		<a href="${ctx}/jsp/admin/resource/admin_resource_create.jsp">�½���������Դ</a>
			           </td>
			           <td style="width:5px"></td>
                       <td  class="TextCen GDray LBoder fB" style="width:108px;border-bottom:none;">
                         <a  href="${ctx}/jsp/admin/resource/admin_atg_resource_create.jsp">�½�ATG����Դ</a>
                       </td>
					   <td style="width:5px"></td>
			           <td  class="TextCen GDray LBoder fB" style="width:108px;border-bottom:none;">
		           		<a href="${ctx}/jsp/admin/resource/admin_product_resource_create.jsp">�½���Ʒ����Դ</a>							      
		               </td>
			           	<td style="width:5px"></td>
			           <td  class="TextCen GDray LBoder fB" style="width:108px;border-bottom:none;">						         
			             <a  href="${ctx}/jsp/admin/resource/admin_outside_resource_create.jsp">�½��ⲿ����Դ</a>
				       </td>
				       
					   <td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="RightTab">
				 <table cellpadding="0" cellspacing="0" class="LBoder"> 
					<tr>
						<td class="TextCen fB GDDray LBodRit DText" width="7%">����Դ��</td> 
						<td class="TextCen fB GDDray LBodRit DText" width="15%">����Դ����</td>
						<td class="TextCen fB GDDray LBodRit DText" width="32%">����Դ����</td>
						<td class="TextCen fB GDDray LBodRit DText" width="7%">����Դ״̬</td>
						<td class="TextCen fB GDDray LBodRit DText" width="7%">����Դ����</td>
						<td class="TextCen fB GDDray LBodRit DText" width="10%">������/�޸���</td>
						<td class="TextCen fB GDDray LBodRit DText" width="14%">����/�޸�ʱ��</td>
						<td class="TextCen fB GDDray DText" width="8%">����</td>
					</tr> 
					<s:iterator value="resourceList" status="t" var="ct">
					<tr bgcolor="<s:property value="%{#t.count%2==0?'#e7eaee':'#f9f9f9'}"/>">
						<td class="TextCen DText LBodTop LBodRit">
							<a href="${ctx}/admin/resource/updateRequest.action?resourceId=<s:property value="id"/>">
								<s:property value="id"/>
							</a>
						</td>						
						<td class="TextCen DText LBodTop LBodRit" title="<s:property value="name"/>" style="text-align: left;vertical-align: middle;">
							<div class="hidden" style="width: auto;margin-top: 10px;margin-left: 10px;">
								<a href="${ctx}/admin/resource/updateRequest.action?resourceId=<s:property value="id"/>">
									<s:property value="name"/>
								</a>
							</div>
						</td> 
						<td class="TextCen DText LBodTop LBodRit" title="<s:property value="resource"/>" style="text-align: left;vertical-align: middle;">
							<div class="hidden" style="width: auto;margin-top: 10px;margin-left: 10px;">
								<s:property value="resource"/>
							</div>
						</td>
						<td class="TextCen DText LBodTop LBodRit">
							<s:property value="%{status == 0 ? '����' : 'ͣ��'}"/>
						</td>
						<td class="TextCen DText LBodTop LBodRit">
							<s:if test="%{#ct.isProcedure()}">
								�����
							</s:if>
							<s:elseif test="%{#ct.isProduct()}">
								��Ʒ��
							</s:elseif>
							<s:elseif test="%{#ct.isOutside()}">
								�ⲿ��
							</s:elseif>
							<s:elseif test="%{#ct.isAtg()}">
								ATG��
							</s:elseif>
						</td>
						<td class="TextCen DText LBodTop LBodRit">
							<s:property value="creator" />/<s:property value="updator"/>
						</td> 
						<td class="TextCen DText LBodTop LBodRit" title="<s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss"/>/<s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss"/>">
							<s:date name="createdTime" format="yyyy-MM-dd"/>
								/
							<s:date name="updatedTime" format="yyyy-MM-dd"/>
						</td> 
						<td class="TextCen DText LBodTop">
							<s:if test="%{#ct.isAtg()}">
								<a href="${ctx}/admin/resource/updateRequest.action?resourceId=<s:property value="id"/>">�༭</a>
								<a href="${ctx}/admin/resource/delete.action?resourceId=<s:property value="id"/>">ɾ��</a>								
							</s:if>
							<s:else>���ɱ༭</s:else>
						</td>
					</tr>
					</s:iterator>
				</table>
				<s:if test="resourceList!= null">
				<table>
					<tr>
						<td colspan="8" style="border: 0 none;"><div class="numpage"><yesky:page name="resourceList" style="js" systemId="1" /></div></td>
					</tr>
				</table>
				</s:if>
			</div>	
     	</div>
 	</td>
    </tr>
</table>
</body>
</html>

