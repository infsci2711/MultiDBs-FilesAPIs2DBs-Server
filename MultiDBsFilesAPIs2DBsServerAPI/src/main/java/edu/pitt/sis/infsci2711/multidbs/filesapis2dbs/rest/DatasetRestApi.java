package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models.TableDBModel;



@Path("Dataset/")
public class DatasetRestApi {
	
	@Path("HelloDataset")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		
		return Response.status(200)
				.entity("{\"msg\" : \"Hello Dataset\"}").build();
	}

	@Path("DatasetName/{tbname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataset(@PathParam("tbname") final String tbname) throws Exception {
		
		//DB manipulation -> object by tbname
		SpssService ss = new SpssService();
		TableDBModel table=new TableDBModel();
		table=ss.readtable(tbname);
		//obeserve oject
		//System.out.println(table.getColumnName().get(1));
		//System.out.println(table.getRows().get(0).get(1));
		
		//return object
		
		return Response.status(200)
				.entity(table).build();
	}
	
}
