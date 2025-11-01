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
import model.Order;
import model.OrderItem;
import model.User;
import utils.DBContext;

/**
 *
 * @author Nguyễn Đào Thu Ngân
 */
public class OrderItemDAO extends DBContext {

    public OrderItemDAO() {
        super();
    }

    public List<OrderItem> getAll() {
        List<OrderItem> list = new ArrayList();
        String sql = "SELECT    order_items.*, orders.user_id, orders.total_amount, orders.status, orders.bank_transfer_note, orders.created_at, users.name, users.email, users.phone, users.password_hash, users.role, \n"
                + "                      users.created_at AS user_created_at\n"
                + "FROM         users INNER JOIN\n"
                + "                      orders ON users.id = orders.user_id INNER JOIN\n"
                + "                      order_items ON orders.id = order_items.order_id";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String itemType = rs.getString("item_type");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");

                int orderId = rs.getInt("order_id");
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

                OrderItem oI = new OrderItem(id, o, itemType, itemId, quantity, unitPrice);

                list.add(oI);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public void create(int orderId, String itemType, int itemId, int quantity, int unitPrice) {
        String sql = "INSERT INTO order_items (order_id, item_type, item_id, quantity, unit_price) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderId);
            ps.setString(2, itemType);
            ps.setInt(3, itemId);
            ps.setInt(4, quantity);
            ps.setInt(5, unitPrice);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int orderItemId) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItemId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, int orderId, String itemType, int itemId, int quantity, int unitPrice) {
        String sql = "UPDATE order_items\n"
                + "SET order_id = ?, item_type = ?, item_id = ?, quantity = ?, unit_price = ?\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setString(2, itemType);
            ps.setInt(3, itemId);
            ps.setInt(4, quantity);
            ps.setInt(5, unitPrice);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        OrderItemDAO dao = new OrderItemDAO();
        for (OrderItem o : dao.getAll()) {
            System.out.println(o.getId());
            System.out.println(o.getItemType());
        }
    }
}
