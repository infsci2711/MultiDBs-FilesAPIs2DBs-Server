package edu.pitt.sis.infsci2711.multidbs.filesapi2dbs.rest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest.Dataverse2dbRestApi;

public class test1Dataverse2dbRestApi {

	@Test
	public void test() {
		Dataverse2dbRestApi<Object> Dataverse2dbRestApitest= new Dataverse2dbRestApi<Object>();
		Dataverse2dbRestApitest.dataverseById(12);
		assertEquals(1, 1);
		fail("Not yet implemented");
	}

}
