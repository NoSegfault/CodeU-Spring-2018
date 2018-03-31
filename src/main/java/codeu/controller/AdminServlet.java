// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.AdminStore;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Servlet class responsible for the chat page. */
public class AdminServlet extends HttpServlet {

  private AdminStore adminStore;

  private UserStore userStore;

  /** Set up state for handling chat requests. */
  @Override
  public void init() throws ServletException {
    super.init();
    setAdminStore(AdminStore.getInstance());
    setUserStore(UserStore.getInstance());
  }

  void setAdminStore(AdminStore adminStore){
    this.adminStore = adminStore;
  }

  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }



  /**
   * This function fires when a user navigates to the chat page. It gets the conversation title from
   * the URL, finds the corresponding Conversation, and fetches the messages in that Conversation.
   * It then forwards to chat.jsp for rendering.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

        // String username = request.getSession().getAttribute("user");



    request.setAttribute("admin",adminStore);
    request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);


  }

  /**
   * This function fires when a user submits the form on the chat page. It gets the logged-in
   * username from the session, the conversation title from the URL, and the chat message from the
   * submitted form data. It creates a new Message from that data, adds it to the model, and then
   * redirects back to the chat page.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

          String username = request.getParameter("search");
          User user = userStore.getUser(username);

          if(user == null){
            request.setAttribute("error","User could not be found");
            request.setAttribute("admin",adminStore);
          }
          else {
            List<Conversation> userConversations = adminStore.getUserConversations(username);

            request.setAttribute("userConversations", userConversations);
            request.setAttribute("userInfo",user);
          }

          request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
          return;

  }
}