package com.application.travelingsalesmanproblem.model;

import lombok.Data;

@Data
public class Path {

    private double value;
    private double from;
    private double to;

    public Path(double value,double from, double to){
        setValue(value);
        setFrom(from);
        setTo(to);
    }

}
