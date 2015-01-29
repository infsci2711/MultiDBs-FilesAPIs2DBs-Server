package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("Filesapis2db/")
public class Filesapis2dbRestApi {
	
	@Path("HelloFilesapis2db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld(){
		return Response.status(200).entity("{\"msg\" : \"Hello Filesapis2db\"}").build();
	}
	
}
