<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped">
    <thead>
        <tr>
            <td>
                <strong><spring:message code='label.project.name'/></strong>
            </td>
            <td>
                <strong><spring:message code='label.project.type'/></strong>
            </td>
            <td>
                <strong><spring:message code='label.project.creator'/></strong>
            </td>
            <td>
                <strong><spring:message code='label.project.requiredmoney'/></strong>
            </td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="project" items="${projects}" varStatus="projectsCounter">
            <tr>
                <td>
                    <a href="<c:url value='show/${project.id}' />">
                        ${project.getSectionByName('nombre').content}
                    </a>
                </td>
                <td>${project.type}</td>
                <td>${project.creator}</td>
                <td>&#36;${project.getSectionByName('dinero requerido').content}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>