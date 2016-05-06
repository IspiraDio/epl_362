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

import Client.*;

public class Consultation_Insert extends JFrame {

	private JPanel frame;
	private JTextField cid;
	private JTextField sid;
	private JTextField bid;
	private JTextField date;
	private JTextField rec;
	private JTextField attend;

	public Consultation_Insert() {		
		initialize();
	}
	
	public void showUserInterface(boolean set){
         setVisible(set);
    }
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 345);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		frame.setLayout(null);
		
		JLabel lblInsertAppointment = new JLabel("Insert Consultation");
		lblInsertAppointment.setBounds(138, 36, 139, 15);
		frame.add(lblInsertAppointment);
		
		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setBounds(10, 80, 95, 15);
		frame.add(lblClientId);
		
		cid = new JTextField();
		cid.setColumns(10);
		cid.setBounds(138, 78, 114, 19);
		frame.add(cid);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setBounds(10, 108, 95, 15);
		frame.add(lblStaffId);
		
		sid = new JTextField();
		sid.setColumns(10);
		sid.setBounds(138, 106, 114, 19);
		frame.add(sid);
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setBounds(10, 136, 95, 15);
		frame.add(lblBranchId);
		
		bid = new JTextField();
		bid.setColumns(10);
		bid.setBounds(138, 134, 114, 19);
		frame.add(bid);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 164, 95, 15);
		frame.add(lblDate);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(138, 162, 114, 19);
		frame.add(date);
		
		JLabel lblTime = new JLabel("Recommendation");
		lblTime.setBounds(10, 192, 118, 15);
		frame.add(lblTime);
		
		rec = new JTextField();
		rec.setColumns(10);
		rec.setBounds(138, 190, 114, 19);
		frame.add(rec);
		
		JLabel lblAttend = new JLabel("Attend");
		lblAttend.setBounds(10, 218, 95, 15);
		frame.add(lblAttend);
		
		attend = new JTextField();
		attend.setColumns(10);
		attend.setBounds(138, 216, 114, 19);
		frame.add(attend);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(10, 255, 117, 25);
		frame.add(insertButton);
		
		JButton clr_button = new JButton("Clear");
		clr_button.setBounds(135, 255, 117, 25);
		frame.add(clr_button);
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String CLIENT_ID =cid.getText();
				String STAFF_ID = sid.getText();
				String BRANCH_ID = bid.getText();
				String DATE = date.getText();
				String RECOMMENDATION =rec.getText();
				String ATTEND = attend.getText();
		
				if (CLIENT_ID.equals("") || STAFF_ID.equals("") ||BRANCH_ID.equals("") || DATE.equals("")  || RECOMMENDATION.equals("")){
					JOptionPane.showMessageDialog(frame,
						    "Please fill all the fields",
						    "Invalid Recommendation insert",
						    JOptionPane.WARNING_MESSAGE);
				}
				else{
					
				String COMANT="insertConsultation";
				
				Gson gson = new Gson();
				Consultation result = new Consultation(COMANT,CLIENT_ID,STAFF_ID,BRANCH_ID,DATE,RECOMMENDATION,ATTEND);
				String jsonout = gson.toJson(result);
				
				  String modifiedSentence;
				  Socket clientSocket;
				try {
				  clientSocket = new Socket("10.16.4.175", 2000);
				
				  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				  
				  outToServer.writeBytes(jsonout + '\n');
				  modifiedSentence = inFromServer.readLine();
				  clientSocket.close();
				  
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		clr_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid.setText("");
				sid.setText("");
				bid.setText("");
				date.setText("");
				rec.setText("");
				attend.setText("");
			}
		});
		
	}

}
