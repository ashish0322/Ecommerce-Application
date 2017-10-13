
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
        <style type="text/css">
    .backRed {
        background: #CC0000;
        padding: 3px 7px;
        margin-right: 3px;
    }
</style>
    </head>
    <body>
       	<div id = "topWrapper">
        <div class="container_16">
                <div class="grid_16">
                        <div id="logo" class="grid_6"><img src="<c:url value="/resources/images/icons/administrator-icon.png"/>" />
                        </div>
                        <div class="grid_9" id="top">
                            <ul>
                                <a href="adminlogout.htm"><li id="greenBtn" class ="Btn showForm">Logout</li></a>
                              <li>  <h1 align="right" style="color:white;">Welcome <c:out value="${sessionScope.validlogin}"></c:out> </h1></li>                                                        
                            </ul>
                        </div>
                </div>
            </div>
    </div>
     
        <%@include file="includesPages/searchbar1.jsp" %>

       	       	
       	<div class="container_16">
       	<div id = "contents">
            <!-- LeftSide -->



            <div id="leftside" class="grid_3">
                <div>
                    <ul id="leftsideNav">
                        <li><strong>Menu</strong></a></li>
                        
                        
                            <li><a href="addSiteAdmin.htm"><strong>Add Seller</strong></a></li>
                        	<li><a href="addHelpDesk.htm"><strong>Add Helpdesk </strong></a></li>
                        	<li><a href="adminViewProducts.htm"><strong>View Products</strong></a></li>
                        	<li><a href="performanceStats.htm"><strong>Performance Stats </strong></a></li>
                      
                    </ul>
                </div>
                <div class="adv">
                    <h2><br/>This is The Header of an Advertisement</h2>
                    <p>We offer Advertisement display here </p>
                </div>
            </div>


        </div>
        
         <!-- Middle -->
        <div id="middle"class="grid_13">
        
        <c:set var="taskValue" value="${requestScope.action}"></c:set>
         <c:if test="${taskValue==null}">
        <h1> Main Admin Home</h1>
        </c:if>
        
        <c:if test="${taskValue!= null }">
        <c:choose>
        <%-- Content for ProKart Employee registration   --%> 
        <c:when test="${taskValue == 'addAdmin' }">
        <div class="grid_6 push_2" id = "RegisterForm">
             <form:form action="addEmployee.htm" commandName="employee" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:right; padding:20px 0px; color:#FFF;"> Site Admin Registration Form</h1></strong>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>First Name</label>
                            </td>
                            <td>
                            <form:input path="firstName" size="30" placeholder="Ashish"/> <font color="red"><form:errors path="firstName"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Last Name</label>
                            </td>
                            <td>
                            <form:input path="lastName" size="30" placeholder="Maheedhara"/> <font color="red"><form:errors path="lastName"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Email</label>
                            </td>
                            <td>
                            <form:input path="emailId" size="30" placeholder="ashish@gmail.com"/> <font color="red"><form:errors path="emailId"/></font>
                            </td>
                        </tr>
                        
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
                        </tr>
                        
                         <tr>
                            <td>
                            </td>
                            <td>
                            	<input type="hidden" value="adminReg" name="regFor"/>
                                <input type="submit" value="Register" id="greenBtn" /><br/>
                            </td>
                        </tr>
                    </table>

                </form:form>
            </div>
         
        </c:when>
        
        
        <%-- Content for help Desk registration   --%> 
        <c:when test="${taskValue == 'addHelpDesk' }">
        <div class="grid_6 push_2" id = "RegisterForm">
             <form:form action="addEmployee.htm" commandName="employee" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:right; padding:20px 0px; color:#FFF;"> HelpDesk Employee Registration Form</h1></strong>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>First Name</label>
                            </td>
                            <td>
                            <form:input path="firstName" size="30" placeholder="Ashish"/> <font color="red"><form:errors path="firstName"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Last Name</label>
                            </td>
                            <td>
                            <form:input path="lastName" size="30" placeholder="Maheedhara"/> <font color="red"><form:errors path="lastName"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Email</label>
                            </td>
                            <td>
                            <form:input path="emailId" size="30" placeholder="ashish@gmail.com"/> <font color="red"><form:errors path="emailId"/></font>
                            </td>
                        </tr>
                        
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
                        </tr>
                        
                         <tr>
                            <td>
                            </td>
                            <td>
                            	<input type="hidden" value="helpDesk" name="regFor"/>
                                <input type="submit" value="Register" id="greenBtn" /><br/>
                            </td>
                        </tr>
                    </table>

                </form:form>
            </div>
         
        </c:when>
        <c:when test="${taskValue=='regSuccess' }">
        
        <b> Emplooyee<c:out value="${requestScope.empName}"></c:out> registered successfully</b>
        </c:when>
        <c:when test="${taskValue=='regFail' }">
        
        <b> Registration Failed!!</b>
        </c:when>
         <c:when test="${taskValue == 'viewProducts' }">
         
         <c:out value="${requestScope.errorrr}"/>
         <b> Products Available on Pro Kart</b>
         <form action="adminViewProducts.htm" method="post" >
         <span>
         
         <label>Category</label>
         <select name="category_name" >
			<c:forEach var="categ" items="${requestScope.catList}">
                       <option value="<c:out value="${categ.title }"/>"/>
                       <c:out value="${categ.title }"/>
            </c:forEach>
		</select>
		<label>Seller</label>
         <select name="sellerName" >
			<c:forEach var="seller" items="${requestScope.sellList}">
                       <option value="<c:out value="${seller.firstName }"/>"/>
                       <c:out value="${seller.firstName }"/>
            </c:forEach>
		</select>
		<input type="submit" value="Submit" id="greenBtn"/>
		</span>
		</form>
		<div class="grid_5 push_1" id = "RegisterForm">
		
         <jsp:include page="includesPages/adminViewProducts.jsp"></jsp:include>
         </div>
         </c:when>
        </c:choose>
        </c:if>
        <a></a>
        
        </div>
        
        </div>
        <jsp:include page="includesPages/footer.jsp"></jsp:include>
    </body>
</html>
