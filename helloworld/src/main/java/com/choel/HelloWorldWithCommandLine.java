package com.choel;

public class HelloWorldWithCommandLine {
    public static void main(String... args) {
        if(args.length > 0) {
            System.out.print(args[0]);
        } else {
            System.out.print("hello world");
        }
    }
}
