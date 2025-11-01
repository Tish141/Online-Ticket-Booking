/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ProductCategory;
import utils.DBContext;

/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class ProductCategoryDAO extends DBContext {

    public ProductCategoryDAO() {
        super();
    }

    public List<ProductCategory> getAll() {
        String sql = "SELECT product_categories.* FROM product_categories";
        List<ProductCategory> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int catId = rs.getInt("id");
                String catName = rs.getString("name");
                String catDes = rs.getString("description");

                list.add(new ProductCategory(catId, catName, catDes));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    
    public void create(String cName, String cDes) {
        String sql = "INSERT INTO [dbo].[product_categories] ([name],[description]) \n"
                + "VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

//            int nextId = getNextId();
//            
//            ps.setInt(1, nextId);
            ps.setString(1, cName);
            ps.setString(2, cDes);
       
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public ProductCategory getCategoryById(int id) {
        String sql = "SELECT * FROM product_categories WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String catName = rs.getString("name");
                String catDes = rs.getString("description");

                return new ProductCategory(id, catName, catDes);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    
    public void update(int cID, String cName, String cDes) {
        String sql = "UPDATE [product_categories] SET [name] = ?, [description] = ? \n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, cName);
            ps.setString(2, cDes);
            ps.setInt(3, cID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM product_categories WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ProductCategoryDAO dao = new ProductCategoryDAO();
        dao.delete(6);

        List<ProductCategory> list = dao.getAll();

        for (ProductCategory pc : list) {
            System.out.println(pc.getId());
            System.out.println(pc.getName());
            System.out.println(pc.getDescription());
        }
    

    }

}
