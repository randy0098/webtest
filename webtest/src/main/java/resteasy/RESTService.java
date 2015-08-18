package resteasy;

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

import vo.OrgnizationVO;
import dao.impl.OrgnizationDAOImpl;

@Path("/rest")
public class RESTService {
    @Autowired
    private OrgnizationDAOImpl orgDAO;
	
	@POST
	@Path("/getOrgList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrgnizationVO> getOrgList(OrgnizationVO form){
		System.out.println("form:" + form);
		ArrayList<OrgnizationVO> orgs = (ArrayList<OrgnizationVO>) orgDAO.findAll();
		return orgs;
	}
    
    @POST @Path("/addOrg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrg(OrgnizationVO org) throws Exception {
    	orgDAO.save(org);
    	return Response.status(200).build();
    }
    
    @GET @Path("/getOneOrg/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrgnizationVO getOneOrg(@PathParam("id") Long id) throws Exception {
    	OrgnizationVO org = orgDAO.get(id);
    	return org;
    }
    
    @POST @Path("/updateOrg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrg(OrgnizationVO org) throws Exception {
    	orgDAO.update(org);
    	return Response.status(200).build();
    }
    
//    @GET @Path("/deleteOrgs/{ids}")
////    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteOrgs(@PathParam("ids") String ids) throws Exception {
//    	orgDAO.deleteOrgs(ids);
//    	return Response.status(200).build();
//    }
    
	@GET
	@Path("/deleteOrgs/{id}")
	// @Produces(MediaType.APPLICATION_JSON)
	public Response deleteOrgs(@PathParam("id") Long id) throws Exception {
		orgDAO.delete(id);
		return Response.status(200).build();
	}
}
