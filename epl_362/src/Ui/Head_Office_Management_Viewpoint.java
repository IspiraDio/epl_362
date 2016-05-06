package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Branch;
import Client.Reports;
import Client.Transaction;
import Client.Consultation;
import Client.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;

public class Head_Office_Management_Viewpoint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Head_Office_Management_Viewpoint frame = new Head_Office_Management_Viewpoint();
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
	public Head_Office_Management_Viewpoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeadOfficeManagement = new JLabel("Head office management viewpoint");
		lblHeadOfficeManagement.setFont(new Font("Calibri Light", Font.BOLD, 13));
		lblHeadOfficeManagement.setBackground(Color.DARK_GRAY);
		lblHeadOfficeManagement.setBounds(10, 35, 284, 29);
		contentPane.add(lblHeadOfficeManagement);

		JButton btnViewConsultations = new JButton("View Consultations");
		btnViewConsultations.setBounds(10, 73, 362, 23);
		contentPane.add(btnViewConsultations);

		JButton btnNewButton = new JButton("Num of Clients attend branch each month");
		btnNewButton.setBounds(10, 107, 362, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Summary of recommendation each month");
		btnNewButton_1.setBounds(10, 140, 362, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Summary of the times clients have had wait for appointment");
		btnNewButton_2.setBounds(10, 248, 362, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("List of clients case types");
		btnNewButton_3.setBounds(10, 282, 362, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("List of recommendation-opinion");
		btnNewButton_4.setBounds(10, 316, 362, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Num of Clients attend each branch today");
		btnNewButton_5.setBounds(10, 174, 362, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton(" Num of Clients not attend consultation today");
		btnNewButton_6.setBounds(10, 208, 362, 23);
		contentPane.add(btnNewButton_6);

		/* view consultations */
		btnViewConsultations.addActionListener(new ActionListener() {
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

		/* client-branch-month */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Reports> cc = new ArrayList<Reports>();

				String jsonout = "{\"COMANT\":\"getBranchMonthReport\"}";
				String report = null;
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					Gson gson = new Gson();
					outToServer.writeBytes(jsonout + '\n');
					report = inFromServer.readLine();
					
					while (!report.equals("")) {
						System.out.println(report);
						
						Reports branchRep = new Reports(
								gson.fromJson(report, Reports.class).getREPORT_ID(),
								gson.fromJson(report, Reports.class).getMONTH(),
								gson.fromJson(report, Reports.class).getRESULT());
						report = inFromServer.readLine();
						cc.add(branchRep);
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_BranchMonthReport reports = new View_BranchMonthReport(cc);
				reports.showUserInterface(true);
			}
		});
		/*summary of recommendations*/
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String COMANT = "getRecMonthReport";
				ArrayList<Reports> cc = new ArrayList<Reports>();
				String client_id = JOptionPane.showInputDialog("Insert client id");
				Gson gson = new Gson();
				
				Customer result = new Customer(COMANT, client_id);
				String jsonout = gson.toJson(result);
				System.out.println(jsonout);
				String report = null;
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					report = inFromServer.readLine();
					
					System.out.println("VV"+report);
					while (!report.equals("")) {
						System.out.println(report);
						
						Reports branchRep = new Reports(
								gson.fromJson(report, Reports.class).getREPORT_ID(),
								gson.fromJson(report, Reports.class).getMONTH(),
								gson.fromJson(report, Reports.class).getRESULT());
						report = inFromServer.readLine();
						cc.add(branchRep);
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_RecommendationEachMonth reports = new View_RecommendationEachMonth(cc);
				reports.showUserInterface(true);
			}
		});
		
		/*num of clients each branch today*/
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Reports> cc = new ArrayList<Reports>();

				String jsonout = "{\"COMANT\":\"getBranchDayReport\"}";
				String report = null;
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					Gson gson = new Gson();
					outToServer.writeBytes(jsonout + '\n');
					report = inFromServer.readLine();
					
					while (!report.equals("")) {
						System.out.println(report);
						
						Reports branchRep = new Reports(
								gson.fromJson(report, Reports.class).getREPORT_ID(),
								gson.fromJson(report, Reports.class).getMONTH(),
								gson.fromJson(report, Reports.class).getRESULT());
						report = inFromServer.readLine();
						cc.add(branchRep);
					}

					clientSocket.close();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				View_BranchDayReport reports = new View_BranchDayReport(cc);
				reports.showUserInterface(true);
			}
		});
		/*list of clients not attend cunsultation today*/
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Customer> cc = new ArrayList<Customer>();

				String jsonout = "{\"COMANT\":\"getCustomerNotAttend\"}";
				String customer_data = null;
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					Gson gson = new Gson();
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

				View_ClientsNotAttend reports = new View_ClientsNotAttend(cc);
				reports.showUserInterface(true);
			}
		});
	}
}
