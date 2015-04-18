package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models.TableDBModel;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.JdbcUtil;

public class SpssDAO {

	String sql;
	String sq12;

	public boolean create1 (ArrayList<String> t) throws SQLException,
			Exception {

		try (Connection connection = JdbcUtil.getConnection()) {
			String sql2="create database if not exists "+t.get(0);

			String sql = "use "+t.get(0)+"; create table if not exists " + t.get(0) + "( ";
			for (int i = 1; i < t.size(); i++) {
				sql = sql + " " + t.get(i) + " ";

				i++;
				if (t.get(i) == "varchar") {
					sql += "varchar(40)";
					if ((i + 1) < t.size())
						sql += ",";
				} else {
					sql += t.get(i);
					if ((i + 1) < t.size())
						sql += ",";
				}

			}
			sql += ");";
			System.out.println(sql);
			boolean flag=false;
			try{
			Statement statement = connection.createStatement();
			Statement statement1 = connection.createStatement();
          
				 flag = statement.execute(sql2);
				flag=statement1.execute(sql);
				
			}
			catch (Exception e){
				System.out.println(e);
			}
			finally { return flag;}
		}
	}

	public boolean create(ArrayList<String> t) throws SQLException,
	Exception {

try (Connection connection = JdbcUtil.getConnection()) {
//	String sql2="create database if not exists "+t.get(0);

	String sql = " create table if not exists " + t.get(0) + "( ";
	for (int i = 1; i < t.size(); i++) {
		sql = sql + " " + t.get(i) + " ";

		i++;
		if (t.get(i) == "varchar") {
			sql += "varchar(40)";
			if ((i + 1) < t.size())
				sql += ",";
		} else {
			sql += t.get(i);
			if ((i + 1) < t.size())
				sql += ",";
		}

	}
	sql += ");";
	System.out.println(sql);
	boolean flag=false;
	try{
	Statement statement = connection.createStatement();
	Statement statement1 = connection.createStatement();
  
//		 flag = statement.execute(sql2);
		flag=statement1.execute(sql);
		
	}
	catch (Exception e){
		System.out.println(e);
	}
	finally { return flag;}
}
}
	public int save(final String tableName,
			final ArrayList<ArrayList<String>> rows) throws SQLException,
			Exception {

		int res = 0;
		try (Connection connection = JdbcUtil.getConnection()) {
			for (int i = 0; i < rows.size(); i++) {
				sql = "INSERT INTO " + tableName + " VALUES (";
				for (int j=0;j<rows.get(i).size();j++) {
					Object o = rows.get(i).get(j);
					
						sql += "'" + o + "'";
					if(j!=rows.get(i).size()-1){
						sql += ",";
					}else{
						sql += ")";
					}
					

				}
				try (Statement statement = connection.createStatement()) {

					statement.executeUpdate(sql);
					res++;

				}
			}
			return res;
		}
	}
	public TableDBModel readtable(String tbname) throws Exception{
		TableDBModel table=new TableDBModel();
		try (Connection connection = JdbcUtil.getConnection()) {
			Statement statement = connection.createStatement();
			String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name ='"+tbname+"'";// 'your_table_name' and table_schema = 'your_db_name'		
			//System.out.println(sql);
		
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				table.addColumnName(rs.getString(1));
			}
			int n=table.columnNum();
			sql="select * from "+tbname;
			ResultSet rs1=statement.executeQuery(sql);
			while(rs1.next()){
				ArrayList<String> a=new ArrayList<String>();
				for(int i=1;i<=n;i++){
					a.add(rs1.getString(i));
				}
				table.addRow(a);
				
			}
		}
			catch(Exception e){
				
				System.out.println(e);
				
			}
		finally{
		return table;
		}
	}

}
