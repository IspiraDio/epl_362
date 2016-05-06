package Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import Client.Customer;

public class View_ClientsNotAttend extends JFrame {

	private ArrayList<JLabel> lbl;

	/**
	 * Create the frame.
	 */
	public View_ClientsNotAttend(ArrayList<Customer> customerList) {
		setTitle("View All Clients not attend");
		setBounds(100, 100, 338, 181);
		setBackground(UIManager.getColor("Button.focus"));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 6, 6, 6));

		lbl = new ArrayList<JLabel>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;

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
