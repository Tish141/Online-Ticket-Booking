/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Thinh
 */
public class TicketCategories {

    private int id;
    private String name;
    private String description;
    private List<Ticket> tickets;

    public TicketCategories() {
    }

    public TicketCategories(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TicketCategories(int id, String name, String description, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
