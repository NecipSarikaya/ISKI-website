import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class altyapij {
    
    public List<String> istenenlistesi = new ArrayList<>();
    public List<altyapij> istenenlistesi2 = new ArrayList<>();
    public String istenen;
    public boolean varmi;
    public boolean calisiyormu;
    public String varmis;
    public String calisiyormus;
    public boolean ac = false;
    
     public boolean getAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }
    
    public String getVarmis() {
        return varmis;
    }

    public void setVarmis(String varmis) {
        this.varmis = varmis;
    }

    public String getCalisiyormus() {
        return calisiyormus;
    }

    public void setCalisiyormus(String calisiyormus) {
        this.calisiyormus = calisiyormus;
    }

    public boolean isVarmi() {
        return varmi;
    }

    public void setVarmi(boolean varmi) {
        this.varmi = varmi;
    }

    public boolean isCalisiyormu() {
        return calisiyormu;
    }

    public void setCalisiyormu(boolean calisiyormu) {
        this.calisiyormu = calisiyormu;
    }

    public void setIstenen(String istenen) {
        this.istenen = istenen;
    }

    public String getIstenen() {
        return istenen;
    }
    
    public void setİstenenlistesi(List<String> istenenlistesi) {
	this.istenenlistesi = istenenlistesi;
    }
    
    public List<String> getİstenenlistesi(){
        return istenenlistesi;
    }
    
    Connection conn=null;
    PreparedStatement stmt= null;
    ResultSet rs = null;
    
        public List<String> liste(){    
        try{
    istenenlistesi.clear();
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT ilce FROM altyapi ORDER BY ilce ASC");
    rs=stmt.executeQuery();
    while(rs.next()){
        istenenlistesi.add(rs.getString("ilce"));
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
    stmt=conn.prepareStatement("SELECT varmi,calisiyormu FROM altyapi WHERE ilce ='"+istenen+"'");
    rs=stmt.executeQuery();
    ac=true;
    while(rs.next()){
        varmi=rs.getBoolean("varmi");
        if(varmi==true){
            varmis="Evet";
        }else{
            varmis="Hayır";
        }
        calisiyormu=rs.getBoolean("calisiyormu");
        if(calisiyormu==true){
            calisiyormus="Evet";
        }else{
            calisiyormus="Hayır";
        }
    }
    }catch(Exception e){
    }     
    }
    
    public List<altyapij> getHepsi(){
        try{
    istenenlistesi2.clear();
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL" ,"kral");
    stmt=conn.prepareStatement("SELECT * FROM altyapi ORDER BY ilce ASC");
    rs=stmt.executeQuery();
    while(rs.next()){
        altyapij a = new altyapij();
        a.setIstenen(rs.getString("ilce"));
        varmi=rs.getBoolean("varmi");
        if(varmi==true){
            a.setVarmis("Evet");
        }else{
            a.setVarmis("Hayır");
        }
         calisiyormu=rs.getBoolean("calisiyormu");
        if(calisiyormu == true){
            a.setCalisiyormus("Evet");
        }else{
            a.setCalisiyormus("Hayır");
        }
        
        istenenlistesi2.add(a);
    }
    }catch(Exception e){
    }     
        return istenenlistesi2;
    }
    
}
