package com.avenga.a360.controller;

import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SessionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sessions")
public class SessionController {
    @Inject
    SessionService sessionService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSession(final SessionDto sessionDto) {

        Status get = sessionService.createSession(sessionDto, sessionDto.getParticipantList());
        String status = get.getStatus();
        return Response.status(Response.Status.OK).entity(status).build();

    }
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveSessions(){

        List<SessionDto> sessionDtoList = sessionService.findAllSessionsWhereIsSentFalse();




        return Response.status(Response.Status.OK).entity(sessionDtoList).build();
    }


}
