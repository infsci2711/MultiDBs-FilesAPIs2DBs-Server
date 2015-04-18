package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models;

import java.util.ArrayList;

public class TableDBModel {
	ArrayList<String> columnName=new ArrayList<String>();
	ArrayList<ArrayList<String>> rows=new ArrayList<ArrayList<String>>();
	public void addColumnName(String name){
		columnName.add(name);
	}
	public void addRow(ArrayList<String> row){
		rows.add(row);
	}
	public int columnNum(){
		return columnName.size();
	}
	public ArrayList<String> getColumnName(){
		return columnName;
	}
	public ArrayList<ArrayList<String>> getRows(){
		return rows;
	}
}
