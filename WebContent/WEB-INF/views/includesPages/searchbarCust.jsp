<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ProKart</title>
    </head>
<body>
<div id = "topSearch">
    <div class="container_16">
        <div class="grid_16">
            <div class="grid_9">


                <input type="text" id="search" class="searchBox" placeholder="Quick search an item ..." autofocus="true"/>

            </div>
            <div class="grid_6" id="topNav">
                <ul>
                    <li><a href="custHome.htm">HOME</a></li>
                    <li><a href="viewOrders.htm?name=<c:out value="${sessionScope.customerName}"/>">VIEW ORDERS</a></li>
                    <li><a href="#">PROFILE</a></li>
                    <li><a href="custFaq.htm">FAQ</a></li>
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

<%
    if (session.getAttribute("admin") != null){
        %>
        <script type="text/javascript">
    $(document).ready(function (){
        //alert ("fdvgf");
                
        $('#search').keyup (function (){
            searchbar = $('#search').attr('value');
            if (searchbar.length >= 3){
                //$('#loadSearches').load('findProductLoad.jsp', {searchbar:searchbar});
                            
                $('#loadSearches').css('display');
                $.get('findProductLoad.jsp', {searchbar:searchbar}, function (data){
                    //alert (data);
                    if (data.length > 760){
                        $('#loadSearches').html(data).slideDown(500);
                    }
                    else {
                        $('#loadSearches').slideUp(500);
                        $('#loadSearches').html("");
                    }
                });
                       
            }else {
                 $('#loadSearches').slideUp(500);
                $('#loadSearches').html("");
            }
        });
    });
            
</script>
<%
    } else {
        %>
<script type="text/javascript">
    $(document).ready(function (){
        //alert ("fdvgf");
                
        $('#search').keyup (function (){
            searchbar = $('#search').attr('value');
            if (searchbar.length >= 3){
                //$('#loadSearches').load('findProductLoad.jsp', {searchbar:searchbar});
                            
                $('#loadSearches').css('display');
                $.get('findProductLoad_1.jsp', {searchbar:searchbar}, function (data){
                    //alert (data);
                    if (data.length > 760){
                        $('#loadSearches').html(data).slideDown(500);
                    }
                    else {
                        $('#loadSearches').slideUp(500);
                        $('#loadSearches').html("");
                    }
                });
                       
            }else {
                 $('#loadSearches').slideUp(500);
                $('#loadSearches').html("");
            }
        });
    });
            
</script>
<%
    }
%>

<div class="container_16" id="loadSearches" ></div>

</body>
</html>
