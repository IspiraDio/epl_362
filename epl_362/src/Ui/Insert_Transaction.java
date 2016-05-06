package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
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
import Client.Transaction;

public class Insert_Transaction extends JFrame {
	private JPanel frame;
	private JTextField cid;
	private JTextField bid;
	private JTextField date;
	private JTextField amt;

	public Insert_Transaction() {		
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
		
		JLabel lblInsertAppointment = new JLabel("Insert Client Transaction");
		lblInsertAppointment.setBounds(138, 36, 139, 15);
		frame.add(lblInsertAppointment);
		
		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setBounds(10, 80, 95, 15);
		frame.add(lblClientId);
		
		cid = new JTextField();
		cid.setColumns(10);
		cid.setBounds(138, 78, 114, 19);
		frame.add(cid);
		
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
		
		JLabel lblTime = new JLabel("Amount");
		lblTime.setBounds(10, 192, 118, 15);
		frame.add(lblTime);
		
		amt = new JTextField();
		amt.setColumns(10);
		amt.setBounds(138, 190, 114, 19);
		frame.add(amt);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(10, 255, 117, 25);
		frame.add(insertButton);
		
		JButton clr_button = new JButton("Clear");
		clr_button.setBounds(135, 255, 117, 25);
		frame.add(clr_button);
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String CLIENT_ID =cid.getText();
				String BRANCH_ID = bid.getText();
				String DATE = date.getText();
				String AMOUNT =amt.getText();
		
				if (CLIENT_ID.equals("") || BRANCH_ID.equals("") || DATE.equals("")  || AMOUNT.equals("")){
					JOptionPane.showMessageDialog(frame,
						    "Please fill all the fields",
						    "Invalid Recommendation insert",
						    JOptionPane.WARNING_MESSAGE);
				}
				else{
					
				String COMANT="insertTransaction";
				
				Gson gson = new Gson();
				Transaction result = new Transaction(COMANT,CLIENT_ID,BRANCH_ID,DATE,AMOUNT);
				String jsonout = gson.toJson(result);

				  Socket clientSocket;
				try {
				  clientSocket = new Socket("10.16.4.175", 2000);
				
				  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//	  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				  
				  outToServer.writeBytes(jsonout + '\n');
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
				bid.setText("");
				date.setText("");
				amt.setText("");
			}
		});
		
	}

}
