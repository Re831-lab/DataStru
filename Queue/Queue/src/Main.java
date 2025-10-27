//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
Queue q = new Queue();
q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.enQueue(4);
        q.enQueue(5);
        System.out.println("The Queue is empty :"+ q.isEmpty());
        System.out.println("The Queue is size :"+ q.size());
        System.out.println("The Queue is Front :"+ q.Front());
        System.out.println("The Queue is size :"+ q.size());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());
        System.out.println("The Queue is deQueue :"+ q.deQueue());

    }
}