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

import org.json.JSONArray;



@Path("dataverse/")
public class Dataverse2dbRestApiSearch<JSONObject> {

	@Path("{file_name}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public org.json.JSONObject dataverseByName(@PathParam("file_name") final String file_name) {
		
		//Sent rest api by file_name = trees
		
		//binary inputstream -> save to upload
		
		Client c = ClientBuilder.newClient();
		
		String url = "https://apitest.dataverse.org/api/search?q=" + file_name + "&key=a23d0453-a1f7-4e02-a5f4-eed75946e2a2";
		
        WebTarget target = c.target(UriBuilder.fromUri(url).build());
//        String responseMsg = target.path("rest").path("test").request().get(String.class);
        String responseMsg = target.request().get(String.class);
       org.json.JSONObject json = new org.json.JSONObject(responseMsg);
       
       org.json.JSONObject data = json.getJSONObject("data");
       JSONArray item = data.getJSONArray("items");
       String line = "";
       for(int i=0; i<item.length();i++){
    	   org.json.JSONObject file = item.getJSONObject(i);
        if( file.has("file_id")){
        	line = "{\"file_id\": \"" + file.getString("file_id") + "\"}";
        }
       }
       
       org.json.JSONObject id = new org.json.JSONObject(line);

		return id;
		
	}
}

