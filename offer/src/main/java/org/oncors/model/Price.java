package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Price {
    private int min;
    private int max;
    private String currency;
}
