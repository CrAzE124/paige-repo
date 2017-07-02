package com.paigeapp.database.result;

import javax.swing.table.AbstractTableModel;

public class TableResult extends AbstractTableModel {
	private static final long serialVersionUID = 24L;
	
	private String [] columns;
	private Object[][] rows;
	
	public void setColumns(String [] columns) {
		this.columns = columns;
	}
	
	public String[] getColumns() {
		return columns;
	}
	
	public void setRows(Object[][] rows) {
		this.rows = rows;
	}
	
	public Object[][] getRows() {
		return rows;
	}

	@Override
	public int getRowCount() {
		return rows.length;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows[rowIndex][columnIndex];
	}
}
