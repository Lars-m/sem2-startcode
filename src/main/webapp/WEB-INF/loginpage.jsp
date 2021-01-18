<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
</head>
<body>

<jsp:include page="include/header.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>
<div style="margin-top: 4em;" class="container">
    <form name="login" action="login" method="POST">
        <div class="row mb-3">
            <label class="col-sm-1 col-form-label" for="email">Email</label>
            <div class="col-sm-4">
                <input class="form-control" type="text" name="email" placeholder="someone@nowhere.com">
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-1 col-form-label"  for="password">Password</label>
            <div class="col-sm-4">
                <input class="form-control" type="password" name="password" placeholder="sesam">
            </div>
        </div>

        <button class="btn btn-primary" type="submit" value="Login">Sign in</button>
    </form>

    <c:if test="${requestScope.loginerror != null }">
    <p style="color:red">
            ${requestScope.loginerror}
    </p>
    </c:if>

    <c:if test="${not empty param.msg}">
    <p style="font-size: large">${param.msg}</p>
    </c:if>
    </div>

    <div class="footer">
        <%@include file="include/home.jsp" %>
    </div>

</body>
</html>
