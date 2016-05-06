package Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Customer;
import Client.Transaction;
import Client.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Legal_Rcords_Staff_Viewpoint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Legal_Rcords_Staff_Viewpoint frame = new Legal_Rcords_Staff_Viewpoint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Legal_Rcords_Staff_Viewpoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Legal records staff viewpoint");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 28, 232, 14);
		contentPane.add(lblNewLabel);

		JButton btnViewStaff = new JButton("View Staff");
		btnViewStaff.setBounds(10, 64, 111, 23);
		contentPane.add(btnViewStaff);

		JButton btnInsertStaff = new JButton("Insert Staff");
		btnInsertStaff.setBounds(10, 98, 111, 23);
		contentPane.add(btnInsertStaff);

		JButton btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.setBounds(10, 132, 111, 23);
		contentPane.add(btnUpdateStaff);

		JButton btnViewClients = new JButton("View Clients");
		btnViewClients.setBounds(153, 64, 111, 23);
		contentPane.add(btnViewClients);

		JButton btnInsertClient = new JButton("Insert Client");
		btnInsertClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInsertClient.setBounds(153, 98, 111, 23);
		contentPane.add(btnInsertClient);

		JButton btnUpdateClient = new JButton("Update Client");
		btnUpdateClient.setBounds(153, 132, 111, 23);
		contentPane.add(btnUpdateClient);

		JButton btnViewTransac = new JButton("View Transactions");
		btnViewTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewTransac.setBounds(295, 64, 156, 23);
		contentPane.add(btnViewTransac);

		JButton btnInsertTranc = new JButton("Insert Transaction");
		btnInsertTranc.setBounds(295, 98, 156, 23);
		contentPane.add(btnInsertTranc);

		btnInsertStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Staff_Insert_frame new_staff = new Staff_Insert_frame();
				new_staff.showUserInterface(true);

			}
		});
		btnUpdateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String staff_id = JOptionPane.showInputDialog("Insert Staff id");
				Staff_Update update_staff = new Staff_Update(staff_id);
				update_staff.showUserInterface(true);

			}
		});

		btnInsertClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer_Insert_frame custom = new Customer_Insert_frame();
				custom.showUserInterface(true);
			}
		});

		btnUpdateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String client_id = JOptionPane.showInputDialog("Insert Client id");
				Customer_Update custom_update = new Customer_Update(client_id);
				custom_update.showUserInterface(true);
			}
		});
		/* View staff */
		btnViewStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jsonout = "{\"COMANT\":\"getAllStaffData\"}";
				String staff_data = null;
				ArrayList<Staff> cc = new ArrayList<Staff>();
				Gson gson = new Gson();

				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					staff_data = inFromServer.readLine();

					while (!staff_data.equals("")) {

						Staff stff = new Staff(gson.fromJson(staff_data, Staff.class).getSTAFF_ID(),
								gson.fromJson(staff_data, Staff.class).getLAST_NAME(),
								gson.fromJson(staff_data, Staff.class).getFIRST_NAME(),
								gson.fromJson(staff_data, Staff.class).getDATE_OF_BIRTH(),
								gson.fromJson(staff_data, Staff.class).getPHONE_NUMBER(),
								gson.fromJson(staff_data, Staff.class).getEMAIL(),
								gson.fromJson(staff_data, Staff.class).getADDRESS(),
								gson.fromJson(staff_data, Staff.class).getROLE());
						cc.add(stff);
						staff_data = inFromServer.readLine();
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_Staff allStaff = new View_Staff(cc);
				allStaff.showUserInterface(true);
			}
		});
		/* View Clients */
		btnViewClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jsonout = "{\"COMANT\":\"getAllCustomerData\"}";
				String customer_data = null;
				ArrayList<Customer> cc = new ArrayList<Customer>();
				Gson gson = new Gson();

				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					customer_data = inFromServer.readLine();

					while (!customer_data.equals("")) {

						Customer cust = new Customer(gson.fromJson(customer_data, Customer.class).getCLIENT_ID(),
								gson.fromJson(customer_data, Customer.class).getLAST_NAME(),
								gson.fromJson(customer_data, Customer.class).getFIRST_NAME(),
								gson.fromJson(customer_data, Customer.class).getDATE_OF_BIRTH(),
								gson.fromJson(customer_data, Customer.class).getPHONE_NUMBER(),
								gson.fromJson(customer_data, Customer.class).getEMAIL());
						cc.add(cust);
						customer_data = inFromServer.readLine();
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_Clients allClients = new View_Clients(cc);
				allClients.showUserInterface(true);
			}
		});

		/* View Transactions */
		btnViewTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Client ID:");
				String COMANT = "getTransactionByClient";

				Gson gson = new Gson();
				Transaction result = new Transaction(COMANT, input);
				String jsonout = gson.toJson(result);

				String transaction = null;

				ArrayList<Transaction> cc = new ArrayList<Transaction>();
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					transaction = inFromServer.readLine();

					while (!transaction.equals("")) {

						Transaction trans = new Transaction(
								gson.fromJson(transaction, Transaction.class).getCLIENT_ID(),
								gson.fromJson(transaction, Transaction.class).getBRANCH_ID(),
								gson.fromJson(transaction, Transaction.class).getDATE(),
								gson.fromJson(transaction, Transaction.class).getAMOUNT());
						cc.add(trans);
						transaction = inFromServer.readLine();
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_Transactions allTransactions = new View_Transactions(cc);
				allTransactions.showUserInterface(true);
			}
		});

		btnInsertTranc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}

		});
	}
}
