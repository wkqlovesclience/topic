<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>�༭�ٶ��Żݿ�</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript">
$(document).ready(function() {
 	$('form').submit(function() {
 	     var id = document.getElementById("id").value;
 	     var contents = document.getElementById("contents").value;
 	    if (contents.length > 200) {
			alert('�Ƽ����ɴ���200���ַ������޸����ݣ�');
			return false;
		}
    });
   scroll(0,0);
});
</script>
</head>
<body>
	<form name="form" action="${ctx}/PromotionCard/save.action" method="post">
		<s:hidden name="cardId"></s:hidden>
		<div class="mod-1">
			<div class="hd">				
					<h3>�༭�ٶ��Żݿ�</h3>				
				<h3><a href="./list.action?page_index=${page_index}">������һ��</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>������Ϣ</h3>
					<table class="tb-1">
						<tbody><input type="hidden" name="id"  id="id"	value="${cardId}" />
						<tr>
								<th>skuId��</th>
								<td>
									${skuId} 							    
								</td>
						
								<th>productId��</th>
								<td> 
									${productId} 
								</td>
						  </tr>

						  <tr>
								<th>��Ʒ���ƣ�</th>
								<td>
									${baiduCard.productName }
								</td>
						 </tr>
						  <tr>
								<th>һ�����ࣺ</th>
								<td>
								    ${baiduCard.firstCatName }
								</td>
						</tr>
						 <tr>	
								<th>�������ࣺ</th>
								<td>
									${baiduCard.secCatName }
								</td>
						</tr>
						 <tr>
								<th>�������ࣺ</th>
								<td>
								    ${baiduCard.thridCatName }
								</td>
						  <tr>
						<tr>
								<th>Ʒ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�</th>
								<td>
									${baiduCard.brandName }
								</td>
							
								<th>��Ʒ�۸�</th>
								<td>
									${baiduCard.price }
								</td>
							</tr>
							<tr>
								<th>��Ʒ״̬��</th>
								<td>	
									 ${baiduCard.onSale == true ? '�ϼ�':'<spean style="color:red;">�¼�</spean>' }
								</td>
							</tr>
							<tr>
								<th>��ƷͼƬ��</th>
								<td>
									<img align="top" src="${baiduCard.imgUrl}_260.jpg" width="225" height="200" id="imgUrl"/> 
								</td>
							</tr>						
							</tbody>
							
					</table>
					<table class="tb-1">
						<tbody>
								<tr>
								<th>ϵͳ�Ƽ����ɣ�</th>
								<td style="margin: 0px; width: 800px;  ">
									${baiduCard.content }
									<input type="hidden" name="baiduCardContent"  id="baiduCardContent"	value="${baiduCard.content}" />
								</td>
							</tr>
							
							<tr>
								<th>�༭�Ƽ����ɣ�</th>
								<td>
									<textarea id="contents" name="contents" style="margin: 0px; width: 800px; height: 200px; resize: none;" >${cardContent}</textarea> 
								</td>
								<td style="color:red;">
								     ���200���ַ���
								</td>
							</tr>
						</tbody>
					</table>																							
					<div class="But">
						<input type="submit" value="����"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="����" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>