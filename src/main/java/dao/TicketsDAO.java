/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.TicketCategories;
import model.Ticket;
import utils.DBContext;

/**
 *
 * @author Hieu
 */
public class TicketsDAO extends DBContext {

    public TicketsDAO() {
        super();
    }

    public List<Ticket> getAll() {
        String sql = "select * from[dbo].[tickets]";
        List<Ticket> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int tikId = rs.getInt("id");
                int eventId = rs.getInt("event_id");
                EventsDAO eventDao = new EventsDAO();
                Event e = eventDao.getEventById(eventId);
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String img = rs.getString("image_url");
                int cateId = rs.getInt("category_id");
                TicketCategoriesDAO ticketDao = new TicketCategoriesDAO();
                TicketCategories t = ticketDao.getTicketCategoryById(cateId);
                Ticket ticket = new Ticket(tikId, e, name, price, quantity, img, t);

                list.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Ticket getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int eventId = rs.getInt("event_id");
                    Event e = new EventsDAO().getEventById(eventId);

                    int cateId = rs.getInt("category_id");
                    TicketCategories t = new TicketCategoriesDAO().getTicketCategoryById(cateId);
                    return new Ticket(
                            rs.getInt("id"),
                            e,
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("quantity"),
                            rs.getString("image_url"),
                            t
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Ticket> getTicketsByCateID(int cateID) {
        String sql = "SELECT tickets.*, ticket_categories.name AS cate_name, ticket_categories.description\n"
                + "FROM     ticket_categories INNER JOIN\n"
                + "tickets ON ticket_categories.id = tickets.category_id\n"
                + "WHERE 	category_id = ?;";
        List<Ticket> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cateID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int tikId = rs.getInt("id");
                int eventId = rs.getInt("event_id");
                EventsDAO eventDao = new EventsDAO();
                Event e = eventDao.getEventById(eventId);

                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String img = rs.getString("image_url");
                int cateId = rs.getInt("category_id");

                TicketCategoriesDAO ticketDao = new TicketCategoriesDAO();
                TicketCategories t = ticketDao.getTicketCategoryById(cateId);
                Ticket ticket = new Ticket(tikId, e, name, price, quantity, img, t);

                list.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void create(int eventId, String name, int price, int quantity, String imageUrl, int categoryId) {
        String sql = "INSERT INTO tickets (event_id, name, price, quantity, image_url, category_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setInt(4, quantity);
            ps.setString(5, imageUrl);
            ps.setInt(6, categoryId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, int eventId, String name, int price, int quantity, String imageUrl, int categoryId) {
        String sql = "UPDATE tickets SET event_id = ?, name = ?, price = ?, quantity = ?, image_url = ?, category_id = ? WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setInt(4, quantity);
            ps.setString(5, imageUrl);
            ps.setInt(6, categoryId);
            ps.setInt(7, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public List<Tickets> getTicketsByEventId(int eventId) {
//        String sql = "SELECT * FROM tickets WHERE event_id = ?";
//        List<Tickets> list = new ArrayList<>();
//
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, eventId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int tikId = rs.getInt("id");
//                String name = rs.getString("name");
//                int price = rs.getInt("price");
//                int quantity = rs.getInt("quantity");
//                String img = rs.getString("image_url");
//
//                // Gọi lại các đối tượng liên quan
//                Event e = new EventsDAO().getEventById(eventId); // hoặc có thể truyền từ ngoài vào để đỡ tốn truy vấn
//                int cateId = rs.getInt("category_id");
//                TicketCategories t = new TicketCategoriesDAO().getTicketCategoryById(cateId);
//
//                Ticket ticket = new Ticket(tikId, e, name, price, quantity, img, t);
//                list.add(ticket);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return list;
//    }
    public List<Ticket> searchTicketsByName(String name) {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE name LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            EventsDAO eventDao = new EventsDAO();
            TicketCategoriesDAO cateDao = new TicketCategoriesDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                int eventId = rs.getInt("event_id");
                Event e = eventDao.getEventById(eventId);

                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image_url");

                int cateId = rs.getInt("category_id");
                TicketCategories t = cateDao.getTicketCategoryById(cateId);

                Ticket ticket = new Ticket(id, e, rs.getString("name"), price, quantity, image, t);
                list.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void main(String[] args) {
        TicketsDAO dao = new TicketsDAO();
        List<Ticket> list = dao.getTicketsByCateID(7);
        for (Ticket e : list) {
            System.out.println(e.getId() + "  " + e.getName());
        }
    }
}
