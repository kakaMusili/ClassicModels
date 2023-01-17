package io.jotech.banksystem.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestResponse {
    boolean success;
    Object data;
    String message;
}
