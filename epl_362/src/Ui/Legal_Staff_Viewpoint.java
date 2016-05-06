package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Consultation;
import Client.Customer;
import Client.Session;
import Client.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Legal_Staff_Viewpoint extends JFrame {

	private JPanel contentPane;
	LoginPage u = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Legal_Staff_Viewpoint frame = new Legal_Staff_Viewpoint();
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
	public Legal_Staff_Viewpoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLegalStaffViewpoint = new JLabel("Legal staff viewpoint");
		lblLegalStaffViewpoint.setFont(new Font("Calibri Light", Font.BOLD, 13));
		lblLegalStaffViewpoint.setBackground(Color.DARK_GRAY);
		lblLegalStaffViewpoint.setBounds(10, 21, 177, 14);
		contentPane.add(lblLegalStaffViewpoint);

		JButton btnNewButton = new JButton("Insert Consultation");
		btnNewButton.setBounds(10, 90, 160, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Update Consultation");
		btnNewButton_1.setBounds(10, 124, 160, 23);
		contentPane.add(btnNewButton_1);

		JButton btnViewConsultations = new JButton("View Consultations");
		btnViewConsultations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewConsultations.setBounds(10, 56, 160, 23);
		contentPane.add(btnViewConsultations);

		JButton btnViewClients = new JButton("View Clients");
		btnViewClients.setBounds(213, 56, 167, 23);
		contentPane.add(btnViewClients);

		JButton btnNewButton_2 = new JButton("Download Clients Data");
		btnNewButton_2.setBounds(213, 90, 167, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Upload Clients Data");
		btnNewButton_3.setBounds(211, 124, 169, 23);
		contentPane.add(btnNewButton_3);

		/* Insert consultation */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Consultation_Insert consl_insert = new Consultation_Insert();
				consl_insert.showUserInterface(true);
			}
		});

		/* Update consultation */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Consultation ID:");
				Consultation_Update update_consl = new Consultation_Update(input);
				update_consl.showUserInterface(true);
			}
		});
		/* view clients */
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
		/* view consultations */
		btnViewClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String jsonout = "{\"COMANT\":\"getAllConsultationData\"}";
				String consultation_data = null;

				ArrayList<Consultation> cc = new ArrayList<Consultation>();
				Gson gson = new Gson();

				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					consultation_data = inFromServer.readLine();

					while (!consultation_data.equals("")) {

						Consultation consl = new Consultation(
								gson.fromJson(consultation_data, Consultation.class).getCONSULTATION_ID(),
								gson.fromJson(consultation_data, Consultation.class).getCLIENT_ID(),
								gson.fromJson(consultation_data, Consultation.class).getSTAFF_ID(),
								gson.fromJson(consultation_data, Consultation.class).getBRANCH_ID(),
								gson.fromJson(consultation_data, Consultation.class).getDATE(),
								gson.fromJson(consultation_data, Consultation.class).getRECOMMENTATION(),
								gson.fromJson(consultation_data, Consultation.class).getATTEND());
						cc.add(consl);
						consultation_data = inFromServer.readLine();
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_Consultations allClients = new View_Consultations(cc);
				allClients.showUserInterface(true);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String COMANT = "downloadClient";

				Customer result = new Customer(COMANT, u.user.getSTAFF_ID());
				Gson gson = new Gson();

				String jsonout = gson.toJson(result);
				String customer_data = null;

				ArrayList<Customer> cc = new ArrayList<Customer>();

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
					try {
						
						File file = new File(u.user.getUSERNAME() + "_clientData.txt");
						FileWriter fileWriter = new FileWriter(file);

						for (int i = 0; i < cc.size(); i++) {
							fileWriter.write(cc.get(i).getCLIENT_ID() + " " + cc.get(i).getLAST_NAME() + " "
									+ cc.get(i).getFIRST_NAME() + " " + cc.get(i).getDATE_OF_BIRTH() + " "
									+ cc.get(i).getPHONE_NUMBER() + " " + cc.get(i).getEMAIL() + " ," + "\n");
						}
						fileWriter.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		/* upload */
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String COMANT = "updateCustomer";
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(u.user.getUSERNAME() + "_clientData.txt"));
				
					
					String line = br.readLine();
					Gson gson = new Gson();
					while (line != null) {
						
						String arr[] = line.split(" ");
						System.out.println("vv"+line);
						line = br.readLine();

						Customer result = new Customer(COMANT, arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
						String jsonout = gson.toJson(result);
					
						try {

							Socket clientSocket = new Socket("10.16.4.175", 2000);
							DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

							outToServer.writeBytes(jsonout + '\n');
							clientSocket.close();

						} catch (IOException e) {
							e.printStackTrace();
						}

					}
					// String everything = sb.toString();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			}
		});

	}

}
