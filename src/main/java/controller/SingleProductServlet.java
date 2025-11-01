/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.EventsDAO;
import dao.TicketsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Cart;
import model.CartItem;
import model.Event;
import model.Ticket;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SingleProductServlet", urlPatterns = {"/single"})
public class SingleProductServlet extends HttpServlet {

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
            out.println("<title>Servlet SingleProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SingleProductServlet at " + request.getContextPath() + "</h1>");
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
        
        
        // Lấy id của vé từ tham số request
        String ticketId = request.getParameter("id");

        // Lấy thông tin vé từ TicketsDAO
        TicketsDAO ticketsDAO = new TicketsDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Ticket ticket = ticketsDAO.getTicketById(id);

        // Nếu tìm thấy vé, lấy thêm thông tin Event từ EventsDAO
        if (ticket != null) {
            EventsDAO eventsDAO = new EventsDAO();
            Event event = eventsDAO.getEventById(ticket.getId());

            // Đặt cả ticket và event làm thuộc tính trong request để truyền sang JSP
            request.setAttribute("ticket", ticket);
            request.setAttribute("event", event);
        } else {
            // Nếu không tìm thấy vé, thiết lập thông báo lỗi
            request.setAttribute("error", "Ticket không tìm thấy");
        }

        // Chuyển tiếp đến trang JSP để hiển thị thông tin
        request.getRequestDispatcher("/single-product.jsp").forward(request, response);
        
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


        int userId = Integer.parseInt(request.getParameter("userId"));
        String ticketId = request.getParameter("ticketId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date addedAt = Date.valueOf(request.getParameter("addedAt"));

        // Lấy thông tin vé từ TicketsDAO
        TicketsDAO ticketsDAO = new TicketsDAO();
        Ticket ticket = ticketsDAO.getTicketById(Integer.parseInt(ticketId));

        // Lấy giỏ hàng hiện tại từ session
//        HttpSession session = request.getSession();
        CartDAO cartDAO = new CartDAO();
        CartItemDAO cartItemDAO = new CartItemDAO();
//        Cart cart = (Cart) session.getAttribute("cart");

        // Nếu giỏ hàng chưa có, tạo mới giỏ hàng và lưu vào session
        if (cartDAO.getCartByUserId(userId) == null) {
//            cartDAO.getCartById(userId) = new Cart();
            cartDAO.create(userId);
            cartDAO.getCartByUserId(userId);  
//            session.setAttribute("cart", cart);
        }

        // Tạo đối tượng CartItem và thêm vào giỏ hàng
        CartItem cartItem = new CartItem();
        cartItem.setId(userId);
        cartItem.setItemId(ticket.getId());
        cartItem.setItemType("ticket");
        cartItem.setQuantity(quantity);
        cartItem.setAddedAt(addedAt);
        cartItem.setUnitPrice(ticket.getPrice());

        // Lưu CartItem vào cơ sở dữ liệu
//        cartItemDAO.create(cartItem);

        // Chuyển hướng người dùng đến trang giỏ hàng sau khi thêm sản phẩm
        response.sendRedirect("single");
        
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
