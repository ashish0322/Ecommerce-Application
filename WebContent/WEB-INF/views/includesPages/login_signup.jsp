<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Login/SignUp Page</title>

	</head>
<body>
<div id = "topWrapper">
    <div class="container_16">
        <div class="grid_16">
            <div id="logo" class="grid_6"> </b><img src="<c:url value="/resources/images/icons/Cart-icon.png"/>" />
            </div>
           
            <div class="grid_6" id="top">
                <ul>
                    
                    <a href="login.htm" id="join"><li id="greenBtn" class ="Btn showForm">Login</li></a>
                    <a href="signUp.htm" id="join"><li id="greenBtn" class ="Btn showForm">SignUp</li></a>
                </ul>
            </div>
        </div>
    </div>
</div>

<c:set var="val" value="${requestScope.action}" ></c:set>
<c:if test="${val!= null}">
<c:choose>
<c:when test="${val=='login'}">
<div id = "topLogin">
    <div class="container_16">
        <div id="LoginBox" class="grid_16">

            <div class="grid_6" id = "loginForm">
                <form:form action="customerlogin.htm" commandName="user" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:left; padding:20px 0px; color:#FFF;">Login...</h1></strong>
                            </td>
                            <td>
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
                        </tr><tr>
                            <td>

                            </td>
                            <td>
                                <input type="submit" value="Login!!" id="greenBtn" /><br/>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
</c:when>
<c:when test="${val=='signUp'}">
            <div class="grid_6 push_2" id = "RegisterForm">
             <form:form action="adduser.htm" commandName="user" method="post">
                    <table>
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:right; padding:20px 0px; color:#FFF;">Register Now!!</h1></strong>
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
                                <label>Confirm Password</label>
                            </td>
                            <td>
                                <input type="password" name="passAgainReg" placeholder="&bull;&bull;&bull;&bull;&bull;&bull;&bull;" /><br/>
                            </td>
                        </tr>
                         <tr>
                            <td>
                            </td>
                            <td>
                                <input type="submit" value="Register" id="greenBtn" /><br/>
                            </td>
                        </tr>
                    </table>

                </form:form>
            </div>
        </div>
    </div>
</div>
</c:when>
</c:choose>
</c:if>
</body>
</html>