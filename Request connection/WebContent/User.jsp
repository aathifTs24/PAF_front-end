<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="RequestConnection.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


  <link rel="stylesheet" href="Views/bootstrap.min.css">


<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/user.js"></script>




<title >Request connection</title>
</head>
<body>
<h2>Request Connection/Disconnection of Electricity Connection</h2>


<form  id="formItem" name="formItem" method="post" action="items.jsp">
 Consumer Name:
<input id="name" name="name" type="text"
 >
 <br>
<br> Address:
<input id="address" name="address" type="text"
 >
 <br>
<br> NIC:
<input id="NIC" name="NIC" type="text"
 >
 <br>

<br>Phone:
<input id="phone" name="phone" type="text"
 >
 <br><br>


 <label for="category" class="form-label">Consumer Category:</label>
<select class="form-select"  name="category" id="category">
  <option value="Domestic">Domestic</option>
  <option value="Garden">Garden</option>
  <option value="Hotel">Hotel</option>
  <option value="Industrial">Industrial</option>
</select>

<br><br>


 <label for="connection" class="form-label" >Connection/Disconnection:</label>
<select class="form-select" name="connection" id="connection" >
  <option value="connect">connection</option>
  <option value="disconnect">disconnection</option>

</select>


<br><br>

<br><br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">

<%

User Obj = new User();
 out.print(Obj.readUser());
%>
</div>
</body>
</html>







<!-- class="form-control form-control-sm" -->