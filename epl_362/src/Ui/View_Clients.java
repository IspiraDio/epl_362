package Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Client.Customer;

public class View_Clients extends JFrame {

	private JPanel contentPane;
	private ArrayList<JLabel> lbl;
	private ArrayList<JButton> btn;

	/**
	 * Create the frame.
	 */
	public View_Clients(ArrayList<Customer> customerList) {
		setTitle("View All Clients");
		setBounds(100, 100, 338, 181);
		setBackground(UIManager.getColor("Button.focus"));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 7, 7, 7));

		lbl = new ArrayList<JLabel>();
		btn = new ArrayList<JButton>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;
		int b = 0;

		lbl.add(new JLabel("Client ID"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Last Name"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("First Name"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Date of Birth"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Phone Number"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Email"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Select"));
		labels.add(lbl.get(i));
		i++;

		String clientID = "";
		String lname = "";
		String fname = "";
		String bithday = "";
		String phone = "";
		String email = "";
		int c = 0;
		
		
		while (c!=customerList.size()) {
			clientID = customerList.get(c).getCLIENT_ID();
			lname = customerList.get(c).getLAST_NAME();
			fname = customerList.get(c).getFIRST_NAME();
			bithday = customerList.get(c).getDATE_OF_BIRTH();
			phone = customerList.get(c).getPHONE_NUMBER();
			email = customerList.get(c).getEMAIL();

			final String cid = clientID;

			lbl.add(new JLabel(clientID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(lname));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(fname));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(bithday));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(phone));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(email));
			labels.add(lbl.get(i));
			i++;

			btn.add(new JButton("Update"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Customer_Update c = new Customer_Update (cid);
					c.showUserInterface(true);
				}
			});

			b++;
			c++;
		}
		JButton btnSolve = new JButton("Print");
		getContentPane().add(btnSolve, BorderLayout.PAGE_END);

		JScrollPane jp = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(jp);

		pack();
		Dimension d = getSize();
		int w = (int) d.getWidth();
		int h = (int) d.getHeight();

		h = (h > 500 ? 500 : h);
		Dimension shrinkHeight = new Dimension(w, h);
		setSize(new Dimension(340, 166));
	}

	public void showUserInterface(boolean set) {
		setVisible(set);
	}

}
