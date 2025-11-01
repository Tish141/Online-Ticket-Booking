/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.OrderDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Order;
import model.Ticket;
import model.User;

/**
 *
 * @author Hieu
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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

        // Nếu chưa login → redirect
        if (u == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action.toLowerCase()) {
            case "add":
                handleAddToCart(request, response, u);
                break;
            case "list":
                handleListCart(request, response, u);
                break;
            case "delete":
                handleDeleteCartItem(request, response, u);
                break;
            default:
                response.sendRedirect("shop");
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
//        String action = request.getParameter("action");
//        if ("checkout".equalsIgnoreCase(action)) {
//            HttpSession session = request.getSession();
//            User user = (User) session.getAttribute("user");
//
//            int userId = user.getId();
//
//            CartDAO cartDAO = new CartDAO();
//
//            OrderDAO orderDAO = new OrderDAO();
//            int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
//            String bankTransferNote = request.getParameter("bankTransferNote");
//
//            orderDAO.create(userId, totalAmount, bankTransferNote);
//
//            cartDAO.delete(userId);
//
//            // 3. Chuyển hướng sau khi thanh toán
//            request.getRequestDispatcher("order-success.jsp").forward(request, response);
//        }

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

    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        try {
            int ticketId = Integer.parseInt(request.getParameter("tid"));

            CartDAO cartDAO = new CartDAO();
            CartItemDAO cartItemDAO = new CartItemDAO();
            TicketsDAO ticketDAO = new TicketsDAO();

            // Lấy hoặc tạo cart nếu chưa có
            Cart cart = u.getCart();
            if (cart == null) {
                cartDAO.create(u.getId()); // tạo mới cart
                cart = cartDAO.getCartByUserId(u.getId()); // lấy lại cart từ DB
                u.setCart(cart); // cập nhật lại user
            }

            // Kiểm tra item đã tồn tại chưa
            CartItem existingItem = cartItemDAO.findByCartIdAndTicketId(cart.getId(), ticketId);

            if (existingItem != null) {
                // Nếu có → tăng quantity
                cartItemDAO.updateQuantity(existingItem.getId(), existingItem.getQuantity() + 1);
            } else {
                // Nếu chưa → thêm mới
                Ticket ticket = ticketDAO.getTicketById(ticketId);
                cartItemDAO.create(ticketId, cart.getId(), 1, ticket.getPrice());
            }

            response.sendRedirect("shop");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi thêm vào giỏ hàng");
        }
    }

    private void handleListCart(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        try {
            CartDAO cDao = new CartDAO();
            List<CartItem> cItemList = cDao.getCartByUserId(u.getId()).getItems(); // nếu em có DAO riêng thì nên load từ đó
            request.setAttribute("cartItems", cItemList);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải giỏ hàng");
        }
    }
    private void handleDeleteCartItem(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId")); // ID của cart_item

            CartItemDAO cartItemDAO = new CartItemDAO();
            cartItemDAO.delete(itemId);

            // Redirect lại trang giỏ hàng
            response.sendRedirect("cart?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lỗi khi xoá sản phẩm khỏi giỏ hàng");
        }
    }
}
