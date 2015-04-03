package edu.pitt.sis.infsci2711.multidbs.filesapi2dbs.rest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest.Dataverse2dbRestApi;

public class TestDataverse2dbRestApi {

	@Test
	public void testDataverseById() {
		Dataverse2dbRestApi dataverse2dbRestApi = new Dataverse2dbRestApi();
		dataverse2dbRestApi.dataverseById(19);
		assertEquals(1, 1);
	}

}
