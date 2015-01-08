<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <spring:message code="menu.project"/>
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="<c:url value='/project/create/game' />"><spring:message code="menu.project.creategame"/></a></li>
            <li><a href="<c:url value='/project/create/movie' />"><spring:message code="menu.project.createmovie"/></a></li>
        </ul>
    </li>
</ul>
