package javaweb.day2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
    	
    	switch(action) {
    	case "login":Login(); break;
    	case "tweet":tweet();break;
    	}
    	
    	RequestDispatcher dispatcher =request.getRequestDispatcher(view);
    	dispatcher.forward(request,response);
		
	}

	private void tweet() {
		String msg = request.getParameter("msg");
		String username = (String) session.getAttribute("user");
		
		List<String> msgs = (List<String>) application.getAttribute("msgs");
		if(msgs == null) {
			msgs = new ArrayList<String>();
			application.setAttribute("msgs", msgs);
		}
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		msgs.add(username+" :: "+msg+" , "+date.format(f));
		application.log(msg+"추가됨");
		
		view = "/day2/tweet_list.jsp";
	}

	private void Login() {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		if(username !=null) {
			session.setAttribute("user", username);
		}
		view="/day2/tweet_list.jsp";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	

}
