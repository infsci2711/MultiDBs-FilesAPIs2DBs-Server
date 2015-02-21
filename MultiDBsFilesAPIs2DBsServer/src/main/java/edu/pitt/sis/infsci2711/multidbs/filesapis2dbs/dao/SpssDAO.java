package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models.PersonDBModel;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.JdbcUtil;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.JdbcUtil1;

public class SpssDAO {

	String sql;

	public boolean createTable(ArrayList<String> t) throws SQLException,
			Exception {

		try (Connection connection = JdbcUtil.getConnection()) {

			String sql = "create table if not exists" + t.get(0) + "( ";
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
			try (Statement statement = connection.createStatement()) {

				boolean flag = statement.execute(sql);

				return flag;
			}
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

}
