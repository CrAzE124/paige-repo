package com.paigeapp.gui.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paigeapp.controller.TeamController;
import com.paigeapp.controller.UserController;
import com.paigeapp.database.model.Team;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class NewUserFrame extends JFrame {
	private TeamController teamController = new TeamController();
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private JTextField txtSurname;

	/**
	 * Create the frame.
	 */
	public NewUserFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(39, 30, 97, 15);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(175, 28, 247, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 68, 91, 15);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(175, 66, 247, 19);
		contentPane.add(txtPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(39, 105, 70, 15);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(175, 103, 247, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(39, 141, 70, 15);
		contentPane.add(lblAge);
		
		JComboBox cbxAge = new JComboBox();
		cbxAge.setModel(new DefaultComboBoxModel(new String[] {"18", "19", "20", "21", "22", "23", "24", "25", "26", "27"}));
		cbxAge.setBounds(175, 136, 247, 24);
		contentPane.add(cbxAge);
		
		NumberFormat numberFormat = DecimalFormat.getInstance();
		
		numberFormat.setMinimumIntegerDigits(0);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		
		JFormattedTextField frmtdtxtfldSalary = new JFormattedTextField(numberFormat);
		frmtdtxtfldSalary.setBounds(175, 172, 247, 19);
		contentPane.add(frmtdtxtfldSalary);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(39, 174, 70, 15);
		contentPane.add(lblSalary);
		
		JComboBox cbxTeam = new JComboBox();
		
		for (Team team : teamController.getTeamsAsModel()) {
			cbxTeam.addItem(team);
		}
		
		cbxTeam.setBounds(175, 245, 247, 24);
		contentPane.add(cbxTeam);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(39, 250, 70, 15);
		contentPane.add(lblTeam);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(39, 201, 70, 15);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(175, 203, 247, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		JButton btnAddUser = new JButton("Add User");
		JFrame thisFrame = this;
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController userController = new UserController();
				
				userController.addPlayer(
						txtUsername.getText(), 
						txtPassword.getText(), 
						txtName.getText(), 
						Integer.valueOf((String) cbxAge.getItemAt(cbxAge.getSelectedIndex())), 
						192.90,
						txtSurname.getText(),
						((Team) cbxTeam.getSelectedItem()).getId()
				);
				
				JOptionPane.showMessageDialog(null, "Successfully added user");
				thisFrame.dispose();
			}
		});
		btnAddUser.setBounds(39, 276, 117, 25);
		contentPane.add(btnAddUser);
	}
}
