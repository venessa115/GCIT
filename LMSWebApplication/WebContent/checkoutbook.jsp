<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.gcit.lms.domain.BookCopies"%>
 <%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.database.LibrarianJDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
String branchId=(String)request.getAttribute("branchid");
LibrarianJDBC jdbc = new LibrarianJDBC();
	List<Book> listbooks=new ArrayList<Book>();
	
	listbooks=jdbc.getBooks(Integer.parseInt(branchId));
	System.out.println("branchid"+branchId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
<p>${param.message}</p>


Select the book you want to add copies to:
<form action="checkBook" method="post">
<select name="book">
<option value="select a book">select a book</option> 
<%for(Book a: listbooks){ %>
	<option  value="<%=a.getBookId() %>"><%=a.getTitle() %></option>	
<% } %>
</select>
 <input type="submit" value="Submit">
</form><br />

 <a href="checkoutbranch.jsp">Quite to previous </a><br/>
 

</body>
</html>