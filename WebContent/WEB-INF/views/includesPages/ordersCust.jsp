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

</head>
<body>

 <div class="wrapper">

  <div class="table">

    <div class="row header">
      <div class="cell">
        Order Id
      </div>
      <div class="cell">
        Product Name
      </div>
      <div class="cell">
        Unit Price
      </div>
      <div class="cell">
        Order Total
      </div>
      <div class="cell">
        Ordered Date
      </div>
      <div class="cell">
        Shipper Name
      </div>
	  <div class="cell">
        Status
      </div>
       <div class="cell">
        Invoice
      </div>
    </div>
<c:forEach var= "orders" items="${requestScope.ordersList}">
    <div class="row">
      <div class="cell">
        <c:out value="${orders.orderId}"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.productName }"/> 
      </div>
      <div class="cell">
        $<c:out value="${orders.unitPrice}"/> 
      </div>
      <div class="cell">
        $<c:out value="${orders.orderTotal}"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.orderDate }"/> 
      </div>
      <div class="cell">
        <c:out value="${orders.shipperName }"/> 
      </div>
	  <div class="cell">
        <c:out value="${orders.status}"/> 
      </div>
       <div class="cell">
        <a href="viewBill.htm?orderId=<c:out value="${orders.orderId}"/>">Download</a> 
      </div>
    </div>
    
</c:forEach>
 
</div>    
</div>
    

  </body>
</html>