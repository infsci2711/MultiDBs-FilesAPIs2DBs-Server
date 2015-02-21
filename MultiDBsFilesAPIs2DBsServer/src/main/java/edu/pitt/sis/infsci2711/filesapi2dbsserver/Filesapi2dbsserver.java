package edu.pitt.sis.infsci2711.filesapi2dbsserver;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader1;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileTuples;

public class Filesapi2dbsserver {

	public static void main(String[] args) throws SQLException, Exception {
		FileReader1 fileReader1 = new FileReader1("upload_files/DatasetTest.sav");
		ArrayList<String> t=fileReader1.readSPSSCreat();
		SpssService s=new SpssService();
		boolean f=s.createTable(t);
		if(f==true){
			System.out.println("success!");
		}
		
		FileTuples file = new FileTuples("upload_files/DatasetTest.sav");
		int res = s.add(file);
		System.out.println(res);
}
}