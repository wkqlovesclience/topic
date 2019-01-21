<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>编辑百度优惠卡</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript">
$(document).ready(function() {
 	$('form').submit(function() {
 	     var id = document.getElementById("id").value;
 	     var contents = document.getElementById("contents").value;
 	    if (contents.length > 200) {
			alert('推荐理由大于200个字符，请修改内容！');
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
					<h3>编辑百度优惠卡</h3>				
				<h3><a href="./list.action?page_index=${page_index}">返回上一级</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>基本信息</h3>
					<table class="tb-1">
						<tbody><input type="hidden" name="id"  id="id"	value="${cardId}" />
						<tr>
								<th>skuId：</th>
								<td>
									${skuId} 							    
								</td>
						
								<th>productId：</th>
								<td> 
									${productId} 
								</td>
						  </tr>

						  <tr>
								<th>商品名称：</th>
								<td>
									${baiduCard.productName }
								</td>
						 </tr>
						  <tr>
								<th>一级分类：</th>
								<td>
								    ${baiduCard.firstCatName }
								</td>
						</tr>
						 <tr>	
								<th>二级分类：</th>
								<td>
									${baiduCard.secCatName }
								</td>
						</tr>
						 <tr>
								<th>三级分类：</th>
								<td>
								    ${baiduCard.thridCatName }
								</td>
						  <tr>
						<tr>
								<th>品 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：</th>
								<td>
									${baiduCard.brandName }
								</td>
							
								<th>商品价格：</th>
								<td>
									${baiduCard.price }
								</td>
							</tr>
							<tr>
								<th>商品状态：</th>
								<td>	
									 ${baiduCard.onSale == true ? '上架':'<spean style="color:red;">下架</spean>' }
								</td>
							</tr>
							<tr>
								<th>商品图片：</th>
								<td>
									<img align="top" src="${baiduCard.imgUrl}_260.jpg" width="225" height="200" id="imgUrl"/> 
								</td>
							</tr>						
							</tbody>
							
					</table>
					<table class="tb-1">
						<tbody>
								<tr>
								<th>系统推荐理由：</th>
								<td style="margin: 0px; width: 800px;  ">
									${baiduCard.content }
									<input type="hidden" name="baiduCardContent"  id="baiduCardContent"	value="${baiduCard.content}" />
								</td>
							</tr>
							
							<tr>
								<th>编辑推荐理由：</th>
								<td>
									<textarea id="contents" name="contents" style="margin: 0px; width: 800px; height: 200px; resize: none;" >${cardContent}</textarea> 
								</td>
								<td style="color:red;">
								     最多200个字符！
								</td>
							</tr>
						</tbody>
					</table>																							
					<div class="But">
						<input type="submit" value="保存"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="重置" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>