package com.avenga.a360.controller;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.service.ParticipantService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/participants")
public class ParticipantController {
    @Inject
    ParticipantService participantService;

    @Path("/{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipantDto getParticipant(@PathParam("uid") String uid){
        Participant participant = participantService.findByUId(uid);
        ParticipantDto participantDto = participantService.ParticipantToParticipantDto(participant);

        return participantDto;
    }
}
