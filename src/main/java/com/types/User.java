package com.types;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements ITypes {
    private String userName;
    private String firstName;
    private String lastName;

}
