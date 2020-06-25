package com.example.retrofit;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Service implements Serializable {
    private String name;

    public String getName() {
        return name;
    }
}
