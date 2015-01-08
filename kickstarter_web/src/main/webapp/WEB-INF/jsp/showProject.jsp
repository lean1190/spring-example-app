<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="project-wrapper">
    <span class="pull-right">
        <img src="<c:url value='/img/saved.png' />" alt="Saved" />
        <strong class="project-invested">&#36;${project.totalInvestments}</strong>
    </span>
    <ol class="unstyled project-content">
        <c:forEach var="section" items="${project.sections}">
            <li>
                <div class="project-section-name">${section.name}</div>
                <p class="project-section-content">${section.content}</p>
            </li>
        </c:forEach>
        <li>
            <div class="project-section-name"><spring:message code='label.project.state'/></div>
            <p class="project-section-content">${project.state}</p>
        </li>
    </ol>
        
    <!-- 
    En vez de tener esta chorrera de if choose when etc etc, es mejor y/o tiene sentido 
    tenerlo en el controller y pasarle directamente las variables a evaluar? con varios boolean
    -->
    
    <!-- If there is a loged in user and his id is set -->
    <c:if test="${not empty userId}">
        <div class="project-actions">             
            <c:choose>
                <c:when test="${project.state == 'Activo'}">
                    <security:authorize access="hasRole('ROLE_USER')">                    
                        <c:choose> 
                            
                            <c:when test="${userId != project.creatorId}">
                                <form method="POST" action="<c:url value='/project/invest/${project.id}' />">
                                    <fieldset>
                                        <legend>
                                            <h3><spring:message code='label.project.helpfunding'/></h3>
                                        </legend>
                                        <div>
                                            <div class="input-prepend">
                                                <span class="add-on"><strong>&#36;</strong></span>
                                                <input name="amount" required="required "class="span2" type="text" placeholder="<spring:message code='label.project.amount'/>">
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-success">
                                            <img class="icon" src="<c:url value='/img/pay.png' />" alt="Invest" />
                                            <spring:message code='label.project.invest'/>
                                        </button>
                                    </fieldset>
                                </form>
                            </c:when>

                            <c:otherwise>
                                <a href="<c:url value='/project/finish/${project.id}' />" class="btn btn-danger">
                                    <img class="icon" src="<c:url value='/img/finish.png' />" alt="Finish" />
                                    <spring:message code='label.project.finish'/>
                                </a>
                            </c:otherwise>   
                            
                        </c:choose>
                    </security:authorize>

                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="<c:url value='/admin/project/suspected/${project.id}' />" class="btn btn-warning">
                            <img class="icon" src="<c:url value='/img/suspect.png' />" alt="Suspected" />
                            <spring:message code='label.project.suspected'/>
                        </a>
                    </security:authorize>  
                </c:when>

                <c:otherwise>
                    <c:if test="${project.state == 'Sospechado'}">
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="<c:url value='/admin/project/finish/${project.id}' />" class="btn btn-danger">
                                <img class="icon" src="<c:url value='/img/skull.png' />" alt="Finish" />
                                <spring:message code='label.project.finishhim'/>
                            </a>
                            <a href="<c:url value='/admin/project/resume/${project.id}' />" class="btn btn-success">
                                <img class="icon" src="<c:url value='/img/smile.png' />" alt="Resume" />
                                <spring:message code='label.project.resume'/>
                            </a>
                        </security:authorize> 
                    </c:if>
                </c:otherwise>
            </c:choose>             
        </div>
    </c:if>
</div>