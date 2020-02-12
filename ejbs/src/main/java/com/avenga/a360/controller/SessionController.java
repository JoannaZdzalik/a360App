package com.avenga.a360.controller;

import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SessionService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/session")
public class SessionController {
    @Inject
    SessionService sessionService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String save(final SessionDto sessionDto) {
        String status = null;
        try {
            Status get = sessionService.createSession(sessionDto, sessionDto.getParticipantList());
            status = get.getStatus();
        } catch (Exception e) {
            status = "fail";
        }
        return status;
    }
}
