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
<style type="text/css">
    .backRed {
        background: #CC0000;
        padding: 3px 7px;
        margin-right: 3px;
    }
</style>

<script type="text/javascript" >

    // This is the script for the banner slider

    $(document).ready(function() {
        $('#slider').s3Slider({
            timeOut: 7000
        });
    });
</script>


<script type="text/javascript" src="<c:url value="/resources/js/myScript.js"/>"></script>

</head>
<body>
<c:set var="task" value="${requestScope.task}"></c:set>
<c:if test="${task == null }">
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
</c:if>
<c:if test="${task == 'customer' }">
<div id = "topWrapper">
        <div class="container_16">
                <div class="grid_16">
                        <div id="logo" class="grid_6"><img src="<c:url value="/resources/images/icons/administrator-icon.png"/>" />
                        </div>
                        <div class="grid_9" id="top">
                            <ul>
                                <a href="customerLogout.htm"><li id="greenBtn" class ="Btn showForm">Logout</li></a>
                              <li>  <h1 align="right" style="color:white;">Welcome <c:out value="${sessionScope.customerName}"></c:out> </h1></li>  
                              <a href="viewCart.htm"><li class ="Btn showForm"><span class="backRed">
                              <c:out value="${sessionScope.cartValue}"/></span> in My Cart </li></a>                                                      
                            </ul>
                        </div>
                </div>
            </div>
    </div>
<%@include file="includesPages/searchbarCust.jsp" %>
</c:if>

<div class="container_16">

 <c:set var="action" value="${requestScope.action}"></c:set>
 <c:if test="${action == null }">
			
			
            <div class="grid_16" id="productStrip">
                <div class="ProductHeading">
                    <div class="grid_16">
                    <c:set var="prod" value="${sessionScope.selectedProd}"/>
                        <h2 class="heading"><c:out value="${prod.productName}"/>- <c:out value="${prod.company}"/> -<c:out value="${prod.category_name}"/></h2>
                    </div>
                </div>

                <div class="grid_10">
                    <div class="grid_10">
                        <br/>
                        <h5>Category :<a href="#"><span class="blue"><c:out value="${prod.productName}"/></span></a> ></h5>
                        <div class="clear"></div>
                        <br/>
                        <h5>Priced At <span class="BigRed">$<c:out value="${prod.price}"/></span></h5>
                        <br/>
                        <br/>
                        
                        <h1>Summary Of this item</h1>
                        <div class="clear"></div>
                        <p>Summary of <c:out value="${prod.productName}"/>

                           <c:out value="${prod.description}"/>

                        <h1>Brief Description</h1>
                        <br/>
                        <table class="grid_6" id="descripTable">
                            <tr class="grid_6">
                                <td>Name:</td>
                                <td><c:out value="${prod.productName}"/></td>
                            </tr>
                            <tr class="grid_6">
                                <td>Category</td>
                                <td><c:out value="${prod.productName}"/></td>
                            </tr>
                            
                            <tr class="grid_6">
                                <td>Company </td>
                                <td><c:out value="${prod.company}"/></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div  class="grid_5">
                    <div id="productImages">
                        <!-- Images with T are thumbs Images While with Q are The actual source Images -->

                        <img class="BigProductBox" alt="<c:out value="${prod.productName}"/>" src="<c:out value="${prod.photoName}"/>" />

                        <div class="clear"></div>
                       
                 <table>
                 <tr><td>
                        Available Quantity:<c:out value="${prod.quantity}"/>
                        </td></tr>
                   <c:set var="isCustIn" value="${sessionScope.isCusIn}"/>
                   <c:if test="${isCustIn == null}" >   
                        <tr> <td><a href="addProductToCart.htm?role=guest&id=<c:out value="${prod.productId}"/>">
                            <div class="grid_3" id="buy">
                                Add to Cart
                            </div>
                        </a></td></tr>
                        </c:if>
                     <c:if test="${isCustIn == 'custIn'}" >   
                        <tr> <td><a href="addProductToCart.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                            <div class="grid_3" id="buy">
                                Add to Cart
                            </div>
                        </a></td></tr>
                        </c:if>
                        </table> 
                
                        </div>
                    <div class="clear"></div>
                    
                </div>

            </div> 
</c:if>
<c:if test="${action == 'addedtoCart' }">
<div class="grid_16" id="productStrip">
                <div class="ProductHeading">
                    <div class="grid_16">
	<h2> Product Added to cart Successfully</h2>
	</div>
	</div>
	</div>
</c:if>
			<c:if test="${action == 'outOfStock' }">
<div class="grid_16" id="productStrip">
                <div class="ProductHeading">
                    <div class="grid_16">
	<h2> Product Out of Stock!!! Sorry for the inconvenience!!!!!!!</h2>
	</div>
	</div>
	</div>
</c:if>

</div>
</body>
</html>