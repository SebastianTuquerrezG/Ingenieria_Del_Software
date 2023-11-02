package co.edu.consumerickandmorty.apirestrickandmorty.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Info {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
