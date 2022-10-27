package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 10);
        int index = binarySearch(list, 3);
        System.out.println(index);
        //forLoopTheory();
        getEmployee(5).stream().forEach(System.out::println);
    }

    public static int binarySearch(List<Integer> arr, int target) {
        Collections.sort(arr);
        int length = arr.size();
        int left = 0, right = length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) == target) return mid;

            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void forLoopTheory() {

        List<List<String>> all = new ArrayList<>();

        all=Arrays.asList(Arrays.asList("ABC","DEF"),Arrays.asList("HIJ","SSS"));

        all.stream().map(x->x.contains("ABC")).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-------");
        all.stream().flatMap(x->x.stream()).collect(Collectors.toList()).forEach(System.out::println);
        all.stream().flatMap(x->x.stream()).collect(Collectors.toList());

        System.out.println(all);
    }

    public static List<Object> getEmployee(int num){
    return Stream.iterate(0,n->n+1)
            .limit(num)
            .map(x-> {
                return new Employer(x,"A"+x);

            })
            .collect(Collectors.toList());
    }
    private static class Employer{
        public Employer(int age, String name) {
            this.age = age;
            this.name = name;
        }

        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return age+" "+name;
        }
    }
}
