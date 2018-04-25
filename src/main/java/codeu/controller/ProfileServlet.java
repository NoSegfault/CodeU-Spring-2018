package codeu.controller;

import codeu.model.data.User;
import codeu.model.data.Message;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.MessageStore;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;


/** Servlet class responsible for the profile page. */
public class ProfileServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;
  private MessageStore messageStore;

  /**
   * Set up state for handling login-related requests. This method is only called when running in a
   * server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setMessageStore(MessageStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

        String requestUrl = request.getRequestURI();
        String userProfileName = requestUrl.substring("/profile/".length());

        User userProfile = userStore.getUser(userProfileName);
        


        if(userProfile != null){

          request.setAttribute("userOwnedProfle",false);


          if(request.getSession().getAttribute("user") != null){
              
            String userLoggedInName = (String) request.getSession().getAttribute("user");
            User userLoggedIn = userStore.getUser(userLoggedInName);

            if(userLoggedIn != null){
              if(userLoggedInName.equals(userProfileName)){

                request.setAttribute("userOwnedProfle",true);

              }
            }
          }

          List<Message> userMessages = messageStore.getUserMessages(userProfile.getId());
          request.setAttribute("messages",userMessages);

          request.setAttribute("userProfileName",userProfileName);

          request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request,response);
          return;

        }


        response.sendRedirect("/conversations");
        return;

  }

  /**
   * This function fires when a user submits the about me form. 
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {


      String profileContent = request.getParameter("profileContent");  

      request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request,response);


  }

}
