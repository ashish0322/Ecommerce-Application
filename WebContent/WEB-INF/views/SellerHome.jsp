
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

<script type="text/javascript">
function madeAjaxCallCategory(){
 $.ajax({
  type: "post",
  url: "checkCategory.htm",
  cache: false,    
  data:'categoryId=' + $("#categoryId").val(),
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
                        <div id="logo" class="grid_6"><img src="<c:url value="/resources/images/icons/administrator-icon.png"/>" />
                        </div>
                        <div class="grid_9" id="top">
                            <ul>
                                <a href="siteAdminlogout.htm"><li id="greenBtn" class ="Btn showForm">Logout</li></a>
                                <c:set var="session" value="${sessionScope.validlogin}"/>
                                	<c:if test="${session!=null}">
                              <li>  <h1 align="right" style="color:white;">Welcome <c:out value="${sessionScope.validlogin}"></c:out> </h1></li>                                                        
                           		 	</c:if>
                            </ul>
                        </div>
                </div>
            </div>
    </div>
     
        <%@include file="includesPages/SellerSearch.jsp" %>

       	       	
       	<div class="container_16">
       	<div id = "contents">
            <!-- LeftSide -->



            <div id="leftside" class="grid_3">
                <div>
                    <ul id="leftsideNav">
                        <li><strong>Categories</strong></a></li>
                        
                        
                            <li><a href="addCategory.htm"><strong>Add Category</strong></a></li>
                        	<li><a href="addProduct.htm"><strong>Add Product </strong></a></li>
                        	<li><a href="manageProduct.htm?sellerName=<c:out value="${sessionScope.validlogin}"/>"><strong>Manage Product </strong></a></li>
                        	<li><a href="sellerViewOrders.htm?sellerName=<c:out value="${sessionScope.validlogin}"/>&status=pending"><strong>Pending Orders </strong></a></li>
                        	<li><a href="sellerViewOrders.htm?sellerName=<c:out value="${sessionScope.validlogin}"/>&status=Approved"><strong>Approved Orders </strong></a></li>
                        	<li><a href="viewSales.htm"><strong>Sales Records </strong></a></li>
                      
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
        
        <c:set var="taskValue" value="${requestScope.task}"></c:set>
         <c:if test="${taskValue==null}">
        <h1> Seller Home</h1>
        </c:if>
        
        <c:if test="${taskValue!= null }">
        <c:choose>
        
         <c:when test="${taskValue == 'manageProductsList' }">
         <c:out  value="${requestScope.noProductsFound}"/>
         <c:out  value="${requestScope.updateSuccessbySeller}"/>
        <div class="grid_5 push_1" id = "RegisterForm">
        <b> Products added by you</b>
        
         <form action="manageProduct.htm?sellerName=<c:out value="${sessionScope.validlogin}"/>" method="post" >
         <span>
         
         <label>Category</label>
         <select name="category_name" >
			<c:forEach var="categ" items="${requestScope.catList}">
                       <option value="<c:out value="${categ.title }"/>"/>
                       <c:out value="${categ.title }"/>
            </c:forEach>
		</select>
		
		
		<input type="submit" value="Submit" class="grid_3" id="buy"/>
		</span>
		</form>
        <%@include file="includesPages/manageProducts.jsp" %> 
        
        </div>
        </c:when>
        
        
        
        <%-- Content for Add Category   --%> 
        <c:when test="${taskValue == 'addCategory' }">
                 <div class="grid_6 push_2" id = "RegisterForm">
                 <form:form action="addCategory.htm" commandName="category" method="post">
                 <table>
                      <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:right; padding:20px 0px; color:#FFF;"> Add Category Form</h1></strong>
                            </td>
                            <td>

                            </td>
                        </tr>
                      <tr>
                            <td>
                                <label>Category Name</label>
                            </td>
                            <td>
                            <form:input path="title" size="30" id ="categoryId"  onblur="madeAjaxCallCategory();" placeholder="Category"/> 
                            <font color="red"><form:errors path="title"/></font>
                            </td>
                            <td>
                           <div id="result1" style="color: red;"></div>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                            <b style="color: red;"><c:out value="${requestScope.categoryExists}"></c:out></b>
                            </td>
                            </tr>
                         <tr>
                            <td>
                            </td>
                            <td>
                            	<input type="hidden" value="newCategory" name="task"/>
                                <input type="submit" value="Add Category" id="greenBtn" /><br/>
                            </td>
                        </tr>
                        </table>
                        </form:form>
                 </div>
        </c:when>
        
        
        <%-- Content for adding products  --%> 
        <c:when test="${taskValue == 'addProduct' }">
        <div class="grid_6 push_2" id = "RegisterForm">
        <form:form  action="addProduct.htm" commandName="product1"  method="post" enctype="multipart/form-data">
        <table>
                      <tr>
                            <td colspan="2">
                                <strong><h1 style="font-wieght:bold; text-align:right; padding:20px 0px; color:#FFF;"> Add Product</h1></strong>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                               <td>
                                <label>Select Category</label>
                            </td>
                            <td>
	                                <form:select path="category_name" >
										<c:forEach var="categ" items="${requestScope.categories}">
                                			<form:option value="${categ.title}"/>
                            			</c:forEach>
									</form:select><br/>
                            </td>
                        </tr>
                      <tr>
                            <td>
                                <label>Product Name</label>
                            </td>
                            <td>
                            <form:input path="productName" size="30" placeholder="Xps"/> <font color="red"><form:errors path="productName"/></font>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                                <label>Company</label>
                            </td>
                            <td>
                            <form:input path="company" size="30" placeholder="Dell"/> <font color="red"><form:errors path="company"/></font>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                                <label>Price</label>
                            </td>
                            <td>
                            <form:input path="price" size="30" placeholder="$900"/> <font color="red"><form:errors path="price"/></font>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                                <label>Quantity</label>
                            </td>
                            <td>
                            <form:input path="quantity" size="30" placeholder="10"/> <font color="red"><form:errors path="quantity"/></font>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                                <label>Description</label>
                            </td>
                            <td>
                            <form:textarea path="description"  placeholder="Awesome Product with High end config"/> <font color="red"><form:errors path="description"/></font>
                            </td>
                        </tr> 
                        
                        <tr>
                            <td>
                                <label>Choose Product Image</label>
                            </td>
                            <td>
                            <form:input path="photo" type="file"/> <font color="red"><form:errors path="photo"/></font>
                            </td>
                        </tr>  
                        <tr>
                            <td>
                            </td>
                            <td>
                           
                             	<input type="hidden" value="newProduct" name="task"/>
                                <input type="submit" value="Add Product" id="greenBtn" /><br/>
                            </td>
                        </tr>
        </table>
        </form:form>
        
        
        </div>        
        </c:when>
        <c:when test="${taskValue=='productSuccess' }">
        
        <b> Product <c:out value="${requestScope.pname}"></c:out> added successfully</b>
        </c:when>
        <c:when test="${taskValue=='productFail' }">
        
        <b> Product add Failed!!</b>
        </c:when>
        <c:when test="${taskValue=='500Error' }">
        
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
        
        
        </c:when>
        <c:when test="${taskValue=='pendingOrders'}">
         <div class="grid_5 push_1" id = "RegisterForm">
        <h1><c:out value="${approveFailed}"/></h1>
        <h1><c:out value="${approveSuccess}"/></h1>
        <%@include file="includesPages/pendingOrders.jsp" %> 
        </div>
        </c:when>
         <c:when test="${taskValue=='approvedOrders'}">
         <div class="grid_5 push_1" id = "RegisterForm">
        <h1>Approved Orders</h1>
        <%@include file="includesPages/approvedOrders.jsp" %> 
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
