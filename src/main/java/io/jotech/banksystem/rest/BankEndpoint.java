package io.jotech.banksystem.rest;

import java.math.BigDecimal;
import java.util.HashMap;

import io.jotech.banksystem.BankSystem;
import io.jotech.classicmodels.entity.Customer;
import io.jotech.classicmodels.vm.FormResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bank")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankEndpoint {

    @Inject
   private  BankSystem bankSystem;
    @POST
    @Path("/{principle}/{income}/{months}")
    public Response processLoanApplication(Customer customer,
                                         @PathParam("principle") BigDecimal principle,
                                           @PathParam("income")  BigDecimal income,
                                           @PathParam("months")    Integer months){

        boolean processed = bankSystem.processLoanApplication(customer, principle,income, months);

        //var res= FormResponse<RestResponse>
        //RestResponse.builder().success(true).data(processed).build()

        var map= new HashMap<String, Boolean>();
        map.put("processed", processed);
        return Response.ok().entity(
                map
        ).build();

    }
}
