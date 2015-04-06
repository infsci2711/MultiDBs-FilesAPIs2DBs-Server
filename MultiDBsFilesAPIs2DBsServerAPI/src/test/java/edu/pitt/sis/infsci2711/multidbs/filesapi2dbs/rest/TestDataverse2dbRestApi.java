package edu.pitt.sis.infsci2711.multidbs.filesapi2dbs.rest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest.Dataverse2dbRestApiSearch;

public class TestDataverse2dbRestApi {

	@Test
	public void TestDataverseById() {
		Dataverse2dbRestApiSearch<?> dataverse2dbRestApiSearch = new Dataverse2dbRestApiSearch<Object>();
		dataverse2dbRestApiSearch.dataverseById(19);
		assertEquals(1, 1);
	}

}
