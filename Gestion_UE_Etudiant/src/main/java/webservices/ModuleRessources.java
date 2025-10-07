package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleRessources {

    static ModuleBusiness helper = new ModuleBusiness();

    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        List<Module> modules = helper.getAllModules();
        return Response.status(200)
                .entity(modules)
                .build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201)
                    .entity("Module added successfully")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Failed to add module ")
                    .build();
        }
    }


    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module deleted successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

    // 🔹 4. Rechercher un module par matricule
    @GET
    @Path("/search/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        return Response.status(200).entity(this.helper.getModuleByMatricule(matricule)).build();

    }

    // 🔹 5. Mettre à jour un module
    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        if (helper.updateModule(matricule, updatedModule)) {
            return Response.status(200)
                    .entity("Module updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

}
