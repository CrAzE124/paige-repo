package com.paigeapp.gui.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.paigeapp.controller.UserController;
import com.paigeapp.database.result.TableResult;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUserFrame extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private UserController userController = new UserController();

	/**
	 * Create the frame.
	 */
	public UpdateUserFrame(int userId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 721, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] user = this.userController.getUserById(userId);		

		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(39, 30, 97, 15);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(175, 28, 247, 19);
		txtUsername.setText((String) user[0]);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 68, 91, 15);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(175, 66, 247, 19);
		txtPassword.setText((String) user[1]);
		contentPane.add(txtPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(39, 105, 70, 15);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(175, 103, 247, 19);
		txtName.setText((String) user[2]);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(39, 141, 70, 15);
		contentPane.add(lblAge);
		
		JComboBox cbxAge = new JComboBox();
		cbxAge.setModel(new DefaultComboBoxModel(new String[] {"18", "19", "20", "21", "22", "23", "24", "25", "26", "27"}));
		cbxAge.setBounds(175, 136, 247, 24);
		cbxAge.setSelectedItem(String.valueOf((int) user[3]));
		contentPane.add(cbxAge);
		
		NumberFormat numberFormat = DecimalFormat.getInstance();
		
		numberFormat.setMinimumIntegerDigits(0);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		
		JFormattedTextField frmtdtxtfldSalary = new JFormattedTextField(numberFormat);
		frmtdtxtfldSalary.setText("Salary");
		frmtdtxtfldSalary.setBounds(175, 172, 247, 19);
		frmtdtxtfldSalary.setText(String.valueOf((double) user[4])); 
		contentPane.add(frmtdtxtfldSalary);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(39, 174, 70, 15);
		contentPane.add(lblSalary);
		
		JFrame thisFrame = this;
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userController.updateUser(userId, txtUsername.getText(), txtPassword.getText(), txtName.getText(), Integer.valueOf((String) cbxAge.getSelectedItem()), Double.valueOf(frmtdtxtfldSalary.getText()));
				
				JOptionPane.showMessageDialog(null, "Successfully updated user ID " + userId);
				thisFrame.dispose();
			}
		});
		btnSave.setBounds(39, 240, 117, 25);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnCancel.setBounds(175, 240, 117, 25);
		contentPane.add(btnCancel);
	}

}
