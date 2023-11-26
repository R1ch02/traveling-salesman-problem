package com.application.travelingsalesmanproblem;

import lombok.Data;

@Data
public class Path {

    double value;
    double from;
    double to;
    boolean addOrEx;

    public Path(double value,double from, double to){
        setValue(value);
        setFrom(from);
        setTo(to);
    }

    public Path(boolean addOrEx, double from, double to){
        setAddOrEx(addOrEx);
        setFrom(from);
        setTo(to);
    }

}
