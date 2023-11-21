package com.elyoub.marjanePromotionApi.dtos.Requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private  String password;
}
