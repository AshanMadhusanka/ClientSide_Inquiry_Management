<%@ page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Inquiry.js"></script>


<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Inquiry Management</h1>

	<form id="formItem" name="formItem">
		
		 Full Name:
		<input id="full_name" name="full_name" type="text" class="form-control form-control-sm"><br> 
		Phone Number:
		<input id="phone_no" name="phone_no" type="text" class="form-control form-control-sm"><br>
		Email:
		<input id="email" name="email" type="text" class="form-control form-control-sm"><br>
		Address:
		<input id="address" name="address" type="text" class="form-control form-control-sm"><br>
		 Description:
		<input id="description" name="description" type="text" class="form-control form-control-sm"><br>

		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	Inquiry InqObj = new Inquiry(); 
	 out.print(InqObj.readInquiry()); 
	%>
	</div>
	<br>
	<br>
</div> </div> </div> 
	
</body>
</html>