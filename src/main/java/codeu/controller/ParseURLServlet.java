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


/** Servlet class responsible for getting user profile name by parsing URL. */
public class ParseURLServlet extends HttpServlet {
  
  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String requestUrl = request.getRequestURI();
    String userProfileName = requestUrl.substring("/user/".length());
    request.setAttribute("userProfileName", userProfileName);
    response.sendRedirect("/profile?uname="+userProfileName);

    return;
  }
}