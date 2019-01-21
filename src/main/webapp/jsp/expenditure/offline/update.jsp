<%@ page language="java" pageEncoding="gbk" %>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>线下花费系统</title>
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
	<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
	<style type="text/css">
		input{background-color:expression((this.readOnly && this.readOnly == true)? "#efefef":"");}
		input[readonly]{
			background-color: #efefef;
		}
	</style>
	<script type="text/javascript">
        $(document).ready(function(){
        });
	</script>
</head>
<body>

<form action="${ctx}/ExpenditureOffLine/modifyExpenditure.action" method="post" id="f1" onsubmit="return verify()">
	<div class="mod-1">
		<div class="hd">
			<h3>修改线下花费</h3>
			<h3><a href="#" onclick="getBack()">返回上一级</a></h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
			</div>
			<div class="container-1">
				<table class="tb-zebra tmp-class" id="dynamicTable" style="width: 100%;">
					<colgroup>
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;">ID</td>
						<td style="line-height: 1;">端口</td>
						<td style="line-height: 1;">一级渠道</td>
						<td style="line-height: 1;">二级渠道</td>
						<td style="line-height: 1;">三级渠道</td>
						<td style="line-height: 1;">UV</td>
						<td style="line-height: 1;">订单量</td>
						<td style="line-height: 1;">销售额</td>
						<td style="line-height: 1;">花费</td>
						<td style="line-height: 1;">操作</td>
					</tr>
					</thead>
					<tbody>
					<tr>
						<input readonly="readonly" type="hidden" name="expenditureDto.portId" value="<s:property value='expenditureDto.portId' /> " />
						<input readonly="readonly" type="hidden" name="expenditureDto.chanStair.id" value="<s:property value='expenditureDto.chanStair.id' />" />
						<input readonly="readonly" type="hidden" name="expenditureDto.chanSecond.id" value="<s:property value='expenditureDto.chanSecond.id' />" />
						<input readonly="readonly" type="hidden" name="expenditureDto.chanThird.id" value="<s:property value='expenditureDto.chanThird.id' />" />
						<td ><input readonly="readonly" type="text" name="expenditureDto.id" value="<s:property value='expenditureDto.id' />" size="5px" /></td>
						<td ><input readonly="readonly" type="text"  value="<s:property value='expenditureDto.port' />" size="10px"/></td>
						<td ><input readonly="readonly" type="text"  value="<s:property value='expenditureDto.chanStair.stairName' />" size="10px"/></td>
						<td ><input readonly="readonly" type="text"  value="<s:property value='expenditureDto.chanSecond.secondName' />" size="10px"/></td>
						<td ><input readonly="readonly" type="text"  value="<s:property value='expenditureDto.chanThird.thirdName' />" size="10px"/></td>
						<td><input id="uvsId"  type="text" name="expenditureDto.UV" value="<s:property value='expenditureDto.UV' />" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px"/></td>
						<td><input id="orderNumsId" type="text" name="expenditureDto.orderNum" value="<s:property value='expenditureDto.orderNum' />" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px" /></td>
						<td><input  id="salesId" type="text" name="expenditureDto.sale" value="<s:property value='expenditureDto.sale' />" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 50px" /></td>
						<td><input id="expendituresId" type="text" name="expenditureDto.expenditure" value="<s:property value='expenditureDto.expenditure' />" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 80px" /></td>
						<td><input type="submit" value="修改花费" /></td>
					</tr>
					</tbody>
				</table>
				<table width="100%">
					<tfoot>
					<tr>
						<td style="border: 0 none;">
						</td>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</form>

</body>
<script type="text/javascript">
    function verify() {
        var uvsId =$("#uvsId") .val();
        var orderNumsId =$("#orderNumsId") .val();
        var salesId = $("#salesId") .val();
        var expendituresId = $("#expendituresId") .val();
        console.log(uvsId);
        if(uvsId==''){
            alert("请填写 UV !");
            return false;
        }
        if(orderNumsId==''){
            alert("请填写 订单量 !");
            return false;
        }
        if(salesId ==''){
            alert("请填写 销售额 !");
            return false;
        }
        if(expendituresId ==''){
            alert("请填写 花费 !");
            return false;
        }
    }
    function getBack() {
        window.history.back();
    }
</script>
</html>