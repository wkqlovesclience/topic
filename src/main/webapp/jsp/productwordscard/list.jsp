<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>商品词管理</title>
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
	<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>

	<style type="text/css">
		.tmp-class thead td,.tmp-class tbody td {
			padding: 5px 5px;
		}

		.tmp-class1 {
			text-align: center;
		}

		.tmp-class1 th {
			width: 63px;
			height: 29px;
			border: 1px solid #C4C8CC;
			background: #E9EBED;
			text-align: center;
		}

		.tmp-class1 td {
			width: 63px;
			border: 1px solid #C4C8CC;
			height: 29px;
			text-align: center;
		}
	</style>
	<script type="text/javascript">
        //有效全选 、无效全选
        function sel_status_all(checked){
        	var check_objz = null;
        	if(checked){
        		check_objz = $("input[name='cbsz'][value='1']");
        	}else{
        		check_objz = $("input[name='cbsz'][value='0']");
        	}
        	for(var i=0; i<check_objz.length;i++){
        		check_objz.get(i).previousElementSibling.checked = true;
            }
            return;
        }
        
      //全选 、取消
        function sel_all(checked){
            var check_obj = $("input[id='cbs']");
            for(var i=0; i<check_obj.length;i++){
                if(checked){
                    check_obj.get(i).checked = true;
                }else{
                    check_obj.get(i).checked = false;
                }
            }
            return;
        }

        //删除
        function delProductWords() {
            var cc = $('input:checked');
            var str = "";
            for ( var j = 0; j < cc.length; j++) {
                str = str + $('input:checked').get(j).value + ";";
            }
            if (str == "") {
                alert('请至少选择一项！');
                return;
            }
            if(confirm('确认删除？')){
                window.location.href = "${ctx}/ProductWordsCard/delete.action?tags=" + str;
            }
        }
		//删除单个
        function delSingleProductWords(id) {
            if(id==""){
                alert('操作有误');
                return;
			}
            if(confirm('确认删除？')){
                window.location.href = "${ctx}/ProductWordsCard/delete.action?tags=" + id;
            }
        }


        //发布
        function putProductWords() {
            var cc = $('input:checked');
            var str = "";
            for ( var j = 0; j < cc.length; j++) {
                str = str + $('input:checked').get(j).value + ";";
                var v = $('input:checked').get(j).nextElementSibling.value;
                if(v == 0){
                	alert("无效商品不可发布");
                	return;
                }
            }
            if (str == "") {
                alert('请至少选择一项！');
                return;
            }
            if(confirm('确认发布？')){
                window.location.href = "${ctx}/ProductWordsCard/putForward.action?tags=" + str;
            }
        }


        function tunePage(num) {
            var productWordsName  = document.getElementById("productWordsName").value;
            var isInvalid = document.getElementById("isInvalid").value;
            var qcreateTime = document.getElementById("qcreateTime").value;
            var status = document.getElementById("status").value;
            var page_size = document.getElementById("page_size").value;

            var url = '${ctx}/ProductWordsCard/list.action?' + 'page_index=' + num;

            if(null != productWordsName && '' != productWordsName){
                url += '&productWordsName=' + encodeURI(encodeURI(productWordsName));
            }
            if(null != isInvalid && '' != isInvalid){
                url += '&isInvalid=' + encodeURIComponent(encodeURIComponent(isInvalid));
            }
            if(qcreateTime != null && qcreateTime != "" )
            {
                url += "&qcreateTime=" + qcreateTime;
            }
            if(status != null && status != "" )
            {
                url += "&status=" + status;
            }
            if(page_size != null && page_size != "" )
            {
                url += "&page_size=" + page_size;
            }
            window.location = url;
        }

        function pageChange() {
            var num=1;
            var productWordsName  = document.getElementById("productWordsName").value;
            var isInvalid = document.getElementById("isInvalid").value;
            var qcreateTime = document.getElementById("qcreateTime").value;
            var status = document.getElementById("status").value;
            var page_size = document.getElementById("page_size").value;

            var url = "${ctx}/ProductWordsCard/list.action?pageNumber=1&page_size="+page_size;

            if(null != productWordsName && '' != productWordsName){
                url += '&productWordsName=' + encodeURI(encodeURI(productWordsName));
            }
            if(null != isInvalid && '' != isInvalid){
                url += '&isInvalid=' + encodeURIComponent(encodeURIComponent(isInvalid));
            }
            if(qcreateTime != null && qcreateTime != "" )
            {
                url += "&qcreateTime=" + qcreateTime;
            }
            if(status != null && status != "" )
            {
                url += "&status=" + status;
            }
            window.location = url;
        }






        //按回车实现搜索
        document.onkeydown=function(event){
            e = event ? event :(window.event ? window.event : null);
            if(e.keyCode==13){
                document.getElementById("f1").submit();
            }
        }

        //校验输入是否为数字
        function numValidate(num){
            var reg = /^\d+$/;
            if(null != num && "" != num){
                if(!reg.test(num)){
                    alert(num + "不是数字，请输入数字！")
                    return false;
                }
            }
            return true;
        }
        //比较两个值大小
        function compareNum(num1,num2){
            if(null != num1 && num2 != null && "" != num1 && "" != num2 && (num1 > num2)){
                alert(num2 + " 值大于 " + num1 + "  请重新输入！");
                return false;
            }
            return true;
        }

        //以id为范围导出excel热词文件。
        function outFile()
        {
            var minId = $.trim($("#minId").val());
            var maxId = $.trim($("#maxId").val());
            if(!minId || !maxId)
            {
                alert("请输入正确的数字！");
                return;
            }
            if(isNaN(minId) || isNaN(maxId))
            {
                alert("对不起您的输入不合法，请输入正确数字！");
                return;
            }
            window.location = "./out.action?minId="+minId+"&maxId="+maxId;
        }
        
        function putForward(id) {
            alert(id);
            var url = '${ctx}/ProductWordsCard/putForward.action?tags=' + id;
            alert(url);
            window.location = url;
        }
        

        window.onload = function(){
        	var d = $("input[name='cbszp'][value='0']");
        	for(var i=0; i<d.length;i++){
        		d.get(i).previousElementSibling.disabled = "disabled";
            }
        }
	</script>
