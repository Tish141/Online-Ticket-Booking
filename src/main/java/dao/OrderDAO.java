/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ManualPayment;
import model.Order;
import model.OrderItem;
import model.User;
import utils.DBContext;

/**
 *
 * @author Nguyễn Đào Thu Ngân
 */
public class OrderDAO extends DBContext {

    public OrderDAO() {
        super();
    }

    public List<Order> getAll() {
        String sql = "SELECT    orders.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         users INNER JOIN\n"
                + "                      orders ON users.id = orders.user_id";
        List<Order> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pass = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(userId, name, email, phone, pass, role, userCreatedAt);

                Order o = new Order(id, u, totalAmount, status, bankTransferNote, createdAt);

                list.add(o);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT    orders.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         users INNER JOIN\n"
                + "                      orders ON users.id = orders.user_id where orders.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                String bankTransferNote = rs.getString("bank_transfer_note");
                Date createdAt = rs.getDate("created_at");

                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pass = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(userId, name, email, phone, pass, role, userCreatedAt);

                Order o = new Order(orderId, u, totalAmount, status, bankTransferNote, createdAt);
                return o;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<OrderItem> loadOrderItemsByOrderId(int orderId) {
        String sql = "SELECT * FROM order_items \n"
                + "WHERE order_id = ?";

        List<OrderItem> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String itemType = rs.getString("item_type");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");

                OrderItem orderItem = new OrderItem(id, getOrderById(orderId), itemType, itemId, quantity, unitPrice);

                list.add(orderItem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public List<ManualPayment> loadManualPaymentsByOrderId(int orderId) {
        String sql = "SELECT * FROM manual_payments \n"
                + "WHERE order_id = ?";

        List<ManualPayment> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("transfer_content");
                boolean confirmed = rs.getBoolean("confirmed_by_admin");
                Date confirmedAt = rs.getDate("confirmed_at");

                ManualPayment m = new ManualPayment(id, getOrderById(orderId), content, confirmed, confirmedAt);

                list.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void create(int userId, int totalAmount, String bankTransferNote) {
        String sql = "INSERT INTO orders (user_id, total_amount, status, bank_transfer_note, created_at) VALUES (?,?,?,?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, totalAmount);
            ps.setString(3, "pending");
            ps.setString(4, bankTransferNote);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int orderId, int userId, int totalAmount, String status, String bankTransferNote, Date createdAt) {
        String sql = "UPDATE orders\n"
                + "SET user_id = ?, total_amount = ?, status = ?, bank_transfer_note = ?, created_at = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, totalAmount);
            ps.setString(3, status);
            ps.setString(4, bankTransferNote);
            ps.setDate(5, new java.sql.Date(createdAt.getTime()));
            ps.setInt(6, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setPaid(int orderId) {
        String sql = "UPDATE orders\n"
                + "SET status = 'paid'\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<ManualPayment> list = dao.loadManualPaymentsByOrderId(1);
        for (ManualPayment m : list) {
            System.out.println(m.getId());
            System.out.println(m.getTransferContent());

        }

    }
}
