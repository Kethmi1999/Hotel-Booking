<%@page import= "com.Room" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Room.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Room Management </h1>
<form id="formItem" name="formItem" method="post" action="Room.jsp">
 Room ID: 
<input id="itemCode" name="itemCode" type="text" 
 class="form-control form-control-sm">
<br>Room Name: 
<input id="itemName" name="itemName" type="text" 
 class="form-control form-control-sm">
<br> Room Type: 
<input id="itemPrice" name="itemPrice" type="text" 
 class="form-control form-control-sm">
<br> Room Date: 
<input id="itemDesc" name="itemDesc" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">

<%
 Room roomObj = new Room(); 
 out.print(roomObj.readRooms()); 
%>
</div>

</div></div></div>


</body>
</html>