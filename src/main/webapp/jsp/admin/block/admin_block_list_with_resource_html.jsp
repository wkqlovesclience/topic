<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%><%@ taglib prefix="s" uri="/struts-tags"%>
<table cellpadding="0" cellspacing="0" class="LBoder"> 
<tr>
	<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">����</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">ģ����</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">����Դ���</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:120px;">������</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">��ʾ����</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">������</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">����</td>
</tr> 
<s:iterator value="blockList" status="t" var="ct">
<tr cellpadding="0" cellspacing="0" class="<s:property value="#t.Even ? 't-even' :''"/>" tid="<s:property value="id"/>"> 
	<td class="TextCen DText LBodTop LBodRit"><s:property value="id"/></td> 
	<td class="TextCen DText LBodTop LBodRit"><s:property value="templateId"/></td>
	<td class="TextCen DText LBodTop LBodRit"><s:property value="resourceId"/></td>
	<td class="TextCen DText LBodTop LBodRit"><s:property value="name"/></td> 
	<td class="TextCen DText LBodTop LBodRit"><s:property value="displayName"/></td>
	<td class="TextCen DText LBodTop LBodRit"><s:property value="dataNum"/></td>
	<td class="TextCen DText LBodTop LBodRit">
		<a href="javascript:showData(<s:property value='staticHtmlId' />,<s:property value='resourceId'/> ,<s:property value="dataNum"/>);">�鿴����</a>
		<!-- 
		<a href="javascript:addData(<s:property value='staticHtmlId' />,<s:property value='resourceId'/> );">�������</a>
		 -->
	</td>
</tr>
</s:iterator>
</table>