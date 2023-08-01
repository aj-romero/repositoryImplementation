package types;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product implements ITypes {
    private String name;
    private int stock;

}
