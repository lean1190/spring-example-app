<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" use-expressions="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
                        
        <!-- CSS -->
        <link rel="stylesheet" href="<c:url value='/css/main.css' />" />
        
        <!-- JS -->
        <script type="text/javascript" src="<c:url value='/js/jquery-2.0.3.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>   
    </head>
    <body>
        <div id="wrap">
            <tiles:insertAttribute name="header" />
            <div class="container">
                <!-- True content, controller rendered -->
                <tiles:insertAttribute name="body" />
            </div>
            <div id="push"></div>
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>
