package com.example.canproject.DTO;


import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String drivingLicenseNumber;
    private List<ReservationDTO> reservations;


}
