package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("dataverse/")
public class Dataverse2dbRestApi {

	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response dataverseById(@PathParam("id") final int id) {
		
		int idFake = 19;
		
		//Sent rest api by id
		
		//binary inputstream -> save to upload
		
		Client c = ClientBuilder.newClient();
        WebTarget target = c.target(UriBuilder.fromUri("https://apitest.dataverse.org/api/dataverses/db?key=d0eeea84-b226-4f26-9ea6-67eae6247ff5").build());
//        String responseMsg = target.path("rest").path("test").request().get(String.class);
        String responseMsg = target.request().get(String.class);
        System.out.println(responseMsg);
		
		return Response.status(200).entity("{\"msg\" : \"Download Success\"}").build();
		
	}
}

