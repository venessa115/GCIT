<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<body>

<p>${param.message}</p>
<a href="editbranches.jsp">Update the details of the Library  </a><br/>
<form action="forwardBranch" method="post">
<a href="forwardBranch">Add copies of Book to the Branch </a><br/>
</form>
<a href="branch.jsp">Quite to previous </a><br/>
</body>
</html>