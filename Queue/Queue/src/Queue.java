import java.util.LinkedList;

public class Queue {
    private LinkedList list;

    public Queue() {
        list = new LinkedList();
    }
    public void enQueue (Object data){
        list.addLast(data);
    }
    public Object deQueue(){
        if (!list.isEmpty()){
            return list.removeFirst();
        }else {
            return " can not remove , because Queue is empty. ";
        }
    }
    public boolean isEmpty () {
        return list.size() == 0? true : false;
    }
    public Object Front(){
        if (!isEmpty()){
            return list.get(0);
        } else {
            return "can not front , because Queue is empty . ";
        }
    }
    public int size(){
        return list.size();
    }
}
