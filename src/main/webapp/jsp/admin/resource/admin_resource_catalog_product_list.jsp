<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>

<s:set name="head_office_id" value="'c1000'"></s:set>

<table cellpadding="0" style="word-break: break-all;" cellspacing="0"
	class="czrz_box t-even" tid="<s:property value="id" />">
	
		<tr>
			<th class="ddlb_box_ydh">
				编号
			</th>
			<th class="ddlb_box_ydh">
				产品ID
			</th>
			<th class="ddlb_box_ydh">
				图片
			</th>
			<th class="ddlb_box_ydh">
				名称
			</th>
			<th class="ddlb_box_ydh">
				价格
			</th>
			<th class="ddlb_box_ydh">
				是否上架
			</th>
			<th class="ddlb_box_ydh">
				状态
			</th>
			<th class="ddlb_box_ydh">
				操作
			</th>
			
		</tr>
	
	<s:iterator value="resultlist" var="bi" status="t">
		<tr id="tr_<s:property value='id'/>">
			<td class="ddlb_box_ydh">
				<s:if test="%{#session.admin_session_comp_id == #head_office_id}">
					<s:if test="%{open == 1}">
						<a href="javascript:;" class="open_a"
								tid="<s:property value="id" />" cid="<s:property value="compId" />" position="<s:property value="position" />" openState="0"><img style="width: 14px; height: 14px;" alt="" src="http://app.gomein.net.cn/topics/images/u155.gif" border="0" complete="complete"/></a>
					</s:if>
					<s:else>
						<a href="javascript:;" class="open_a"
								tid="<s:property value="id" />" cid="<s:property value="compId" />" position="<s:property value="position" />" openState="1"><img style="width: 14px; height: 14px;" alt="" src="http://app.gomein.net.cn/topics/images/u153.gif" border="0" complete="complete"/></a>
					</s:else>
				</s:if>
				<s:property value="id" />
			</td>
			<td class="ddlb_box_ydh clean_data" style="width: 50px;">
				<s:property value="item.itemid"/>
			</td>
			<td class="ddlb_box_ydh clean_data">
				<img alt="" src="<s:property value="item.mainimg6"/>" width="30px"
					height="30px" />
			</td>
			<td class="ddlb_box_ydh clean_data" style="width: 250px;">
				<s:property value="item.productname" />
			</td>
			<td class="ddlb_box_ydh clean_data">
				<s:property value="item.originalprice" />
			</td>
			<td class="ddlb_box_ydh clean_data">
				<s:if test="%{(item.status&8)!=0}">已上架</s:if>
				<s:if test="%{(item.status&4)!=0}">已下架</s:if>
			</td>

			<td id="td_status_<s:property value="id" />" class="ddlb_box_ydh">
				<s:if test="%{open == 1}">开放</s:if>
				<s:else>关闭</s:else>
			</td>

			<td class="ddlb_box_ydh">
			
				<s:if test="%{#session.admin_session_comp_id == #head_office_id}">
					<a href="javascript:" class="moveup_a" tid="<s:property value="id" />"  position="<s:property value="position" />"><img src="http://app.gomein.net.cn/topics/images/u5.gif"/></a>
             		<a href="javascript:" class="movedown_a" tid="<s:property value="id" />"  position="<s:property value="position" />"><img src="http://app.gomein.net.cn/topics/images/u7.gif"/></a>　
				</s:if>
				
				<s:if test="%{#session.admin_session_comp_id == #head_office_id || open == 1}">
					<a href="javascript:;" class="add_a" tid="<s:property value="id" />"
					onclick="addData('<s:property value='htmlId' />', '<s:property value='resourceId'/>' ,'<s:property value='position'/>','<s:property value='id'/>');">添加</a>

					<a href="javascript:;" class="delete_a"
						tid="<s:property value="id" />" position="<s:property value="position" />">删除</a>
				</s:if>
			</td>

		</tr>
	</s:iterator>
</table>
