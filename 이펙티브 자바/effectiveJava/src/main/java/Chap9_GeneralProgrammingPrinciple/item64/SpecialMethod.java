package Chap9_GeneralProgrammingPrinciple.item64;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SpecialMethod {
    Queue<Integer> queue = new PriorityQueue<>();
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    Map<String, String> map = new LinkedHashMap<>();
    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();

    public void init() {
//        queue.comparator(); //없다!!
//        priorityQueue.comparator();

    }
}
