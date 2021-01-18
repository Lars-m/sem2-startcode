<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
<body>
<div id="pageheader" style="font-family: sans-serif;margin:25px;">
    <h2>Demo Project for DAT 2. semester</h2>
    <h4>
        <jsp:invoke fragment="header"/>
        <c:if test="${sessionScope.user != null }">
            <span style="margin-left: 3em;">Logged in as:  ${sessionScope.user.email}</span>
        </c:if>
    </h4>
    <hr/>
</div>
<div id="body" style="font-family: sans-serif;margin:25px;">
    <jsp:doBody/>
</div>
<div id="pagefooter" style="font-family: sans-serif;margin: 2em 25px 25px;">
    <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
    <jsp:invoke  fragment="footer"/>
</div>
</body>
</html>
