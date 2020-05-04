/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_login;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Administrator
 */
public interface Detail_Interface extends Remote{
    
   // public String getAllSensorDetails() throws RemoteException;

   // public String loginValidator(String email, String password) throws RemoteException;

    public String addSensor( String sensorname,String floor, String room) throws RemoteException;

    public String updateSensor(int id, String sensorname,String floor, String room) throws RemoteException;

    public String deleteSensor(int id) throws RemoteException;
    
    public String login(String username, String password) throws RemoteException;
    
    public void sendEmail()throws RemoteException ;

}
