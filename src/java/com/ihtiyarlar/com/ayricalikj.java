import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class ayricalikj {
    
    String subno;
    String subno2;
    String isim;
    String miktar;
    String sorgu;
    String mesaj;

    public String getSubno2() {
        return subno2;
    }

    public void setSubno2(String subno2) {
        this.subno2 = subno2;
    }


    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
    
    public String getSorgu(){
        return sorgu;
    }
    
    public void setSorgu(String sorgu) {
	this.sorgu = sorgu;
    }
    
    public String getSubno(){
        return subno;
    }
    
    public void setSubno(String subno) {
	this.subno = subno;
    }
    
    public String getİsim(){
        return isim;
    }
    
    public void setİsim(String isim) {
	this.isim = isim;
    }
    
    public String getMiktar(){
        return miktar;
    }
    
    public void setMiktar(String miktar) {
	this.miktar = miktar;
    }
    
    Connection conn=null;
    PreparedStatement stmt= null;
    ResultSet rs = null;
    
    public void ayricalikekle(){
        try{
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    
    stmt=conn.prepareStatement("UPDATE SUBSCRIBERS SET PRIV = '"+isim+" "+miktar+"' WHERE SUBNO ="+subno+"");
    stmt.executeUpdate();
    
    stmt=conn.prepareStatement("SELECT * FROM subscribers WHERE SUBNO ="+subno+"");
    rs=stmt.executeQuery();
        
        if(rs.next()){
    mesaj="Ayrıcalığınız Aboneye Eklendi";
        }else{
    mesaj="Bu Numarada Bir Abone Yok";
            }
        }catch(Exception e){}     
    }
    
    public void ayricaliksorgula(){
        try{
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT priv from SUBSCRIBERS WHERE SUBNO ="+subno2+"");
    rs=stmt.executeQuery();
    if(rs.next()){
    sorgu=rs.getString("priv");
    mesaj="Abonenin Ayrıcalığı:";
    }else{
        mesaj="Bu Numarada Bir Abone Yok";
    }
    }catch(Exception e){
    }     
    }
    
}
