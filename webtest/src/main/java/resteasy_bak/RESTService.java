package resteasy_bak;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/rest")
public class RESTService {
    @Autowired
    private OrgnizationDAO orgDAO;
    
//    @Deprecated
//	@POST
//	@Path("/getOrgList")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<Orgnization> getOrgList(@Form Orgnization form){
//		ArrayList<Orgnization> orgs = orgDAO.getOrgList(form);
//		return orgs;
//	}
//	
//	@Deprecated
//	@GET
//	@Path("/getOrgList")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<Orgnization> getOrgList(){
//		ArrayList<Orgnization> orgs = orgDAO.getOrgList();
//		return orgs;
//	}
	
	@POST
	@Path("/getOrgList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Orgnization> getOrgList(Orgnization form){
		System.out.println("form:" + form);
		ArrayList<Orgnization> orgs = orgDAO.getOrgList(form);
		return orgs;
	}
    
    @POST @Path("/addOrg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrg(Orgnization org) throws Exception {
//    	Orgnization org = new Orgnization();
//    	org.setName(form.getName());
//    	org.setEmail(form.getEmail());
    	orgDAO.addOrg(org);
    	return Response.status(200).build();
    }
    
//    @Deprecated
//    @GET @Path("/addOrg/{name}/{email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addOrg(@PathParam("name") String name, @PathParam("email") String email) throws Exception {
//    	Orgnization org = new Orgnization();
//    	org.setName(name);
//    	org.setEmail(email);
//    	orgDAO.addOrg(org);
//    	return Response.status(200).entity("success").build();
//    }
    
    @GET @Path("/getOneOrg/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Orgnization getOneOrg(@PathParam("id") long id) throws Exception {
    	Orgnization org = orgDAO.getOneOrg(id);
    	return org;
    }
    
    @POST @Path("/updateOrg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrg(Orgnization org) throws Exception {
    	orgDAO.updateOrg(org);
    	return Response.status(200).build();
    }
    
//    @Deprecated
//    @POST @Path("/updateOrg")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response updateOrg(@Form Orgnization form) throws Exception {
//    	Orgnization org = new Orgnization();
//    	org.setId(form.getId());
//    	org.setName(form.getName());
//    	org.setEmail(form.getEmail());
//    	orgDAO.updateOrg(org);
//    	return Response.status(200).build();
//    }
    
//    @Deprecated
//    @GET @Path("/updateOrg/{name}/{email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateOrg(@PathParam("name") String name, @PathParam("email") String email) throws Exception {
//    	Orgnization org = new Orgnization();
//    	org.setName(name);
//    	org.setEmail(email);
//    	orgDAO.updateOrg(org);
//    	return Response.status(200).entity("success").build();
//    }
    
//    @Deprecated
//    @GET @Path("/deleteOrg/{name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteOrg(@PathParam("name") String name) throws Exception {
//    	Orgnization org = new Orgnization();
//    	org.setName(name);
//    	orgDAO.deleteOrg(org);
//    	return Response.status(200).entity("success").build();
//    }
    
    @GET @Path("/deleteOrgs/{ids}")
//    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrgs(@PathParam("ids") String ids) throws Exception {
    	orgDAO.deleteOrgs(ids);
    	return Response.status(200).build();
    }
}
