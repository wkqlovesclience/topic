<%@ page language="java" pageEncoding="gbk" %>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>���»���ϵͳ</title>
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
            			alert("��������¼��ɹ�!");
            		}else{
            			alert("��������¼��ʧ��!");
            		}
        		}else if(type ==1){
        			if(flag >0){
            			alert("���������޸ĳɹ�!");
            		}else{
            			alert("���������޸�ʧ��!");
            		}
        		}else if(type ==2){
        			if(flag >0){
            			alert("������ӳɹ�!");
	        			}else{
	        				if(chan_type ==1){
	        					alert("һ�������Ѵ�,�����ظ����!");
	        				}else if(chan_type ==2){
	        					alert("���������Ѵ�,�����ظ����!");
	        				}else if(chan_type ==3){
	        					alert("���������Ѵ�,�����ظ����!");
	        				}else{
	        					alert("�������ʧ��!");
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