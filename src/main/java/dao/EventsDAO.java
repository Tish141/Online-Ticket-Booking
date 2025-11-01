/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Event;
import model.TicketCategories;
import model.Ticket;
import utils.DBContext;

/**
 *
 * @author Hieu
 */
public class EventsDAO extends DBContext {

    public EventsDAO() {
        super();
    }

    public List<Event> getAll() {
        String sql = "SELECT * FROM [dbo].[events]";
        List<Event> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("description");
                Date date = rs.getDate("date");
                String location = rs.getString("location");
                Date created = rs.getDate("created_at");

                Event event = new Event(id, name, des, date, location, created);

                list.add(event);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String name = rs.getString("name");
                String des = rs.getString("description");
                Date date = rs.getDate("date");
                String location = rs.getString("location");
                Date created = rs.getDate("created_at");
                return new Event(id, name, des, date, location, created);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void create(String name, String description, Date date, String location, Date createdAt) {
        String sql = "INSERT INTO events (name, description, date, location, created_at) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDate(3, (java.sql.Date) date);
            ps.setString(4, location);
            ps.setDate(5, (java.sql.Date) createdAt);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String name, String description, Date date, String location, Date created_at) {
        String sql = "UPDATE [dbo].[events] SET [name] = ?, [description] = ?, [date] = ?, [location] = ?, [create_at] = ? WHERE [id] = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDate(3, new java.sql.Date(date.getTime()));
            ps.setString(4, location);
            ps.setDate(5, new java.sql.Date(created_at.getTime()));
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Ticket> loadTicketsByEventId(int eventID) {
        String sql = "SELECT tickets.*, ticket_categories.name AS categoryname, ticket_categories.description\n"
                + "FROM     tickets INNER JOIN\n"
                + "                  ticket_categories ON tickets.category_id = ticket_categories.id where event_id = ?";

        List<Ticket> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int tikId = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String img = rs.getString("image_url");

                int id = rs.getInt("category_id");
                String cateName = rs.getString("categoryname");
                String description = rs.getString("description");

                TicketCategories ticketcate = new TicketCategories(id, cateName, description);

                Ticket ticket = new Ticket(tikId, getEventById(eventID), name, price, quantity, img, ticketcate);

                list.add(ticket);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        EventsDAO dao = new EventsDAO();
        for (Ticket t : dao.loadTicketsByEventId(1)) {
            System.out.println(t.getId());
            System.out.println(t.getCategory().getName());
        }
    }
}
