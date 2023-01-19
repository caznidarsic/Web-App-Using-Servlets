package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;

import java.io.PrintWriter;


/*
 * Christian Znidarsic
 * hikeQuoteGenerator Class
 * 
 * The hikeQuoteGenerator class extends HttpServlet and dynamically
 * sends html in response to http requests. It communicates with
 * the BhcUtils API to produce hike quotes.
 */

/**
 * Servlet implementation class hikeQuoteGenerator
 */
@WebServlet("/hikeQuoteGenerator")
public class hikeQuoteGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean firstAccess = true;
	private String[] hikeNames;
//	private final int minYear;
	private final int maxYear;
//	private BookingDay bookingDay;
	private static LocalDateTime localDateTime;
	
	// input variables
	private static String inputHike = null;
	private static String inputDuration = null;
	private static String inputYear = null;
	private static String inputMonth = null;
	private static String inputDay = null;
	private static String inputHikers = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hikeQuoteGenerator() throws ServletException {
        super();
        hikeNames = HikeType.getHikeNames();
//        minYear = BookingDay.DEFAULT_MIN_YEAR;
        maxYear = BookingDay.DEFAULT_MAX_YEAR;
		localDateTime = LocalDateTime.now();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// initialize the input variables
		inputHike = request.getParameter("Hike");
		inputDuration = request.getParameter("Duration");
		inputYear = request.getParameter("Year");
		inputMonth = request.getParameter("Month");
		inputDay = request.getParameter("Day");
		inputHikers = request.getParameter("Hikers");
		
		
		
		// generate the response
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
	
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Hike Quote Generator</title>");
		
		
		out.println("<script type=\"text/javascript\" src=\"ValidateHikers.js\">  </script>");
		
		
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<form method=POST>");
		out.println("<h1>Hike Quote Generator</h1>");
		
		
		
		// HIKE SELECTION
		out.println("Choose a Hike: ");
		out.println("<select name=\"Hike\" id=hikeSelector>");
		if (inputHike != null) {
			for (String name: hikeNames) {
				if (name.equals(request.getParameter("Hike"))) {
					out.println("<option value=\"" + name + "\" selected>" + name + "</option>");
				}
				else {
					out.println("<option value=\"" + name + "\">" + name + "</option>");
				}
			}
		}
		else {
			for (String name: hikeNames) {
					out.println("<option value=\"" + name + "\">" + name + "</option>");
			}
		}
		out.println("</select>");
		out.println("<br />");
		
		
		
		// DURATION SELECTION
		out.println("Select Duration: ");
		out.println("<select name=\"Duration\" id=durationSelector>");
		if (inputDuration != null) {
			for (int x = 2; x <= 7; x++) {
				if (x == Integer.valueOf(request.getParameter("Duration"))) {
					out.println("<option value=\"" + x + "\" selected>" + x + "</option>");
				}
				else {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
				}
			}
		}
		else {
			for (int x = 2; x <= 7; x++) {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
			}
		}
		out.println("</select>");
		out.println("<br />");
		
		
		
		// YEAR SELECTION
		out.println("Select Year: ");
		out.println("<select name=\"Year\">");
		if (inputYear != null) {
			for (int x = localDateTime.getYear(); x <= maxYear; x++) {
				if (x == Integer.valueOf(request.getParameter("Year"))) {
					out.println("<option value=\"" + x + "\" selected>" + x + "</option>");
				}
				else {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
				}
			}
		}
		else {
			for (int x = localDateTime.getYear(); x <= maxYear; x++) {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
			}
		}
		out.println("</select>");
		out.println("<br />");
		
		
		
		// MONTH SELECTION
		out.println("Select Month: ");
		out.println("<select name=\"Month\">");
		if (inputMonth != null) {
			for (int x = 1; x <= 12; x++) {
				if (x == Integer.valueOf(request.getParameter("Month"))) {
					out.println("<option value=\"" + x + "\" selected>" + x + "</option>");
				}
				else {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
				}
			}
		}
		else {
			for (int x = 1; x <= 12; x++) {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
			}
		}
		out.println("</select>");
		out.println("<br />");
		
		
		
		// DAY SELECTION
		out.println("Select Day: ");
		out.println("<select name=\"Day\">");
		if (inputDay != null) {
			for (int x = 1; x <= 31; x++) {
				if (x == Integer.valueOf(request.getParameter("Day"))) {
					out.println("<option value=\"" + x + "\" selected>" + x + "</option>");
				}
				else {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
				}
			}
		}
		else {
			for (int x = 1; x <= 31; x++) {
					out.println("<option value=\"" + x + "\">" + x + "</option>");
			}
		}
		out.println("</select>");
		out.println("<br />");
		
		
		
		
		// NUMBER OF HIKERS SELECTION
		out.println("Select Number of Hikers: ");
		if (inputHikers != null) {
			out.println("<input name=\"Hikers\" id=\"hikersInput\" type=\"text\" value=\"" + inputHikers + "\" oninput=validateHikers() size=4 required>");
		}
		else {
			out.println("<input name=\"Hikers\" id=\"hikersInput\" type=\"text\" placeholder=\"ex: 4\" oninput=validateHikers() size=4 required>");
		}
		out.println("</select>");
		out.println("<br />");
		
		
		out.println("<p id=errorMessage style=\"color:red\"></p>");
		
		
		if (inputHikers != null) {
			out.println("<input type=\"SUBMIT\" id=\"submitButton\">");
		}
		else {
			out.println("<input type=\"SUBMIT\" id=\"submitButton\" disabled>");
		}
		
		out.println("</form>");
		
		
		
		if (firstAccess) {
			firstAccess = false;
		}
		else {
			if (inputHike != null && inputDuration != null && inputYear != null && inputMonth != null && inputDay != null && inputHikers != null) {
        		if (!inputHike.matches("^[a-zA-Z\\s-]+$")) {
        			out.println("The field \"Hike\" is formatted incorrectly. The Hike name can only contain letters, spaces and \"-\" characters.");
        		}
        		else if (!inputDuration.matches("^\\d{1,2}$")) {
        			out.println("The field \"Duration\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputMonth.matches("^\\d{1,2}$")) {
        			out.println("The field \"Month\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputDay.matches("^\\d{1,2}$")) {
        			out.println("The field \"Day\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputYear.matches("^\\d{4}$")) {
        			out.println("The field \"Year\" is formatted incorrectly. Proper format is: \"####\"");
        		}
        		else if (!inputHikers.matches("^\\d+$")) {
        			out.println("The field \"Hikers\" is formatted incorrectly. The field \"Hikers\" can only contain numbers.");
        		}
        		else {
    				out.println(GetQuote.getQuote(Integer.valueOf(inputYear), Integer.valueOf(inputMonth), Integer.valueOf(inputDay), Integer.valueOf(inputDuration), inputHike, Integer.valueOf(inputHikers)));
    				out.println("<br />");
        		}
			}
			else {
				out.println("Some inputs were missing/formatted incorrectly.");
			}

		}
		
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
