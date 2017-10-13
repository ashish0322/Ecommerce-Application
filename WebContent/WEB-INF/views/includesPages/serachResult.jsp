<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

 <!-- Middle -->
            <div class="container_16">
            <div class="grid_15 push_1">
                <div class="grid_14" id="whiteBox">
                
                <div class="clear"></div>
                        <div class="grid_2">
                            
                        </div>
                        <c:forEach var="prod" items="${sessionScope.searchResult}"> 
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
                        <div class="clear"></div>
                        
                        </div>
                        </div>
                        </div>
                        