<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
<h2>Demo Project for DAT 2. semester</h2>
<h4>
    <span class="badge bg-light text-dark">${param.pageTitle}</span>
<c:if test="${sessionScope.user != null }">
    <span style="margin-left: 3em;">Logged in as: ${sessionScope.user.email}</span>
</c:if>
</h4>
<hr/>
</div>