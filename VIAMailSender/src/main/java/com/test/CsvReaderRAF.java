package com.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CsvReaderRAF {
	
	public static void main(String[] args){
		
		System.out.println("starting csv reader..");
        String inputCsv = "src/main/resources/data/input.csv";
        String outputCsv = "src/main/resources/data/output.csv";
        String cvsSplitBy = ",";
        
        RandomAccessFile raf = null;
        String line = "";
        try {
			raf = new RandomAccessFile(inputCsv, "rw");
			while((line = raf.readLine()) != null){
				
				 // use comma as separator
              String[] emailData = line.split(cvsSplitBy);
              System.out.println("line num0="+raf.getFilePointer());
              System.out.println("0= " + emailData[0] + " , 1=" + emailData[1] + ", 2"+ emailData[2]);
              System.out.println("line num1="+raf.getFilePointer());
              
              raf.write("test2323".getBytes(), 0, 4);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
//        char SEPARATOR = ",";
       
//        writer.writeAll(csvBody);

        
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";

//        try {
//
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] emailData = line.split(cvsSplitBy);
//
//                System.out.println("0= " + emailData[0] + " , 1=" + emailData[1] + ", 2"+ emailData[2]);
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
	}

}
