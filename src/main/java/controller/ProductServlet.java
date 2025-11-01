/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import model.Product;

/**
 *
 * @author Lê Hữu Tính
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAO();
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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("shop");
        //        ProductDAO dao = new ProductDAO();
        //
        //        String keyword = request.getParameter("keyword");
        //        String category = request.getParameter("category");
        //        String sort = request.getParameter("sort");
        //        List<Product> productList;
        //
        //        //Tìm kiếm theo keyword
        //        if (keyword != null && !keyword.trim().isEmpty()) {
        //            productList = dao.searchByName(keyword.trim());
        //        } else {
        //            if (category != null && !category.equals("all")) {
        //                try {
        //                    int catId = Integer.parseInt(category);
        //                    productList = dao.getByCategory(catId);
        //                } catch (NumberFormatException e) {
        //                    productList = dao.getAll();
        //                }
        //            } else {
        //                productList = dao.getAll();
        //            }
        //        }
        //
        //        // Sắp xếp
        //        if (sort != null) {
        //            switch (sort) {
        //                case "az":
        //                    productList.sort(Comparator.comparing(Product::getName));
        //                    break;
        //                case "za":
        //                    productList.sort(Comparator.comparing(Product::getName).reversed());
        //                    break;
        //                case "lowtohigh":
        //                    productList.sort(Comparator.comparingLong(Product::getPrice));
        //                    break;
        //                case "hightolow":
        //                    productList.sort(Comparator.comparingLong(Product::getPrice).reversed());
        //                    break;
        //            }
        //        }
        //
        //        request.setAttribute("productList", productList);
        //        request.setAttribute("keyword", keyword != null ? keyword : "");
        //        request.setAttribute("currentCategory", category != null ? category : "all");
        //        request.setAttribute("currentSort", sort != null ? sort : "");
        //        request.getRequestDispatcher("shop.jsp").forward(request, response);
     
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
        doGet(request, response); // Gọi lại doGet cho xử lý chung
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
