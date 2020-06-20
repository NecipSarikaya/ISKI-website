/*
 made it by orkun karakuş
 */
package com.ihtiyarlar.com;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.sql.*;
 
@ManagedBean(name="user")

@SessionScoped
public class Login{
 
   private String nickname;
   private String pass;
   private String name;
   private String surname;
   private String secici;
   private String mail;
   private String serx;
   private String hata = null;
   private int i = 0;
 
	//action listener event
    // public void method(ActionEvent event){}
    

    public void baglan()
    {
        PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("SELECT * FROM users");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            if(nickname.matches(rs.getString("nickname"))&&pass.matches(rs.getString("pass"))){
                secici = "main";
                name = rs.getString("name");
                surname = rs.getString("surname");
                i++;
                break;
            }
            else{
                secici = outcome();
                hata = "Kullanıcı Adı veya Şifre Hatalı !!";
            }
            }
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void uyeol()
    {
        PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps=con.prepareStatement("INSERT INTO users(nickname, pass, name, surname, mail) VALUES(?,?,?,?,?)");

            ps.setString(1, nickname);
            ps.setString(2, pass);
            ps.setString(3, name);
            ps.setString(4, surname);
            ps.setString(5, mail);
            ps.executeUpdate();
           secici = outcome();
           hata = "Üyelik Tamam";
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void forgot()
    {
        PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/iskiver","KRAL","kral");
            ps = con.prepareStatement("UPDATE users SET pass = ? WHERE nickname = ?");

    // set the preparedstatement parameters
    ps.setString(1,"123");
    ps.setString(2,nickname);
    ps.executeUpdate();
    ps.close();
           secici = outcome();
           hata = "Şifreniz '123' olarak sıfırlanmıştır";
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
	public String outcome(){
            return "index";
	}
        
        public String secici() {
		return secici;
	}
        
        public String exit() {
                hata = "";
		return "index";
	}
        
        public String serx() {
            serx = "signup";
		return serx;
	}
 
	public String getNickname() {
		return nickname;
	}
 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
        
        public String getPass() {
		return pass;
	}
 
	public void setPass(String pass) {
		this.pass = pass;
	}
        
        public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
        
        public String getsurname() {
		return surname;
	}
 
	public void setsurname(String surname) {
		this.surname = surname;
	}
        
        public String getMail() {
		return mail;
	}
 
	public void setMail(String mail) {
		this.mail = mail;
	}
        
        public String getHata() {
		return hata;
	}
}




