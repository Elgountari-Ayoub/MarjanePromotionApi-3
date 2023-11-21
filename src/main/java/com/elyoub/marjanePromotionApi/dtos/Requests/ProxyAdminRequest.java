package com.elyoub.marjanePromotionApi.dtos.Requests;

import com.elyoub.marjanePromotionApi.entities.SuperAdmin;
import lombok.Data;

@Data
public class ProxyAdminRequest {

    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private SuperAdmin superAdmin;
}
