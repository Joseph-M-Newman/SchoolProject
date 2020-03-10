package com.company;

import java.io.Console;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a path to read data from: ");
        String Text_File = scan.nextLine();
        System.out.println(".....Reading Data");
        System.out.println("Enter a schoolName: ");
        String schoolName = scan.nextLine();
        School SCI = new School(schoolName);

        SCI.readData(Text_File);


    }
}
