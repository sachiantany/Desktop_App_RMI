/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_login;


import Form.Form_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author Administrator
 */
public class Detail_Main {
    public Detail_Main() throws RemoteException{
        super();
    }
    
     public static void main(String[] args) throws RemoteException, AlreadyBoundException, IOException{
     Registry registry = LocateRegistry.createRegistry(1800);
     //Work work =  new Work();
     //Login login = new Login();
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Login().setVisible(true);
            }
        });
             
     Detail_Impl di = new Detail_Impl();
     registry.rebind("db", di);
         System.out.println("Server is running");
     }
        
}
