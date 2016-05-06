package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Appointment;
import Client.Customer;
import Client.Recommendation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
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

public class Reseptionist_Viewpoint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reseptionist_Viewpoint frame = new Reseptionist_Viewpoint();
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
	public Reseptionist_Viewpoint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReceptionistViewpoint = new JLabel("Receptionist Viewpoint");
		lblReceptionistViewpoint.setFont(new Font("Calibri Light", Font.BOLD, 13));
		lblReceptionistViewpoint.setBounds(10, 11, 275, 14);
		contentPane.add(lblReceptionistViewpoint);
		
		JButton btnNewButton = new JButton("View Appointments");
		btnNewButton.setBounds(10, 46, 188, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Insert Appointment");
		btnNewButton_1.setBounds(10, 80, 188, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search Appointment");
		btnNewButton_2.setBounds(10, 112, 188, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Recommendation");
		btnNewButton_3.setBounds(10, 146, 188, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jsonout = "{\"COMANT\":\"getAllAppointmentData\"}";
				String appointment = null;
				ArrayList<Appointment> cc = new ArrayList<Appointment>();
				Gson gson = new Gson();
				System.out.println("SS");
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					appointment = inFromServer.readLine();

					while (!appointment.equals("")) {
						System.out.println(gson.fromJson(appointment, Appointment.class).getAPPOINTMENT_ID());

						Appointment appoint = new Appointment(gson.fromJson(appointment, Appointment.class).getAPPOINTMENT_ID(),gson.fromJson(appointment, Appointment.class).getCLIENT_ID(), gson.fromJson(appointment, Appointment.class).getSTAFF_ID(), gson.fromJson(appointment, Appointment.class).getBRANCH_ID(), gson.fromJson(appointment, Appointment.class).getDATE(), gson.fromJson(appointment, Appointment.class).getTIME(),gson.fromJson(appointment, Appointment.class).getATTEND() );
						cc.add(appoint);
						appointment = inFromServer.readLine();
					}
					
					clientSocket.close();
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				View_Appointment allAppointments = new View_Appointment(cc);
				allAppointments.showUserInterface(true);
			}
		});
		
		/*insert appointment*/
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Appointment_Insert appInsert = new Appointment_Insert();
				appInsert.showUserInterface(true);
			}
		});
		
		/*Search Appointment*/
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Client ID:");
				String COMANT = "getAppointmentByClient";

				Gson gson = new Gson();
				Appointment result = new Appointment(COMANT,input);
				String jsonout = gson.toJson(result);
				
				String appointment = null;
				
				ArrayList<Appointment> cc = new ArrayList<Appointment>();
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					appointment = inFromServer.readLine();
					
					System.out.println(appointment);
					while (!appointment.equals("")) {

						Appointment appoint = new Appointment(gson.fromJson(appointment, Appointment.class).getAPPOINTMENT_ID(),gson.fromJson(appointment, Appointment.class).getCLIENT_ID(), gson.fromJson(appointment, Appointment.class).getSTAFF_ID(), gson.fromJson(appointment, Appointment.class).getBRANCH_ID(), gson.fromJson(appointment, Appointment.class).getDATE(), gson.fromJson(appointment, Appointment.class).getTIME(),gson.fromJson(appointment, Appointment.class).getATTEND() );
						cc.add(appoint);
						System.out.println("ss");
						appointment = inFromServer.readLine();
						System.out.println(appointment);
					}
					
					clientSocket.close();
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("d");
				View_Appointment allAppointments = new View_Appointment(cc);
				allAppointments.showUserInterface(true);
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Client ID:");
				String COMANT = "getRecommendationByClient";

				Gson gson = new Gson();
				Recommendation result = new Recommendation(COMANT,input);
				String jsonout = gson.toJson(result);
				
				String recommendation = null;
				
				ArrayList<Recommendation> cc = new ArrayList<Recommendation>();
				Socket clientSocket;
				try {
					clientSocket = new Socket("10.16.4.175", 2000);

					DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
					BufferedReader inFromServer = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));

					outToServer.writeBytes(jsonout + '\n');
					recommendation = inFromServer.readLine();
					
					while (!recommendation.equals("")) {

						Recommendation rec = new Recommendation(gson.fromJson(recommendation, Recommendation.class).getCOMANT(),gson.fromJson(recommendation, Recommendation.class).getCLIENT_ID());
						cc.add(rec);
						recommendation = inFromServer.readLine();
					}
					
					clientSocket.close();
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
				View_Recommendation allRecommendations = new View_Recommendation(cc);
				allRecommendations.showUserInterface(true);
			}
		});
		
	}
}
