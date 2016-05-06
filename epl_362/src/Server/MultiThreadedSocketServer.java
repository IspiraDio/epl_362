package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MultiThreadedSocketServer {

	ServerSocket myServerSocket;
	boolean ServerOn = true;

	Connection conn = null;
	 
	 
	

	public MultiThreadedSocketServer(Connection conn) {
		try {
			this.conn = conn;
			 
			myServerSocket = new ServerSocket(2000);
		} catch (IOException ioe) {
			System.out.println("Could not create server socket on port 1000. Quitting.");
			System.exit(-1);
		}

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println("It is now : " + formatter.format(now.getTime()));

		ArrayList<ClientServiceThread> list_cliThread = new ArrayList<>();
		int i = 0;

		// Successfully created Server Socket. Now wait for connections.
		while (ServerOn) {

			try {
				// Accept incoming connections.
				Socket clientSocket = myServerSocket.accept();

				// accept() will block until a client connects to the server.
				// If execution reaches this point, then it means that a client
				// socket has been accepted.

				// For each client, we will start a service thread to
				// service the client requests. This is to demonstrate a
				// Multi-Threaded server. Starting a thread also lets our
				// MultiThreadedSocketServer accept multiple connections
				// simultaneously.

				// Start a Service thread

				ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
				cliThread.start();
				

				list_cliThread.add(cliThread);
				// list_cliThread.get(0)

				System.out.println("*** Clid id: " + cliThread.getId() + "\n*** Clid name: " + cliThread.getName());

			} catch (IOException ioe) {
				System.out.println("Exception encountered on accept. Ignoring. Stack Trace :");
				ioe.printStackTrace();
			}

			if (list_cliThread.size() > 3) {
				for (int j = 0; j < i; j++) {
					System.out.println(list_cliThread.get(j));

				}
			}

		}

		try {
			myServerSocket.close();
			System.out.println("Server Stopped");
		} catch (Exception ioe) {
			System.out.println("Problem stopping server socket");
			System.exit(-1);
		}
	}

	class ClientServiceThread extends Thread {

		Socket myClientSocket;
		boolean m_bRunThread = true;

		public ClientServiceThread() {
			super();
		}

		ClientServiceThread(Socket s) {
			myClientSocket = s;

		}

		public void run() {
			// Obtain the input stream and the output stream for the socket
			// A good practice is to encapsulate them with a BufferedReader
			// and a PrintWriter as shown below.
			BufferedReader in = null;
			PrintWriter out = null;
			String to_do;

			// Print out details of this connection
			System.out
					.println("Accepted Client Address - " + myClientSocket.getInetAddress().getHostName() + " MAC - ");

			try {
				in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream()));

				// At this point, we can read for input and reply with
				// appropriate output.

				// Run in a loop until m_bRunThread is set to false
				while (m_bRunThread) {
					// read incoming stream
					String clientCommand = in.readLine();
                    
					

//					System.out.println("  Client Says :" + clientCommand);

					if (!ServerOn) {
						// Special command. Quit this thread
						System.out.print("Server has already stopped");
						out.println("Server has already stopped");
						out.flush();
						m_bRunThread = false;

					}

					if (clientCommand != null) {
						String[] clientArgv = clientCommand.split(" ");
						
						Gson gson = new Gson();
						String cmd = gson.fromJson(clientCommand,Comant.class).getCOMANT();
						System.out.println(clientCommand);
						System.out.println(cmd);
					    if(cmd.equals("getCustomerData")){					    	
					    	Customer customer=new Customer();
					    	customer.get_client(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID());   	
					    	out.println(gson.toJson(customer));
							out.flush();
					    }
					    else if(cmd.equals("insertCustomer")){					    	
					    	Customer customer=new Customer(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID(), gson.fromJson(clientCommand,Customer.class).getLAST_NAME(),gson.fromJson(clientCommand,Customer.class).getFIRST_NAME(),gson.fromJson(clientCommand,Customer.class).getDATE_OF_BIRTH(), gson.fromJson(clientCommand,Customer.class).getPHONE_NUMBER(),gson.fromJson(clientCommand,Customer.class).getEMAIL());				    	
					    	out.println(customer.insert_client());
							out.flush();
					    }
					    else if(cmd.equals("updateCustomer")){
					    	
					    	Customer customer=new Customer(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID(), gson.fromJson(clientCommand,Customer.class).getLAST_NAME(), gson.fromJson(clientCommand,Customer.class).getFIRST_NAME(), gson.fromJson(clientCommand,Customer.class).getDATE_OF_BIRTH(), gson.fromJson(clientCommand,Customer.class).getPHONE_NUMBER(), gson.fromJson(clientCommand,Customer.class).getEMAIL());   	
					    	out.println(customer.update_client());
							out.flush();
					    }
					    else  if(cmd.equals("getStaffData")){					    	
					    	Staff staff=new Staff();
					    	staff.get_staff(gson.fromJson(clientCommand,Staff.class).getSTAFF_ID());   	
					    	out.println(gson.toJson(staff));
							out.flush();
					    }
					    else if(cmd.equals("insertStaff")){		
					    	
					    	Staff staff=new Staff(gson.fromJson(clientCommand,Staff.class).getSTAFF_ID(),gson.fromJson(clientCommand,Staff.class).getFIRST_NAME(),gson.fromJson(clientCommand,Staff.class).getLAST_NAME(),gson.fromJson(clientCommand,Staff.class).getDATE_OF_BIRTH(),gson.fromJson(clientCommand,Staff.class).getPHONE_NUMBER(),gson.fromJson(clientCommand,Staff.class).getEMAIL(),gson.fromJson(clientCommand,Staff.class).getADDRESS(),gson.fromJson(clientCommand,Staff.class).getROLE());				    	
					    	out.println(staff.insert_staff());
							out.flush();
					    }
					    else if(cmd.equals("updateStaff")){
					    	
					    	Staff staff=new Staff(gson.fromJson(clientCommand,Staff.class).getSTAFF_ID(),gson.fromJson(clientCommand,Staff.class).getFIRST_NAME(),gson.fromJson(clientCommand,Staff.class).getLAST_NAME(),gson.fromJson(clientCommand,Staff.class).getDATE_OF_BIRTH(),gson.fromJson(clientCommand,Staff.class).getPHONE_NUMBER(),gson.fromJson(clientCommand,Staff.class).getEMAIL(),gson.fromJson(clientCommand,Staff.class).getADDRESS(),gson.fromJson(clientCommand,Staff.class).getROLE());   	
					    	out.println(staff.update_staff());
							out.flush();
					    }
					    else  if(cmd.equals("getAppointmentData")){					    	
					    	Appointment appointment=new Appointment();
					    	appointment.get_appointment(gson.fromJson(clientCommand,Appointment.class).getAPPOINTMENT_ID());   	
 
					    	out.println(gson.toJson(appointment));
							out.flush();
							System.out.println(gson.toJson(appointment));
					    }
					    else if(cmd.equals("insertAppointment")){		
					    	
					    	Appointment appointment=new Appointment(gson.fromJson(clientCommand,Appointment.class).getCLIENT_ID(),gson.fromJson(clientCommand,Appointment.class).getSTAFF_ID(),gson.fromJson(clientCommand,Appointment.class).getBRANCH_ID(),gson.fromJson(clientCommand,Appointment.class).getDATE(),gson.fromJson(clientCommand,Appointment.class).getTIME(),gson.fromJson(clientCommand,Appointment.class).getATTEND());				    	
					    	out.println(appointment.insert_appointment());
							out.flush();
					    }
					    else if(cmd.equals("updateAppointment")){
					    	
					    	Appointment appointment=new Appointment(gson.fromJson(clientCommand,Appointment.class).getAPPOINTMENT_ID(),gson.fromJson(clientCommand,Appointment.class).getCLIENT_ID(),gson.fromJson(clientCommand,Appointment.class).getSTAFF_ID(),gson.fromJson(clientCommand,Appointment.class).getBRANCH_ID(),gson.fromJson(clientCommand,Appointment.class).getDATE(),gson.fromJson(clientCommand,Appointment.class).getTIME(),gson.fromJson(clientCommand,Appointment.class).getATTEND());   	
					    	out.println(appointment.update_appointment());
							out.flush();
					    }
					    else  if(cmd.equals("getBranchData")){					    	
					    	Branch branch=new Branch();
					    	branch.get_branch(gson.fromJson(clientCommand,Branch.class).getBRANCH_ID());   	
 
					    	out.println(gson.toJson(branch));
							out.flush();
					    }
					    else if(cmd.equals("insertBranch")){		
					    	
					    	Branch branch=new Branch(gson.fromJson(clientCommand,Branch.class).getBRANCH_ID(),gson.fromJson(clientCommand,Branch.class).getCITY(),gson.fromJson(clientCommand,Branch.class).getSTREET_NAME(),gson.fromJson(clientCommand,Branch.class).getZIP_CODE(), gson.fromJson(clientCommand,Branch.class).getCOUNTRY());				    	
					    	out.println(branch.insert_branch());
							out.flush();
					    }
					    else if(cmd.equals("updateBranch")){
					    	
					    	Branch branch=new Branch(gson.fromJson(clientCommand,Branch.class).getBRANCH_ID(),gson.fromJson(clientCommand,Branch.class).getCITY(),gson.fromJson(clientCommand,Branch.class).getSTREET_NAME(),gson.fromJson(clientCommand,Branch.class).getZIP_CODE(), gson.fromJson(clientCommand,Branch.class).getCOUNTRY());				    	
					    	out.println(branch.update_branch());
							out.flush();
					    }
					    else  if(cmd.equals("getConsultationData")){	
					  
					    	Consultation consultation=new Consultation();
					    	consultation.get_consultation(gson.fromJson(clientCommand,Consultation.class).getCONSULTATION_ID());   	
 
					    	out.println(gson.toJson(consultation));
							out.flush();
					    }
					    else if(cmd.equals("insertConsultation")){		
					    	
					    	Consultation consultation=new Consultation(gson.fromJson(clientCommand,Consultation.class).getDATE(),gson.fromJson(clientCommand,Consultation.class).getCLIENT_ID(),gson.fromJson(clientCommand,Consultation.class).getBRANCH_ID(),gson.fromJson(clientCommand,Consultation.class).getRECOMMENTATION(),gson.fromJson(clientCommand,Consultation.class).getATTEND(),gson.fromJson(clientCommand,Consultation.class).getSTAFF_ID());				    	
					    	out.println(consultation.insert_consultation());
							out.flush();
					    }
					    else if(cmd.equals("updateConsultation")){
					    	
					    	Consultation consultation=new Consultation(gson.fromJson(clientCommand,Consultation.class).getCONSULTATION_ID(),gson.fromJson(clientCommand,Consultation.class).getDATE(),gson.fromJson(clientCommand,Consultation.class).getCLIENT_ID(),gson.fromJson(clientCommand,Consultation.class).getBRANCH_ID(),gson.fromJson(clientCommand,Consultation.class).getRECOMMENTATION(),gson.fromJson(clientCommand,Consultation.class).getATTEND(),gson.fromJson(clientCommand,Consultation.class).getSTAFF_ID());				    	
					    	out.println(consultation.update_consultation());
							out.flush();
					    }
					    else  if(cmd.equals("login")){	
							  
					    	Login login=new Login(gson.fromJson(clientCommand,Login.class).getUSERNAME(),gson.fromJson(clientCommand,Login.class).getPASSWORD());
					    	
					    	out.println(login.checkLogin());
					    	out.println(gson.toJson(login));
							out.flush();
				 
					
					    }
					    else  if(cmd.equals("getAllCustomerData")){	
							  
					    	Customer customer=new Customer();	  
					    	out.println(customer.get_clients());
							out.flush();
					    }
					    else  if(cmd.equals("getAllStaffData")){	
							  
					    	Staff customer=new Staff();	  
					    	out.println(customer.get_staffs());
							out.flush();
					    }
					    else  if(cmd.equals("getAllConsultationData")){	
							  
					    	Consultation customer=new Consultation();	  
					    	out.println(customer.get_consultations());
							out.flush();
					    }
					    else  if(cmd.equals("getAllBranchData")){	
							  
					    	Branch customer=new Branch();	  
					    	out.println(customer.get_branchs());
							out.flush();
					    }
					    else  if(cmd.equals("getAllAppointmentData")){	
							  
					    	Appointment customer=new Appointment();	  
					    	out.println(customer.get_appointments());
							out.flush();
							 
					    }
					    else  if(cmd.equals("delAppointment")){	
							  
					    	Appointment customer=new Appointment();	  
					    	out.println(customer.del_appointment(gson.fromJson(clientCommand,Appointment.class).getAPPOINTMENT_ID()));
							out.flush();
					    }		
					    else  if(cmd.equals("delBranch")){					    	
					    	Branch branch=new Branch();  	  
					    	out.println(branch.del_branch(gson.fromJson(clientCommand,Branch.class).getBRANCH_ID()));
							out.flush();
					    }
					    else  if(cmd.equals("delCustomer")){					    	
					    	Customer customer=new Customer();						       	
						    out.println(customer.del_client(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID()));
							out.flush();
					    }
					    else  if(cmd.equals("delStaff")){					    	
					    	Staff staff=new Staff();					    	   	
					    	out.println(staff.del_staff(gson.fromJson(clientCommand,Staff.class).getSTAFF_ID()));
							out.flush();
					    }
					    else  if(cmd.equals("getAppointmentByClient")){					    	
					    	Appointment appointment=new Appointment();	
					    	out.println(appointment.get_appointment_by_customer(gson.fromJson(clientCommand,Appointment.class).getCLIENT_ID()));    
							out.flush();
							 
					    }
					    else  if(cmd.equals("getRecommendationByClient")){					    	
					    	Recommendation recommendation=new Recommendation();	
					    	out.println(recommendation.get_recommendations(gson.fromJson(clientCommand,Recommendation.class).getCLIENT_ID()));    
							out.flush();
							 
					    }
					    else  if(cmd.equals("getTransactionByClient")){					    	
					    	Transaction transaction=new Transaction();	
					    	out.println(transaction.get_transactions(gson.fromJson(clientCommand,Transaction.class).getCLIENT_ID()));    
							out.flush();
							 
					    }
					    else if(cmd.equals("insertTransaction")){		
					    	
					    	Transaction transaction=new Transaction(gson.fromJson(clientCommand,Transaction.class).getCLIENT_ID(),gson.fromJson(clientCommand,Transaction.class).getBRANCH_ID(),gson.fromJson(clientCommand,Transaction.class).getDATE(),gson.fromJson(clientCommand,Transaction.class).getAMOUTN());				    	
					    	out.println(transaction.insert_transaction());
							out.flush();
					    }
					    else  if(cmd.equals("downloadClient")){	
							  
					    	Customer customer=new Customer();	  
					    	out.println(customer.downdoled_clients(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID()));
							out.flush();
					    }
					    else  if(cmd.equals("getBranchMonthReport")){					    	
					    	Reports reports=new Reports();					    	 	
 					    	out.println(reports.get_branch_month());
							out.flush();
					    }
					    else  if(cmd.equals("getRecMonthReport")){					    	
					    	Reports reports=new Reports();					    	 	
 					    	out.println(reports.get_client_rec_month(gson.fromJson(clientCommand,Customer.class).getCLIENT_ID()));
							out.flush();
					    }
					    
					    
					    
					    
						
						System.out.println("  Client Says :" + clientCommand);
//						if (clientArgv[0].equalsIgnoreCase("quit")) {
//							// Special command. Quit this thread
//							m_bRunThread = false;
//							System.out.print("Stopping client thread for client : ");
//						} else if (clientArgv[0].equalsIgnoreCase("end")) {
//							// Special command. Quit this thread and Stop the
//							// Server
//							m_bRunThread = false;
//							System.out.print("Stopping client thread for client : ");
//							ServerOn = false;
//						} else {
//							// Process it
//							if (clientArgv[0].equalsIgnoreCase("myid")) {
//								out.println("ok");
//								out.flush();
//								String ip = myClientSocket.getInetAddress().getHostName();
//								String name = "temp_hose";
//								String description = "ok";
//								 
//								
//
//							} else if (clientArgv[0].equalsIgnoreCase("temp:")) {
//								
//								out.println("ok");
//								out.flush();
//								
//								
//							} else {
//								out.println("Server Says : " + clientArgv[0]);
//								out.flush();
//							}
//						 
//
//						}
						
					}
//					else {
//						// Special command. Quit this thread
//						m_bRunThread = false;
//						System.out.print("Stopping client thread for client : ");
//					}
				}
				System.out.println("false");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Clean up
				try {
					in.close();
					out.close();
					myClientSocket.close();
					System.out.println("...Stopped");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

	}
}