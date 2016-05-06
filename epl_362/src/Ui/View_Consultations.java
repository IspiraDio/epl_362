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
import javax.swing.border.EmptyBorder;

import Client.Consultation;

public class View_Consultations extends JFrame {

	private ArrayList<JLabel> lbl;
	private ArrayList<JButton> btn;

	public View_Consultations(ArrayList<Consultation> conslList) {
		setTitle("View All Consulations");
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

		lbl.add(new JLabel("Consultation ID"));
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

		lbl.add(new JLabel("Recommendation"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Attend"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Select"));
		labels.add(lbl.get(i));
		i++;

		String conslID = "";
		String clientID = "";
		String staffID = "";
		String branchID = "";
		String date = "";
		String rec = "";
		String attend = "";
		int c = 0;

		while (c != conslList.size()) {
			conslID = conslList.get(c).getCONSULTATION_ID();
			clientID = conslList.get(c).getCLIENT_ID();
			staffID = conslList.get(c).getSTAFF_ID();
			branchID = conslList.get(c).getBRANCH_ID();
			date = conslList.get(c).getDATE();
			rec = conslList.get(c).getRECOMMENTATION();
			attend = conslList.get(c).getATTEND();
			
			final String cid = conslID;

			lbl.add(new JLabel(conslID));
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

			lbl.add(new JLabel(rec));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(attend));
			labels.add(lbl.get(i));
			i++;

			btn.add(new JButton("Update"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Consultation_Update c = new Consultation_Update(cid);
					c.showUserInterface(true);
				}
			});
			btn.add(new JButton("Insert Comment"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Consultation_Comment_Insert c = new Consultation_Comment_Insert(cid);
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
