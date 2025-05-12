package candyBar.rest;

import candyBar.entities.Ingredient;
import candyBar.entities.SourceContract;
import candyBar.persistence.IngredientsDAO;
import candyBar.persistence.SourceContractsDAO;
import candyBar.rest.contracts.SourceContractDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/sourceContracts")
public class SorceContractController {

    @Inject
    @Setter @Getter
    private SourceContractsDAO sourceContractsDAO;

    @Inject
    @Setter @Getter
    private IngredientsDAO ingredientsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        SourceContract sourceContract = sourceContractsDAO.findOne(id);
        if (sourceContract == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SourceContractDto sourceContractDto = new SourceContractDto();
        sourceContractDto.setName(sourceContract.getName());
        sourceContractDto.setCountryOfOrigin(sourceContract.getCountryOfOrigin());
        sourceContractDto.setPrice(sourceContract.getPrice());
        sourceContractDto.setIngredientId(sourceContract.getIngredient().getId());

        return Response.ok(sourceContractDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(SourceContractDto sourceContractData) {
        try {
            Ingredient existingIngredient = ingredientsDAO.findOne(sourceContractData.getIngredientId());
            if (existingIngredient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            SourceContract newContract = new SourceContract();
            newContract.setName(sourceContractData.getName());
            newContract.setCountryOfOrigin(sourceContractData.getCountryOfOrigin());
            newContract.setPrice(sourceContractData.getPrice());
            newContract.setIngredient(existingIngredient);

            this.sourceContractsDAO.persist(newContract);
            existingIngredient.getSourceContracts().add(newContract);
            ingredientsDAO.update(existingIngredient);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer sourceContractId,
            SourceContractDto sourceContractData) {
        try {
            SourceContract existingSourceContract = sourceContractsDAO.findOne(sourceContractId);
            if (existingSourceContract == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSourceContract.setName(sourceContractData.getName());
            existingSourceContract.setCountryOfOrigin(sourceContractData.getCountryOfOrigin());
            existingSourceContract.setPrice(sourceContractData.getPrice());

            sourceContractsDAO.update(existingSourceContract);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
