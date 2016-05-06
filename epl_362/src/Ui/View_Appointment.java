package Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import Client.Appointment;

public class View_Appointment extends JFrame {

	private ArrayList<JLabel> lbl;
	private ArrayList<JButton> btn;

	public View_Appointment(ArrayList<Appointment> appointList) {
		setTitle("View All Appointments");
		setBounds(100, 100, 338, 181);
		setBackground(UIManager.getColor("Button.focus"));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 8, 8, 8));

		lbl = new ArrayList<JLabel>();
		btn = new ArrayList<JButton>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;
		int b = 0;

		lbl.add(new JLabel("Appontment ID"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Client ID"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Staff ID"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Branch ID"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Date"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Time"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Attend"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Select"));
		labels.add(lbl.get(i));
		i++;
		
		String appointID = "";
		String clientID = "";
		String staffID = "";
		String branchID = "";
		String date = "";
		String time = "";
		String attend = "";
		int c = 0;
		
		
		while (c!=appointList.size()) {
			appointID = appointList.get(c).getAPPOINTMENT_ID();
			clientID = appointList.get(c).getCLIENT_ID();
			staffID = appointList.get(c).getSTAFF_ID();
			branchID = appointList.get(c).getBRANCH_ID();
			date = appointList.get(c).getDATE();
			time = appointList.get(c).getTIME();
			attend = appointList.get(c).getATTEND();
			System.out.println(appointID);
			final String aid = appointID;

			lbl.add(new JLabel(appointID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(clientID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(staffID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(branchID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(date));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(time));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(attend));
			labels.add(lbl.get(i));
			i++;

			btn.add(new JButton("Update"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Appointment_Update c = new Appointment_Update (aid);
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
