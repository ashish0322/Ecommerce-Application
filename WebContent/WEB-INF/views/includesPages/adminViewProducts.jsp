<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <html>
<body>
<head>
<style type="text/css">

body {
  font-family: "Helvetica Neue", Helvetica, Arial;
  font-size: 14px;
  line-height: 20px;
  font-weight: 400;
  color: #3b3b3b;
  -webkit-font-smoothing: antialiased;
  font-smoothing: antialiased;
  background: 
}

.wrapper {
  margin: 0 auto;
  padding: 40px;
  max-width: 800px;
}

.table {
  margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .table {
    display: block;
  }
}

.row {
  display: table-row;
  background: #f6f6f6;
}
.row:nth-of-type(odd) {
  background: #e9e9e9;
}
.row.header {
  font-weight: 900;
  color: #ffffff;
  background: #ea6153;
}
.row.green {
  background: #27ae60;
}
.row.blue {
  background: #2980b9;
}
@media screen and (max-width: 580px) {
  .row {
    padding: 8px 0;
    display: block;
  }
}

.cell {
  padding: 6px 12px;
  display: table-cell;
}
@media screen and (max-width: 580px) {
  .cell {
    padding: 2px 12px;
    display: block;
  }
}



</style>

<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>

 <div class="wrapper">

  <div class="table">

    <div class="row header">
      <div class="cell">
        Product Id
      </div>
      <div class="cell">
        Product Name
      </div>
      <div class="cell">
        Price
      </div>
      <div class="cell">
        Quantity
      </div>
      <div class="cell">
        Company
      </div>
      <div class="cell">
        Category
      </div>
	  <div class="cell">
        Seller
      </div>
    </div>
<c:forEach var= "orders" items="${requestScope.prodList}">
    <div class="row">
      <div class="cell">
        <c:out value="${orders.productId}"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.productName }"/> 
      </div>
      <div class="cell">
        $<c:out value="${orders.price }"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.quantity }"/> 
      </div>
	  <div class="cell">
        <c:out value="${orders.company }"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.category_name }"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.sellerName}"/> 
      </div>
    </div>
    
</c:forEach>
 
</div>    

<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="adminViewProducts.htm?page=${currentPage - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="adminViewProducts.htm?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="adminViewProducts.htm?page=${currentPage + 1}">Next</a></td>
    </c:if>
</div>
    

  </body>
</html>