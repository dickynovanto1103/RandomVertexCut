package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import jdk.internal.util.xml.impl.Pair;

public class HashPartitioner {
    private int numVertex;
    private int numPartition;
    private List<TreeSet<Integer>> partitionOfVertices;

    public HashPartitioner(int numVertex, int numPartition) {
        this.numVertex = numVertex;
        this.numPartition = numPartition;
        setPartitionOfVertices();
    }

    private void setPartitionOfVertices() {
        partitionOfVertices = new ArrayList<TreeSet<Integer> >();
        for(int i = 0; i < this.numVertex; i++) {
            partitionOfVertices.add(new TreeSet<Integer>());
        }
    }

    private void addToPartition(int vertex, Integer idxPartition) {
        partitionOfVertices.get(vertex).add(idxPartition);
    }

    public int getPartition(int src, int dest) {
        Integer satu = src;
        Integer dua = dest;
        int hash = 31*satu.hashCode()%this.numPartition + dua.hashCode()%this.numPartition;
        hash = hash % this.numPartition;
        addToPartition(src, hash);
        addToPartition(dest, hash);
        return hash;
    }

    public double getReplicationFactor() {
        int sum = 0;
        int cnt = 0;
        for(int i=0;i<numVertex;i++){
            if(partitionOfVertices.get(i).size() > 0){
                sum += partitionOfVertices.get(i).size();
                cnt++;
            }
        }
        return (double)sum / (double)cnt;
    }
}
