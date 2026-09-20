package LW01.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        List<PrintJob> JobList = new ArrayList<>();

            while(sc.hasNext()){
             String type = sc.next();
             String id = sc.next();
             int pages = sc.nextInt();

             PrintJob job;

             if(type.equals("MONO")){
                job = new MonoPrint(id,pages);
             }else{
                job = new ColourPrint(id,pages);
             }
             JobList.add(job);
        }
        for(PrintJob job : JobList){
            System.out.println(job.summary());
        }
        sc.close();



    }
}




    

    

