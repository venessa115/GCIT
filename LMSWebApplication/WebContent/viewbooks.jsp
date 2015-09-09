<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>
<h2> View Existing Books</h2>
<table border="3">
	<tr>
	<th>Book ID</th>
	<th>Title of Book</th>
	<th>Edit Book</th>
	<th>Delete Book</th>
	</tr>
	<%for(Book a: books){ %>
		<tr>
			<td align="center"><%=a.getBookId() %></td>
			<td align="center"><%=a.getTitle() %></td>
			<td align="center"><button type="button" onclick="javascript:location.href='editBook?bookId=<%=a.getBookId()%>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deleteBook?bookId=<%=a.getBookId()%>'">DELETE</button></td>
			</tr>
	<%} %>
	
</table>

</body>
</html>