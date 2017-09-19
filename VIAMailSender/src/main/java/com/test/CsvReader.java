package com.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CsvReader {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("starting csv reader..");
        String inputCsv = "src/main/resources/data/input.csv";
        String outputCsv = "src/main/resources/data/output.csv";
        CSVReader reader = null;
        CSVWriter writer = null;
        
        try{
        	reader = new CSVReader(new FileReader(inputCsv));
            writer = new CSVWriter(new FileWriter(outputCsv));
        String[] line;
        
        while ((line = reader.readNext()) != null) {
            System.out.println(line[0]+","+line[1]+","+line[2]);
            writer.writeNext(line);
            
        }
        }catch(IOException e){
        	e.printStackTrace();
        }finally {
			
        	try {
				reader.close();
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        


	}

}
