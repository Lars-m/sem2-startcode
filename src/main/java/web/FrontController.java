package web;

import web.commands.Command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/fc/*"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Command action = Command.fromPath(request);

            String view = action.execute(request, response);

            if (view.equals(Command.REDIRECT_TO_HOME)) {
                //index.jsp is the only page not located in WEB-INF
                response.sendRedirect(request.getContextPath());
                return;
            }

            if (view.startsWith(Command.REDIRECT)) {
                String page = view.substring(Command.REDIRECT.length());
                response.sendRedirect(page);
                return;
            }
            if (view.equals(Command.NOT_FOUND_COMMAND)) {
                response.sendError(404);
                return;
            }
            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger("web").log(Level.SEVERE,ex.getMessage(),ex);
            throw new ServletException(ex.getCause());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
