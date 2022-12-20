package com.example.demo.Chain_of_respnsibility;

abstract public class Handler {
    Handler nextHandler=null;
    public  Handler(Handler nextHandler){
        this.nextHandler=nextHandler;
    }
}
