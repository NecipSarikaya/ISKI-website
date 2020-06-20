import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class kullanimgirj {
    
    int subno;
    int price;
    int usage;
    String mesaj;

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
 
    
    public int getSubno(){
        return subno;
    }
    
    public void setSubno(int subno) {
	this.subno = subno;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price) {
	this.price = price;
    }
    
    public int getUsage(){
        return usage;
    }
    
    public void setUsage(int usage) {
	this.usage = usage;
    }
    
    Connection conn=null;
    PreparedStatement stmt= null;
    ResultSet rs = null;
    
    public void kullanimgir(){
        try{
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);
    
    stmt=conn.prepareStatement("SELECT * FROM subscribers WHERE SUBNO ="+subno+"");
    rs=stmt.executeQuery();
        if(rs.next()){
        }else{
    mesaj="Bu Numarada Bir Abone Yok";
        return;
            }
    
    stmt=conn.prepareStatement("INSERT INTO subpay(subno,price,kesimdate,status,usage) VALUES("+subno+","+price+",'"+date+"',FALSE,"+usage+")");
    stmt.executeUpdate();
    mesaj="Abonenin Borcu Sisteme Girilmiştir";
    }catch(Exception e){    
        try{
    long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis); 
    stmt=conn.prepareStatement("UPDATE subpay SET price= "+price+", usage ="+usage+" WHERE SUBNO ="+subno+" AND kesimdate = '"+date+"'");
    stmt.executeUpdate();
    mesaj="Abonenin Borcu Güncellenmiştir";
        }catch(Exception ex){ 
        }
    }
    }
    
}

