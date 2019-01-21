<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>编辑百度黑五</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript">
			function changeProDuctType(){
				   var productTypeSEValue = document.getElementById('productTypeSE').value;
				   var hidProductType = document.getElementById('productType');
				   hidProductType.value = productTypeSEValue;
				   var showProductType = document.getElementById('showProductType');
				   showProductType.innerText = productTypeSEValue;
			}
			function changeTag(){
				   var tagSE = document.getElementById('tagSE').value;
				   var tag = document.getElementById('tag');
				   tag.value = tagSE;
				   var showTag = document.getElementById('showTag');
				   showTag.innerText = tagSE;
			}
			function changeIsInvalid(){
				   var isInvalidSE = document.getElementById('isInvalidSE').value;
				   var isInvalid = document.getElementById('isInvalid');
				   isInvalid.value = isInvalidSE;
				   var showIsInvalid = document.getElementById('showIsInvalid');
				   var str = isInvalidSE == "0"?"有效":"无效" ;
				   showIsInvalid.innerText = str;
			}
			function isNotNullAndBlank( str ){
				if(str == null) return false;
				if ( str == "" ) return false;
				var regu = "^[ ]+$";
				var re = new RegExp(regu);
				return !re.test(str);
				}
			function isRealNum(val){
			    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
			    if(val === "" || val ==null){
			        return false;
			    }
			    if(!isNaN(val)){
			        return true;
			    }else{
			        return false;
			    }
			}
		 	function checkForm(){
		 		 var customurl = document.getElementById("customUrl").value;
		 	     var saleprice = document.getElementById("salePrice").value;
		 	     var originprice = document.getElementById("originPrice").value;
		 	     
		 	     if(isNotNullAndBlank(customurl) || isNotNullAndBlank(saleprice) || isNotNullAndBlank(originprice)){
		 	    	 var re = /^(https?:\/\/)(tuan\.m\.gome\.com\.cn){1}.*(\.html){1}$/;
		 	    	 if(!re.test(customurl)){
		 	    		alert("团抢url必须符合'http(s)://tuan.m.gome.com.cn*.html'结构");
	 	    			return false; 
		 	    	 }
		 	    	 if(!isRealNum(saleprice)){
			 	    	alert("活动价请填入数字");
	 	    			return false;
			 	     }
			 	     if(!isRealNum(originprice)){
			 	    	alert("原价请填入数字");
	 	    			return false;
			 	     }
		 	    	 if(isNotNullAndBlank(customurl) && isNotNullAndBlank(saleprice) && isNotNullAndBlank(originprice)){
		 	    		 if(parseFloat(saleprice) >= parseFloat(originprice)){
		 	    			alert("活动价应该低于原价");
		 	    			return false;
		 	    		 }
		 	    		 return true;
		 	    	 }else{
		 	    		 alert("请对自定义路径、活动价、原价进行统一编辑");
		 	    		 return false;
		 	    	 }
		 	     }
		 	}
</script>
</head>
<body>
	<form name="form" action="${ctx}/BaiDuBlackFridayCard/save.action" method="post" onsubmit="return checkForm()">
		<s:hidden name="cardId"></s:hidden>
		<div class="mod-1">
			<div class="hd">				
					<h3>编辑百度黑五商品</h3>				
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
							
								<th>商品活动价：</th>
								<td>
									${priceInfo.finalPrice }
								</td>
								<th>商品原价：</th>
								<td>
									${priceInfo.basePrice }
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
									<img align="top" src="${baiduCard.imgUrl}_200.jpg" width="150" height="120" id="imgUrl"/> 
								</td>
							</tr>
							<tr>
								<th>xml生成次数：</th>
								<td>
									${createNum }
								</td>
							</tr>
							</tbody>
							
					</table>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>商品分类：</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showProductType">${productType }</span><input type="hidden" name="productType"  id="productType"	value="${productType}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="productTypeSE">
									    <option value="数码" ${productType=="数码"?"selected='selected'":''}>数码</option>
									    <option value="电器" ${productType=="电器"?"selected='selected'":''}>电器</option>
									    <option value="服装" ${productType=="服装"?"selected='selected'":''}>服装</option>
									    <option value="箱包" ${productType=="箱包"?"selected='selected'":''}>箱包</option>
									    <option value="美妆" ${productType=="美妆"?"selected='selected'":''}>美妆</option>
									    <option value="轻奢" ${productType=="轻奢"?"selected='selected'":''}>轻奢</option>
									    <option value="运动" ${productType=="运动"?"selected='selected'":''}>运动</option>
									    <option value="图书" ${productType=="图书"?"selected='selected'":''}>图书</option>
									    <option value="其他" ${productType=="其他"?"selected='selected'":''}>其他</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeProDuctType()">确认</button>
								</td>
							</tr>
							
							<tr>
								<th>打折信息：</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showTag">${tag }</span><input type="hidden" name="tag"  id="tag"	value="${tag}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="tagSE">
									    <option value="优品直降" ${tag=="优品直降"?"selected='selected'":''}>优品直降</option>
									    <option value="限时秒杀" ${tag=="限时秒杀"?"selected='selected'":''}>限时秒杀</option>
									    <option value="五折优惠" ${tag=="五折优惠"?"selected='selected'":''}>五折优惠</option>
									    <option value="0元抢购" ${tag=="0元抢购"?"selected='selected'":''}>0元抢购</option>
									    <option value="买赠专区" ${tag=="买赠专区"?"selected='selected'":''}>买赠专区</option>
									    <option value="满减专区" ${tag=="满减专区"?"selected='selected'":''}>满减专区</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeTag()">确认</button>
								</td>
							</tr>
							
							<tr>
								<th>是否失效：</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showIsInvalid">${isInvalid == 0 ? "有效":"无效" }</span><input type="hidden" name="isInvalid"  id="isInvalid"	value="${isInvalid}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="isInvalidSE">
									    <option value="0" ${isInvalid=="0"?"selected='selected'":''}>有效</option>
									    <option value="1" ${isInvalid=="1"?"selected='selected'":''}>无效</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeIsInvalid()">确认</button>
								</td>
							</tr>
							
							<tr>
								<th>自定义路径</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="customUrl" name="customUrl" value="${customUrl}" />
								</td>
								<th>活动价</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="salePrice" name="salePrice" value="${salePrice}"/>
								</td>
								<th>原价</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="originPrice" name="originPrice"  value="${originPrice}"/>
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