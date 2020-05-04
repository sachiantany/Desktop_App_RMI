/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_login;


import Form.Form_Dashboard;
import Form.Form_Login;
import Form.Form_showDetails;
import Form.Form_showDetailsForUsers;
import com.mysql.cj.xdevapi.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author Administrator
 */
public class Detail_Impl extends UnicastRemoteObject implements Detail_Interface{

    public Detail_Impl() throws RemoteException {
		super();
      
	}
    
    
   
    /*
     * method to retrieve sensor readings and other details of all sensors
     */
            
   /* @Override
    public String getAllSensorDetails(){
        return null;
    }
    
    /*
     * used to retrieve authenticate Admin login credentials
     */

   /*@Override
    public String loginValidator(String email, String password) {
       
        
        return null;     
    }*/
    /*
     * used to add data
   
     */
   
    @Override
    public String addSensor(String sensorname,String floor, String room){
        try {
           // Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/fire_alarm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");    
            java.sql.Statement st = con.createStatement();
          
           
            String sql = "insert into monitoring (sensorname,floor,room) value('"+sensorname+"','"+floor+"','"+room+"')";
            st.executeUpdate(sql);   
            JOptionPane.showMessageDialog(null, "Insert Success!");
            return "Added"; 
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    
    }

    @Override
    public String updateSensor(int id, String sensorname,String floor, String room) {
         try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/fire_alarm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");    
            java.sql.Statement st = con.createStatement();
            String sql = "update monitoring set sensorname = '"+sensorname+"',floor = '"+floor+"',room = '"+room+"'where id ="+id;
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Update Success!");
            return "updated"; 
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    @Override
    public String deleteSensor(int id) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/fire_alarm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            java.sql.Statement st = con.createStatement();
            String sql = "delete from monitoring where id = "+id;            
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Delete Success!");
            return "deleted"; 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    @Override
    public String login(String username, String password) throws RemoteException {
        try {
            PreparedStatement ps;
            ResultSet rs;
            
            Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/fire_alarm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");    
            String sql = "select * from admin where username = ? AND password = ?";
             
            ps = con.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if( username.equals("admin") && password.equals("admin"))
            {
                    JOptionPane.showMessageDialog(null, "Admin Logged in Success!");
                    Form_Dashboard fdash = new Form_Dashboard();
                    fdash.setVisible(true);
                    fdash.pack();
                    fdash.setLocationRelativeTo(null);
                    fdash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
         }else if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Logged in Success!");
                    Form_showDetailsForUsers fshow = new Form_showDetailsForUsers();
                    fshow.setVisible(true);
                    fshow.pack();
                    fshow.setLocationRelativeTo(null);
                    fshow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password!");
            }
            return "success";
        } catch (SQLException ex) {
            Logger.getLogger(Form_Login.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }   
}
