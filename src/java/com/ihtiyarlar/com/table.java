/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihtiyarlar.com;

/**
 *
 * @author Orkun Karakuş
 */
public class table {
    
    private String method,request;
    private int subno,usage,price,reqno;
    private boolean status;
 
    //constructor yani kurucu metot ile yeni bir obje yaratırken verileri    kolay bir şekilde atayabileceğiz.
    public table(int subno, int price, String method, int usage) {
        this.subno = subno;
        this.price = price;
        this.method = method;
        this.usage = usage;
    }
    
    public table(int reqno, int subno, String request, boolean status) {
        this.reqno = reqno;
        this.subno = subno;
        this.request = request;
        this.status = status;
    }
    
    public table(int subno) {
        this.subno = subno;
    }
    
    public int getSubno(){
        return subno;
    }
    
    public void setSubno(int subno){
        this.subno = subno;
    }
    
    public int getReqno(){
        return reqno;
    }
    
    public void setReqno(int reqno){
        this.reqno = reqno;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public int getUsage(){
        return usage;
    }
    
    public void setUsage(int usage){
        this.usage = usage;
    }
    
    public String getMethod(){
        return method;
    }
   
    public void setMethod(String method){
        this.method = method;
    }
    
    public String getRequest(){
        return request;
    }
   
    public void setRequest(String request){
        this.request = request;
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
}
    
