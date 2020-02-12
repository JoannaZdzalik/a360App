package com.avenga.a360.controller;

import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SessionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sessions")
public class SessionController {


    @Inject
    SessionService sessionService;
/*    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Status createSession(final SessionDto sessionDto) {
        return sessionService.createSession(sessionDto, sessionDto.getParticipants());
    }*/

@GET
@Path("/m")
@Produces(MediaType.TEXT_PLAIN)
public  String message(){
    return "message";
}

}

