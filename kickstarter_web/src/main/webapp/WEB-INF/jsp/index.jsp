<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <ul class="unstyled projects-list">
        <c:forEach var="project" items="${projects}" varStatus="projectsCounter">
            <li class="project-item">
                <blockquote>
                    <p>
                        <a href="<c:url value='show/${project.id}' />">
                            ${project.getSectionByName('nombre').content}
                        </a>
                    </p>
                    <small>${project.creator}</small>
                    <h5>
                        <img class="icon" src="<c:url value='/img/money.png' />" alt="Required money" />
                        &#36;${project.getSectionByName('dinero requerido').content}
                        <span class="separator"> | </span>
                        <img class="icon" src="<c:url value='/img/save.png' />" alt="Gathered money" />
                        ${project.totalInvestments}
                    </h5>
                </blockquote>                
            </li>    
        </c:forEach>
        <div class="clearfloat"></div>
    </ul>
</div>