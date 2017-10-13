

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
		<
        <div class="container_16">
            <div class="grid_13 push_2" id="whiteBox" style="padding:10px 0px 10px 0px;">
                <h1 class="push_4" style="padding: 10px;" >Seller Login</h1>    
                <hr/> <br/><div class="grid_9 push_2" style="padding:5px;">
                     <form:form action="employee_login.htm" commandName="login" method="post">
                    <table>
                          <tr>
                            <td>
                                <label>Username</label>
                            </td>
                            <td>
                            <form:input path="userName" size="30" placeholder="ashish0322"/> <font color="red"><form:errors path="userName"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Password</label>
                            </td>
                            <td>
                            <form:password path="password" size="30" placeholder="............"/> <font color="red"><form:errors path="password"/></font>
                            </td>
                        </tr><tr>
                               <td>
                                <label>Role</label>
                            </td>
                            <td>
	                                <select  name="role" required>
										<option placeholder="choose one" ></option>
									  <option value="Seller" >Seller</option>
									 <option value="Helpdesk" >Helpdesk</option>
									</select><br/>
                            </td>
                        </tr><tr>
                            <td>

                            </td>
                            <td>
                                <input type="submit" value="Login!!" id="greenBtn" /><br/>
                               <b style="color:red;"></b> <c:out value="${requestScope.error}"/></b>
                            </td>
                        </tr>
                    </table>
                </form:form>
                    </div>
                </div>
        </div>
    </body>
</html>
