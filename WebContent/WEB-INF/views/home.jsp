<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ProKart</title>
        <!-- Default Stylesheets -->
       	
		<link rel="shortcut icon" href="<c:url value="/resources/images/logo/ico.ico"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/text.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/960_16.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/slider.css"/>">

        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>

        <script type="text/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>

<script type="text/javascript">

    // This is the script for the banner slider

    $(document).ready(function() {
        $('#slider').s3Slider({
            timeOut: 7000
        });
    });
    </script>


<script type="text/javascript" src="<c:url value="/resources/js/myScript.js"/>"></script>

<script type="text/javascript">
function madeAjaxCall(){
 $.ajax({
  type: "post",
  url: "checkuser.htm",
  cache: false,    
  data:'userName=' + $("#userName").val(),
  success: function(response){
   $('#result').html("");
   var json = JSON.parse(response);
   //var obj = JSON.parse(response);
   $('#result').html(json);
  },
  error: function(){      
   alert('Error while request..');
  }
 });
}
</script>

<script type="text/javascript">
function madeAjaxCallEmail(){
 $.ajax({
  type: "post",
  url: "checkemail.htm",
  cache: false,    
  data:'emailId=' + $("#emailId").val(),
  success: function(response){
   $('#result1').html("");
   var json = JSON.parse(response);
   //var obj = JSON.parse(response);
   $('#result1').html(json);
  },
  error: function(){      
   alert('Error while request..');
  }
 });
}
</script>


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
<%@include file="includesPages/searchbar.jsp" %>
<c:set var="isValid" value="${requestScope.InvalidLogin}" ></c:set>
<c:if test="${isValid == null }">

<c:set var="val" value="${requestScope.action}" ></c:set>
<c:if test="${val== null}">
    
 

    <div id = "banner">
        <div class="container_16">
            <div class="grid_16" style="padding-left: 20px; " id="slider">	
                <ul id="sliderContent">		
                    <!-- Duplicate this code for each image -->				

                    <li class="sliderImage" style="display: none; ">

                        <img src="<c:url value="/resources/images/banner/b1.png"/>" width="900" height="300" /> 

                        <span class="top" style="display: none; ">

                            <strong>Fountain Pens...</strong>	

                            <br>From the International and Indian markets, we have the variety of the High Class fountain 

                            pens. Available now for you to get your hands on.

                        </span>

                    </li>  
                    <li class="sliderImage" style="display: none; ">

                        <img src="<c:url value="/resources/images/banner/b2.png"/>" width="900" height="300" /> 

                        <span class="top" style="display: none; ">

                            <strong>Books, Diaries...</strong>				

                            <br>Text Books, Single Lines, Double Lines, Red 'n Blue Lines, Fullscape Books

                            Drawing Books, and all the paper you want to write all throughout to learn 

                            Available now at your doorsteps* 



                        </span>

                    </li>  



                    <li class="sliderImage" style="display: none; ">

                        <img src="<c:url value="/resources/images/banner/b5.png"/>" width="900" height="300" /> 

                        <span class="top" style="display: none; ">

                            <strong>Kids Colors...</strong>				

                            <br>Collection of Non-Toxic childrens colors available

                            Let your child learn the art of painting at an early age 

                            by having his hands on the colors available here... 

                        </span>

                    </li>  

                    <li class="sliderImage" style="display: none; ">

                        <img src="<c:url value="/resources/images/banner/b7.jpg"/>" width="900" height="300" /> 

                        <span class="top" style="display: none; ">

                            <strong>Graphic Novels...</strong>				

                            <br>The most amazing titles that you always wanted to get your hands onn.. 

                            Now you have the opportunity to have them all in your personal library.

                            International as well as Indian titles of many authors available

                        </span>

                    </li>   



                    <li class="sliderImage" style="display: none; ">

                        <img src="<c:url value="/resources/images/banner/b6.png"/>" width="900" height="300" /> 

                        <span class="top" style="display: none; ">

                            <strong>Color World...</strong>				

                            <br>A vast variety of different sets of colors including Oil-Pastels,

                            Pencil Colors, Poster Colors, Acrylic Colors and many more...

                        </span>

                    </li>   

                    <div class="clear sliderImage"></div>  

                </ul>
            </div>
        </div>
    </div>



    <div class="container_16">
        <div id = "contents">
            <!-- LeftSide -->



            <div id="leftside" class="grid_3">
                <div>
                    <ul id="leftsideNav">
                        <li><a href="#"><strong>Categories</strong></a></li>
                   		<c:forEach var="categ" items="${sessionScope.categories}">   
                        
                            <li><a href="viewProducts.htm?prod=<c:out value="${categ.title}"/>">
                            <c:out value="${categ.title}"></c:out></a></li>
                        
                        </c:forEach>  
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
         <c:set var="taskValue" value="${requestScope.selected}"></c:set>
         <c:if test="${taskValue==null}">
            <jsp:include page="includesPages/mainHeaders/products.jsp"></jsp:include>
            </c:if>
         
         
         <c:if test="${taskValue!= null }">
         <div class="grid_13" id="productStrip"> 
                       <a href="viewProducts_.jsp">
                           <div class="ProductHeading">
                               <div class="grid_12">
                                   <h2 class="heading"><c:out value="${requestScope.selected}"/></h2>
                               </div>
                               <!--<div id="viewMore" class="grid_3">
                                   <h6 class="blue">View More</h6>
                               </div>-->
                           </div>
                       </a>
                       <div class="clear"></div>
        
         <c:forEach var="prod" items="${sessionScope.products}"> 
                       <div id="productList" class="grid_3 prodGrid"> 
                       
                    <a href="product.jsp?id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>" /></a>
                    
                    <p id="info">
                        <a href="product.jsp?id="><span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span><br/>
                       <span class="blue">Seller Name :aka<c:out value="${prod.sellerName}"></c:out></span>
                    </p>   
                          
                </div>
                </c:forEach>   
        </div>
        </c:if>
        </div>
        <!--The Middle Content Div Closing -->
    </div>
    <!--The Center Content Div Closing -->
    
