package Ui;
import java.sql.*;

import Client.Session;
import Ui.*;

public class main {
	 
	public static  Session user_ss;
	
    public static void main (String[] args){ 
    
    	LoginPage user_login = new LoginPage ();
    	user_login.showUserInterface(true);
    	
    } 
}