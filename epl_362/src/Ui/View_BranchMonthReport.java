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


import Client.Reports;

public class View_BranchMonthReport extends JFrame {

	private ArrayList<JLabel> lbl;
	
	public View_BranchMonthReport(ArrayList<Reports> rep) {
		setTitle("View Report");
		setBounds(100, 100, 338, 181);
		setBackground(UIManager.getColor("Button.focus"));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 3, 3, 3));

		lbl = new ArrayList<JLabel>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;


		lbl.add(new JLabel("Branch ID"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Month"));
		labels.add(lbl.get(i));
		i++;

		lbl.add(new JLabel("Number of Clients"));
		labels.add(lbl.get(i));
		i++;

		String branchID = "";
		String month = "";
		String num = "";

		int c = 0;

		while (c != rep.size()) {

			branchID = rep.get(c).getREPORT_ID();
			month = rep.get(c).getMONTH();
			num = rep.get(c).getRESULT();

			lbl.add(new JLabel(branchID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(month));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(num));
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
	public void showUserInterface(boolean set){
        setVisible(set);
   }
}
