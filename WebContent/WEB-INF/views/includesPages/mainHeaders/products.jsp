<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <style type="text/css">
            .prodGrid {
                margin: 10px;
                margin-right: -12px;
                margin-left: 36px;
            }
        </style>
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
                       
                    <a href="productDetails.htm?id=<c:out value="${prod.productId}"/>">
                    <img src="<c:out value="${prod.photoName}"/>"/></a>
                    
                    
                    <p id="info">
                        <a href="productDetails.htm?id=<c:out value="${prod.productId}"/>"><span class="blue"><c:out value="${prod.productName}"/></span></a><br/>
                        By <c:out value="${prod.company}"/> <c:out value="${prod.category_name}"/><br/>
                        <span class="red">$<c:out value="${prod.price}"></c:out></span><br/>
                        <span class="blue">Seller Name :aka<c:out value="${prod.sellerName}"></c:out></span>
                       
                    </p>   
                          
                </div>
                </c:forEach>   
              </div>
              