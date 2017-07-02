package com.paigeapp.gui.fixtures;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paigeapp.controller.FixturesController;
import com.paigeapp.controller.TeamController;
import com.paigeapp.database.model.Team;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Font;

public class NewFixtureFrame extends JFrame {
	private TeamController teamController = new TeamController();
	private FixturesController fixturesController = new FixturesController();
	
	private JPanel contentPane;
	private JComboBox<Team> cbxTeamA;
	private JComboBox<Team> cbxTeamB;
	private JSpinner spinTeamAResult;
	private JSpinner spinTeamBResult;
	private JFormattedTextField formattedTextField;
	private DateFormat dateFormat;
	private JLabel lblPleaseFormatThe;
	
	private class FixtureModel {
		public int teamA;
		public int teamB;
		public int teamAResult;
		public int teamBResult;
		public String date;
	}
	
	private FixtureModel parseForm() {
		FixtureModel model = new FixtureModel();
		
		model.teamA = ((Team) cbxTeamA.getSelectedItem()).getId();
		model.teamB = ((Team) cbxTeamB.getSelectedItem()).getId();
		model.teamAResult = (Integer) spinTeamAResult.getValue();
		model.teamBResult = (Integer) spinTeamBResult.getValue();
		model.date = formattedTextField.getText();
		
		return model;
	}
	
	private boolean validateModel(FixtureModel model) {
		if (model.teamA == model.teamB) {
			JOptionPane.showMessageDialog(null, "The teams cannot be the same", "Error", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		try {
			dateFormat.parse(model.date);
			
			return true;
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Please enter the played date in the following format: YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return false;
	}

	public NewFixtureFrame() {
		setTitle("New Fixture");
		init();
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FixtureModel model = parseForm();
				
				if (validateModel(model)) {
					fixturesController.saveFixture(model.teamA, model.teamB, model.teamAResult, model.teamBResult, model.date);
					
					JOptionPane.showMessageDialog(null, "Successfully saved the fixture");
					dispose();
				}
			}
		});
		btnSave.setBounds(20, 177, 68, 25);
		contentPane.add(btnSave);
	}
	
	public NewFixtureFrame(int id) {
		setTitle("Update Fixture");
		init();
		
		Object[] fixture = fixturesController.getFixture(id);
		
		Object[] teamARaw = teamController.getTeam((int) fixture[1]);
		Object[] teamBRaw = teamController.getTeam((int) fixture[2]);
		Team teamA = new Team((int) teamARaw[0], (String) teamARaw[1]);
		Team teamB = new Team((int) teamBRaw[0], (String) teamBRaw[1]);
		
		cbxTeamA.setSelectedItem(teamA);
		cbxTeamB.setSelectedItem(teamB);
		spinTeamAResult.setValue((int) fixture[3]);
		spinTeamBResult.setValue((int) fixture[4]);
		
		try {
		formattedTextField.setValue(dateFormat.parse((String) fixture[5]));
		} catch (ParseException ex) {
			System.out.println("Error when parsing date from database");
		}
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FixtureModel model = parseForm();
				
				if (validateModel(model)) {					
					fixturesController.updateFixture(id, model.teamA, model.teamB, model.teamAResult, model.teamBResult, model.date);
					
					JOptionPane.showMessageDialog(null, "Successfully updated the fixture");
					dispose();
				}
			}
		});
		
		btnUpdate.setBounds(20, 177, 98, 25);
		contentPane.add(btnUpdate);
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeamA = new JLabel("Team A");
		lblTeamA.setBounds(20, 14, 51, 15);
		contentPane.add(lblTeamA);
		
		cbxTeamA = new JComboBox<>();
		
		for (Team team : teamController.getTeamsAsModel()) {
			cbxTeamA.addItem(team);
		}
		
		cbxTeamA.setBounds(168, 9, 163, 24);
		contentPane.add(cbxTeamA);
		
		JLabel lblTeamB = new JLabel("Team B");
		lblTeamB.setBounds(20, 78, 51, 15);
		contentPane.add(lblTeamB);
		
		cbxTeamB = new JComboBox<>();
		
		for (Team team : teamController.getTeamsAsModel()) {
			cbxTeamB.addItem(team);
		}
		
		cbxTeamB.setBounds(168, 73, 163, 24);
		contentPane.add(cbxTeamB);
		
		JLabel lblTeamAResult = new JLabel("Team A Result");
		lblTeamAResult.setBounds(20, 45, 100, 15);
		contentPane.add(lblTeamAResult);
		
		SpinnerModel spinTeamAModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinTeamAResult = new JSpinner(spinTeamAModel);
		spinTeamAResult.setBounds(168, 43, 163, 20);
		contentPane.add(spinTeamAResult);
		
		JLabel lblTeamBResult = new JLabel("Team B Result");
		lblTeamBResult.setBounds(20, 107, 100, 15);
		contentPane.add(lblTeamBResult);
		
		SpinnerModel spinTeamBModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinTeamBResult = new JSpinner(spinTeamBModel);
		spinTeamBResult.setBounds(168, 105, 163, 20);
		contentPane.add(spinTeamBResult);
		
		JLabel lblDatePlayed = new JLabel("Date Played");
		lblDatePlayed.setBounds(20, 134, 86, 15);
		contentPane.add(lblDatePlayed);
		
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		formattedTextField = new JFormattedTextField(dateFormat);
		formattedTextField.setToolTipText("Enter date in YYYY-MM-DD format");
		formattedTextField.setBounds(168, 132, 163, 19);
		contentPane.add(formattedTextField);
		
		lblPleaseFormatThe = new JLabel("Please format the date as YYYY-MM-DD");
		lblPleaseFormatThe.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblPleaseFormatThe.setBounds(349, 134, 239, 15);
		lblPleaseFormatThe.setVisible(false);
		contentPane.add(lblPleaseFormatThe);
	}
}
