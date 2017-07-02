package com.paigeapp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.paigeapp.controller.LoginController;
import com.paigeapp.controller.PlayerController;
import com.paigeapp.controller.StaffController;
import com.paigeapp.controller.TeamController;
import com.paigeapp.controller.UserController;
import com.paigeapp.database.UserDatabaseAccess;
import com.paigeapp.database.result.TableResult;
import com.paigeapp.gui.fixtures.FixturesPanel;
import com.paigeapp.gui.staff.NewStaffFrame;
import com.paigeapp.gui.team.TeamFrame;
import com.paigeapp.gui.user.NewUserFrame;
import com.paigeapp.gui.user.UpdateUserFrame;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JMenuBar menuBar;
	
	//Menu
	private JPanel menuPanel;
	
	//Login
	private JPanel loginPanel;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Application Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 579);
		
		initPanels();
	}
	
	//Coach Roster
	private JPanel coachRosterPanel;
	
	//Player roster
	private JPanel playerRosterPanel;
	private JLabel lblIncorrectUsername;
	private JTable playerRosterTable;
	private JButton btnDeleteSelectedRow;
	private JButton btnInsertNewItem;
	private PlayerController playerController = new PlayerController();
	private JButton btnUpdateUser;
	private JButton btnRefresh;
	private JTable staffTable;
	
	//Staff roster
	private JPanel staffRosterPanel;
	private StaffController staffController = new StaffController();
	private JButton btnExit;
	private JButton btnDeleteSelectedRow_1;
	private JButton btnCreateNewStaff;
	private JButton btnUpdateSelectedItem;
	
	//Team
	private JPanel teamPanel;
	private JTable teamTable;
	private TeamController teamController = new TeamController();
	private JButton btnAddTeam;
	private JButton btnUpdateTeam;
	private JButton btnDeleteTeam;
	private JButton btnRefreshTeams;

	/**
	 * Initialize main panel
	 */
	private void initContentPane() {
		menuBar = new JMenuBar();
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmHomePage = new JMenuItem("Home Page");
		mntmHomePage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerRosterPanel.setVisible(false);
				menuPanel.setVisible(true);
			}
		});
		mnFile.add(mntmHomePage);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				menuBar.setVisible(false);
			}
		});
		mnFile.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
	}
	
	/**
	 * Initialize login panel
	 */
	private void initLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setName("login");
		contentPane.add(loginPanel, "name_2961778788051");
		loginPanel.setLayout(null);
		
		loginPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				setTitle("Application Login");
			}
		});
		
		txtUsername = new JTextField();
		txtUsername.setBounds(447, 135, 344, 19);
		loginPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(270, 137, 118, 15);
		loginPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(270, 187, 70, 15);
		loginPanel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(447, 185, 344, 19);
		loginPanel.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				try {
					LoginController loginController = new LoginController();
					
					if (!loginController.isUsernamePasswordValid(username, password)) {
						throw new RuntimeException("Username or password not valid");
					}
					
					lblIncorrectUsername.setVisible(false);
					menuBar.setVisible(true);
					
					loginPanel.setVisible(false);
					menuPanel.setVisible(true);
				} catch (RuntimeException ex) {
					System.out.println(ex.getMessage());
					
					lblIncorrectUsername.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(400, 285, 183, 53);
		loginPanel.add(btnLogin);
		
		lblIncorrectUsername = new JLabel("The username you entered was incorrect");
		lblIncorrectUsername.setVisible(false);
		lblIncorrectUsername.setBounds(347, 398, 433, 15);
		loginPanel.add(lblIncorrectUsername);
	}
	
	/**
	 * Initialize the menu panel
	 */
	private void initMenuPanel() {
		//TODO - make this use the MenuPanel 
		menuPanel = new JPanel();
		contentPane.add(menuPanel, "name_2985312170414");
		menuPanel.setLayout(null);
		
		JButton btnOpenPlayerScreen = new JButton("Users");
		btnOpenPlayerScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				playerRosterPanel.setVisible(true);
			}
		});
		btnOpenPlayerScreen.setBounds(902, 484, 117, 25);
		menuPanel.add(btnOpenPlayerScreen);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffRosterPanel.setVisible(true);
				menuPanel.setVisible(false);
			}
		});
		btnStaff.setBounds(438, 63, 117, 25);
		menuPanel.add(btnStaff);
		
		btnExit = new JButton("Exit");
		JFrame thisFrame = this;
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnExit.setBounds(438, 288, 117, 25);
		menuPanel.add(btnExit);
		
		JButton btnTeams = new JButton("Teams");
		btnTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamPanel.setVisible(true);
				menuPanel.setVisible(false);
			}
		});
		btnTeams.setBounds(438, 101, 117, 25);
		menuPanel.add(btnTeams);
		
		JButton btnFixtures = new JButton("Fixtures");
		btnFixtures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(new FixturesPanel());
			}
		});
		btnFixtures.setBounds(438, 138, 117, 25);
		menuPanel.add(btnFixtures);
		
		menuPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				setTitle("Option Select");
			}
		});
	}
	
	private void initPlayerTable() {
		TableResult tableResult = playerController.getPlayers();
		
		playerRosterTable.setModel(tableResult);
		
		playerRosterTable.updateUI();
	}
	
	/**
	 * Initialize the player panel
	 */
	private void initPlayRosterPanel() {		
		playerRosterPanel = new JPanel();
		contentPane.add(playerRosterPanel, "name_6984261615644");
		playerRosterPanel.setLayout(null);
		
		playerRosterTable = new JTable();
		playerRosterTable.setBounds(0, 0, 1019, 440);
		JScrollPane scrollPane = new JScrollPane(playerRosterTable);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(1031, 449);
		playerRosterPanel.add(scrollPane);
		
		btnDeleteSelectedRow = new JButton("Delete Selected Row");
		btnDeleteSelectedRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = playerRosterTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "You must select a user to delete");
					
					return;
				}
				
				int playerId = (int) playerRosterTable.getValueAt(selectedRow, 0);
				
				playerController.deletePlayer(playerId);
				initPlayerTable();
				JOptionPane.showMessageDialog(null, "Successfully deleted the user");
			}
		});
		btnDeleteSelectedRow.setBounds(236, 461, 214, 25);
		btnDeleteSelectedRow.setVisible(false);
		playerRosterPanel.add(btnDeleteSelectedRow);
		
		btnInsertNewItem = new JButton("Insert New Item");
		btnInsertNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserFrame newUserFrame = new NewUserFrame();
				newUserFrame.setVisible(true);
				
				newUserFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent ev) {
						initPlayerTable();
					}
				});
			}
		});
		btnInsertNewItem.setBounds(10, 461, 214, 25);
		playerRosterPanel.add(btnInsertNewItem);
		
		btnUpdateUser = new JButton("Update User");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateUserFrame updateUserFrame = new UpdateUserFrame((int) playerRosterTable.getValueAt(playerRosterTable.getSelectedRow(), 0));
				updateUserFrame.setVisible(true);
				
				updateUserFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent ev) {
						initPlayerTable();
					}
				});
			}
		});
		btnUpdateUser.setBounds(462, 461, 162, 25);
		btnUpdateUser.setVisible(false);
		playerRosterPanel.add(btnUpdateUser);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initPlayerTable();
			}
		});
		btnRefresh.setBounds(902, 461, 117, 25);
		playerRosterPanel.add(btnRefresh);
		
		playerRosterPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				setTitle("Player Screen");
				
				initPlayerTable();
			}
		});
		
		playerRosterTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || playerRosterTable.getSelectedRow() == -1) {
					btnDeleteSelectedRow.setVisible(false);
					btnUpdateUser.setVisible(false);
					
					return;
				}
				
				btnDeleteSelectedRow.setVisible(true);
				btnUpdateUser.setVisible(true);
				
				int selectedRow = playerRosterTable.getSelectedRow();
				
				System.out.println("Row selected: " + selectedRow);
			}
		});
	}
	
	private void initStaffTable() {
		TableResult tableResult = staffController.getStaff();
		
		staffTable.setModel(tableResult);
		
		staffTable.updateUI();
	}
	
	private void initStaffPanel() {
		staffRosterPanel = new JPanel();
		contentPane.add(staffRosterPanel, "name_2641119531328");
		staffRosterPanel.setLayout(null);
		
		staffTable = new JTable();
		
		JScrollPane scrollPane_1 = new JScrollPane(staffTable);
		scrollPane_1.setBounds(0, 0, 1031, 434);
		staffRosterPanel.add(scrollPane_1);
		
		btnDeleteSelectedRow_1 = new JButton("Delete Selected Row");
		btnDeleteSelectedRow_1.setToolTipText("Delete the staff member you just selected");
		btnDeleteSelectedRow_1.setVisible(false);
		btnDeleteSelectedRow_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = staffTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "You must select a staff member to delete");
					
					return;
				}
				
				int staffId = (int) staffTable.getValueAt(selectedRow, 0);
				
				staffController.deleteStaff(staffId);
				initStaffTable();
				JOptionPane.showMessageDialog(null, "Successfully deleted the staff member");
			}
		});
		btnDeleteSelectedRow_1.setBounds(356, 446, 230, 25);
		staffRosterPanel.add(btnDeleteSelectedRow_1);
		
		btnCreateNewStaff = new JButton("Create New Staff Member");
		btnCreateNewStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewStaffFrame newStaffFrame = new NewStaffFrame();
				newStaffFrame.setVisible(true);
			}
		});
		btnCreateNewStaff.setBounds(10, 446, 334, 25);
		staffRosterPanel.add(btnCreateNewStaff);
		
		btnUpdateSelectedItem = new JButton("Update Selected Item");
		btnUpdateSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = staffTable.getSelectedRow();
				
				int staffId = (int) staffTable.getValueAt(selectedRow, 0);
				
				NewStaffFrame newStaffFrame = new NewStaffFrame(staffId);
				newStaffFrame.setVisible(true);
			}
		});
		btnUpdateSelectedItem.setBounds(598, 446, 187, 25);
		staffRosterPanel.add(btnUpdateSelectedItem);
		
		staffRosterPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				setTitle("Staff Page");
				
				initStaffTable();
			}
		});
		
		staffTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || staffTable.getSelectedRow() == -1) {
					btnDeleteSelectedRow_1.setVisible(false);
					btnUpdateSelectedItem.setVisible(false);
//					btnUpdateUser.setVisible(false);
					
					return;
				}
				
				btnDeleteSelectedRow_1.setVisible(true);
				btnUpdateSelectedItem.setVisible(true);
