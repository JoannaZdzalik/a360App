package com.avenga.a360.controller;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.UserEditDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.service.ParticipantService;
import com.avenga.a360.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/participants")
public class ParticipantResource {
    @Inject
    ParticipantService participantService;

    @Inject
    UserService userService;

    @Path("{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParticipantDto(@PathParam("uid") String uid) {
        Participant participant = participantService.findByUId(uid);
        if (participant != null) {
            ParticipantDto participantDto = participantService.participantToParticipantDto(participant);
            return Response.status(Response.Status.OK)
                    .entity(participantDto)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Participant dosen't exist")
                    .build();
        }
        //  return Application.validator(participant, participantDto,"Participant dosen't exist" );
    }

//    @Path("/editRole")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response editUserRole(UserEditDto userEditDto) {
//        boolean status = userService.updateUserRole(userEditDto);
//        if (status) {
//            return Response.status(Response.Status.CREATED).entity("User role editted").build();
//        } else return Response.status(Response.Status.BAD_REQUEST).entity("User role not editted").build();
//    }

}
