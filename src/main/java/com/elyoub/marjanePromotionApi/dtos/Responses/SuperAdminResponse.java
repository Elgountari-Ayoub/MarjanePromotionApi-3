package com.elyoub.marjanePromotionApi.dtos.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperAdminResponse {
    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
