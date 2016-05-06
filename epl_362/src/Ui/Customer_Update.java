package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.google.gson.Gson;

import Client.Customer;

import java.io.*;
import java.net.*;

public class Customer_Update extends JFrame {

	private JPanel frame;
	private JTextField pn_tb;
	private JTextField db_tb;
	private JTextField fn_tb;
	private JTextField ln_tb;
	private JTextField ci_tb;
	private JTextField em_tb;
	private JLabel title;
	private String COMANT;
	private String CLIENT_ID;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String DATE_OF_BIRTH;
	private String PHONE_NUMBER;
	private String EMAIL;

	public Customer_Update(String CLIENT_ID) {
		this.CLIENT_ID = CLIENT_ID;
		initialize();
	}

	public void showUserInterface(boolean set) {
		setVisible(set);
	}

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

		title = new JLabel("Update Customer");
		title.setBounds(154, 12, 139, 15);
		frame.add(title);

		/** connect with server to take data **/
		COMANT = "getCustomerData";

		Gson gson = new Gson();
		Customer result = new Customer(COMANT, this.CLIENT_ID);
		String jsonout = gson.toJson(result);

		String customer_data = null;

		Socket clientSocket;
		try {
			clientSocket = new Socket("10.16.4.175", 2000);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes(jsonout + '\n');
			customer_data = inFromServer.readLine();
            
			Customer serverJson = new Customer();
			 

			serverJson = gson.fromJson(customer_data, Customer.class);
			clientSocket.close();
			ci_tb.setText(serverJson.getCLIENT_ID());
			ln_tb.setText(serverJson.getLAST_NAME());
			fn_tb.setText(serverJson.getFIRST_NAME());
			db_tb.setText(serverJson.getDATE_OF_BIRTH());
			pn_tb.setText(serverJson.getPHONE_NUMBER());
			em_tb.setText(serverJson.getEMAIL());

			JButton btnUpdate = new JButton("Update");
			btnUpdate.setBounds(12, 238, 117, 25);
			frame.add(btnUpdate);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					CLIENT_ID = ci_tb.getText();
					LAST_NAME = ln_tb.getText();
					FIRST_NAME = fn_tb.getText();
					DATE_OF_BIRTH = db_tb.getText();
					PHONE_NUMBER = pn_tb.getText();
					EMAIL = em_tb.getText();

					/* elexos gia kena */
					if (CLIENT_ID.equals("") || LAST_NAME.equals("") || FIRST_NAME.equals("")
							|| DATE_OF_BIRTH.equals("") || PHONE_NUMBER.equals("") || EMAIL.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please fill all the fields", "Invalid update",
								JOptionPane.WARNING_MESSAGE);
					} else {
						COMANT = "updateCustomer";

						Customer result = new Customer(COMANT, CLIENT_ID, LAST_NAME, FIRST_NAME, DATE_OF_BIRTH,
								PHONE_NUMBER, EMAIL);
						String jsonout = gson.toJson(result);
						System.out.print(jsonout);

						try {
							
							Socket clientSocket = new Socket("10.16.4.175", 2000);
							DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
							 
							outToServer.writeBytes(jsonout + '\n');
							clientSocket.close();
							// String modifiedSentence;
							// modifiedSentence = inFromServer.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COMANT="delCustomer";
					Gson gson = new Gson();
					Customer result = new Customer(COMANT, CLIENT_ID);
					String jsonout = gson.toJson(result);
					try {
						Socket clientSocket = new Socket("10.16.4.175", 2000);
						DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

						outToServer.writeBytes(jsonout + '\n');
						clientSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnDelete.setBounds(154, 238, 117, 25);
			frame.add(btnDelete);
			clientSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}