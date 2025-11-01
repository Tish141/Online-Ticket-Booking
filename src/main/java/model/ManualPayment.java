/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Thinh
 */
public class ManualPayment {
     private int id;
    private Order order;
    private String transferContent;
    private boolean confirmedByAdmin;
    private Date confirmedAt;

    public ManualPayment() {
    }

    public ManualPayment(int id, Order order, String transferContent, boolean confirmedByAdmin, Date confirmedAt) {
        this.id = id;
        this.order = order;
        this.transferContent = transferContent;
        this.confirmedByAdmin = confirmedByAdmin;
        this.confirmedAt = confirmedAt;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getTransferContent() {
        return transferContent;
    }

    public void setTransferContent(String transferContent) {
        this.transferContent = transferContent;
    }

    public boolean isConfirmedByAdmin() {
        return confirmedByAdmin;
    }

    public void setConfirmedByAdmin(boolean confirmedByAdmin) {
        this.confirmedByAdmin = confirmedByAdmin;
    }

    public Date getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
    }
    
}
