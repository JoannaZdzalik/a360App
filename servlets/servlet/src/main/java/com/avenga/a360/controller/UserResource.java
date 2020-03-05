package com.avenga.a360.controller;

import com.avenga.a360.dto.UserDto;
import com.avenga.a360.dto.UserEditDto;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDto userDto) {
        Status status = userService.createUser(userDto);
        if (status.getStatus().equals("Success")) {
            return Response.status(Response.Status.CREATED).entity(status).build();
        } else return Response.status(Response.Status.BAD_REQUEST).entity(status).build();
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<UserDto> userDtoList = userService.findAllUsers();
        return Response.status(Response.Status.OK).entity(userDtoList).build();
    }

    @Path("/editRole")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response editUserRole(UserEditDto userEditDto) {
        boolean status = userService.updateUserRole(userEditDto);
        if (status) {
            return Response.status(Response.Status.CREATED).entity("User role editted").build();
        } else return Response.status(Response.Status.BAD_REQUEST).entity("User role not editted").build();
    }
}
