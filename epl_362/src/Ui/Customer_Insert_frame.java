package Ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

 

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.google.gson.Gson;

import Client.Customer;


import java.io.*;
import java.net.*;
 
public class Customer_Insert_frame extends JFrame {

	private JPanel frame;
	private JTextField pn_tb;
	private JTextField db_tb;
	private JTextField fn_tb;
	private JTextField ln_tb;
	private JTextField ci_tb;
	private JTextField em_tb;
	private JLabel title;

 
	public Customer_Insert_frame() {		
		initialize();
		 
	}
	
	public void showUserInterface(boolean set){
         setVisible(set);
    }
	
	
	 
	/**
	 * Create the frame.
	 */
	private void initialize() {
		
 
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		frame.setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(12, 53, 95, 15);
		frame.add(lblCustomerId);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(12, 80, 90, 15);
		frame.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(12, 107, 90, 15);
		frame.add(lblFirstName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(12, 134, 95, 15);
		frame.add(lblDateOfBirth);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(12, 161, 110, 15);
		frame.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 188, 70, 15);
		frame.add(lblEmail);
		
		pn_tb = new JTextField();
		pn_tb.setBounds(140, 159, 114, 19);
		frame.add(pn_tb);
		pn_tb.setColumns(10);
		
		db_tb = new JTextField();
		db_tb.setBounds(140, 132, 114, 19);
		frame.add(db_tb);
		db_tb.setColumns(10);
		
		fn_tb = new JTextField();
		fn_tb.setBounds(140, 105, 114, 19);
		frame.add(fn_tb);
		fn_tb.setColumns(10);
		
		ln_tb = new JTextField();
		ln_tb.setBounds(140, 78, 114, 19);
		frame.add(ln_tb);
		ln_tb.setColumns(10);
		
		ci_tb = new JTextField();
		ci_tb.setBounds(140, 51, 114, 19);
		frame.add(ci_tb);
		ci_tb.setColumns(10);
		
		em_tb = new JTextField();
		em_tb.setBounds(140, 186, 114, 19);
		frame.add(em_tb);
		em_tb.setColumns(10);
		
		title = new JLabel("Insert Customer");
		title.setBounds(154, 12, 139, 15);
		frame.add(title);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String CLIENT_ID=ci_tb.getText();
				String LAST_NAME=ln_tb.getText();
				String FIRST_NAME=fn_tb.getText();
				String DATE_OF_BIRTH=db_tb.getText();
				String PHONE_NUMBER=pn_tb.getText();
				String EMAIL=em_tb.getText();
				
				/* elexos gia kena */
				
				String COMANT="insertCustomer";
				
				Gson gson = new Gson();
				Customer result = new Customer(COMANT,CLIENT_ID,LAST_NAME,FIRST_NAME,DATE_OF_BIRTH,PHONE_NUMBER,EMAIL);
				String jsonout = gson.toJson(result);
				
				System.out.println(jsonout);
			
				
				  String sentence;
				  String modifiedSentence;
				  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
				  Socket clientSocket;
				try {
				  clientSocket = new Socket("10.16.4.175", 2000);
				
				  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				  sentence = jsonout;
				  outToServer.writeBytes(sentence + '\n');
				  modifiedSentence = inFromServer.readLine();
				  System.out.println("FROM SERVER: " + modifiedSentence);
				  clientSocket.close();
				  
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnInsert.setBounds(12, 238, 117, 25);
		frame.add(btnInsert);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci_tb.setText("");
				ln_tb.setText("");
				fn_tb.setText("");
				db_tb.setText("");
				pn_tb.setText("");
				em_tb.setText("");
			}
		});
		btnClear.setBounds(154, 238, 117, 25);
		frame.add(btnClear);
	}
}