//				btnUpdateUser.setVisible(true);
			}
		});
	}
	
	private void initTeamTable() {
		TableResult tableResult = teamController.getTeams();
		
		teamTable.setModel(tableResult);
		
		teamTable.updateUI();
	}
	
	private void initTeamPanel() {
		teamPanel = new JPanel();
		contentPane.add(teamPanel, "name_426798350567");
		teamPanel.setLayout(null);
		
		teamPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				setTitle("Team Page");
				
				initTeamTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1031, 454);
		teamPanel.add(scrollPane);
		
		teamTable = new JTable();
		teamTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(teamTable);
		
		teamTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || teamTable.getSelectedRow() == -1) {
					btnUpdateTeam.setVisible(false);
					btnDeleteTeam.setVisible(false);
					
					return;
				}
				
				btnUpdateTeam.setVisible(true);
				btnDeleteTeam.setVisible(true);
			}
		});
		
		btnAddTeam = new JButton("Add Team");
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamFrame teamFrame = new TeamFrame();
				teamFrame.setVisible(true);
			}
		});
		btnAddTeam.setBounds(10, 466, 117, 25);
		teamPanel.add(btnAddTeam);
		
		btnUpdateTeam = new JButton("Update Team");
		btnUpdateTeam.setVisible(false);
		btnUpdateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = teamTable.getSelectedRow();
				
				int teamId = (int) teamTable.getValueAt(selectedRow, 0);
				
				TeamFrame teamFrame = new TeamFrame(teamId);
				teamFrame.setVisible(true);
			}
		});
		btnUpdateTeam.setBounds(139, 466, 146, 25);
		teamPanel.add(btnUpdateTeam);
		
		btnDeleteTeam = new JButton("Delete Team");
		btnDeleteTeam.setVisible(false);
		btnDeleteTeam.setBounds(297, 466, 146, 25);
		teamPanel.add(btnDeleteTeam);
		
		btnRefreshTeams = new JButton("Refresh Teams");
		btnRefreshTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initTeamTable();
			}
		});
		btnRefreshTeams.setBounds(845, 466, 174, 25);
		teamPanel.add(btnRefreshTeams);
	}
	
	/**
	 * Initialize all panels
	 */
	private void initPanels() {
		initContentPane();
		initLoginPanel();
		initMenuPanel();
		initPlayRosterPanel();
		initStaffPanel();
		initTeamPanel();
	}
}
