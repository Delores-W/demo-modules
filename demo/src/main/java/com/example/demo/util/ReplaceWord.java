package com.example.demo.util;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William
 * @date 11/21/21 10:59 PM
 * @description
 */
public class ReplaceWord {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("/Users/delores/Hubs/Work/Refinitiv/EJV/work/2021/Nov/EJVGCMT-7134/dtc_fer_fep_bcp.bcp"));
        List<String> list = new ArrayList<>();

        for (String line : lines) {
            String newLine = line.replaceAll("\\|", ",");
            list.add(newLine);
        }

        Writer out = new FileWriter("/Users/delores/Hubs/Work/Refinitiv/EJV/work/2021/Nov/EJVGCMT-7134/bcp_dbrs_19.csv");
        try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {
          for (String line : list) {
              System.out.println(line);
              bufferedWriter.write(line + "\r\n");
          }
        }

    }
}
