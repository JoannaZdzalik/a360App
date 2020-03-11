package com.avenga.a360.controller;

import com.avenga.a360.dto.EditDto.SessionEditDto;
import com.avenga.a360.dto.EmailConfDto;
import com.avenga.a360.model.EmailConf;
import com.avenga.a360.service.EmailConfigService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/config")
public class EmailConfResource {

    @Inject
    EmailConfigService emailConfigService;


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmailConfig() {
        EmailConfDto emailConfDto = emailConfigService.findEmailConfig();
        if (emailConfDto != null) {
            return Response.status(Response.Status.OK).entity(emailConfDto).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No such entity").build();
        }
    }

    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response editEmailConfig(EmailConfDto emailConfDto) {
        boolean status = emailConfigService.editEmailConfig(emailConfDto);
        if (status) {
            return Response.status(Response.Status.CREATED).entity("Edit config succesfull").build();
        } else return Response.status(Response.Status.BAD_REQUEST).entity("Fail on editConfig request").build();
    }
}
