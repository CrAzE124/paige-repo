package com.paigeapp.gui.fixtures;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.paigeapp.controller.FixturesController;
import com.paigeapp.database.result.TableResult;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FixturesPanel extends JPanel {
	private static final long serialVersionUID = 31L;
	
	private FixturesController fixturesController = new FixturesController();
	private JTable tblFixtures;

	/**
	 * Create the panel.
	 */
	public FixturesPanel() {
		setLayout(new MigLayout("", "[1040px]", "[452px][25px]"));
		init();
	}
	
	private void loadTableData() {
		TableResult tableResult = fixturesController.getFixtures();
		
		tblFixtures.setModel(tableResult);
		tblFixtures.updateUI();
	}
	
	private void init() {
		tblFixtures = new JTable();
		loadTableData();
		
		tblFixtures.setBounds(0, 24, 1040, 346);
		add(tblFixtures.getTableHeader(), BorderLayout.NORTH);
		add(tblFixtures, "cell 0 0,grow");
		
		JButton btnAddFixture = new JButton("Add Fixture");
		btnAddFixture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame newFixturesPanel = new NewFixtureFrame();
				newFixturesPanel.setVisible(true);
			}
		});
		
		JButton btnUpdateFixture = new JButton("Update Fixture");
		btnUpdateFixture.setVisible(false);
		add(btnUpdateFixture, "flowx,cell 0 1");
		
		JButton btnDeleteFixture = new JButton("Delete Fixture");
		btnDeleteFixture.setVisible(false);
		add(btnDeleteFixture, "cell 0 1");
		
		btnAddFixture.setBounds(29, 420, 339, 25);
		add(btnAddFixture, "cell 0 1,alignx left,aligny top");
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTableData();
			}
		});
		add(btnRefreshTable, "cell 0 1,alignx right,aligny center");
		
		tblFixtures.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || tblFixtures.getSelectedRow() == -1) {
					btnDeleteFixture.setVisible(false);
					btnUpdateFixture.setVisible(false);
				} else {
					btnDeleteFixture.setVisible(true);
					btnUpdateFixture.setVisible(true);
				}
			}
		});
		
		btnDeleteFixture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedFixtureId = (int) tblFixtures.getValueAt(tblFixtures.getSelectedRow(), 0);
				
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this fixture?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

				if (dialogResult == JOptionPane.YES_OPTION) {
					fixturesController.deleteFixture(selectedFixtureId);
					
					JOptionPane.showMessageDialog(null, "Successfully deleted the fixture");
					loadTableData();
				}
			}
		});
		
		btnUpdateFixture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedFixtureId = (int) tblFixtures.getValueAt(tblFixtures.getSelectedRow(), 0);
				
				JFrame updateFixtureFrame = new NewFixtureFrame(selectedFixtureId);
				updateFixtureFrame.setVisible(true);
			}
		});
	}
}
