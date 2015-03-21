package edu.pitt.sis.infsci2711.filesapi2dbsserver;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileTuples;

public class Filesapi2dbsserver {

	public static void main(String[] args) throws SQLException, Exception {
		FileReader2 fileReader1 = new FileReader2(
				"upload/DatasetTest.sav");
		ArrayList<String> t = fileReader1.readSPSSCreat();
		SpssService s = new SpssService();
		boolean f = s.createTable(t);
		if (f == true) {
			System.out.println("success!");
		}

		FileTuples file = new FileTuples("upload/DatasetTest.sav");
		int res = s.add(file);
		System.out.println(res);
	}
}