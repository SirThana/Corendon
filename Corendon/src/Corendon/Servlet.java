package Corendon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ticketNumber = request.getParameter("ticketnumber");


        URL url = new URL("http://fys.securidoc.nl:11111/Ticket");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

        String function = "Check";
        String teamId = "IC104-1";
        String teamKey = "589023aa55da3edf623bdd94761e5510";

        outputStreamWriter.write("{\n" +
                "\"function\": \"" + function + "\",\n" +
                "\"teamId\": \"" + teamId + "\",\n" +
                "\"teamKey\": \"" + teamKey + "\",\n" +
                "\"requestId\": 1,\n" +
                "\"firstName\": \"" + firstName + "\",\n" +
                "\"lastName\": \"" + lastName + "\",\n" +
                "\"ticketNumber\": \"" + ticketNumber + "\"\n" +
                "}");

        outputStreamWriter.flush();
        outputStreamWriter.close();
        outputStream.close();

        connection.connect();
        String resultApi;
        BufferedInputStream inputBuffer = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream outputArray = new ByteArrayOutputStream();

        int resultRead = inputBuffer.read();
        while (resultRead != -1) {
            outputArray.write((byte) resultRead);
            resultRead = inputBuffer.read();
        }

        resultApi = outputArray.toString();

        String checkTicket = "\"ticketStatus\":\"Valid\"";

        if (resultApi.contains(checkTicket)) {

            String ipAddress = request.getRemoteAddr();
            Process p;
            try {
                p = Runtime.getRuntime().exec("sudo iptables -t nat -I PREROUTING -s "
                        + ipAddress + " -j ACCEPT");
                p = Runtime.getRuntime().exec("sudo iptables -I FORWARD -s " + ipAddress + " -j ACCEPT");
                p.waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            response.sendRedirect("index.jsp");
        }

        if(request.getParameter("Logout") != null){
            String ipAddress = request.getRemoteAddr();
            Process p;
            p = Runtime.getRuntime().exec("sudo iptables -I FORWARD -s " + ipAddress + " -j DROP");

        }else{
//            response.sendRedirect("http://www.corendon.nl");
        }




    }
}