<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         <h5>Register as new User</h5>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <form name="register" action="register" method="POST">
            <input type="hidden" name="target" placeholder="register">
            Email:<br>
            <input type="text" name="email" placeholder="somewhere.com">
            <br>
            Password:<br>
            <input type="password" name="password1" placeholder="sesam">
            <br>
            Retype Password:<br>
            <input type="password" name="password2" placeholder="sesam">
            <br>
            <input type="submit" value="Submit">
        </form>

        <c:if test="${requestScope.userinputerror != null }">
            <p style="color:red">
                    ${requestScope.userinputerror}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>


