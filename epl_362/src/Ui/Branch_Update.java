package Ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import Client.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;

public class Branch_Update extends JFrame {
	
	private JPanel frame;
	private JTextField bid;
	private JTextField str;
	private JTextField zip;
	private JTextField city;
	private JTextField cntry;
	
	private String BRANCH_ID;
	private String CITY;
	private String STREET_NAME;
	private String ZIP_CODE;
	private String COUNTRY;
	private String COMANT;
	
	public Branch_Update(String BRANCH_ID) {
		this.BRANCH_ID = BRANCH_ID;
		initialize();
	}

	public void showUserInterface(boolean set) {
		setVisible(set);
		}

	private void initialize() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		frame.setLayout(null);
		
		JLabel lblUpdateBranch = new JLabel("Update Branch");
		lblUpdateBranch.setBounds(152, 24, 91, 14);
		frame.add(lblUpdateBranch);
		
		JLabel label_1 = new JLabel("Branch ID");
		label_1.setBounds(13, 68, 95, 15);
		frame.add(label_1);
		
		JLabel label_2 = new JLabel("Street");
		label_2.setBounds(13, 95, 90, 15);
		frame.add(label_2);
		
		JLabel label_3 = new JLabel("Zip Code");
		label_3.setBounds(13, 122, 90, 15);
		frame.add(label_3);
		
		JLabel label_4 = new JLabel("City");
		label_4.setBounds(13, 149, 95, 15);
		frame.add(label_4);
		
		JLabel label_5 = new JLabel("Country");
		label_5.setBounds(13, 176, 110, 15);
		frame.add(label_5);
		
		bid = new JTextField();
		bid.setColumns(10);
		bid.setBounds(141, 66, 114, 19);
		frame.add(bid);
		
		str = new JTextField();
		str.setColumns(10);
		str.setBounds(141, 93, 114, 19);
		frame.add(str);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(141, 120, 114, 19);
		frame.add(zip);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(141, 147, 114, 19);
		frame.add(city);
		
		cntry = new JTextField();
		cntry.setColumns(10);
		cntry.setBounds(141, 174, 114, 19);
		frame.add(cntry);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 219, 117, 25);
		frame.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(138, 219, 117, 25);
		frame.add(btnDelete);
		
		COMANT = "getBranchData";

		Gson gson = new Gson();
		Branch result = new Branch(COMANT, this.BRANCH_ID);
		String jsonout = gson.toJson(result);

		String branch_data = null;

		Socket clientSocket;
		try {
			clientSocket = new Socket("10.16.4.175", 2000);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes(jsonout + '\n');
			branch_data = inFromServer.readLine();
            System.out.println(branch_data);
			Branch serverJson = new Branch();
			 
			serverJson = gson.fromJson(branch_data, Branch.class);
			clientSocket.close();
			
			bid.setText(serverJson.getBRANCH_ID());
			str.setText(serverJson.getSTREET_NAME());
			zip.setText(serverJson.getZIP_CODE());
			city.setText(serverJson.getCITY());
			cntry.setText(serverJson.getCOUNTRY());
			
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					BRANCH_ID = bid.getText();
					STREET_NAME = str.getText();
					ZIP_CODE = zip.getText();
					CITY = city.getText();
					COUNTRY = cntry.getText();

					/* elexos gia kena */
					if (BRANCH_ID.equals("") || STREET_NAME.equals("") || ZIP_CODE.equals("")
							|| CITY.equals("") || COUNTRY.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please fill all the fields", "Invalid update",
								JOptionPane.WARNING_MESSAGE);
					} else {
						COMANT = "updateBranch";

						Branch result = new Branch(COMANT, BRANCH_ID, STREET_NAME, ZIP_CODE, CITY,
								COUNTRY);
						String jsonout = gson.toJson(result);
						System.out.print(jsonout);

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

			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					COMANT="deleteBranch";
					Gson gson = new Gson();
					Staff result = new Staff(COMANT, BRANCH_ID);
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
