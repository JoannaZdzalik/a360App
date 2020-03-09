package com.avenga.a360.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/a360")
public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(AnswerResource.class);
        set.add(ParticipantResource.class);
        set.add(QuestionResource.class);
        set.add(SessionResource.class);
        set.add(UserResource.class);
        return set;
    }

    public static Response validator(Object o, Object b, String message) {
        if (o != null) {
            return Response.status(Response.Status.OK)
                    .entity(b)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .build();
        }
    }

    public static Response validator(String o, String b, Object object, String message) {
        if (o.equals(b)) {
            return Response.status(Response.Status.OK)
                    .entity(b)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .build();
        }
    }
}
