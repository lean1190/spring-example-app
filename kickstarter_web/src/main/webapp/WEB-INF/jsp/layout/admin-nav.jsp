<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <spring:message code="menu.admin"/>
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="<c:url value='/admin/rankingUsers' />"><spring:message code="menu.project.rankingUsers"/></a></li>
            <li><a href="<c:url value='/admin/inversions' />"><spring:message code="menu.project.inversions"/></a></li>
        </ul>
    </li>
</ul>