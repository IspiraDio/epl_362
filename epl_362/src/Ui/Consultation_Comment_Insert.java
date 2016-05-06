package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import com.google.gson.Gson;

import Client.Consultation_Comment;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class Consultation_Comment_Insert extends JFrame {
	private JTextField textField;

	public Consultation_Comment_Insert(String ConsultationID){
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(23, 59, 244, 129);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInsertYourComment = new JLabel("Insert your Comment:");
		lblInsertYourComment.setBounds(23, 27, 180, 14);
		getContentPane().add(lblInsertYourComment);

		JButton insertButton = new JButton("Insert");
		insertButton.setBounds(23, 199, 117, 25);
		getContentPane().add(insertButton);
		
		JButton clr_button = new JButton("Clear");
		clr_button.setBounds(150, 199, 117, 25);
		getContentPane().add(clr_button);
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String comment =textField.getText();

				String COMANT="insertConsultationComment";
				
				Gson gson = new Gson();
				Consultation_Comment result = new Consultation_Comment(COMANT,comment, "2525");
				String jsonout = gson.toJson(result);

				  Socket clientSocket;
				try {
				  clientSocket = new Socket("10.16.4.175", 2000);
				
				  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				  //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				  
				  outToServer.writeBytes(jsonout + '\n');
				  clientSocket.close();
				  
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		clr_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
	}
	public void showUserInterface(boolean set) {
		setVisible(set);
	}
}
