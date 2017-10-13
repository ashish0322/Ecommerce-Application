<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.round-button {
	width:25%;
	display:inline;
	float:left;
}
.round-button-circle {
	width: 100%;
	height:0;
	padding-bottom: 100%;
    border-radius: 50%;
	border:10px solid #cfdcec;
    overflow:hidden;
    display:inline;
	float:left;
    background: #4679BD; 
    box-shadow: 0 0 3px gray;
}
.round-button-circle:hover {
	background:#30588e;
}
.round-button a {
    display:inline;
	float:left;
	width:100%;
	padding-top:50%;
    padding-bottom:50%;
	line-height:1em;
	margin-top:-0.5em;
    
	text-align:center;
	color:#e2eaf3;
    font-family:Verdana;
    font-size:1.2em;
    font-weight:bold;
    text-decoration:none;
    
  
}
li {
    display: inline;
}

</style>
</head>
<body>


   	<ul>
           <li>  
           
   		   <div class="round-button"><div class="round-button-circle">
   		   <a href="prokarthome.htm" class="round-button">ProKart CustomerHome</a></div></div>
   	</li>
   	<li>
   		  
   		   <div class="round-button"><div class="round-button-circle">
   		   <a href="adminlogin.htm" class="round-button">Admin Login</a></div></div>
   		</li>   
   		 <li>
   		   <div class="round-button"><div class="round-button-circle">
   		   <a href="empLoginHome.htm" class="round-button">Employee Login</a></div></div>
   		    </li>
   		 
   	</ul>		
   			
</body>
</html>