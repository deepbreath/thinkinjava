package day6;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;

public class Deque<T> {
    private LinkedList<T> deque=new LinkedList<>();
    public void addFirst(T e){deque.addFirst(e);}
    public void addLast(T e){deque.addLast(e);}
    public T getFirst(){return deque.getFirst();}
    public T getLast(){return deque.getLast();}
    public T removeFrist(){return deque.removeFirst();}
    public T removerLast(){return deque.removeLast();}
    public int size(){return deque.size();}


}
