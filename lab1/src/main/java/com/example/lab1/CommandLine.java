package com.example.lab1;

import com.example.lab1.utils.service.SkinService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CommandLine implements CommandLineRunner {
    private SkinService skinService;

    @Autowired
    public CommandLine(SkinService skinService) {
        this.skinService = skinService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        skinService.findAll().forEach(System.out::println);
    }
}
