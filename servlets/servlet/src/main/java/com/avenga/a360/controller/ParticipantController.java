package com.avenga.a360.controller;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.service.ParticipantService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/participants")
public class ParticipantController {
    @Inject
    ParticipantService participantService;
    @Inject
    ParticipantDto participantDto;


    @Path("{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderItemByItemId(@PathParam("uid") String uid) {
        Participant participant = participantService.findByUId(uid);
      //  ParticipantDto participantDto = participantService.ParticipantToParticipantDto(participant);
        if(participant !=null ) {
            ParticipantDto participantDto = participantService.ParticipantToParticipantDto(participant);
            return Response.status(Response.Status.OK)
                    .entity(participantDto)
                    .build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Participant dosen't exist")
                    .build();
        }
      //  return Application.validator(participant, participantDto,"Participant dosen't exist" );
    }


}
