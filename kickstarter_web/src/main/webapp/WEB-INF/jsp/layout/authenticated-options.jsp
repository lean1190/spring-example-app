<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="btn-group pull-right">
    <a class="btn btn-primary" href="#">
        <i class="icon-user icon-white"></i>
        <security:authentication property="principal.username" />
    </a>
    <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li>
            <a href="<c:url value='/j_spring_security_logout' />">
                <i class="icon-off"></i>
                <spring:message code="label.logout"/>
            </a>
        </li>
    </ul>
</div>
