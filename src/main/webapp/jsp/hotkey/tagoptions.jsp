<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">��ѡ��</option>
<c:forEach items="${subtags}" var="subtag">
	<option value="${subtag.id}">${subtag.tagName}</option>
</c:forEach>
