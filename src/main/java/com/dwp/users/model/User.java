package com.dwp.users.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    int id;
    String first_name;
    String last_name;
    String email;
    String ip_address;
    double latitude;
    double longitude;


}
