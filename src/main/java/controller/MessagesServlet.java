/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MessageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Message;
import model.User;

/**
 *
 * @author BACH YEN
 */
@WebServlet(name = "MessagesServlet", urlPatterns = {"/messages"})
public class MessagesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MessagesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessagesServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        if (session.getAttribute("user") != null && u.getRole() == 1) {

            String view = request.getParameter("view");

            if (view == null) {
                view = "list";
            }

            if (view.equalsIgnoreCase("list")) {
                MessageDAO mesDao = new MessageDAO();
                List<Message> mesList = mesDao.getAll();
                request.setAttribute("mesList", mesList);
                request.getRequestDispatcher("message.jsp").forward(request, response);
            } else if (view.equalsIgnoreCase("inbox")) {
                MessageDAO mesDao = new MessageDAO();
                List<Message> mesList = mesDao.getAllUnreaded();
                request.setAttribute("mesList", mesList);
                request.getRequestDispatcher("message.jsp").forward(request, response);
            }

        } else {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = request.getParameter("view");

        if (view == null) {
            view = "edit";
        }

        if (view.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("idEdit"));
            int statusInt = Integer.parseInt(request.getParameter("statusEdit"));
            boolean status = false;

            if (statusInt == 1) {
                status = true;
            }

            MessageDAO mesDao = new MessageDAO();
            mesDao.checkReaded(id, status);
            response.sendRedirect("messages");
        } else if (view.equalsIgnoreCase("delete")) {

            int id = Integer.parseInt(request.getParameter("idDelete"));
            MessageDAO mesDao = new MessageDAO();
            mesDao.delete(id);
            response.sendRedirect("messages");
        } else if (view.equalsIgnoreCase("add")) {
            String messContent = request.getParameter("messageInput");
            int hook = Integer.parseInt(request.getParameter("hook"));

            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");

            MessageDAO dao = new MessageDAO();
            dao.create(u.getId(), messContent);

            if (hook == 1) {
                response.sendRedirect("contact");
            } else {
                response.sendRedirect("about");
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
