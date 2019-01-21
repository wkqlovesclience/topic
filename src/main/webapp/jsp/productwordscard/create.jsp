<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>编辑商品词</title>
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

</script>
</head>
<body>
	<form name="form" action="${ctx}/ProductWordsCard/save.action" method="post">
		<s:hidden name="cardId"></s:hidden>
		<div class="mod-1">
			<div class="hd">				
					<h3>编辑商品词</h3>
				<h3><a href="./list.action?page_index=${page_index}">返回上一级</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>基本信息</h3>
					<table class="tb-1">
						<tbody><input type="hidden" name="id"  id="id"	value="${cardId}" />

							  <tr>
									<th>商品词：</th>
									<td>
										${productWordsCardForValueStack.productWordsName}
									</td>
							 </tr>
							<tr>
								<th>编号：</th>
								<td>
									${productWordsCardForValueStack.id}
								</td>
							</tr>

							<tr>
								<th>词根：</th>
								<td>
									<input type="text" name="productWordsBase" value="${productWordsCardForValueStack.productWordsBase}"/>
								</td>
							</tr>

						</tbody>
							
					</table>

					<div class="But">
						<input type="submit" value="保存"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>