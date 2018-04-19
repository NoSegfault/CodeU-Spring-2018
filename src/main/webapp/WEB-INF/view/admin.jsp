
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.store.basic.AdminStore" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ include file="/../../nav-bar.html" %>

<% AdminStore admin = (AdminStore) request.getAttribute("adminInfo"); %>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Page</title>
	<link rel="stylesheet" href="/css/main.css" type="text/css">

</head>
<body>


	<div id="container">

		<% if(request.getSession().getAttribute("admin") == null){ %>
			<h2 style="color:red">You do not have access to this site </h2>

     	<% } else if((boolean) request.getSession().getAttribute("admin") == false) { %>

     		<h2 style="color:red">You do not have access to this site </h2>

     	<% } else { %>

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
			<li>
				<p>Most Active User: <%= admin.getMostActiveUser() %></p>
			</li>
			
		</ul>	


	<%	} else{  %>

		<% User user = (User) request.getAttribute("userInfo"); %> 
		<% Map<Conversation,List<Message>> userConversationsMap = (Map<Conversation,List<Message>>) request.getAttribute("userConversationsMap"); %>
			<% Set<Conversation> userConversations = userConversationsMap.keySet(); %>


		<ul> 
			<li>Name: <%= user.getName()%></li> 
			<li>Id: <%= user.getId()%></li> 
			<li>Admin: <%= user.isAdmin() %></li>
			<li>Creation Date: <%= user.getCreationTime().toString()%></li> 
			<li>Total Messages: <%= admin.getUserTotalMessages(user.getName()) %></li>
		</ul>

		<button style="display: block;" onclick="makeAdmin('<%= user.getName()%>')">Make Admin</button>


		<div style="width: 40%; float: left; border: 1px solid; padding: 10px;">
			<h4>Conversations</h4>
			<ul>
				
				<% int i = 0; %>
				<% for(Conversation conversation : userConversations){ %>


					<div class="click_box" onclick="dropdown(<%= i %>)" >
						<h5><%= conversation.getTitle() %></a></h5>
						<a class="convo_link" href="/chat/<%= conversation.getTitle()%>">Go to Convo</a>
	          			<% List<Message> userMessages = (List<Message>) userConversationsMap.get(conversation); %>
          			</div>


          			<div class="dropdown_box">
          			<% for(Message message : userMessages){ %>

						<li >
		         			 <%= message.getContent() %>
		        		</li>

		        	<% } %>

		        	</div>

		        	<% ++i; %>
        		<% } %>

			</ul>
		</div>

		<form style="display:none;" action="/admin" method="post" id="makeAdmin_form">		
			<input type="text" name="makeAdmin" id="makeAdmin">
			<input type="submit" name="Make Admin">
		</form>

	<% } %>
	<% } %>
	


	</div>



	<script type="text/javascript">
		
		function dropdown(i) {


			document.getElementsByClassName("dropdown_box")[i].classList.toggle("expanded_box");


		}


		function makeAdmin(user){

			document.getElementById("makeAdmin").value = user;
			document.getElementById("makeAdmin_form").submit();
		}


	</script>
</body>
</html>