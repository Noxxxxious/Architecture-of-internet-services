package com.example.lab1;

import com.example.lab1.utils.entity.Champion;
import com.example.lab1.utils.entity.Skin;
import com.example.lab1.utils.service.ChampionService;
import com.example.lab1.utils.service.SkinService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private SkinService skinService;
    private ChampionService championService;
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public CommandLine(SkinService skinService, ChampionService championService) {
        this.skinService = skinService;
        this.championService = championService;
    }

    @Override
    public void run(String... args) throws Exception {
        printOpts();
        manageInput();
    }

    public void manageInput(){
        while(true){
            String command = scanner.nextLine();
            switch (command) {
                case "s" -> skinService.findAll().forEach(System.out::println);
                case "c" -> championService.findAll().forEach(System.out::println);
                case "x" -> System.exit(0);
                case "h" -> printOpts();
                case "+s" -> {
                    insertSkin();
                }
                case "+c" -> {
                    insertChampion();
                }
                case "-s" -> {
                    removeSkin();
                }
                case "-c" -> {
                    removeChampion();
                }
                default -> System.out.println("This command is invalid.");
            }
        }
    }

    public void insertSkin(){
        System.out.println("Name: ");
        String newName = scanner.nextLine();
        System.out.println("Price: ");
        while(!scanner.hasNextInt()){
            System.out.println("Invalid input");
            scanner.next();
        }
        int newPrice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Champion: ");
        String championName = scanner.nextLine();
        Optional<Champion> optionalChampion = championService.find(championName);
        optionalChampion.ifPresentOrElse(
                original -> {
                    Champion dstChampion = optionalChampion.get();
                    Skin newSkin = Skin.builder().name(newName).priceRP(newPrice).champion(dstChampion).splashArt(null).build();
                    skinService.create(newSkin);
                    System.out.println("Skin was successfully added.");

                },
                () -> System.out.println("This champion does not exist."));
    }

    public void insertChampion(){
        System.out.println("Name: ");
        String newName = scanner.nextLine();
        System.out.println("Price: ");
        while(!scanner.hasNextInt()){
            System.out.println("Invalid input");
            scanner.next();
        }
        int newPrice = scanner.nextInt();
        scanner.nextLine();
        Optional<Champion> optionalChampion = championService.find(newName);
        optionalChampion.ifPresentOrElse(
                original -> System.out.println("This champion already exists."),
                () -> {
                    Champion newChampion = Champion.builder().name(newName).priceBE(newPrice).splashArt(null).build();
                    championService.create(newChampion);
                    System.out.println("Champion was successfully added.");
                });
    }

    public void removeSkin(){
        System.out.println("Name: ");
        String name = scanner.nextLine();
        Optional<Skin> optionalSkin = skinService.find(name);
        optionalSkin.ifPresentOrElse(
                original -> {
                    skinService.delete(name);
                    System.out.println("Skin was successfully deleted.");
                },
                () -> {
                    System.out.println("This skin does not exist.");
                });
    }

    public void removeChampion(){
        System.out.println("Name: ");
        String name = scanner.nextLine();
        Optional<Champion> optionalChampion = championService.find(name);
        optionalChampion.ifPresentOrElse(
                original -> {
                    List<Skin> championsSkins = skinService.findByChampion(name);
                    for(Skin skin : championsSkins){
                        skinService.delete(skin.getName());
                    }
                    championService.delete(name);
                    System.out.println("Champion was successfully deleted.");
                },
                () -> {
                    System.out.println("This champion does not exist.");
                });
    }

    public void printOpts() {
        System.out.println("+---------------------------------------+");
        System.out.println("|-----------------Opts------------------|");
        System.out.println("|s     - prints all skins               |");
        System.out.println("|c     - prints all champions           |");
        System.out.println("|+s/+c - adds a new skin/champion       |");
        System.out.println("|-s/-c - removes existing skin/champion |");
        System.out.println("|x     - exits the program              |");
        System.out.println("|h     - prints available commands.     |");
        System.out.println("+---------------------------------------+");
    }
}
