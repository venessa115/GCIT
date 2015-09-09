<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Author> authors = new ArrayList<Author>();
	authors = jdbc.getAuthors();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>

<body>
<h2> View Existing Authors</h2>
<table border="3">
	<tr class="border_bottom">
	<th>Author ID</th>
	<th>Author Name</th>
	<th>Edit Author</th>
	<th>Delete Author</th>
	</tr>
	<%for(Author a: authors){ %>
		<tr class="border_bottom">
			<td align="center"><%=a.getAuthorId() %></td>
			<td align="center"><%=a.getAuthorName() %></td>
			<td align="center"><button type="button" onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td>
			</tr>
	<%} %>
	
</table>

</body>
</html>