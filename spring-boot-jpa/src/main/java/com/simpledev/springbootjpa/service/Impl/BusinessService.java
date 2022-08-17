package com.simpledev.springbootjpa.service.Impl;

import com.simpledev.springbootjpa.validators.IBusinessValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusinessService {

    @Autowired
    private IBusinessValidator iBusinessValidator;

    @Getter
    @Setter
    @AllArgsConstructor
    class CllClass{
        int in;
        int out;
        private List<Integer> listOfall;
    }
    public int isItemValid(int in, int out){
        Predicate<Integer> moreThanHundred= x->x>1;

        List<Integer> itemNumbers= new ArrayList<>();
        itemNumbers.add(in);
        itemNumbers.add(out);
        double val=iBusinessValidator.calculateMax(in,out);
        itemNumbers.add((int)val);
        List<Object> fullList=new ArrayList<>();

        fullList.add(itemNumbers);
        fullList.add("TestNumbers");


        CllClass privateClass=new CllClass(in,out,itemNumbers);
        CllClass privateClass1=new CllClass(out,in,itemNumbers);

        List<CllClass> iteratorClass=new ArrayList<>();
        iteratorClass.add(privateClass);
        iteratorClass.add(privateClass1);

        int sum=iteratorClass.stream()
                .flatMap(o -> o.listOfall.stream())
                .filter(moreThanHundred)
                        .map(c->Optional.ofNullable(c).orElse(1)*c)
                                .collect(Collectors.summingInt(s->s.intValue()));

        System.out.println(sum);
        return sum;
    }
}
