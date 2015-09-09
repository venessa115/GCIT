<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCIT LMS</title>
</head>

<body>
<p>${param.message}</p>

	<h1>Add Publisher</h1>
	<h2>Enter Publisher Details</h2>
	<form action="addPublisher" method="post">
	
		Enter Publisher Name: <input type="text" name="publisherName"><br /><br />
		Enter Publisher Address: <input type="text" name="publisherAddress"><br /><br />
		
		 
		<input type="submit" value="Submit">
	
	</form>
	

	
</body>
</html>