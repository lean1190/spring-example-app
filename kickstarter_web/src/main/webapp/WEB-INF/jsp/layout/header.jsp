<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="<c:url value="/" />"><strong class="orange">kickstarter</strong></a>
            <tiles:insertTemplate template="/WEB-INF/jsp/layout/menu.jsp"></tiles:insertTemplate>
        </div>
    </div>

</div>  
