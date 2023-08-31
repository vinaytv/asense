package com.alphasense.cloudservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MessageRequest {

    private String id;
    private String name;

    private Long amount;

}
