<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
</head>
<body>

<jsp:include page="WEB-INF/include/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<div class="content">
    <h2>Our Cool Site</h2>
    <c:if test="${sessionScope.user != null }">
        <p><a href="fc/logout">logout</a></p>
    </c:if>
    <c:if test="${sessionScope.user == null }">
    <p><a href="fc/loginpage">Login</a> or <a href="fc/registerpage">Register as new user</a>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
    <p><a href="fc/employeepage">Employee Page</a>
        </c:if>

        <c:if test="${sessionScope.role == 'customer' }">
    <p><a href="fc/customerpage">Customer Page</a>
        </c:if>
</div>

<div class="footer">
    <br>
    <br>
    <hr>
</div>
</body>

