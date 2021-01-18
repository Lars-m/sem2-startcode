
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user != null }">
    <h4>
    ${sessionScope.user.email}
    </h4>
</c:if>
