/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Nguyễn Đào Thu Ngân
 */
public class Order {
    private int id;
    private User user;
    private long totalAmount;
    private String status;
    private String bankTransferNote;
    private Date createAt;
    private List<OrderItem> items;
    private ManualPayment manualPayment;

    public Order() {
    }
    
    public Order(int id, User user, long totalAmount, String status, String bankTransferNote, Date createAt) {
        this.id = id;
        this.user = user;
        this.totalAmount = totalAmount;
        this.status = status;
        this.bankTransferNote = bankTransferNote;
        this.createAt = createAt;
        this.items = null;
        this.manualPayment = null;
    }

    public Order(int id, User user, long totalAmount, String status, String bankTransferNote, Date createAt, List<OrderItem> items, ManualPayment manualPayment) {
        this.id = id;
        this.user = user;
        this.totalAmount = totalAmount;
        this.status = status;
        this.bankTransferNote = bankTransferNote;
        this.createAt = createAt;
        this.items = items;
        this.manualPayment = manualPayment;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankTransferNote() {
        return bankTransferNote;
    }

    public void setBankTransferNote(String bankTransferNote) {
        this.bankTransferNote = bankTransferNote;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public ManualPayment getManualPayment() {
        return manualPayment;
    }

    public void setManualPayment(ManualPayment manualPayment) {
        this.manualPayment = manualPayment;
    }

}