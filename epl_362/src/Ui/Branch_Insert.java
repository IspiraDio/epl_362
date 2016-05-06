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

public class Branch_Insert extends JFrame {
	
	private JPanel contentPane;
	private JTextField bid;
	private JTextField city;
	private JTextField str;
	private JTextField zip;
	private JTextField ctry;
	
	public Branch_Insert() {		
		initialize();
	}
	
	public void showUserInterface(boolean set){
         setVisible(set);
    }
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insert Branch");
		lblNewLabel.setBounds(156, 11, 91, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel label_0 = new JLabel("Branch ID");
		label_0.setBounds(17, 55, 95, 15);
		getContentPane().add(label_0);
		
		JLabel label_1 = new JLabel("Street");
		label_1.setBounds(17, 82, 90, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Zip Code");
		label_2.setBounds(17, 109, 90, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("City");
		label_3.setBounds(17, 136, 95, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Country");
		label_4.setBounds(17, 163, 110, 15);
		getContentPane().add(label_4);
		
		bid = new JTextField();
		bid.setColumns(10);
		bid.setBounds(145, 53, 114, 19);
		getContentPane().add(bid);
		
		str = new JTextField();
		str.setColumns(10);
		str.setBounds(145, 80, 114, 19);
		getContentPane().add(str);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(145, 107, 114, 19);
		getContentPane().add(zip);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(145, 134, 114, 19);
		getContentPane().add(city);
		
		ctry = new JTextField();
		ctry.setColumns(10);
		ctry.setBounds(145, 161, 114, 19);
		getContentPane().add(ctry);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(14, 206, 117, 25);
		getContentPane().add(insertButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(142, 206, 117, 25);
		getContentPane().add(clearButton);
			
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String BRANCH_ID=bid.getText();
				String STREET_NAME=str.getText();
				String ZIP_CODE=zip.getText();
				String CITY=city.getText();
				String COUNTRY=ctry.getText();
				
				/* check for all the values*/
				if (BRANCH_ID.equals("") ||STREET_NAME.equals("") ||ZIP_CODE.equals("") || CITY.equals("")  || COUNTRY.equals("")){
					JOptionPane.showMessageDialog(contentPane,
						    "Please fill all the fields",
						    "Invalid branch insert",
						    JOptionPane.WARNING_MESSAGE);
				}
				else{
					
				String COMANT="insertBranch";
				
				Gson gson = new Gson();
				Branch result = new Branch(COMANT,BRANCH_ID,STREET_NAME,ZIP_CODE,CITY,COUNTRY);
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
				bid.setText("");
				str.setText("");
				zip.setText("");
				city.setText("");
				ctry.setText("");
			}
		});
	}
}
