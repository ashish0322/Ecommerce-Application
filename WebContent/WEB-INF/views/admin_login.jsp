

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ProKart</title>
        

        
        <link rel="shortcut icon" href="<c:url value="/resources/images/logo/ico.ico"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/text.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/960_16.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/product.css"/>"/>

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/lightbox.css"/>"  />

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>"/>

        <script src="<c:url value="/resources/js/jquery-1.7.2.min.js"/>"></script>
        <script src="<c:url value="/resources/js/lightbox.js"/>"></script>
        <script src="<c:url value="/resources/js/myScript.js"/>"></script>
    </head>
    <body>
        
        
       
        <%@include file="includesPages/searchbar.jsp" %>

        <div class="container_16">
            <div class="grid_13 push_2" id="whiteBox" style="padding:10px 0px 10px 0px;">
                <h1 class="push_4" style="padding: 10px;" >Administrator Login</h1>    
                <hr/> <br/><div class="grid_9 push_2" style="padding:10px;">
                        <form method="post" action="adminlogin.htm">
                            <div class="grid_2">
                                Email
                            </div>
                            <div class="grid_5">
                                <input type="text" name="email" placeholder="xyz@xyz.com" required/>
                            </div>
                            <div class="clear"></div><br/>
                            
                            <div class="grid_2">
                                Password 
                            </div>
                            <div class="grid_5">
                                <input type="password" name="pass" placeholder="&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;" required/><br/><br/> 
                                <b style="color:red;"></b> <c:out value="${requestScope.error}"/></b>
                                <input id="greenBtn" type="submit" value="Login"/>
                            </div>
                            <div class="clear"></div><br/>
                        </form>
                    </div>
                </div>
        </div>
    </body>
</html>
