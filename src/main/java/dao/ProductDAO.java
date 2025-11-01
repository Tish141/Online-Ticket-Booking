/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductCategory;
import utils.DBContext;

/**
 *
 * @author Lê Hữu Tính
 */
public class ProductDAO extends DBContext {

    public ProductDAO() {
        super();
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, pc.name AS category_name, pc.description AS category_des "
                + "FROM products p "
                + "LEFT JOIN product_categories pc ON p.category_id = pc.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int catId = rs.getInt("category_id");
                String catName = rs.getString("category_name");
                String catDes = rs.getString("category_des");
                ProductCategory cate = new ProductCategory(catId, catName, catDes);

                int proId = rs.getInt("id");
                String proName = rs.getString("name");
                String proDes = rs.getString("description");
                long proPrice = rs.getLong("price");
                int proQuan = rs.getInt("stock");
                String proImgUrl = rs.getString("image_url");

                list.add(new Product(proId, proName, proDes, proPrice, proQuan, proImgUrl, cate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return list;
    }

    public List<Product> getByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT products.*, product_categories.name AS category_name, product_categories.description AS category_des "
                + "FROM products "
                + "INNER JOIN product_categories ON products.category_id = product_categories.id "
                + "WHERE products.category_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int catId = rs.getInt("category_id");
                String catName = rs.getString("category_name");
                String catDes = rs.getString("category_des");
                ProductCategory cate = new ProductCategory(catId, catName, catDes);
                int proId = rs.getInt("id");
                String proName = rs.getString("name");
                String proDes = rs.getString("description");
                long proPrice = rs.getLong("price");
                int proQuan = rs.getInt("stock");
                String proImgUrl = rs.getString("image_url");

                list.add(new Product(proId, proName, proDes, proPrice, proQuan, proImgUrl, cate));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    

    public void create(String pName, String pDes, long pPrice, int pQuantity, String pImgUrl, int cateID) {
        String sql = "INSERT INTO [dbo].[products] ([name],[description],[price],[stock],[image_url],[category_id]) \n"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pName);
            ps.setString(2, pDes);
            ps.setLong(3, pPrice);
            ps.setInt(4, pQuantity);
            ps.setString(5, pImgUrl);
            ps.setInt(6, cateID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name, c.description as category_des "
                + "FROM products p JOIN product_categories c ON p.category_id = c.id "
                + "WHERE p.name LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCategory cate = new ProductCategory(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_des")
                );
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getLong("price"),
                        rs.getInt("stock"),
                        rs.getString("image_url"),
                        cate
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void update(int pID, String pName, String pDes, long pPrice, int pQuantity, String pImgUrl, int cateID) {
        String sql = "UPDATE [dbo].[products] SET [name] = ?, [description] = ?, [price] = ?, [stock] = ?, [image_url] = ?, [category_id] = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pName);
            ps.setString(2, pDes);
            ps.setLong(3, pPrice);
            ps.setInt(4, pQuantity);
            ps.setString(5, pImgUrl);
            ps.setInt(6, cateID);
            ps.setInt(7, pID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

}
