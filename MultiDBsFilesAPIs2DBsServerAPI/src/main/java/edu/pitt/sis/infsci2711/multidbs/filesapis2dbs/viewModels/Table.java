package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.viewModels;

import java.util.ArrayList;

public class Table {
	ArrayList<String> columnName=new ArrayList<String>();
	ArrayList<ArrayList<String>> rows=new ArrayList<ArrayList<String>>();
	public void addColumnName(String name){
		columnName.add(name);
	}
	public void addRow(ArrayList<String> row){
		rows.add(row);
	}
}
