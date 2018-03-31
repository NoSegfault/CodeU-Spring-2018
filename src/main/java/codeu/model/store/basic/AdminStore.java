package codeu.model.store.basic;

import java.time.Instant;
import java.util.UUID;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
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

/**
 * Class representing a conversation, which can be thought of as a chat room. Conversations are
 * created by a User and contain Messages.
 */
public class AdminStore {

    private int totalUsers;
    private int totalMessages;
    private int totalConversations;
    private User newestUser;
    //private User mostActiveUser;



    private MessageStore messageStore;
    private ConversationStore conversationStore;
    private UserStore userStore;

    


    void setMessageStore(MessageStore messageStore) {
      this.messageStore = messageStore;
    }

    void setConversationStore(ConversationStore conversationStore) {
      this.conversationStore = conversationStore;
    }

    void setUserStore(UserStore userStore) {
      this.userStore = userStore;
    }


    private static AdminStore instance;


    public static AdminStore getInstance(){
      if (instance == null) {
        instance = new AdminStore();
      }
      return instance;
    }


    public AdminStore(){

      setMessageStore(MessageStore.getInstance());
      setConversationStore(ConversationStore.getInstance());
      setUserStore(UserStore.getInstance());

      this.totalUsers = userStore.getTotal();
      this.totalMessages = messageStore.getTotal();
      this.totalConversations = conversationStore.getTotal();
      this.newestUser = userStore.getNewest();

    }

    //this method will update the adimin info
    public void update(){
      this.totalUsers = userStore.getTotal();
      this.totalMessages = messageStore.getTotal();
      this.totalConversations = conversationStore.getTotal();
      this.newestUser = userStore.getNewest();
    }


    //These are the getter methods to get all of the admin info
    public int getTotalUsers(){
      return totalUsers;
    }

    public int getTotalMessages(){
      return totalMessages;
    }

    public int getTotalConversations(){
      return totalConversations;
    }

    public String getNewestUser(){
      return newestUser.getName();
    }

    public List<Conversation> getUserConversations(String username){

      UUID userId = userStore.getUser(username).getId();
      return conversationStore.getUserConversations(userId);
    }

    //These methods constantly keep track of the admin info
    public void addMessage(Message message){
      totalMessages += 1;
    }

    public void addUser(User user){
      newestUser = user;
      totalUsers += 1;
    }
  
    public void addConversation(Conversation conversation){
      totalConversations += 1;
    }

}