package com.avenga.a360.model.response;

import com.avenga.a360.model.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private String status;
    private List<StatusMessage> statusMessageList;
}
