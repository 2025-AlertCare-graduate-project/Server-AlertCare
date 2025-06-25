package com.alertcare.server.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String careGiverName;
    private String careReceiverName;
    private String careReceiverPhoneNumber;
    private int careReceiverAge;
}
