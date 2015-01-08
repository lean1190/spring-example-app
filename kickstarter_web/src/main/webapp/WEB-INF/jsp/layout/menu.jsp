<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="nav-collapse collapse">
    <c:if test="${empty enable}">
        <!-- ROLE_USER nav menu -->
        <security:authorize access="hasRole('ROLE_USER')">
            <tiles:insertTemplate template="/WEB-INF/jsp/layout/user-nav.jsp"></tiles:insertTemplate>
        </security:authorize>

        <!-- ROLE_ADMIN nav menu -->
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <tiles:insertTemplate template="/WEB-INF/jsp/layout/admin-nav.jsp"></tiles:insertTemplate>
        </security:authorize>

        <!-- Search form -->
        <form id="search-box" method="POST" action="<c:url value="/search" />" class="navbar-form navbar-left pull-left" role="search">
            <input name="search" type="text" class="form-control" placeholder="<spring:message code='label.search'/>">
            <button type="submit" class="btn btn-default"><img src="<c:url value="/img/search.png" />" alt="Search" /></button>
        </form>

        <!-- User related content -->
        <security:authorize access="isAnonymous()">
            <tiles:insertTemplate template="/WEB-INF/jsp/layout/anonymous-options.jsp"></tiles:insertTemplate>
        </security:authorize>

        <security:authorize access="isAuthenticated()">
            <tiles:insertTemplate template="/WEB-INF/jsp/layout/authenticated-options.jsp"></tiles:insertTemplate>
        </security:authorize>
    </c:if>

</div>