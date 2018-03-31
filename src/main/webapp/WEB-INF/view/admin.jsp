
<%@ page import="codeu.model.store.basic.AdminStore" %>
<%@ page import="codeu.model.data.User" %>
<%@ include file="/../../nav-bar.html" %>

<% AdminStore admin = (AdminStore) request.getAttribute("admin"); %>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Page</title>
	<link rel="stylesheet" href="/css/main.css" type="text/css">

</head>
<body>

	<div id="container">
		<h1>Admin Page</h1>

		<form method="POST" action="/admin">
			Search User: <input type="text" name="search">
			<input type="submit" name="Submit">
		</form>

	<% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getAttribute("userInfo") == null) { %>

		<ul>
			<li>
				<p>Total Users: <%= admin.getTotalUsers() %></p>
			</li>
			<li>
				<p>Total Conversations: <%= admin.getTotalConversations() %></p>
			</li>
			<li>
				<p>Total Messages: <%= admin.getTotalMessages() %></p>
			</li>
			<li>
				<p>Newest User: <%= admin.getNewestUser() %></p>
			</li>
			
		</ul>	

	<%	} else{  %>
		<% User user = (User) request.getAttribute("userInfo"); %> 

		<ul> 
			<li>Name: <%= user.getName()%></li> 
			<li>Id: <%= user.getId()%></li> 
			<li>Creation Date: <%= user.getCreationTime().toString()%></li> 
		</ul>

	<% } %>


	</div>




</body>
</html>