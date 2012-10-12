package project.server;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GUIServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, 
	        HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-java-serialized-object");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("solarPanel", "Marek is awesome");
//		
//		response.setContentType("text/plain");
//		response.getWriter().println("Hello, world");
//		
//		request.setAttribute("solarPanel", "Marek is awesome");

InputStream in = request.getInputStream();
ObjectInputStream input = new ObjectInputStream(in);
String echo = null;

		echo = "Awesome";



OutputStream out = response.getOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(out);
oos.writeObject(echo);
oos.flush();
oos.close();
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        
    }
	
}
