package javaweb.day2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TwitterController
 */
@WebServlet("/twitter")
public class TwitterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext application;
	private HttpSession session;
	private String view;
    private TweetDAO dao=new TweetDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		// TODO Auto-generated method stub
    	request.setCharacterEncoding("utf-8");
    	this.request=request;
    	this.response=response;
    	session = request.getSession();
    	application = request.getServletContext();
    	
    	
    	String action =request.getParameter("action");
    	if(action==null) {
    		session.invalidate();
    		response.sendRedirect("/JavaWeb/day2/twitter_login.jsp");
    		return;
    	}
    	application.log("로그3");
    	switch(action) {
    	case "login":Login(); break;
    	case "tweet":tweet();break;
    	}
    	application.log("로그1");
    	RequestDispatcher dispatcher =request.getRequestDispatcher(view);
    	application.log(view);
    	dispatcher.forward(request,response);
		
	}

	private void tweet() throws SQLException {
		dao.Connect();
		application.log("로그2");
		String msg = request.getParameter("msg");
		String username = (String) session.getAttribute("user");
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<String> msgs =dao.getDB();  
		request.setAttribute("msgs",msgs);
		
		dao.insertDB(username+" :: "+msg+" , "+date.format(f));
		application.log(msg+"추가됨");
		
		
		
	   
		dao.Disconnect();
		view = "/day2/tweet_list.jsp";
	
	}

	private void Login() throws SQLException {
		// TODO Auto-generated method stub
		dao.Connect();
		application.log("로그");
		String username=request.getParameter("username");
		if(username !=null) {
			session.setAttribute("user", username);
			
		}
		List<String> msgs =dao.getDB();  
		
	    request.setAttribute("msgs",msgs);
		dao.Disconnect();
		view="/day2/tweet_list.jsp";
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
