
import java.sql.*;

import Dada_Base_Connection.DBConnection;
import Server.MultiThreadedSocketServer;
import Ui.Customer_Insert_frame;
import test.test_db;

 


public class main {

	
    public static void main (String[] args){ 
    	
//    	Customer_Insert_frame ff=new Customer_Insert_frame();
    	
//    	ff.showUserInterface(true);
 
    	 
//    	test_db test=new test_db();
//    	test.test();
    	
    	
    	Connection conn = DBConnection.getDBConnection();
    	MultiThreadedSocketServer server = new MultiThreadedSocketServer(conn);  
    	
    	// gra4e stin vasi pos eklisen o server
    	
       
        
         
    } 
}