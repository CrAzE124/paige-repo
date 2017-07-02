package com.paigeapp.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.paigeapp.gui.fixtures.FixturesPanel;

public class MenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setLayout(null);
		
		JButton btnOpenPlayerScreen = new JButton("Users");
		btnOpenPlayerScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				menuPanel.setVisible(false);
//				playerRosterPanel.setVisible(true);
			}
		});
		btnOpenPlayerScreen.setBounds(902, 484, 117, 25);
		add(btnOpenPlayerScreen);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				staffRosterPanel.setVisible(true);
//				menuPanel.setVisible(false);
			}
		});
		btnStaff.setBounds(438, 63, 117, 25);
		add(btnStaff);
		
//		JButton btnExit = new JButton("Exit");
//		btnExit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				getParent().dispose();
//			}
//		});
//		btnExit.setBounds(438, 288, 117, 25);
//		add(btnExit);
		
		JButton btnTeams = new JButton("Teams");
		btnTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				teamPanel.setVisible(true);
//				menuPanel.setVisible(false);
			}
		});
		btnTeams.setBounds(438, 101, 117, 25);
		add(btnTeams);
		
		JButton btnFixtures = new JButton("Fixtures");
		btnFixtures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getParent().removeAll();
				getParent().add(new FixturesPanel());
			}
		});
		btnFixtures.setBounds(438, 138, 117, 25);
		add(btnFixtures);
	}

}
