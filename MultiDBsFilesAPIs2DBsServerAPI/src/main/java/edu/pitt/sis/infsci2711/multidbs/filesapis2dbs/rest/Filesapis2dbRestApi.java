package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("Filesapis2db/")
public class Filesapis2dbRestApi {

	@Path("HelloFilesapis2db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		return Response.status(200)
				.entity("{\"msg\" : \"Hello Filesapis2db\"}").build();
	}


	@Path("SPSSUpload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
	 
			String uploadedFileLocation = "e:/uploaded/" + fileDetail.getFileName();
	 
			// save it
			writeToFile(uploadedInputStream, uploadedFileLocation);
	 
			String output = "File uploaded to : " + uploadedFileLocation;
	 
			return Response.status(200).entity(output).build();
	 
		}
	 
		// save uploaded file to new location
		private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
	 
			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];
	 
				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {
	 
				e.printStackTrace();
			}
	 
		}

}
