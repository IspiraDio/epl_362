package Ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Client.Recommendation;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class View_Recommendation extends JFrame {

	private JPanel contentPane;
	private ArrayList<JLabel> lbl;
	//private ArrayList<JButton> btn;
	
	public View_Recommendation(ArrayList<Recommendation> recList) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewRecommendation = new JLabel("View Recommendation");
		lblViewRecommendation.setFont(new Font("Calibri Light", Font.BOLD, 13));
		lblViewRecommendation.setBounds(10, 28, 211, 14);
		contentPane.add(lblViewRecommendation);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(0, 2, 2, 2));

		lbl = new ArrayList<JLabel>();
		//btn = new ArrayList<JButton>();

		panel.add(labels, BorderLayout.LINE_START);

		int i = 0;
		//int b = 0;

		lbl.add(new JLabel("Client ID"));
		labels.add(lbl.get(i));
		i++;
		
		lbl.add(new JLabel("Recommendation"));
		labels.add(lbl.get(i));
		i++;
		
		String clientID = "";
		String rec = "";

		int c = 0;
		
		
		while (c!=recList.size()) {
			clientID = recList.get(c).getCLIENT_ID();
			rec= recList.get(c).getRECOMMENDATION();
			
			//final String cid = clientID;

			lbl.add(new JLabel(clientID));
			labels.add(lbl.get(i));
			i++;

			lbl.add(new JLabel(rec));
			labels.add(lbl.get(i));
			i++;

			/*btn.add(new JButton("Update"));
			labels.add(btn.get(b));
			btn.get(b).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Customer_Update c = new Customer_Update (cid);
					c.showUserInterface(true);
				}
			});*/

			//b++;
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
