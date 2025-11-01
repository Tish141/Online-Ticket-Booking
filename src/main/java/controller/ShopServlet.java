/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.TicketsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import model.Ticket;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {
     private TicketsDAO dao;

    @Override
    public void init() throws ServletException {
       dao = new TicketsDAO();
    }
     
     

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
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
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
        TicketsDAO dao = new TicketsDAO();

        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        String sort = request.getParameter("sort");
        List<Ticket> ticketList;

        //Tìm kiếm theo keyword
        if (keyword != null && !keyword.trim().isEmpty()) {
            ticketList = dao.searchTicketsByName(keyword.trim());
        } else {
            if (category != null && !category.equals("all")) {
                try {
                    int catId = Integer.parseInt(category);
                    ticketList = dao.getTicketsByCateID(catId);
                } catch (NumberFormatException e) {
                    ticketList = dao.getAll();
                }
            } else {
                ticketList = dao.getAll();
            }
        }

        // Sắp xếp
        if (sort != null) {
            switch (sort) {
                case "az":
                    ticketList.sort(Comparator.comparing(Ticket::getName));
                    break;
                case "za":
                    ticketList.sort(Comparator.comparing(Ticket::getName).reversed());
                    break;
                case "lowtohigh":
                    ticketList.sort(Comparator.comparingLong(Ticket::getPrice));
                    break;
                case "hightolow":
                    ticketList.sort(Comparator.comparingLong(Ticket::getPrice).reversed());
                    break;
            }
        }

        request.setAttribute("productList", ticketList);
        request.setAttribute("keyword", keyword != null ? keyword : "");
        request.setAttribute("currentCategory", category != null ? category : "all");
        request.setAttribute("currentSort", sort != null ? sort : "");
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
        doGet(request, response);
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
