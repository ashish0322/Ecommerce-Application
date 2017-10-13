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
<style type="text/css">
            .adminMenu {
                margin-top: 10px;
                margin-bottom: 10px;
                margin-right: 0px;
                background: #FFF;
                box-shadow: 0px 0px 10px #333;
            }
            .adminMain {
                margin-top: 10px;
                margin-bottom: 10px;
                background: #FFF;
                box-shadow: 0px -1px 10px #333;
            }
            #leftside {
                cursor: pointer;
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
                        
                        
                            <li><a href="addSiteAdmin.htm"><strong>Add Site Admin</strong></a></li>
                        	<li><a href="addHelpDesk.htm"><strong>Add Helpdesk </strong></a></li>
                        	<li><a href="performanceStats.htm"><strong>Performance Stats </strong></a></li>
                      
                    </ul>
                </div>
                <div class="adv">
                    <h2><br/>This is The Header of an Advertisement</h2>
                    <p>We offer Advertisement display here </p>
                </div>
            </div>


        </div>
       </div>
       
       <div class="grid_16" style="padding: 10px;" id="whiteBox">
                    <br/>
                      <h1 class="grid_15">Dashboard Home</h1><hr/>
                <a href="admin_manageProduct.jsp" id="buy"  class="grid_3"> Items below Quantity</a>     
                <a href="admin_approvedOrders.jsp" id="buy"  class="grid_3">Items to be Delivered</a>    
                <a href="admin_pendingOrders.jsp" id="buy" class="grid_3">Items to be Approved</a>    
           </div>
                <div class="clear"></div>
            <!--Loading the AJAX API-->
                <script type="text/javascript" src="js/gclibrary/jsapi.js"></script>
                <script type="text/javascript" src="js/gclibrary/core.js"></script>
                <script type="text/javascript" src="js/gclibrary/core1.js"></script>
                
              
                
                <jsp:include page="includesPages/headers_sidebars/admin_menuSideBar.jsp"></jsp:include>
                <div class="grid_13">
                <jsp:include page="includesPages/charts/pieChartItemsSoldByCategory.jsp"></jsp:include>
                
                </div>
                <jsp:include page="includesPages/charts/top10ProductsSold.jsp"></jsp:include>
                <jsp:include page="includesPages/charts/top10Products_viewed.jsp"></jsp:include>
                
        
        
        
 <jsp:include page="includesPages/footer.jsp"></jsp:include>
    </body>
</html>