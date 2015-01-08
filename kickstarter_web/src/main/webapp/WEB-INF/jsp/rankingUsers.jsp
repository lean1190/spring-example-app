<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped">
    <thead>
        <tr>
            <td>
                <strong><spring:message code='label.user'/></strong>
            </td>
            <td>
                <strong><spring:message code='label.name'/></strong>
            </td>
            <td>
                <strong><spring:message code='label.user.successPoints'/></strong>
            </td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}" varStatus="usersCounter">
            <tr>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.successPoints}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
