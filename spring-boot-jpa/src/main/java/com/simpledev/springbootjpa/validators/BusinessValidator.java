package com.simpledev.springbootjpa.validators;

import org.springframework.stereotype.Component;

@Component
public class BusinessValidator implements IBusinessValidator {

    public double calculateMax(int in, int out){
        return (in*Math.pow(2,out));
    }
}
