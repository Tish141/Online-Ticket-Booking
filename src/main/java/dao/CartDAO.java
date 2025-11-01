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
import model.Cart;
import model.CartItem;
import model.User;
import utils.DBContext;

/**
 * @author Lê Hữu Tính
 */
public class CartDAO extends DBContext {

    public CartDAO() {
        super();
    }

    public Cart getCartByUserId(int userId) {
        String sql = "SELECT    carts.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         carts INNER JOIN\n"
                + "                      users ON carts.user_id = users.id\n"
                + "					  where users.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                int uid = rs.getInt("user_id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(uid, name, email_db, phone, pass_db, role, userCreatedAt);

                Cart cart = new Cart(id, u, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);
                return cart;
            }
        } catch (Exception e) {
            System.out.println("getCartByUserId: " + e.getMessage());
        }
        return null;
    }

    public List<Cart> getAll() {
        String sql = "SELECT    carts.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         carts INNER JOIN\n"
                + "                      users ON carts.user_id = users.id";
        List<Cart> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                int uid = rs.getInt("user_id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(uid, name, email_db, phone, pass_db, role, userCreatedAt);

                Cart cart = new Cart(id, u, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);

                list.add(cart);
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    public void create(int userId) {
        String sql = "INSERT INTO carts (user_id, created_at, updated_at) VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM carts WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

    public Cart getCartById(int id) {
        String sql = "SELECT    carts.*, users.name, users.email, users.phone, users.password_hash, users.role, users.created_at AS user_created_at\n"
                + "FROM         carts INNER JOIN\n"
                + "                      users ON carts.user_id = users.id\n"
                + "					  where carts.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                int uid = rs.getInt("user_id");
                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date userCreatedAt = rs.getDate("user_created_at");

                User u = new User(uid, name, email_db, phone, pass_db, role, userCreatedAt);

                Cart cart = new Cart(id, u, createdAt, updatedAt);

                CartItemDAO itemsDAO = new CartItemDAO();
                List<CartItem> items = itemsDAO.getItemsByCartId(id);
                cart.setItems(items);
                return cart;
            }
        } catch (Exception e) {
            System.out.println("getCartById: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CartDAO dao = new CartDAO();
        Cart c = dao.getCartByUserId(1);

        List<CartItem> list = c.getItems();

        for (CartItem cItem : list) {
            System.out.println(cItem.getItemId());
            System.out.println(cItem.getItemType());
            System.out.println(cItem.getQuantity());
            System.out.println(cItem.getUnitPrice());
            System.out.println("");

        }

    }

}
