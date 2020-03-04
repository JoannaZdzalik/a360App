package com.avenga.a360.controller;

import com.avenga.a360.dto.ParticipantDto;
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
public class SessionResource {
    @Inject
    SessionService sessionService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSessionFromSessionDto(final SessionDto sessionDto) {
        Status status = sessionService.createSession(sessionDto, sessionDto.getParticipantList());
        if (status.getStatus().equals("success")) {
            return Response.status(Response.Status.CREATED).entity(status).build();
        } else return Response.status(Response.Status.BAD_REQUEST).entity(status).build();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveSessions() {
        List<SessionDto> sessionDtoList = sessionService.findAllSessionsWhereIsSentFalse();
        return Response.status(Response.Status.OK).entity(sessionDtoList).build();
    }

    @Path("/get/{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionByParticipantUid(@PathParam("uid") String uid) {
        SessionDto sessionDto = sessionService.findSessionByParticipantUid(uid);
        if (sessionDto != null) {
            return Response.status(Response.Status.OK)
                    .entity(sessionDto)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Session does not exist")
                    .build();
        }
    }
}
