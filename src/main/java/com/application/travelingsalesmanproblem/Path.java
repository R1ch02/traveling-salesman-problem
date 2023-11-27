package com.application.travelingsalesmanproblem;

import lombok.Data;

@Data
public class Path {

    double value;
    double from;
    double to;

    public Path(double value,double from, double to){
        setValue(value);
        setFrom(from);
        setTo(to);
    }


    public Path(double from, double to){
        setFrom(from);
        setTo(to);
    }

}
