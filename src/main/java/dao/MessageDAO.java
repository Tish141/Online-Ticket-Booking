/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Message;
import model.User;
import utils.DBContext;

/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class MessageDAO extends DBContext {

    public MessageDAO() {
        super();
    }

    public List<Message> getAll() {
        String sql = "SELECT   messages.*, users.name, users.phone, users.email, users.password_hash, users.role, users.created_at \n"
                + "FROM	messages INNER JOIN \n"
                + "users ON messages.users_id = users.id";

        List<Message> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idMess = rs.getInt("id");
                int userId = rs.getInt("users_id");
                String content = rs.getString("content");
                boolean readed = rs.getBoolean("readed");
                Date realeaseDate = rs.getDate("release_date");

                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(userId, name, email_db, phone, pass_db, role, createdAt);

                Message message = new Message(idMess, u, content, readed, realeaseDate);
                list.add(message);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void create(int userId, String content) {
        String sql = "INSERT INTO [dbo].[messages] ([users_id],[content],[readed],[release_date]) \n"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //      int nextId = getNextId();
            //      ps.setInt(1, nextId);
            ps.setInt(1, userId);
            ps.setString(2, content);
            ps.setBoolean(3, false);
            ps.setDate(4, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Message getMessageById(int id) {
        String sql = "SELECT   messages.*, users.name, users.phone, users.email, users.password_hash, users.role, users.created_at \n"
                + "FROM	messages INNER JOIN \n"
                + "users ON messages.users_id = users.id \n"
                + "WHERE messages.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idMess = rs.getInt("id");
                int userId = rs.getInt("users_id");
                String content = rs.getString("content");
                boolean readed = rs.getBoolean("readed");
                Date realeaseDate = rs.getDate("release_date");

                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(userId, name, email_db, phone, pass_db, role, createdAt);

                Message message = new Message(idMess, u, content, readed, realeaseDate);
                return message;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    private int getNextId() {
//        String sql = "SELECT MAX(id) AS maxID FROM product";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("maxID") + 1;
//            } else {
//                return 1;
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return -1;
//    }
    
    public void checkReaded(int messID, boolean readed) {
        String sql = "UPDATE [dbo].[messages] SET [readed] = ? \n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, readed);
            ps.setInt(2, messID);
           
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void delete(int id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public List<Message> getAllUnreaded() {
        String sql = "SELECT   messages.*, users.name, users.phone, users.email, users.password_hash, users.role, users.created_at \n"
                + "FROM	messages INNER JOIN \n"
                + "users ON messages.users_id = users.id \n"
                + "WHERE readed = ?";

        List<Message> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setBoolean(1, false);
            
            ResultSet rs = ps.executeQuery();
            
            

            while (rs.next()) {
                int idMess = rs.getInt("id");
                int userId = rs.getInt("users_id");
                String content = rs.getString("content");
                boolean readed = rs.getBoolean("readed");
                Date realeaseDate = rs.getDate("release_date");

                String name = rs.getString("name");
                String email_db = rs.getString("email");
                String phone = rs.getString("phone");
                String pass_db = rs.getString("password_hash");
                int role = rs.getInt("role");
                Date createdAt = rs.getDate("created_at");

                User u = new User(userId, name, email_db, phone, pass_db, role, createdAt);

                Message message = new Message(idMess, u, content, readed, realeaseDate);
                list.add(message);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public static void main(String[] args) {
        MessageDAO dao = new MessageDAO();
//        dao.delete(7);
//        
//        dao.checkReaded(1, true);
////        
        List<Message> list = new ArrayList<>();

        list = dao.getAllUnreaded();

        for (Message m : list) {
            System.out.println(m.getId());
            System.out.println(m.getUser().getEmail());
            System.out.println(m.getContent());
            System.out.println(m.isReaded());
            System.out.println(m.getReleaseDate().toString() + "");
            System.out.println("----------------------");
        }

//        Message m = dao.getMessageById(1);
//        
//        System.out.println(m.getId());
//        System.out.println(m.getUser().getName());
//        System.out.println(m.getContent());
//        System.out.println(m.isReaded());
//        System.out.println(m.getReleaseDate().toString() + "");
//        System.out.println("----------------------");

    }

}
