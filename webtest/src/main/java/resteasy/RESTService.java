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
import vo.PageVO;
import dao.impl.OrgnizationDAOImpl;
import framework.HibernatePage;

@Path("/rest")
public class RESTService {
    @Autowired
    private OrgnizationDAOImpl orgDAO;
    @Autowired
    private HibernatePage page;
    
	@POST
	@Path("/getOrgListPage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PageVO getOrgListPage(OrgnizationVO form){
//		String sender = messageVO.getSender();
//		String receiver = messageVO.getReceiver();
//		String sql = " FROM MessageVO WHERE 1=1 ";
//		if (sender != null && sender.equalsIgnoreCase("") == false) {
//			sql = sql + " AND sender = '" + sender + "' ";
//		}
//		if (receiver != null && receiver.equalsIgnoreCase("") == false) {
//			sql = sql + " AND receiver LIKE '%" + receiver + "%' ";
//		}
//		if (mintime != null && mintime.equalsIgnoreCase("") == false) {
//			sql = sql + " AND msg_time >= '" + mintime + "' ";
//		}
//		if (maxtime != null && maxtime.equalsIgnoreCase("") == false) {
//			sql = sql + " AND msg_time <= '" + maxtime + "' ";
//		}
		
//		String currentPageIndex = form.getCurrentPageIndex();
//		String currentPageIndex1 = "";
//		if(currentPageIndex==null){
//			currentPageIndex1 = currentPageIndex;
//		}
		
		String sql = " FROM OrgnizationVO WHERE 1=1 ";
		page.setQuerySql(sql);
		page.setCountSql("SELECT COUNT(ID) " + sql);
		page.setPageRecordNum(1);
		page.paging(form.getAction(), String.valueOf(form.getCurrentPageIndex()));	
		PageVO pageVO = new PageVO();
		pageVO.setCurrentPageIndex(page.getCurrentPageIndex());
		pageVO.setPageRecordNum(page.getPageRecordNum());
		pageVO.setRecords(page.getRecords());
		return pageVO;
	}
    
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
    
	@GET
	@Path("/deleteOrgs/{ids}")
	public Response deleteOrgs(@PathParam("ids") String ids) throws Exception {
		String[] idArray = ids.split(",");
		for(String id: idArray){
			orgDAO.delete(Long.parseLong(id));
		}
		return Response.status(200).build();
	}
}
