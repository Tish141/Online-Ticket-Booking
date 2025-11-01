/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lê Hữu Tính - CE190016
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import utils.DBContext;

public class CartItemDAO extends DBContext {

    public CartItemDAO() {
        super();
    }

    public List<CartItem> getAll() {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT ci.*, p.name AS product_name \n"
                + "FROM cart_items ci JOIN products p ON ci.item_id = p.id;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(rs.getInt("cart_id"));
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));
                item.setProductName(rs.getString("product_name"));
                list.add(item);
            }
        } catch (Exception e) {
            System.out.println("getAll: " + e.getMessage());
        }
        return list;
    }

    public List<CartItem> getItemsByCartId(int cartId) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM cart_items WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(cartId);
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));

                list.add(item);
            }
        } catch (Exception e) {
            System.out.println("getItemsByCartId: " + e.getMessage());
        }
        return list;
    }

    public void create(int itemID, int cartID, int quantity, int price) {
        String sql = "INSERT INTO cart_items (cart_id, item_type, item_id, quantity, unit_price, added_at) "
                + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartID);
            ps.setString(2, "ticket");
            ps.setInt(3, itemID);
            ps.setInt(4, quantity);
            ps.setInt(5, price);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("create: " + e.getMessage());
        }
    }

    public CartItem getById(int id) {
        String sql = "SELECT * FROM cart_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));

                Cart cart = new Cart();
                cart.setId(rs.getInt("cart_id"));
                item.setCart(cart);

                item.setItemType(rs.getString("item_type"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                item.setAddedAt(rs.getDate("added_at"));

                return item;
            }
        } catch (Exception e) {
            System.out.println("getById: " + e.getMessage());
        }
        return null;
    }

    public void update(CartItem item) {
        String sql = "UPDATE cart_items SET quantity = ?, unit_price = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getQuantity());
            ps.setInt(2, item.getUnitPrice());
            ps.setInt(3, item.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("delete: " + e.getMessage());
        }
    }

    public CartItem findByCartIdAndTicketId(int cartId, int ticketId) {
        String sql = "SELECT * FROM cart_items "
                + "JOIN tickets ON cart_items.item_id = tickets.id "
                + "WHERE cart_items.cart_id = ? "
                + "AND cart_items.item_type = 'ticket' "
                + "AND cart_items.item_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setInt(2, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));
                Cart cartTempt = new Cart();
                cartTempt.setId(rs.getInt("cart_id"));

                item.setCart(cartTempt);
                item.setItemType("ticket");
                item.setItemId(ticketId);
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getInt("unit_price"));
                // Nếu cần, có thể set thêm info từ bảng tickets
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateQuantity(int cartItemId, int newQuantity) {
        String sql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newQuantity);
            ps.setInt(2, cartItemId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CartItemDAO dao = new CartItemDAO();

        dao.create(7, 1, 1, 10000);

    }
}
