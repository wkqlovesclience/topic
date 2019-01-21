<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>�༭��Ʒ��</title>
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

</script>
</head>
<body>
	<form name="form" action="${ctx}/ProductWordsCard/save.action" method="post">
		<s:hidden name="cardId"></s:hidden>
		<div class="mod-1">
			<div class="hd">				
					<h3>�༭��Ʒ��</h3>
				<h3><a href="./list.action?page_index=${page_index}">������һ��</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>������Ϣ</h3>
					<table class="tb-1">
						<tbody><input type="hidden" name="id"  id="id"	value="${cardId}" />

							  <tr>
									<th>��Ʒ�ʣ�</th>
									<td>
										${productWordsCardForValueStack.productWordsName}
									</td>
							 </tr>
							<tr>
								<th>��ţ�</th>
								<td>
									${productWordsCardForValueStack.id}
								</td>
							</tr>

							<tr>
								<th>�ʸ���</th>
								<td>
									<input type="text" name="productWordsBase" value="${productWordsCardForValueStack.productWordsBase}"/>
								</td>
							</tr>

						</tbody>
							
					</table>

					<div class="But">
						<input type="submit" value="����"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="����" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>