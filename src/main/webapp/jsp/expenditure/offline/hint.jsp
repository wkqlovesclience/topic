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
        <script type="text/javascript">
        	  $(document).ready(function(){  
        		var flag = $("#suc_err").val();
        		var type = $("#suc_err_type").val();
        		var chan_type = $("#chan_type").val();
        		if(type ==0){
        			if(flag >0){
            			alert("花费数据录入成功!");
            		}else{
            			alert("花费数据录入失败!");
            		}
        		}else if(type ==1){
        			if(flag >0){
            			alert("花费数据修改成功!");
            		}else{
            			alert("花费数据修改失败!");
            		}
        		}else if(type ==2){
        			if(flag >0){
            			alert("渠道添加成功!");
	        			}else{
	        				if(chan_type ==1){
	        					alert("一级渠道已存,请勿重复添加!");
	        				}else if(chan_type ==2){
	        					alert("二级渠道已存,请勿重复添加!");
	        				}else if(chan_type ==3){
	        					alert("三级渠道已存,请勿重复添加!");
	        				}else{
	        					alert("渠道添加失败!");
	        				}
	        			}
	        			}
        		window.location.href = "${ctx}/ExpenditureOffLine/list.action";
        	  });
</script>
</head>
<body>
<input type="hidden" id="suc_err" value="<s:property value='sucNum' />"/>
<input type="hidden" id="suc_err_type" value="<s:property value='type' />"/>
<input type="hidden" id="chan_type" value="<s:property value='chanT' />"/>
</body>
</html>