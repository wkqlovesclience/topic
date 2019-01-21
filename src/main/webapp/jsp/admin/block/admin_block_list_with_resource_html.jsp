<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%><%@ taglib prefix="s" uri="/struts-tags"%>
<table cellpadding="0" cellspacing="0" class="LBoder"> 
<tr>
	<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">块编号</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:50px;">模板编号</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">数据源编号</td> 
	<td class="TextCen fB GDDray LBodRit DText" style="width:120px;">块名字</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:90px;">显示名字</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">数据行</td>
	<td class="TextCen fB GDDray LBodRit DText" style="width:60px;">操作</td>
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
		<a href="javascript:showData(<s:property value='staticHtmlId' />,<s:property value='resourceId'/> ,<s:property value="dataNum"/>);">查看数据</a>
		<!-- 
		<a href="javascript:addData(<s:property value='staticHtmlId' />,<s:property value='resourceId'/> );">添加数据</a>
		 -->
	</td>
</tr>
</s:iterator>
</table>