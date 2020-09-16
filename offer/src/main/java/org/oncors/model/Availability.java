package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Availability {
    private int day;
    private String startTime;
    private String endTime;
    private Offer offer;

}
