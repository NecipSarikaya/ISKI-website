import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class baraj {
    public String isim,name,endoluname;
    public boolean ac = false,ac2=false;
    public int occu,endolumiktar,namemiktar;
    public List<String> istenenlistesi = new ArrayList<>();
    public List<baraj> istenenlistesi2 = new ArrayList<>();

    public boolean getAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }
    
        public boolean getAc2() {
        return ac2;
    }

    public void setAc2(boolean ac2) {
        this.ac2 = ac2;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getNamemiktar() {
        return namemiktar;
    }

    public void setNamemiktar(int namemiktar) {
        this.namemiktar = namemiktar;
    }    

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getOccu() {
        return occu;
    }

    public void setOccu(int occu) {
        this.occu = occu;
    }
    
    public String getEndoluname(){
        return endoluname;
    }
    
    public void setEndoluname(String endoluname) {
	this.endoluname = endoluname;
    }
    
    public int getEndolumiktar(){
        return endolumiktar;
    }
    
    public void setEndoluname(int endolumiktar) {
	this.endolumiktar = endolumiktar;
    }
    
    public void setİstenenlistesi(List<String> istenenlistesi) {
	this.istenenlistesi = istenenlistesi;
    }
    
    public List<String> getİstenenlistesi(){
        return istenenlistesi;
    }
    
     public void setİstenenlistesi2(List<baraj> istenenlistesi2) {
	this.istenenlistesi2 = istenenlistesi2;
    }
    
    public List<baraj> getİstenenlistesi2(){
        return istenenlistesi2;
    }    
    
Connection conn=null;
PreparedStatement stmt= null;
ResultSet rs = null;

    public List<String> liste(){    
        try{
    istenenlistesi.clear();
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT damname FROM dams ORDER BY damname DESC");
    rs=stmt.executeQuery();
    while(rs.next()){
        istenenlistesi.add(rs.getString("damname"));
    }
    }catch(Exception e){
    }     
        return istenenlistesi;
    }
    
    public void listeyial(){    
        try{
    istenenlistesi.clear();
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT occurate FROM dams WHERE damname ='"+name+"'");
    rs=stmt.executeQuery();
    ac=true;
    while(rs.next()){
        namemiktar=rs.getInt("occurate");
    }
    }catch(Exception e){
    }    
    }
    
    public List<baraj> getHepsi(){
        try{
    istenenlistesi2.clear();
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT * FROM dams ORDER BY damname DESC");
    rs=stmt.executeQuery();
    while(rs.next()){
        baraj b = new baraj();
        b.setIsim(rs.getString("damname"));
        b.setOccu(rs.getInt("occurate"));
        istenenlistesi2.add(b);
    }
    }catch(Exception e){
    }     
        return istenenlistesi2;
    }
     
    public void endolu(){
      try{               
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT * FROM dams ORDER BY occurate DESC FETCH NEXT 1 ROWS ONLY");
    rs=stmt.executeQuery();
    while(rs.next()){
    endoluname = rs.getString("damname");
    endolumiktar = rs.getInt("occurate");
        }
    ac2=true;
    }catch(Exception e){
        }
    }
    public String yon(){
        return "tumbarajlar";
    }
    public String yon1(){
        return "altyapii";
    }public String yon2(){
        return "ayricaliki";
    }public String yon3(){
        return "kullanimgiri";
    }public String yon4(){
        return "baraji";
    }
    public String yon5(){
        return "tumaltyapilar";
    }
}
