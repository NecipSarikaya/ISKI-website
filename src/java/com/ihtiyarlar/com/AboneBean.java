/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihtiyarlar.com;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static javax.faces.component.UIInput.isEmpty;
import javax.faces.context.FacesContext;


/**
 *
 * @author NECİP
 */
@ManagedBean(name = "aboneli")
@SessionScoped
public class AboneBean {
   private int subno;
    private String tc;
    private String name;
    private String surname;
    private String adress;
    private String phone;
    private String mail;
    private int counterno;
    private int price;
    private java.sql.Date kdate;
    private java.sql.Date odate;
    private int usage;
    private int aranan;
    private boolean durum;
    private String odemesek;
    private String data ;
    private String asb;
    private java.sql.Date date1;
    private int taahut;
    private boolean a;
    private String message;
    private int ax;
    private String sayfasec;
    private String hata=null;
    private String neden=null;
    private String gecti;

    public String getGecti() {
        return gecti;
    }

    public void setGecti(String gecti) {
        this.gecti = gecti;
    }

    public void setNeden(String neden) {
        this.neden = neden;
    }

    public String getNeden() {
        return neden;
    }

    public String getHata() {
        return hata;
    }

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }
  
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }
    private long millis=System.currentTimeMillis();
    java.sql.Date date=new java.sql.Date(millis);

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getKdate() {
        return kdate;
    }

    public void setKdate(Date kdate) {
        this.kdate = kdate;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getAranan() {
        return aranan;
    }

    public void setAranan(int aranan) {
        this.aranan = aranan;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public String getOdemesek() {
        return odemesek;
    }

    public void setOdemesek(String odemesek) {
        this.odemesek = odemesek;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAsb() {
        return asb;
    }

    public void setAsb(String asb) {
        this.asb = asb;
    }

    public int getSubno() {
        return subno;
    }

    public void setSubno(int subno) {
        this.subno = subno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCounterno() {
        return counterno;
    }

    public void setCounterno(int counterno) {
        this.counterno = counterno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public int getTaahut() {
        return taahut;
    }

    public void setTaahut(int taahut) {
        this.taahut = taahut;
    }
    
    public boolean hata(){
        return true;
    }
    
    
    
    public List<Abone> getAbone() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "Select * from SUBSCRIBERS";
      
      List<Abone> records = new ArrayList<Abone>();
      try { 
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();              
         while(rs.next()) { 
            if( aranan == rs.getInt("subno") ){
            Abone author = new Abone();
            author.setSubno(rs.getInt(1));
            author.setTc(rs.getString(2));
            author.setName(rs.getString(3));
            author.setSurname(rs.getString(4));
            author.setAdress(rs.getString(5));
            author.setPhone(rs.getString(6));
            author.setMail(rs.getString(7));
            author.setDate(rs.getDate(9));
            author.setOdate(rs.getDate(10));
            records.add(author);        
            }else{
                sorguabonex();
             }
               
         }
      } catch (SQLException e) {
         
         e.printStackTrace();
      }      
      return records;

}
    
  public String silme(){
      String url = "jdbc:derby://localhost:1527/iskiver";
      String user = "KRAL";
      String password = "kral";
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = 
      fc.getExternalContext().getRequestParameterMap();
      int as = parseInt(params.get("asb1"));
      Connection myConn = null;
      Statement myStmt = null;
      String sql = null,sql2=null,sql3=null,sql4=null;
      try {
        myConn = DriverManager.getConnection(url, user, password);
        myStmt = myConn.createStatement();
        sql = "delete from subscribers where subno="+as+"";  
        sql2 = "delete from subpay where subno="+as+"";  
        sql3 = "delete from subpenalty where subno="+as+"";  
        sql4 = "delete from subrequests where subno="+as+"";  
        myStmt.executeUpdate(sql);
        myStmt.executeUpdate(sql2);  
        myStmt.executeUpdate(sql3);  
        myStmt.executeUpdate(sql4);  
      }catch(Exception e){  
          
      }
      return "abonesorgu";
  }
    public void odeme(){
      
      String url = "jdbc:derby://localhost:1527/iskiver";
      String user = "KRAL";
      String password = "kral";
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = 
      fc.getExternalContext().getRequestParameterMap();
      data = params.get("username"); 
      ax = parseInt(params.get("asb1"));
      neden = params.get("ned"); 
      
      Connection myConn = null;
      Statement myStmt = null;
      long millis=System.currentTimeMillis();
    java.sql.Date date123=new java.sql.Date(millis);
    String sql = null;
      try {
          myConn = DriverManager.getConnection(url, user, password);
 

            myStmt = myConn.createStatement();
          if(neden.equals("Fatura")){
              sql = "update SUBPAY set STATUS ='TRUE',PAYMETHOD= '"+asb+"',PAYDATE= '"+date123+"' where KESIMDATE='"+data+"' AND subno="+ax+"";  
          }
          else{
               sql = "update SUBPENALTY set PENS ='TRUE',PAYMETHOD= '"+asb+"',PAYDATE= '"+date123+"' where KESIMDATE='"+data+"' AND subno="+ax+"";
          }

            
 

                 
            myStmt.executeUpdate(sql);         
            
      }catch(Exception e){  
          
      }
    }
    
    public List<Abone> getBorc() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "Select * from SUBPAY where subno ="+aranan+"";
      List<Abone> records = new ArrayList<Abone>();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();

         while(rs.next()) {
            Abone author = new Abone();
            author.setSubno(rs.getInt(1));
            author.setPrice(rs.getInt(2));
            author.setDate(rs.getDate(3));
            author.setDurum(rs.getBoolean(5));
            author.setOdemesek(rs.getString(6));
            author.setNeden("Fatura");
            records.add(author);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return records;

}
   
    public List<Abone> getCeza() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = "Select * from SUBPENALTY where subno ="+aranan+"";
      List<Abone> records = new ArrayList<Abone>();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();

         while(rs.next()) {
            Abone author = new Abone();
            author.setSubno(rs.getInt(1));
            author.setPrice(rs.getInt(2));
            author.setDate(rs.getDate(4));
            author.setDurum(rs.getBoolean(3));
            author.setNeden(rs.getString(8));
            author.setOdemesek(rs.getString(6));
            records.add(author);				
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return records;

}
    
    public List<Abone> getKullanim() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = getConnection();
      String stm = " SELECT * FROM subpay WHERE subno = "+aranan+"";
      List<Abone> records = new ArrayList<Abone>();
      try {   
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();

         while(rs.next()) {           
            Abone author = new Abone();
            author.setSubno(rs.getInt(1));
            author.setKdate(rs.getDate(3));
            author.setOdate(rs.getDate(4));
            author.setUsage(rs.getInt(7));
            records.add(author);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return records;

    }

    public Connection getConnection() {
      Connection con = null;
      String url = "jdbc:derby://localhost:1527/iskiver";
      String user = "KRAL";
      String password = "kral";
      
      try {
         con = DriverManager.getConnection(url, user, password);
         System.out.println("Connection completed.");
      } catch (SQLException ex) {
         System.out.println(ex.getMessage());
      }
      
      finally {
      }
      return con;
   }
   
    public String aboneolma() throws ParseException{
    if(taahut == 6){
        String str="2020-11-12";  
        date1=Date.valueOf(str); 
    }else if(taahut ==12){
        String str="2021-05-12";  
        date1=Date.valueOf(str); 
    }else if(taahut ==24){
        String str="2022-05-12";  
        date1=Date.valueOf(str); 
    }    
    Connection conn=null;
    PreparedStatement stmt= null;
    
    counterno=(int)(Math.random()*(999999+1));
    try{
        conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
        stmt=conn.prepareStatement("insert into SUBSCRIBERS(SUBNO,TC,NAME,SURNAME,ADRESS,PHONE,MAIL,COUNTERNO,SDATE,FDATE) values( "+subno+" , '"+tc+"' , '"+name+"' , '"+surname+"' , '"+adress+"' , '"+phone+"' , '"+mail+"' , "+counterno+" , '"+date+"' , '"+date1+"' )");
        stmt.execute();
        gecti = "Abonelik oluşturuldu";
    }catch(Exception e){
        return "index";
        }
        return "aboneolustur?faces-redirect=true";
    }
    
    
    public String sorguabonex(){        
            return sayfasec;
    }
    public int aliş() throws SQLException{
        PreparedStatement ps=null;
        Connection con=null;
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
        ps=con.prepareStatement("SELECT * FROM subscribers");
        ResultSet rs=ps.executeQuery();
         while(rs.next()){
        
            setSubno(rs.getInt("subno")+1);
        }
        if(subno == 0){
            subno =1;
        } 
        return subno;
    }
    
    public void kontrol(){
        PreparedStatement ps=null;
        Connection con=null;
        int a=0;
        try{
            
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subscribers");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){                
                if(rs.getInt("subno") == aranan){
                    hata=" ";
                    sayfasec="abonesorgu1";
                    a++;
                    break;
                }else{
                    hata = "Kullanici bulunamadi";
                    sayfasec="abonesorgu";
                }
            }
            if(a%2==0){
                 hata = "Kullanici bulunamadi";
             sayfasec="abonesorgu";
            }

        }
        catch(Exception e)
        {
            
        }
    }
    
    public void kontrol3(){
        PreparedStatement ps=null;
        Connection con=null;
        int a=0;
        try{
            
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subpay");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){                
                if(rs.getInt("subno") == aranan){
                    hata=" ";
                    sayfasec="kullanimsorgu1";
                     a++;
                    break;
                }else{
                    hata = "Kullanici bulunamadi";
                    sayfasec="kullanimsorgu";
                }
            }if(a%2==0){
                 hata = "Kullanici bulunamadi";
                sayfasec="kullanimsorgu";
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void kontrol2(){
        PreparedStatement ps=null;
        Connection con=null;
        int a=0;
        try{
            
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subpay");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){                
                if(rs.getInt("subno") == aranan){
                    hata=" ";
                    sayfasec="borcsorgu1";
                     a++;
                    break;
                }else{
                    hata = "Kullanici bulunamadi";
                    sayfasec="borcsorgu";
                }
            }if(a%2==0){
                 hata = "Kullanici bulunamadi";
             sayfasec="borcsorgu";
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public String sorguborc(){
        return "borcsorgu1";
    }
    
    public String sorgukul(){
        return "kullanimsorgu1";
    }
    
    public String aboneolsayfa(){
        return "aboneolustur";
    }
    
    public String abonesorgusay(){
        return "abonesorgu";
    }
    
    public String borcsorgusay(){
        return "borcsorgu";
    }
    
    public String kullanimsorgusay(){
        return "kullanimsorgu";
    }
    
    public String anasayfa(){
        return "main";
    }
    
    
   
}
