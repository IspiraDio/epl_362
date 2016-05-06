package JUnit;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSocket {

	String sentence;
	String modifiedSentence;
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	Socket clientSocket;
	
	@Before
	public void before() {
		
 
		try {
			clientSocket = new Socket("10.16.4.175", 2000);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@After
	public void after() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCommunication() {

		
		try {	
			
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			sentence = "{\"COMANT\":\"JUnit test\"}";
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();
			
			assertEquals("","JUnit test",modifiedSentence);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	


	

}
