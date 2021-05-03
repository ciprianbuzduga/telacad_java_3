/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.rest;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import ro.telacad.service.MapPatientsService;


/**
 *
 * @author Buzy
 */
@Path("/cnp")
@Stateless
public class RestCNPService {
    
    @EJB
    private MapPatientsService mapPatientsService;
    
    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GenericEntity<List<String>> getCnpLikeQuery(@QueryParam("userId") String userId,
            @QueryParam("query") String query) {
        if(query == null || userId == null) {
            return null;
        }
        
        List<String> list = mapPatientsService.listCnpsNotUser(Integer.valueOf(userId), query);
        
        return new GenericEntity<List<String>>(list){};
    }
}
