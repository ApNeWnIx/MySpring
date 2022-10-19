package com.news.example;

import annotation.MyComponent;

@MyComponent
public class TestClass {

    public String hello(String name) {
        return "hello" + name;
    }
}
