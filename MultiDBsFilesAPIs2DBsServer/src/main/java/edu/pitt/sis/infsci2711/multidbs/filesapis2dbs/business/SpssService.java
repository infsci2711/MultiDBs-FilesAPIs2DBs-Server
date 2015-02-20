package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao.SpssDAO;


public class SpssService {
	public boolean createTable(ArrayList<String> t)throws SQLException, Exception{
		boolean flag=SpssDAO.createTable(t);
		return flag;
	}
	
}
