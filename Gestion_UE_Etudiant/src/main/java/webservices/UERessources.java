package webservices;

import metiers.UniteEnseignementBusiness;
import entities.UniteEnseignement;

import javax.ws.rs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UERessources {
    //getALLUe

   static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @Path("/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getALLUe() {
        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201)
                    .entity("added successfully")
                    .build();
        } else {
            return Response.status(409)
                    .entity("already exists")
                    .build();
        }
    }


    @Path("/delete/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200)
                    .entity("deleted successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("not found")
                    .build();
        }}

    //search by code
    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search (@QueryParam(value="s")int semestre) {
        return Response.status(200).entity(this.helper.getUEBySemestre(semestre)).build();
    }

}