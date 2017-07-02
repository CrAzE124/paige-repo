package com.paigeapp.gui.team;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paigeapp.controller.StaffController;
import com.paigeapp.controller.TeamController;
import com.paigeapp.database.model.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TeamFrame extends JFrame {
	private TeamController teamController = new TeamController();
	private StaffController staffController = new StaffController();
	private JPanel contentPane;
	private JTextField txtTeamName;
	private JButton btnSave;
	private JButton btnUpdate;
	private JComboBox<Staff> cbxCoach;
	
	private int editingTeamId;
	private JLabel lblCoach;
	
	public TeamFrame(int teamId) {
		init();
		
		Object[] team = teamController.getTeam(teamId);
		
		editingTeamId = (int) team[0];
		String teamName = (String) team[1];
		int coachId = (int) team[2];
		String coachName = (String) team[3];
		
		Staff coach = new Staff(coachId, coachName);
		
		txtTeamName.setText(teamName);
		cbxCoach.setSelectedItem(coach);
		
		btnSave.setVisible(false);
		btnUpdate.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public TeamFrame() {
		init();
		
		btnSave.setVisible(true);
		btnUpdate.setVisible(false);
	}
	
	private void init() {
		setTitle("New Team");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 491);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeamName = new JLabel("Team Name");
		lblTeamName.setBounds(33, 22, 171, 15);
		contentPane.add(lblTeamName);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(243, 20, 189, 19);
		contentPane.add(txtTeamName);
		txtTeamName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamName = txtTeamName.getText();
				int coachId = ((Staff) cbxCoach.getSelectedItem()).getId();
				
				teamController.addTeam(teamName, coachId);
				
				JOptionPane.showMessageDialog(null, "Successfully created the team");
				dispose();
			}
		});
		btnSave.setBounds(33, 251, 117, 25);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teamName = txtTeamName.getText();
				int coachId = ((Staff) cbxCoach.getSelectedItem()).getId();
				
				teamController.updateTeam(editingTeamId, teamName, coachId);
				
				JOptionPane.showMessageDialog(null, "Successfully updated the team");
				dispose();
			}
		});
		btnUpdate.setBounds(33, 251, 117, 25);
		contentPane.add(btnUpdate);
		
		lblCoach = new JLabel("Coach");
		lblCoach.setBounds(33, 57, 70, 15);
		contentPane.add(lblCoach);
		
		cbxCoach = new JComboBox<>();
		
		for (Staff staff : staffController.getStaffAsModel()) {
			cbxCoach.addItem(staff);
		}
		
		cbxCoach.setBounds(243, 52, 189, 24);
		contentPane.add(cbxCoach);
	}
}
