<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3> <spring:message code='label.createnewproject'/> </h3>

<form:form action="${pageContext.request.contextPath}/project/save/${type}" commandName="projectDTO" method="POST">
    <table> 
        
        <form:errors path="*" class="text-error" element="div"/>
        
        <c:forEach var="section" items="${projectDTO.sections}" varStatus="status">                        
                <form:label path="sections[${status.index}].name"> ${section.name}: </form:label>
                <form:input path="sections[${status.index}].content" required="required" />                  
                <form:input path="sections[${status.index}].name" value="${section.name}" type="hidden" />                 
                <br/>
            </c:forEach>   
                
        <td colspan='2'>        
            <input name="Save" type="submit" value="<spring:message code='label.save'/>" class="btn btn-info"/>            
            <input onclick="window.location = '${pageContext.request.contextPath}'" type="button" value="<spring:message code='label.cancel'/>" class="btn btn-danger"/>
        </td>
    
    </table>    
</form:form>

      
