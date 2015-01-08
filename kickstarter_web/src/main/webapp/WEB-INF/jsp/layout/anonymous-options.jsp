<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="<c:url value="/register" />" class="btn btn-success pull-right register-btn"><spring:message code="label.register"/></a>
<form action="<c:url value='/j_spring_security_check' />" method="post" class="navbar-form pull-right">
    <input class="span2" type="text" name="j_username" placeholder="<spring:message code='label.user'/>">
    <input class="span2" type="password" name="j_password" placeholder="<spring:message code='label.password'/>">
    <button type="submit" class="btn btn-info"><spring:message code="label.login"/></button>
</form>
