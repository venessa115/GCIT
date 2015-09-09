<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.database.JDBC"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%JDBC jdbc = new JDBC();
	List<Publisher> publishers = new ArrayList<Publisher>();
	publishers = jdbc.getPublishers();
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
	<th>Publisher ID</th>
	<th>Publisher Name</th>
	<th>Publisher Address</th>
	<th>Edit Publisher</th>
	<th>Delete Publisher</th>
	</tr>
	<%for(Publisher a: publishers){ %>
		<tr>
			<td align="center"><%=a.getPublisherId() %></td>
			<td align="center"><%=a.getPublisherName() %></td>
			<td align="center"><%=a.getPublisherAddress() %></td>
		
			<td align="center"><button type="button" onclick="javascript:location.href='editPublisher?publisherId=<%=a.getPublisherId()%>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deletePublisher?publisherId=<%=a.getPublisherId()%>'">DELETE</button></td>
			</tr>
	<%} %>
	
</table>

</body>
</html>