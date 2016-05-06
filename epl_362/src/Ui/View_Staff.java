package Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Client.Customer;
import Client.Staff;

public class View_Staff extends JFrame {

	private JPanel contentPane;
	private ArrayList<JLabel> lbl;
	private ArrayList<JButton> btn;

	/**
	 * Create the frame.
	 */
	public View_Staff(ArrayList<Staff> staffList) {
		setTitle("View All Clients");
		setBounds(100, 100, 338, 181);
		setBackground(UIManager.getColor("Button.focus"));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 9, 9, 9));

		lbl = new ArrayList<JLabel>();
		btn = new ArrayList<JButton>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;
		int b = 0;

		lbl.add(new JLabel("Staff ID"));
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
		
		lbl.add(new JLabel("Address"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Staff Role"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Select"));
		labels.add(lbl.get(i));
		i++;

		String staffID = "";
		String lname = "";
		String fname = "";
		String bithday = "";
		String phone = "";
		String email = "";
		String role = "";
		String address = "";
		int c = 0;
		
		
		while (c!=staffList.size()) {
			staffID = staffList.get(c).getSTAFF_ID();
			lname = staffList.get(c).getLAST_NAME();
			fname = staffList.get(c).getFIRST_NAME();
			bithday = staffList.get(c).getDATE_OF_BIRTH();
			phone = staffList.get(c).getPHONE_NUMBER();
			email = staffList.get(c).getEMAIL();
			role = staffList.get(c).getROLE();
			address = staffList.get(c).getADDRESS();
			
			final String sid = staffID;

			lbl.add(new JLabel(staffID));
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
			
			lbl.add(new JLabel(role));
			labels.add(lbl.get(i));
			i++;
			
			lbl.add(new JLabel(address));
			labels.add(lbl.get(i));
			i++;
			
			btn.add(new JButton("Update"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Staff_Update c = new Staff_Update (sid);
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
