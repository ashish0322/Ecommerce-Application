
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
    
    <c:set var="sessionValid" value="${sessionScope.Customer}"></c:set>
                            <c:if test="${sessionValid == null }">
       	<div id = "topWrapper">
        <div class="container_16">
                <div class="grid_16">
                        <div id="logo" class="grid_6"><img src="<c:url value="/resources/images/icons/administrator-icon.png"/>" />
                        </div>
                        <div class="grid_9" id="top">
                            <ul>
                                                     
                            
                                <a href="customerLogout.htm"><li id="greenBtn" class ="Btn showForm">Logout</li></a>
                              <li>  <h1 align="right" style="color:white;">Welcome <c:out value="${sessionScope.customerName}"></c:out> </h1></li>  
                              <a href="viewCart.htm"><li class ="Btn showForm">
                              <span class="backRed"><c:out value="${sessionScope.cartValue}"/>
                              </span> in My Cart </li></a>                                                      
                            </ul>
                        </div>
                </div>
            </div>
    </div>
     
        <%@include file="includesPages/searchbarCust.jsp" %>

       	       	
       	<div class="container_16">
       	
       	<c:set var="mainValue" value="${requestScope.action1}"></c:set>
       	<c:if test="${mainValue == null}">
       	<div id = "contents">
            <!-- LeftSide -->



            <div id="leftside" class="grid_3">
                <div>
                    <ul id="leftsideNav">
                        <li><a href="#"><strong>Categories</strong></a></li>
                   		<c:forEach var="categ" items="${sessionScope.categories}">   
                        
                            <li><a href="viewProducts.htm?prod=<c:out value="${categ.title}"/>&user=cust">
                            <c:out value="${categ.title}"></c:out></a></li>
                        
                        </c:forEach>  
                    </ul>
                </div>
               
            </div>


        </div>
        </c:if>
        
         <c:if test="${mainValue == 'showPlacedOrders'}">
        
         <div class="grid_16">
                    <div class="grid_16"  id="CartTable" style="padding:10px 00px;">
                        <h1 class="grid_15" style ="text-align: center; padding: 10px 0px 10px 0px;  border-top: 20px #444 solid;"> Your Orders</h1>  
                        
                       
                        <%@include file="includesPages/ordersCust.jsp" %> 
                        
                        
                      </div>
                      </div>  
                        
        
        </c:if>
        
         <!-- Middle -->
        <div id="middle"class="grid_13">
        
        <c:set var="taskValue" value="${requestScope.action}"></c:set>
         <c:if test="${taskValue!=null}">
         <c:if test="${taskValue=='showall'}">
        <h1> Enjoy your shopping!!!!!! </h1>
         <div class="grid_13" id="productStrip"> 
                       <a href="viewProducts_.jsp">
                           <div class="ProductHeading">
                               <div class="grid_12">
                                   <h2 class="heading">Products Available Currently</h2>
                               </div>
                               <!--<div id="viewMore" class="grid_3">
                                   <h6 class="blue">View More</h6>
                               </div>-->
                           </div>
                       </a>
                       <div class="clear"></div>
                       
                       <c:forEach var="prod" items="${sessionScope.products }"> 
                       <div id="productList" class="grid_3 prodGrid"> 
                       
                    <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>" /></a>
                    
                    <p id="info">
                        <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>"><span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span>
                        <span class="blue">Seller Name :aka<c:out value="${prod.sellerName}"></c:out></span>
                       
                    </p>   
                          
                </div>
                </c:forEach>   
              </div>
        </c:if>
       </c:if> 
        <c:if test="${taskValue == 'selectedcat' }">
        
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
        
         <c:forEach var="prod" items="${sessionScope.products }"> 
                       <div id="productList" class="grid_3 prodGrid"> 
                       
                    <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>" /></a>
                    
                    <p id="info">
                         <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                         <span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span><br/>
                        <span class="blue">Seller Name :aka<c:out value="${prod.sellerName}"></c:out></span>
                       
                    </p>   
                          
                </div>
                </c:forEach>   
        </div>
        </c:if>
        
        <%-- Content View Cart customer   --%> 
        <c:if test="${taskValue == 'viewCart' }">
        
        <div class="grid_13" id="productStrip"> 
                       <a href="viewProducts_.jsp">
                           <div class="ProductHeading">
                               <div class="grid_12">
                                   <h2 class="heading">Items currently in your cart</h2>
                               </div>
                               <!--<div id="viewMore" class="grid_3">
                                   <h6 class="blue">View More</h6>
                               </div>-->
                           </div>
                       </a>
                       
                       <div><h1 style="color: blue;"><c:out value="${requestScope.nullCart}"/></h1></div>
                       <div class="clear"></div>
                       
                       <c:forEach var="prod" items="${sessionScope.cart }"> 
                       <div id="productList" class="grid_3 prodGrid"> 
                       
                    <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>" /></a>
                    
                    <p id="info">
                        <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>"><span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span>
                       <span><a href="removeItem.htm?id=<c:out value="${prod.productId}"/>&
                       price="<c:out value="${prod.price}"/>"><b style="color: blue;" > Remove</b></a></span>
                    </p>   
                          
                </div>
                </c:forEach>   
              </div>
         <div class="grid_3" id="buy">
                                Cart Total:<c:out value="${sessionScope.finalTotal}"/>
                       </div>
                       <div class="grid_3" id="buy">
                        <a href="checkout.htm">Check Out</a>
                       </div>
         </c:if>
        
        <c:if test="${taskValue == 'checkOutCart' }">
        <div class="grid_13" id="productStrip"> 
                       <a href="viewProducts_.jsp">
                           <div class="ProductHeading">
                               <div class="grid_12">
                                   <h2 class="heading">Order Checkout</h2>
                               </div>
                              
                           </div>
                       </a>
                        <c:forEach var="prod" items="${sessionScope.cart }"> 
                       <div id="productList" class="grid_3 prodGrid"> 
                       
                    <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>" /></a>
                    
                    <p id="info">
                        <a href="productDetails.htm?role=customer&id=<c:out value="${prod.productId}"/>"><span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span><br/>
                        
                    </p>   
                          
                </div>
                </c:forEach> 
                       <span class="blue">Orders will be shipped to your default address</span><br/>
                       <span class="red">Estimated Shipping 3 days </span><br/>
                       <span class="green">Shipper: Prokart Logistics </span> 
                       
                    <div >  
                    <ul>
                  <li> <div class="grid_3" id="buy">
                                Cart Total:<c:out value="${sessionScope.finalTotal}"/>
                       </div></li>
                      <li>  <div class="grid_3" id="buy">
                           <a href="placeorder.htm?customerName=<c:out value="${sessionScope.customerName}"/>&
                           orderTotal=<c:out value="${sessionScope.finalTotal}"/>" >
                           
                           Place Order</a>
                       </div></li>
                       </ul> 
                       </div> 
                    </div>
                           
                       
        
        </c:if>
        <c:if test="${taskValue == 'orderplacedSuccessfully' }">
        <div class="grid_16" id="productStrip">
                <div class="ProductHeading">
                    <div class="grid_16">
	<h2> Your Order has been placed Successfully</h2>
	</div>
	</div>
	</div>
        
        </c:if>
         <c:if test="${taskValue == 'orderFailed' }">
        <div class="grid_16" id="productStrip">
                <div class="ProductHeading">
                    <div class="grid_16">
	<h2> Order processing failed!!! Please try again!!</h2>
	</div>
	</div>
	</div>
        
        </c:if>
        
        
        <c:if test="${taskValue == 'error500' }">
        
        <div class="grid_14 push_1" id="whiteBox" style="padding: 5px;">
                <br/><h1 style="padding: 10px;">Error Status <span class="blue">500</span> - Internal Server Error!!
                </h1><hr/><br/>
                <p  class="grid_4" style="text-align: right; font-size: 72px;">500</p>
                <p class="grid_7">
                It seems that the page you've requested has been undergone through several errors 
                We request you to inform this to our administrator if you wish to like by contacting us
                please check the requested web page again or try again Later!! 
                <br/> We are extremely sorry about the inconvenience for not finding the requested webpage, Please Do co-operate!! We wish you best
                </p>
            </div>
        
        </c:if>
        <a></a>
        
        </div>
        
        </div>
        
        <jsp:include page="includesPages/footer.jsp"></jsp:include>
        </c:if>
        <c:if test="${sessionValid != null }">
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
    </body>
</html>
