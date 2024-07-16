package manager;

public class Node<Task>  {
    private Task task;

    public Task getTask() {
        return task;
    }


    protected Node prev;
    protected Node next;

    public Node(Task data, Node<Task> prev, Node<Task> next) {
        this.task = data;
        this.next = next;
        this.prev = prev;
    }
}