</c:if>
<c:if test="${val!= null}">
<c:choose>
<c:when test="${val=='regSuccess'}">

<center>
                   
                    <h2> Registration Success!!!!!!!! please login to continue shopping</h2>
	
	</center>
	
</c:when>

<c:when test="${val=='regFail'}">

<center>
                   
                    <h2> Registration failed!!!!!!! please retry </h2>
	
	</center>
	
</c:when>
<c:when test="${val=='login'}">

    
<center>
             
                <form:form action="customerlogin.htm" commandName="login" method="post">
                    <table>
                    
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:left; padding:20px 0px; color:black;">Login...</h1></strong>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:left; padding:20px 0px; color:blue;">
                                <c:out value="${requestScope.pleaseLogin}"/></h1></strong>
                            </td>
                            <td>
                            </td>
                        </tr>
                           <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:left; padding:20px 0px; color:blue;">
                                <c:out value="${requestScope.result}"/></h1></strong>
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
                            	<input type="hidden" name="role" value="customer"/>
                                <input type="submit" value="Login!!" id="greenBtn" /><br/>
                            </td>
                        </tr>
                      
                     
                          
                    </table>
                </form:form>
         </center>
</c:when>
<c:when test="${val=='signUp'}">
           <center>
            
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
                            <form:input path="emailId" size="30" id ="emailId"  onblur="madeAjaxCallEmail();" placeholder="ashish@gmail.com"/> <font color="red"><form:errors path="emailId"/></font>
                            </td>
                            <td>
                           <div id="result1" style="color: red;"></div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                <label>Username</label>
                            </td>
                            <td>
                            <form:input path="userName" size="30" id="userName" onblur="madeAjaxCall();" placeholder="ashish0322"/> <font color="red"><form:errors path="userName"/></font>
                            </td>
                            <td>
                            <div id="result" style="color: red;"></div>
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
                                <label>Street</label>
                            </td>
                            <td>
                            <form:input path="address.street" size="30" placeholder="Street"/> 
                            <font color="red"><form:errors path="address.street"/></font>
                            </td>
                        </tr><tr>
                            <td>
                                <label>City</label>
                            </td>
                            <td>
                            <form:input path="address.city" size="30" placeholder="Rajahmundry"/> 
                            <font color="red"><form:errors path="address.city"/></font>
                            </td>
                        </tr><tr>
                            <td>
                                <label>State</label>
                            </td>
                            <td>
                            <form:input path="address.state" size="30" placeholder="AndhraPradesh"/> 
                            <font color="red"><form:errors path="address.state"/></font>
                            </td>
                        </tr><tr>
                            <td>
                                <label>Country</label>
                            </td>
                            <td>
                            <form:input path="address.country" size="30" placeholder="India"/> 
                            <font color="red"><form:errors path="address.country"/></font>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Contact Number</label>
                            </td>
                            <td>
                            <form:input path="address.contactNumber" size="30" placeholder="6176020501"/> <font color="red">
                            <form:errors path="address.contactNumber"/></font>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td>
                                <label>ZipCode</label>
                            </td>
                            <td>
                            <form:input path="address.pinCode" size="30" placeholder="02120"/> <font color="red">
                            <form:errors path="address.pinCode"/></font>
                            </td>
                        </tr>
                         <tr>
                            <td>
                            
                            </td>
                            <td>
                                <input type="submit" value="Register" id="greenBtn" /><br/>
                            </td>
                        </tr>
                        <tr>
                        <td>
                        <b style="color: red;" ><c:out value="${requestScope.emailId_UsernameExists}">></c:out></b>
                        </td>
                        </tr>
                    </table>

                </form:form>
       </center>
        
</c:when>
</c:choose>
</c:if>
</c:if>
<c:if test="${isValue == 'InvalidLogin'}">
<h1> Please Login!!!!!!!!!!</h1>
</c:if>


</body>
</html>



