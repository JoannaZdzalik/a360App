package com.avenga.a360.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;


@ApplicationPath("/A360")
public class Application extends javax.ws.rs.core.Application {

    public static Response validator(Object o, Object b, String message){
        if(o != null) {
            return Response.status(Response.Status.OK)
                    .entity(b)
                    .build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .build();
        }
    }

    public static Response validator(String o, String b, Object object, String message){
        if(o.equals(b)) {
            return Response.status(Response.Status.OK)
                    .entity(b)
                    .build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .build();
        }
    }


}
