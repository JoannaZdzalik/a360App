package com.avenga.a360.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String sendTo;
    private String subject;
    private String body;
    private List<String> emailList;
    private List<String> linkList;
    private List<String> questionList;
    private List<String> answerList;

    public Email(String sendTo, String subject, String body) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.body = body;
    }
}
