<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3><spring:message code='header.register'/></h3>

<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/register/save">
    <table>
        <tr>
            <td><h3><c:out value="${error}"/></h3></td>
        </tr>
        <tr>
            <td><spring:message code="label.name"/></td>
            <td>
                <form:input path="name" required="required" /><form:errors path="name" class="text-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="label.user"/></td>
            <td>
                <form:input path="username" required="required" /><form:errors path="username" class="text-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="label.password"/></td>
            <td>
                <form:input path="password" required="required" /><form:errors path="password" class="text-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="<spring:message code='label.register'/>" class="btn btn-info"/>
                <input onclick="window.location = '${pageContext.request.contextPath}'" type="button" value="<spring:message code='label.cancel'/>" class="btn"/>
            </td>
        </tr>
    </table>
</form:form>
