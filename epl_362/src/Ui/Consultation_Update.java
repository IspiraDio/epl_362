package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Consultation;

public class Consultation_Update extends JFrame {
	
	private JPanel frame;
	private JTextField cid;
	private JTextField sid;
	private JTextField bid;
	private JTextField date;
	private JTextField rec;
	private JTextField attend;

	private String CONSULTATION_ID;
	private String DATE;
	private String CLIENT_ID;
	private String BRANCH_ID;
	private String STAFF_ID;
	private String RECOMMENDATION;
	private String ATTEND;
	private String COMANT;
	
	public Consultation_Update(String CONSULTATION_ID ) {
		this.CONSULTATION_ID = CONSULTATION_ID;
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
		
		JLabel lblUpdateAppointment = new JLabel("Update Consultation");
		lblUpdateAppointment.setBounds(138, 11, 139, 15);
		frame.add(lblUpdateAppointment);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setBounds(10, 55, 95, 15);
		frame.add(label_1);
		
		cid = new JTextField();
		cid.setColumns(10);
		cid.setBounds(138, 53, 114, 19);
		frame.add(cid);
		
		JLabel label_2 = new JLabel("Staff ID");
		label_2.setBounds(10, 83, 95, 15);
		frame.add(label_2);
		
		sid = new JTextField();
		sid.setColumns(10);
		sid.setBounds(138, 81, 114, 19);
		frame.add(sid);
		
		JLabel label_3 = new JLabel("Branch ID");
		label_3.setBounds(10, 111, 95, 15);
		frame.add(label_3);
		
		bid = new JTextField();
		bid.setColumns(10);
		bid.setBounds(138, 109, 114, 19);
		frame.add(bid);
		
		JLabel label_4 = new JLabel("Date");
		label_4.setBounds(10, 139, 95, 15);
		frame.add(label_4);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(138, 137, 114, 19);
		frame.add(date);
		
		JLabel label_5 = new JLabel("Recommendation");
		label_5.setBounds(10, 167, 118, 15);
		frame.add(label_5);
		
		rec = new JTextField();
		rec.setColumns(10);
		rec.setBounds(138, 165, 114, 19);
		frame.add(rec);
		
		JLabel label_6 = new JLabel("Attend");
		label_6.setBounds(10, 193, 95, 15);
		frame.add(label_6);
		
		attend = new JTextField();
		attend.setColumns(10);
		attend.setBounds(138, 191, 114, 19);
		frame.add(attend);
		
		JButton updateButton = new JButton("Update");
		updateButton.setBounds(10, 230, 117, 25);
		frame.add(updateButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(135, 230, 117, 25);
		frame.add(deleteButton);
		
		COMANT = "getConsultationData";

		Gson gson = new Gson();
		Consultation result = new Consultation(COMANT, this.CONSULTATION_ID);
		String jsonout = gson.toJson(result);
		
		String consultation_data = null;

		Socket clientSocket;
		try {
			clientSocket = new Socket("10.16.4.175", 2000);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes(jsonout + '\n');
			consultation_data = inFromServer.readLine();
			
			Consultation serverJson = new Consultation();
			 
			serverJson = gson.fromJson(consultation_data, Consultation.class);
			clientSocket.close();
			
			cid.setText(serverJson.getCLIENT_ID());
			sid.setText(serverJson.getSTAFF_ID());
			bid.setText(serverJson.getBRANCH_ID());
			date.setText(serverJson.getDATE());
			rec.setText(serverJson.getRECOMMENTATION());
			attend.setText(serverJson.getATTEND());
			
		System.out.println(consultation_data);
		System.out.println(serverJson.getRECOMMENTATION());
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					STAFF_ID = sid.getText();
					CLIENT_ID = cid.getText();
					BRANCH_ID = bid.getText();
					DATE = date.getText();
					RECOMMENDATION = rec.getText();
					ATTEND = attend.getText();
					
					/* elexos gia kena */
					if (STAFF_ID.equals("") || CLIENT_ID.equals("") || BRANCH_ID.equals("")
							|| DATE.equals("") || RECOMMENDATION.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please fill all the fields", "Invalid update",
								JOptionPane.WARNING_MESSAGE);
					} else {
						COMANT = "updateConsultation";

						Consultation result = new Consultation(COMANT, CONSULTATION_ID, STAFF_ID, CLIENT_ID, BRANCH_ID, DATE,
								RECOMMENDATION, ATTEND);
						String jsonout = gson.toJson(result);
						try {
							
							Socket clientSocket = new Socket("10.16.4.175", 2000);
							DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
							 
							outToServer.writeBytes(jsonout + '\n');
							clientSocket.close();
							// String modifiedSentence;
							// modifiedSentence = inFromServer.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COMANT="deleteConsultation";
					Gson gson = new Gson();
					Consultation result = new Consultation(COMANT, CONSULTATION_ID);
					String jsonout = gson.toJson(result);
				}
			});
			clientSocket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
