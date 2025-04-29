package ru.netology.springbootdemo;

import org.springframework.stereotype.Component;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}