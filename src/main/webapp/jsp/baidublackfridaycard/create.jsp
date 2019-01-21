<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>�༭�ٶȺ���</title>
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
				   var str = isInvalidSE == "0"?"��Ч":"��Ч" ;
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
			    // isNaN()���� �ѿմ� �ո� �Լ�NUll ����0������ ������ȥ��
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
		 	    		alert("����url�������'http(s)://tuan.m.gome.com.cn*.html'�ṹ");
	 	    			return false; 
		 	    	 }
		 	    	 if(!isRealNum(saleprice)){
			 	    	alert("�������������");
	 	    			return false;
			 	     }
			 	     if(!isRealNum(originprice)){
			 	    	alert("ԭ������������");
	 	    			return false;
			 	     }
		 	    	 if(isNotNullAndBlank(customurl) && isNotNullAndBlank(saleprice) && isNotNullAndBlank(originprice)){
		 	    		 if(parseFloat(saleprice) >= parseFloat(originprice)){
		 	    			alert("���Ӧ�õ���ԭ��");
		 	    			return false;
		 	    		 }
		 	    		 return true;
		 	    	 }else{
		 	    		 alert("����Զ���·������ۡ�ԭ�۽���ͳһ�༭");
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
					<h3>�༭�ٶȺ�����Ʒ</h3>				
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
							
								<th>��Ʒ��ۣ�</th>
								<td>
									${priceInfo.finalPrice }
								</td>
								<th>��Ʒԭ�ۣ�</th>
								<td>
									${priceInfo.basePrice }
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
									<img align="top" src="${baiduCard.imgUrl}_200.jpg" width="150" height="120" id="imgUrl"/> 
								</td>
							</tr>
							<tr>
								<th>xml���ɴ�����</th>
								<td>
									${createNum }
								</td>
							</tr>
							</tbody>
							
					</table>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>��Ʒ���ࣺ</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showProductType">${productType }</span><input type="hidden" name="productType"  id="productType"	value="${productType}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="productTypeSE">
									    <option value="����" ${productType=="����"?"selected='selected'":''}>����</option>
									    <option value="����" ${productType=="����"?"selected='selected'":''}>����</option>
									    <option value="��װ" ${productType=="��װ"?"selected='selected'":''}>��װ</option>
									    <option value="���" ${productType=="���"?"selected='selected'":''}>���</option>
									    <option value="��ױ" ${productType=="��ױ"?"selected='selected'":''}>��ױ</option>
									    <option value="����" ${productType=="����"?"selected='selected'":''}>����</option>
									    <option value="�˶�" ${productType=="�˶�"?"selected='selected'":''}>�˶�</option>
									    <option value="ͼ��" ${productType=="ͼ��"?"selected='selected'":''}>ͼ��</option>
									    <option value="����" ${productType=="����"?"selected='selected'":''}>����</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeProDuctType()">ȷ��</button>
								</td>
							</tr>
							
							<tr>
								<th>������Ϣ��</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showTag">${tag }</span><input type="hidden" name="tag"  id="tag"	value="${tag}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="tagSE">
									    <option value="��Ʒֱ��" ${tag=="��Ʒֱ��"?"selected='selected'":''}>��Ʒֱ��</option>
									    <option value="��ʱ��ɱ" ${tag=="��ʱ��ɱ"?"selected='selected'":''}>��ʱ��ɱ</option>
									    <option value="�����Ż�" ${tag=="�����Ż�"?"selected='selected'":''}>�����Ż�</option>
									    <option value="0Ԫ����" ${tag=="0Ԫ����"?"selected='selected'":''}>0Ԫ����</option>
									    <option value="����ר��" ${tag=="����ר��"?"selected='selected'":''}>����ר��</option>
									    <option value="����ר��" ${tag=="����ר��"?"selected='selected'":''}>����ר��</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeTag()">ȷ��</button>
								</td>
							</tr>
							
							<tr>
								<th>�Ƿ�ʧЧ��</th>
								<td style="margin: 0px; width: 200px;  ">
									<span id="showIsInvalid">${isInvalid == 0 ? "��Ч":"��Ч" }</span><input type="hidden" name="isInvalid"  id="isInvalid"	value="${isInvalid}" />
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<select id="isInvalidSE">
									    <option value="0" ${isInvalid=="0"?"selected='selected'":''}>��Ч</option>
									    <option value="1" ${isInvalid=="1"?"selected='selected'":''}>��Ч</option>
									</select>
								</td>
								<td  style="margin: 0px; width: 300px;  ">
									<button type="button" onclick="changeIsInvalid()">ȷ��</button>
								</td>
							</tr>
							
							<tr>
								<th>�Զ���·��</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="customUrl" name="customUrl" value="${customUrl}" />
								</td>
								<th>���</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="salePrice" name="salePrice" value="${salePrice}"/>
								</td>
								<th>ԭ��</th>
								<td style="margin: 0px; width: 200px;  ">
									<input type="text" id="originPrice" name="originPrice"  value="${originPrice}"/>
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