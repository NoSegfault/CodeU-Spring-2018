package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class RegisterServletTest {

 private RegisterServlet registerServlet;
 private HttpServletRequest mockRequest;
 private PrintWriter mockPrintWriter;
 private HttpServletResponse mockResponse;
 private RequestDispatcher mockRequestDispatcher;

 @Before
 public void setup() throws IOException {
   registerServlet = new RegisterServlet();
   mockRequest = Mockito.mock(HttpServletRequest.class);
   mockPrintWriter = Mockito.mock(PrintWriter.class);
   mockResponse = Mockito.mock(HttpServletResponse.class);
   mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
   Mockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
 }



}