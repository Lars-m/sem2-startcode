<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         <h5>The server experienced an unexpected problem</h5>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div style="font-family: sans-serif; margin: 10px;">
            <c:if test="${requestScope.problem != null }">
                <h2 style="color:crimson">
                        ${requestScope.problem}
                </h2>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>