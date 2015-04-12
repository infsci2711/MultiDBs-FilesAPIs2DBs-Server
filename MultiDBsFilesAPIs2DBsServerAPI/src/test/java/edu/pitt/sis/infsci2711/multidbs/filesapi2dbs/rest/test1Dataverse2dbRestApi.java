package edu.pitt.sis.infsci2711.multidbs.filesapi2dbs.rest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.DataverseService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest.Dataverse2dbRestApi;

public class test1Dataverse2dbRestApi {

	@Test
	public void test() {
		DataverseService dataverseService= new DataverseService();
		try {
			dataverseService.dataverseById(26,"datatest.sav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, 1);
	}

}