</head>
<body>
<form action="${ctx}/ProductWordsCard/list.action" method="post" id="f1">
	<div class="mod-1">
		<div class="hd">
			<h3>商品词管理</h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
				&nbsp;&nbsp;商品词名称：
				<input type="text" class="txt-5" id="productWordsName" name="productWordsName" value="${productWordsName}"/>
				&nbsp;&nbsp;是否有效：
				<s:select id="status" name="status" list="#{3:'全显',1:'有效',0:'无效'}" />
				&nbsp;&nbsp; &nbsp;
				创建时间：
				<input class="laydate-icon" name="qcreateTime" id="qcreateTime" value="${param.qcreateTime}" onclick="laydate()" readonly="readonly">
				&nbsp; &nbsp;&nbsp;
				状态：
				<s:select id="isInvalid" name="isInvalid" list="#{3:'全显',0:'未发布',1:'已发布'}" />
				<input type="submit" value="搜索" />
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				<span>商品词有效个数：
					<em style="color:#0d14ff;">${isEnableCount}</em>
				</span>
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				<span>商品词无效个数：
					<em style="color:#ff000a;">${notEnableCount}</em>
				</span>

			</div>
			<div class="container-1">

				<table style="width: 100%;">
					<tbody>
					<tr>
						<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
							<input type="button" value="全选" onclick="sel_all('true')" />
							
							<input type="button" value="有效全选" onclick="sel_status_all(true)" />
							
							<input type="button" value="无效全选" onclick="sel_status_all(false)" />

							<input type="button" value="删除" onclick="delProductWords()"/>

							<input type="button" value="发布" onclick="putProductWords()"/>

							<input type="button" value="取消" onclick="sel_all(false)" />
							<span id="importSpan" style="margin-left: 150ex">
										导出excel文件：ID从<input type="text" size="10" id="minId" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"/>到 <input type="text" size="10" id="maxId" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"/>
										<input type="button" value="导出" onclick="outFile()" />
									</span>
						</td>
					</tr>
					</tbody>
				</table>
				<table class="tb-zebra tmp-class" style="width: 100%;">
					<colgroup>
						<col style="width: 6%;" />
						<col style="width: 6%;" />
						<col style="width: 14%;" />
						<col style="width: 12%;" />
						<col style="width: 6%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 6%;" />
						<col style="width: 6%;" />
						<col style="width: 15%;" />
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;"></td>
						<td style="line-height: 1;">编号</td>
						<td style="line-height: 1;">商品词</td>
						<td style="line-height: 1;">词根</td>
						<td style="line-height: 1;">发布者</td>
						<td style="line-height: 1;">添加/修改时间</td>
						<td style="line-height: 1;">是否有效</td>
						<td style="line-height: 1;">状态</td>
						<td style="line-height: 1;">是否补词</td>
						<td style="line-height: 1;">搜索结果数量</td>
						<td style="line-height: 1;">站点</td>
						<td style="line-height: 1;">操作</td>
					</tr>
					</thead>
					<tbody>
					<s:if test="productWordsCardList!= null">
						<s:iterator value="productWordsCardList"  status="st">
							<tr>
								<td><label> <input type="checkbox" name="cbs"
												   id="cbs" value="<s:property value='id' />" />
								            <input type="hidden" name="cbsz"
												    value="<s:property value='isEnable' />" />
												    </label></td>
								<td><s:property value='id' /></td>
								<td ><s:property value='productWordsName' /></td>
								<td ><s:property value='productWordsBase' /></td>
								<td ><s:property value='creator' /></td>
								<td><s:date name="createDate" format="yyyy.MM.dd" />/<s:date name="updateDate" format="yyyy.MM.dd" /></td>
								<td>${isEnable == 1 ?'有效':'无效' }</td>
								<td>${isInvalid == 1 ?'已发布':'未发布' }</td>
								<td>${search_status == 1 ?'原词':'补词' }</td>
								<td>${search_amount}</td>
								<td >国美</td>
								<td>
									<a href="${ctx}/ProductWordsCard/edit.action?id=<s:property value='id'/>">编辑</a>
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									<%--<a href="${ctx}/ProductWordsCard/delete.action?tags=<s:property value='id'/>">删除</a>--%>
									<a href="#" onclick="delSingleProductWords(<s:property value='id'/>)">删除</a>
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									
									<button onclick="putForward(<s:property value='id'/>)">发布</button>
									<input type="hidden" name="cbszp"
												    value="<s:property value='isEnable' />" />
												    </label></td>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					</tbody>
				</table>
				<table width="100%">
					<tfoot>
					<tr>

						<td style="border: 0 none;">
							<div class="numpage-box">
								<div class="numpage">
									<coo8:page name="productWordsCardList" style="js" systemId="1" />

								</div>
							</div></td>
						<td><s:select style="margin-top:-30px;" id="page_size" name="page_size" list="#{10:'10/页',100:'100/页',500:'500/页',1000:'1000/页'}" onchange="pageChange()"/></td>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</form>

</body>
</html>