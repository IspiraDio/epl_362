package Ui;

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
import Client.Login;
import Client.Session;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {
	private JPanel contentPane;
	private JTextField username;
	private String COMANT;
	private JPasswordField passwordField;
	public static Session user = new Session();
	public LoginPage() {		
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setBounds(179, 53, 113, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setBounds(101, 93, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(101, 118, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		username = new JTextField();
		username.setBounds(170, 90, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(167, 143, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 118, 86, 20);
		contentPane.add(passwordField);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				/* check for all the values*/
				if  (username.getText().equals("") || passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane,
						    "Please fill the fields",
						    "Invalid insert", JOptionPane.WARNING_MESSAGE);
				}
				else{
					COMANT = "login";
					
					Gson gson = new Gson();
					Login result = new Login(COMANT, username.getText(), passwordField.getText());
					String jsonout = gson.toJson(result);
		
					String login_info = null;

					Socket clientSocket;
					try {
						clientSocket = new Socket("10.16.4.175", 2000);

						DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
						BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

						outToServer.writeBytes(jsonout + '\n');
						login_info = inFromServer.readLine();
						System.out.println(login_info);
						
						if (login_info.equals("ok")){
							
							login_info = inFromServer.readLine();
							
							main.user_ss= new Session(gson.fromJson(login_info, Session.class).getUSERNAME(),gson.fromJson(login_info, Session.class).getSTAFF_ID(),gson.fromJson(login_info, Session.class).getSTAFF_ROLE_ID());
							
							user.setUSERNAME(username.getText());
							user.setSTAFF_ID(main.user_ss.getSTAFF_ID());
							user.setSTAFF_ROLE_ID(main.user_ss.getSTAFF_ROLE_ID());
							
							if(main.user_ss.getSTAFF_ROLE_ID().equals("1")){
								System.out.println("Viewpoint1");
								Reseptionist_Viewpoint resept = new Reseptionist_Viewpoint();
								resept.main(null);
							}
							else if(main.user_ss.getSTAFF_ROLE_ID().equals("2")){
								System.out.println("Viewpoint2");
								Legal_Rcords_Staff_Viewpoint rcd_staff = new Legal_Rcords_Staff_Viewpoint();
								rcd_staff.main(null);
							}
							else if(main.user_ss.getSTAFF_ROLE_ID().equals("3") || main.user_ss.getSTAFF_ROLE_ID().equals("4")){
								System.out.println("Viewpoint3");
								Legal_Staff_Viewpoint legal_staff = new Legal_Staff_Viewpoint();
								legal_staff.main(null);
							}
							else if(main.user_ss.getSTAFF_ROLE_ID().equals("5")){
								System.out.println("Viewpoint3");
								Legal_Staff_Viewpoint legal_staff = new Legal_Staff_Viewpoint();
								legal_staff.main(null);
							}
						}
						else{
							JOptionPane.showMessageDialog(contentPane,
								    "Check your username and password.",
								    "Invalid Insert", JOptionPane.WARNING_MESSAGE);
						}
						clientSocket.close();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					}
				}
			});
	}
}
