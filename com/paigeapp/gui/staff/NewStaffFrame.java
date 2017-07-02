package com.paigeapp.gui.staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paigeapp.controller.StaffController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

public class NewStaffFrame extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField pwdPassword;
	private JComboBox cbxPosition;
	private JSpinner spinnerYearsPresent;
	private JFormattedTextField fmtSalary;
	private JSpinner spinnerAge;
	
	private JButton btnSave;
	
	private StaffController staffController = new StaffController();
	private JButton btnUpdate;
	
	public NewStaffFrame(int staffId) {
		init();
		
		Object[] staffMember = staffController.getStaffMember(staffId);
		
		//USERNAME, PASSWORD, NAME, SURNAME, AGE, POSITION, SALARY, YEARS_PRESENT
		String username = (String) staffMember[0];
		String password = (String) staffMember[1];
		String name = (String) staffMember[2];
		String surname = (String) staffMember[3];
		int age = (int) staffMember[4];
		String position = (String) staffMember[5];
		double salary = (double) staffMember[6];
		int yearsPresent = (int) staffMember[7];
		
		txtUsername.setText(username);
		txtName.setText(name);
		txtSurname.setText(surname);
		pwdPassword.setText(password);
		cbxPosition.setSelectedItem(position);
		spinnerYearsPresent.setValue(yearsPresent);
		spinnerAge.setValue(age);
		fmtSalary.setValue(salary);
		
		btnSave.setVisible(false);
		btnUpdate.setVisible(true);
	}

	/**
	 * Used when creating a new staff member
	 */
	public NewStaffFrame() {
		init();
		
		btnSave.setVisible(true);
		btnUpdate.setVisible(false);
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 893, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(34, 41, 113, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(34, 80, 70, 15);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(139, 39, 274, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(34, 124, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(34, 177, 70, 15);
		contentPane.add(lblSurname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(34, 225, 70, 15);
		contentPane.add(lblAge);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(34, 274, 70, 15);
		contentPane.add(lblPosition);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(34, 319, 70, 15);
		contentPane.add(lblSalary);
		
		JLabel lblYearsPresent = new JLabel("Years Present");
		lblYearsPresent.setBounds(34, 370, 113, 15);
		contentPane.add(lblYearsPresent);
		
		txtName = new JTextField();
		txtName.setBounds(131, 122, 272, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(129, 177, 274, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		cbxPosition = new JComboBox();
		cbxPosition.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Coach"}));
		cbxPosition.setBounds(131, 269, 272, 24);
		contentPane.add(cbxPosition);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(143, 78, 264, 19);
		contentPane.add(pwdPassword);
		
		SpinnerModel yearsSpinnerModel = new SpinnerNumberModel(0, 0, 50, 1);
		
		spinnerYearsPresent = new JSpinner();
		spinnerYearsPresent.setBounds(156, 368, 122, 20);
		spinnerYearsPresent.setModel(yearsSpinnerModel);
		contentPane.add(spinnerYearsPresent);
		
		NumberFormat numberFormat = DecimalFormat.getInstance();
		
		numberFormat.setMinimumIntegerDigits(0);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		
		fmtSalary = new JFormattedTextField(numberFormat);
		fmtSalary.setBounds(139, 317, 264, 19);
		contentPane.add(fmtSalary);
		
		SpinnerModel ageSpinnerModel = new SpinnerNumberModel(16, 16, 100, 1);
		
		spinnerAge = new JSpinner();
		spinnerAge.setBounds(139, 223, 122, 20);
		spinnerAge.setModel(ageSpinnerModel);
		contentPane.add(spinnerAge);
		
		JFrame thisFrame = this;
		btnSave = new JButton("Save");
		btnSave.setVisible(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = pwdPassword.getText();
				String name = txtName.getText();
				String surname = txtSurname.getText();
				int age = (int) spinnerAge.getValue();
				String position = (String) cbxPosition.getSelectedItem();
				double salary = (double) fmtSalary.getValue();
				int yearsPresent = (int) spinnerYearsPresent.getValue();
				
				staffController.addStaffMember(username, password, name, surname, age, position, salary, yearsPresent);
				
				JOptionPane.showMessageDialog(null, "Successfully created a staff member");
				
				thisFrame.dispose();
			}
		});
		btnSave.setBounds(34, 410, 117, 25);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.setBounds(166, 410, 117, 25);
		contentPane.add(btnUpdate);
	}
}
