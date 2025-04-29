package ru.netology.springbootdemo;

import org.springframework.stereotype.Component;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}