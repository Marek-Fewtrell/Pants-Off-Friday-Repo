package project.server;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalculatorServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		/*String panel = request.getParameter("solarPanel");
		String numPanels = request.getParameter("numPanels");
		String inverter = request.getParameter("inverter");
		double daytimeUsage = Double.parseDouble(request.getParameter("daytimeUsage"));
		double efficiencyLoss = Double.parseDouble(request.getParameter("efficiencyLoss"));
		String postcode = request.getParameter("postcode");
		String suburb = request.getParameter("suburb");
		String energyProvider = request.getParameter("energyProvider");
		String installationCost = request.getParameter("installationCost");
		String interestRate = request.getParameter("interestRate");*/
		HttpSession session = request.getSession(true);
		session.setAttribute("solarPanel", request.getParameter("solarPanel"));
		session.setAttribute("numPanels", request.getParameter("numPanels"));
		session.setAttribute("inverter", request.getParameter("inverter"));
		session.setAttribute("daytimeUsage", request.getParameter("daytimeUsage"));
		session.setAttribute("efficiencyLoss", request.getParameter("efficiencyLoss"));
		session.setAttribute("postcode", request.getParameter("postcode"));
		session.setAttribute("suburb", request.getParameter("suburb"));
		session.setAttribute("energyProvider", request.getParameter("energyProvider"));
		session.setAttribute("interestRate", request.getParameter("interestRate"));
		session.setAttribute("installationCost", request.getParameter("installationCost"));
		try {
			//response.sendRedirect("/contact us.jsp");
			response.sendRedirect("/powerGeneratedReport.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
