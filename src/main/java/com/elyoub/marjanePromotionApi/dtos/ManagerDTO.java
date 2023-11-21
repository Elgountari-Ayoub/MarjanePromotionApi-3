package com.elyoub.marjanePromotionApi.dtos;

import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerDTO {

    private ProxyAdmin admin;
    private String cin;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
