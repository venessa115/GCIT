<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Branch> branches = new ArrayList<Branch>();
	branches = jdbc.getBranches();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
<p>${param.message}</p>

Select your branch:
<form action="returnBranch" method="post">
<select name="branch">
<option value="select a branch">select a branch</option>

<%for(Branch a: branches){ %>
	<option value="<%=a.getBranchId() %>" ><%=a.getBranchName() %></option>	
<% } %>
</select>
 <input type="submit" value="Submit">
</form><br />

 
<a href="index.html">Quite to previous </a><br/>

</body>
</html>