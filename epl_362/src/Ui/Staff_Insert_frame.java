package Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.Customer;
import Client.Staff;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Staff_Insert_frame extends JFrame {

	private JPanel contentPane;
	private JTextField sid;
	private JTextField ln;
	private JTextField fn;
	private JTextField bd;
	private JTextField pn;
	private JTextField eml;
	private JTextField add;
	private JTextField rl;

	public Staff_Insert_frame() {		
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
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insert Staff");
		lblNewLabel.setBounds(168, 11, 67, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setBounds(17, 55, 95, 15);
		getContentPane().add(lblStaffId);
		
		JLabel label_1 = new JLabel("Last Name");
		label_1.setBounds(17, 82, 90, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("First Name");
		label_2.setBounds(17, 109, 90, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Date of Birth");
		label_3.setBounds(17, 136, 95, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Phone Number");
		label_4.setBounds(17, 163, 110, 15);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(17, 190, 70, 15);
		getContentPane().add(label_5);
		
		sid = new JTextField();
		sid.setColumns(10);
		sid.setBounds(145, 53, 114, 19);
		getContentPane().add(sid);
		
		ln = new JTextField();
		ln.setColumns(10);
		ln.setBounds(145, 80, 114, 19);
		getContentPane().add(ln);
		
		fn = new JTextField();
		fn.setColumns(10);
		fn.setBounds(145, 107, 114, 19);
		getContentPane().add(fn);
		
		bd = new JTextField();
		bd.setColumns(10);
		bd.setBounds(145, 134, 114, 19);
		getContentPane().add(bd);
		
		pn = new JTextField();
		pn.setColumns(10);
		pn.setBounds(145, 161, 114, 19);
		getContentPane().add(pn);
		
		eml = new JTextField();
		eml.setColumns(10);
		eml.setBounds(145, 188, 114, 19);
		getContentPane().add(eml);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(10, 276, 117, 25);
		getContentPane().add(insertButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(145, 276, 117, 25);
		getContentPane().add(clearButton);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(17, 216, 46, 14);
		getContentPane().add(lblAddress);
		
		add = new JTextField();
		add.setBounds(145, 213, 114, 20);
		getContentPane().add(add);
		add.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(17, 241, 46, 14);
		getContentPane().add(lblRole);
		
		rl = new JTextField();
		rl.setBounds(145, 244, 114, 20);
		getContentPane().add(rl);
		rl.setColumns(10);
		
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String STAFF_ID=sid.getText();
				String LAST_NAME=ln.getText();
				String FIRST_NAME=fn.getText();
				String DATE_OF_BIRTH=bd.getText();
				String PHONE_NUMBER=pn.getText();
				String EMAIL=eml.getText();
				String ADDRESS= add.getText();
				String ROLE= rl.getText();
				
				/* check for all the values*/
				if (STAFF_ID.equals("") ||LAST_NAME.equals("") ||FIRST_NAME.equals("") || DATE_OF_BIRTH.equals("")  || PHONE_NUMBER.equals("") || EMAIL.equals("") || ADDRESS.equals("") || ROLE.equals("")){
					JOptionPane.showMessageDialog(contentPane,
						    "Please fill all the fields",
						    "Invalid staff insert",
						    JOptionPane.WARNING_MESSAGE);
				}
				else{
					
				String COMANT="insertStaff";
				
				Gson gson = new Gson();
				Staff result = new Staff(COMANT,STAFF_ID,LAST_NAME,FIRST_NAME,DATE_OF_BIRTH,PHONE_NUMBER,EMAIL,ADDRESS,ROLE);
				String jsonout = gson.toJson(result);
				
				System.out.println(jsonout);
				
				  String modifiedSentence;
				  Socket clientSocket;
				try {
				  clientSocket = new Socket("10.16.4.175", 2000);
				
				  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				  
				  outToServer.writeBytes(jsonout + '\n');
				  modifiedSentence = inFromServer.readLine();
				  System.out.println("FROM SERVER: " + modifiedSentence);
				  clientSocket.close();
				  
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid.setText("");
				ln.setText("");
				fn.setText("");
				bd.setText("");
				pn.setText("");
				eml.setText("");
				add.setText("");
				rl.setText("");
			}
		});
	}
}
