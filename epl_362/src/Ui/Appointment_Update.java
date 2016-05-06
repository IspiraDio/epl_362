package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Appointment_Update extends JFrame {

	private JPanel frame;
	private JTextField cid;
	private JTextField sid;
	private JTextField bid;
	private JTextField date;
	private JTextField time;
	private JTextField attend;
	private String APPOINTMENT_ID;
	private String COMANT;
	private String CLIENT_ID;
	private String STAFF_ID;
	private String BRANCH_ID;
	private String DATE;
	private String TIME;
	private String ATTEND;

	public Appointment_Update(String APPOINTMENT_ID) {
		this.APPOINTMENT_ID = APPOINTMENT_ID;
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

		JLabel lblUpdateAppointment = new JLabel("Update Appointment");
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

		JLabel label_5 = new JLabel("Time");
		label_5.setBounds(10, 167, 95, 15);
		frame.add(label_5);

		time = new JTextField();
		time.setColumns(10);
		time.setBounds(138, 165, 114, 19);
		frame.add(time);

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

		COMANT = "getAppointmentData";

		Gson gson = new Gson();
		Appointment result = new Appointment(COMANT, this.APPOINTMENT_ID);
		String jsonout = gson.toJson(result);

		String appointment_data = null;

		Socket clientSocket;
		try {
			clientSocket = new Socket("10.16.4.175", 2000);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes(jsonout + '\n');
			appointment_data = inFromServer.readLine();

			Appointment serverJson = new Appointment();
			System.out.print(appointment_data);
			serverJson = gson.fromJson(appointment_data, Appointment.class);
			clientSocket.close();

			cid.setText(serverJson.getCLIENT_ID());
			sid.setText(serverJson.getSTAFF_ID());
			bid.setText(serverJson.getBRANCH_ID());
			date.setText(serverJson.getDATE());
			time.setText(serverJson.getTIME());
			attend.setText(serverJson.getATTEND());

			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					STAFF_ID = sid.getText();
					CLIENT_ID = cid.getText();
					BRANCH_ID = bid.getText();
					DATE = date.getText();
					TIME = time.getText();
					ATTEND = attend.getText();

					/* elexos gia kena */
					if (STAFF_ID.equals("") || CLIENT_ID.equals("") || BRANCH_ID.equals("") || DATE.equals("")
							|| TIME.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please fill all the fields", "Invalid update",
								JOptionPane.WARNING_MESSAGE);
					} else {
						COMANT = "updateAppointment";

						Appointment result = new Appointment(COMANT, APPOINTMENT_ID, STAFF_ID, CLIENT_ID, BRANCH_ID,
								DATE, TIME, ATTEND);
						String jsonout = gson.toJson(result);
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

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COMANT = "delAppointment";
					Gson gson = new Gson();
					Appointment result = new Appointment(COMANT, APPOINTMENT_ID);
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
