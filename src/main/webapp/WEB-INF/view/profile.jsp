
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
<%@ include file="/../../nav-bar.html" %>
<%@ page import="codeu.model.data.Conversation" %>

<!DOCTYPE html>
<html>
<head>
  <title><%= request.getSession().getAttribute("user") %>'s Profile Page</title>
  <link rel="stylesheet" href="/css/main.css">
      <style>
      label {
        display: inline-block;
        width: 100px;
      }
    </style>
</head>
<body>
    <div id="container">
      <h1>'s Profile Page</h1>
      <a><strong> About </strong></a>
      <p><span id='profileInfo'></span></p>

      <br/>
      <br/>

            <p><strong>Edit your profile here! (only you can see this) </strong></p>
            <textarea rows="4" cols="100" id ="aboutMeText" name = "profileContent">
          </textarea>
          <br/>
          <button type="submit" onclick= "newProfile()">Update</button>
          <p id = "test"> </p>
       </form>

       <script>
        function newProfile(){
         document.getElementById("profileInfo").innerHTML = document.getElementById("aboutMeText").value;
       }
       </script>

      <br/>
      <br/>
      <a><strong>'s Sent Messages </strong></a>
      <%
      List<Conversation> conversationss =
        (List<Conversation>) request.getAttribute("conversations");
      if(conversationss != null && !conversationss.isEmpty()){
      %>
        <ul class="mdl-list">
       <%
        for(Conversation conversation : conversationss){
       %>
          <%if (conversation.getOwnerId() != null){ 
          %>
             <li><a href="/chat/<%= conversation.getTitle() %>">
               <%= conversation.getTitle() %></a></li>
        <% 
         }
        %>
      <%
        }
      %>
      	</ul>
      <%
      }
      %>

  </div>
</body>
</html>