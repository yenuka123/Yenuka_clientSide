<%@page import="com.Fin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/Fin.js"></script>

<meta charset="ISO-8859-1">
<title>Finance</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Finance Management</h1>        
				
				<form id="formCustomer" name="formCustomer" method="post" action="FinAPI">  
					Product Category<br>  
					<input id="Product_category" name="Product_category" type="text" class="form-control form-control-sm">  
					
					<br> 
					Category Price<br>  
					<input id="Category_price" name="Category_price" type="text" class="form-control form-control-sm">  
					
					<br>
					 Tax Rate<br>  
					 <input id="Tax_rate" name="Tax_rate" type="text" class="form-control form-control-sm">  
					 
		  
					 <br><br>  
					 <input id="btnSave" name="btnSave" type="submit" value="SUBMIT" class="btn btn-primary">  
					 <input type="hidden" id="hidFinIDSave" name="hidFinIDSave" value="Fin.jsp"> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divItemsGrid">   
					<%
   						Fin finObj = new Fin();
   									out.print(finObj.readFin());
   					%>  
					
					<br>
					<br>
					 <a href="Login.jsp" class="btn btn-success">Logout</a>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>