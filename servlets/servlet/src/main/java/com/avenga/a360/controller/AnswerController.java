package com.avenga.a360.controller;

import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.AnswerService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/answers")
public class AnswerController {
    @Inject
    AnswerService answerService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAnswer(final List<AnswerDto> answersDto) {

       List<Status> statusList =answerService.createAnswersDto(answersDto);
       return Response.status(Response.Status.OK).entity(statusList).build()  ;

    }
}




