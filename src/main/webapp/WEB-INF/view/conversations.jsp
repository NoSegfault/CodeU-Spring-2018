<%--
  Copyright 2017 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.User" %>
<%@ include file="/../../nav-bar.html" %>

<!DOCTYPE html>
<html>
<head>
  <title>Conversations</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<!-- 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Autocomplete - Multiple values</title>
 
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
       
  $( function() {
    var availableTags = [(List<String>) request.getAttribute("usernames")];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags" )
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
  } );
  </script> -->


<body>
  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>


      <h1>New Conversation</h1>
      <form action="/conversations" method="POST">
          <div class="form-group">
            <label class="form-control-label">Title:</label>
            <input type="text" name="conversationTitle" size="50">

      		<br>
      		<label for="tags">Invite: </label>
		    <input type="text" name="invitedUsers" id="tags" size="50">
		</div>
        <button type="submit">Create</button>

      </form>

      <hr/>


    <h1>Conversations</h1>
    <%
    List<Conversation> pubConversations =
      (List<Conversation>) request.getAttribute("publicConversations");
    List<Conversation> privConversations =
      (List<Conversation>) request.getAttribute("privateConversations");
    if(pubConversations.isEmpty() && privConversations.isEmpty()){
    %>
      <p>Create a conversation to get started.</p>
    <%
    }
    if (!pubConversations.isEmpty()){ %>
      <h2>Public Conversations</h2>
	<%}
	if (!privConversations.isEmpty()){ %>
      <h2>Private Conversations</h2>
	<%}
    else{
    %>
      <ul class="mdl-list">
    <%
      for(Conversation conversation : pubConversations){
    %>
      <li><a href="/chat/<%= conversation.getTitle() %>">
        <%= conversation.getTitle() %></a></li>
    <%
      }
    %>
     <%
      for(Conversation conversation : privConversations){
    %>
      <li><a href="/chat/<%= conversation.getTitle() %>">
        <%= conversation.getTitle() %></a></li>
    <%
      }
    %>
      </ul>
    <%
    }
    %>
    <hr/>
  </div>
</body>
</html>
