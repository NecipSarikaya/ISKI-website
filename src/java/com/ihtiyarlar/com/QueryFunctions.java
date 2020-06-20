/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihtiyarlar.com;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
 
@ManagedBean(name="query")

@SessionScoped

/**
 *
 * @author Orkun Karakuş
 */
public class QueryFunctions {
    
    private ArrayList<table> yaziListesi;
    private ArrayList<table> reqListesi;
    private int subno,reqno,i=0,j=0,penp,penno;
    private String hata;
    private String request,penalty;
    private int subnoreq;
    private boolean status,tempstatus;
    private long millis=System.currentTimeMillis();
    java.sql.Date date=new java.sql.Date(millis);
    
    
    
        @PostConstruct
        public void init(){
        yaziListesi = new ArrayList<table>();
        PreparedStatement ps=null;
        Connection con=null;
        i=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subpay");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                if(subno == rs.getInt("subno")){
                    if(rs.getString("paymethod").equals("Kredi")){
                    yaziListesi.add(new table(rs.getInt("subno"), rs.getInt("price"), "Kredi Kartı", rs.getInt("usage")));
                }
                    else{
                        yaziListesi.add(new table(rs.getInt("subno"), rs.getInt("price"), rs.getString("paymethod"), rs.getInt("usage")));
                    }
                  
                i++;
                }          
            }
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
        
        private static Map<String, Object> sublistValue;
    static
    {
        
               PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subscribers");
            ResultSet rs=ps.executeQuery();
            sublistValue = new LinkedHashMap<String, Object>();
            while(rs.next()){
         sublistValue.put(String.valueOf(rs.getInt("subno")),rs.getInt("subno"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
               
        
    }
    
    public Map<String, Object> getSublistValue()
    {
        return sublistValue;
    }
    
        
        public void findreq(){
            reqListesi = new ArrayList<table>();
        PreparedStatement ps=null;
        Connection con=null;
        i=0;
        String temp;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subrequests");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                if(subno == rs.getInt("subno")){
                    if("saatariza".equals(rs.getString("request"))){
                       temp = "Saat Arızası";
                    }
                    else if("sebekeariza".equals(rs.getString("request"))){
                        temp = "Şebeke Arızası";
                    }
                    else {
                        temp = "Diğer Arızalar";
                    }
                reqListesi.add(new table(rs.getInt("reqno"), rs.getInt("subno"), temp, rs.getBoolean("status")));  
                i++;
                }          
            }
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
    
    
    
        public void makereq(){
            PreparedStatement ps=null;
        Connection con=null;
        i=0;
        j=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subrequests");
            ResultSet rs=ps.executeQuery();
            
            j++;
            while(rs.next()){
                reqno = rs.getInt("reqno");
                i++;
            }
            if(i==0){
                reqno = 1;
            }
            else{
                reqno++;
            }
            ps=con.prepareStatement("INSERT INTO subrequests(reqno, subno, request, status) VALUES(?,?,?,?)");
            
            ps.setInt(1, reqno);
            ps.setInt(2, subnoreq);
            ps.setString(3, request);
            ps.setBoolean(4, status);
            ps.executeUpdate();
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
        
        public void payquery(){
            init();
        }
        
        
        public void makepenalty(){
        PreparedStatement ps=null;
        Connection con=null;
        i=0;  
        j=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subpenalty");
            ResultSet rs=ps.executeQuery();
            j++;
            while(rs.next()){
                penno = rs.getInt("penno");
                i++;
            }
            if(i==0){
                penno = 1;
            }
            else{
                penno++;
            }
            ps=con.prepareStatement("INSERT INTO subpenalty(subno, penp, pens, kesimdate, paydate, paymethod, penno, penreason) VALUES(?,?,?,?,?,?,?,?)");
           if(penalty.equals("kacakuse")){
               penp = 3000;
           }
           else if(penalty.equals("kurcalama")){
               penp = 1500;
           }
           else{
               penp = 644;
           }
           
            ps.setInt(1, subnoreq);
            ps.setInt(2, penp);
            ps.setBoolean(3, false);
            ps.setDate(4, date);
            ps.setDate(5, null);
            ps.setString(6, null);
            ps.setInt(7, penno);
            ps.setString(8, penalty);
            ps.executeUpdate();       
            }
            
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
        
         public ArrayList<table> getYaziListesi() {
            return yaziListesi;
        }


        public void setYaziListesi(ArrayList<table> yaziListesi) {
            this.yaziListesi = yaziListesi;
        }
        
          public ArrayList<table> getreqListesi() {
            return reqListesi;
        }


        public void setreqListesi(ArrayList<table> reqListesi) {
            this.reqListesi = reqListesi;
        }
        
        public String pastpayquerypage(){
            if(i!=0){
                return "pastpay";
            }
            else{
                hata = "Abone Numarası Yanlış Veya Bu Abonenin Hiç Ödeme Kaydı Yok !!";
                return "sorgula";
            }
        }
        
        public String makereqpage(){
            if(j!=0){
                return "requests";
            }
            else{
                hata = "Abone Numarası Yanlış !!";
                return "requests";
            }
        }
        
        public String penaltypage(){
            if(j!=0){
                hata = "Başarı İle Girildi";
            }
            else{
                hata = "Başarısız";
            }
            return "penalty";
        }
        
        public String reqtablepage(){
            return "reqtable";
        }
        
        public String findreqpage(){
            if(i!=0){
                return "reqtable";
            }
            else{
                hata = "Abone Numarası Yanlış !!";
                return "requests";
            }
        }
        
        
        public void changereqstatus(){
            PreparedStatement ps=null;
        Connection con=null;
        String tempreqno;
        FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = 
         fc.getExternalContext().getRequestParameterMap();
      tempreqno =  params.get("reqcek"); 
      reqno = Integer.parseInt(tempreqno);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM subrequests");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(reqno == rs.getInt("reqno")){
                    if(rs.getBoolean("status")== true){
                        tempstatus = false;
                    }
                    else{
                        tempstatus = true;
                    }
                }
                i++;
            }
            ps=con.prepareStatement("UPDATE subrequests SET status=? WHERE reqno=?");
             ps.setInt(2, reqno);
              ps.setBoolean(1, tempstatus);
              ps.executeUpdate();
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
        findreq();
        }
        
        public String pastpayquery(){
            hata = "";
            return "sorgula";
        }
        
        public String reqquerypageopener(){
            hata = "";
            return "requests";
        }
        
        public int getSubno(){
            return subno;
        }
        
        public void setSubno(int subno){
            this.subno = subno;
        }
        
        public String gethata(){
            return hata;
        }
        
        public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
        
        public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
        
        public int getSubnoreq() {
		return subnoreq;
	}

	public void setSubnoreq(int subnoreq) {
		this.subnoreq = subnoreq;
	}
        
}