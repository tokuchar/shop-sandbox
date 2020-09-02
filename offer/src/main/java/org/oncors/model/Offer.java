package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Offer {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Price price;
}