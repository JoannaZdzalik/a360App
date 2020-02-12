package com.avenga.a360.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Test {

//    @Path("/get")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public JsonObject getAuthor(){
//        JsonObjectBuilder builder = Json.createObjectBuilder();
//        builder.add("jan", "elooo").add("msg", "test message");
//        return builder.build();
//    }

    @Path("/get")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAuthor(){

        return "asasasasa";
    }
}
