<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ProKart</title>
        
<script type="text/javascript">
$(document).ready(function (){
    //alert ("fdvgf");
            
    $('#search').keyup (function (){
        searchbar = $('#search').attr('value');
        if (searchbar.length >= 3){
            //$('#loadSearches').load('findProductLoad.jsp', {searchbar:searchbar});
                        
            $('#loadSearches').css('display');
            $.get('findProductLoad.htm', {searchbar:searchbar}, function (data){
                //alert (data);
                if (data.length > 760){
                    $('#loadSearches').html(data).slideUp(500);
                }
                else {
                    $('#loadSearches').slideDown(500);
                    $('#loadSearches').html("");
                }
            });
                   
        }else {
             $('#loadSearches').slideDown(500);
            $('#loadSearches').html("");
        }
    });
});
</script>
    </head>
<body>
<div id = "topSearch">
    <div class="container_16">
        <div class="grid_16">
            <div class="grid_9">


                <input type="text" id="search" class="searchBox" placeholder="Quick search an item ..."
                 autofocus="autofocus"/>

            </div>
            <div class="grid_6" id="topNav">
                
                   
                    <ul>
                    <li><a href="index.jsp">HOME</a></li>
                    <li><a href="#">PRODUCTS</a></li>
                    <li><a href="#">CONTACT</a></li>
                    <li><a href="#">FAQ</a></li>
                
                </ul>
            </div>
        </div>
    </div>
</div>

<div id = "topThird">
    <div class="container_16">
        <div class="grid_16">
            <div class="grid_9">
               <p><span>ProKart</span> is one of the best places to <span>shop</span> for any product we are the only one to have your <span>thoughts</span> at the right place... For More Information Call Us : +16176020501</p>
           
            <!-- <p><span>Mahavir Paints</span> is the one of the most best places in Mira Bhyyandar to find <span>First Class Paints</span></p>
            -->
            </div>
            <div class="grid_6" id="top">
                <a href="http://www.facebook.com"> <div id="greenBtn" align="center" class ="Btn">Join the Community on Facebook!!</div></a>
            </div>
        </div>
    </div>
</div>

<div class="container_16" id="loadSearches" >
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
            
            </div>

</body>
</html>
