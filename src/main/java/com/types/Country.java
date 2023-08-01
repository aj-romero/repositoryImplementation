package com.types;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Country implements ITypes {
    private String name;
    private String isoCode;
}
