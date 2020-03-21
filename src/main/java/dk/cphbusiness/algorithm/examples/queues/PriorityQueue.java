package dk.cphbusiness.algorithm.examples.queues;

public interface PriorityQueue<T extends Comparable<T>> extends Queue<T> {
    void Sort(T[] inputArr);
    void SortAsc(T[] inputArr);
    void SortDesc(T[] inputArr);
  }
