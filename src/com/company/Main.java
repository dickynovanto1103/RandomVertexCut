package com.company;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileInputStream in = null;

	    HashPartitioner hashPartitioner = new HashPartitioner(2000005, 3);
	    try {
	        in = new FileInputStream("/home/dickynovanto1103/Documents/ITB/Semester8/TA/Preprocess & HDRF/input.txt");
            Scanner sc = new Scanner(in);
            int cnt = 0;
            while(sc.hasNext()) {
                int src = sc.nextInt(), dest = sc.nextInt();
                //System.out.println("src: " + src  + " dest: " + dest);
                int hash = hashPartitioner.getPartition(src, dest );
                cnt++;
                if(cnt % 100000 == 0){
                    System.out.println(cnt);
                }
            }
        }catch (Exception e) {
	        e.printStackTrace();
        }

        System.out.println("replication: " + hashPartitioner.getReplicationFactor());
    }
}
