package JUnitServer;

 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Server.MultiThreadedSocketServer;

public class TestSocket {

	MultiThreadedSocketServer server=null;
	
	@Before
	public void before() { 
			server = new MultiThreadedSocketServer();  		
	}

	@After
	public void after() {		 
			server.CloseServer();		 
	}
	
	@Test
	public void testServer() {	 
		server.CheckServer();
	}
	


	

}
