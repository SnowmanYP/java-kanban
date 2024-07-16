package manager;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    //private final int CAPACITY = 10; //5 - если тестировать в Main
    public List<Task> history = new ArrayList<>(/*CAPACITY*/);
    public Map<Integer, Node> historyMap = new HashMap<>();

    private Node head = null;
    private Node tail = null;
    //Создайте HashMap — будет достаточно её стандартной реализации. В ключах будут храниться id задач, а в значениях
    //Node — узлы связного списка. Изначально HashMap пустая. Она будет заполняться по мере добавления новых задач.
    // Напишите реализацию метода add(Task task).
    // Теперь с помощью HashMap и метода удаления removeNode метод add(Task task) будет быстро удалять задачу из списка,
    // если она там есть, а затем вставлять её в конец двусвязного списка.
    // После добавления задачи не забудьте обновить значение узла в HashMap.

    @Override
    public void add(Task task) { //должен помечать команды как просмотренные
        removeNode(historyMap.get(task.getId()));  //Удаляет задачу
        linkLast(task);// Добавляет задачу в конец списка
    }

    @Override
    public void remove(int id) {
        //проверки 1 если удаляем первый, последний, единственный и т.д.
        if (historyMap.isEmpty()) return;
        if (!historyMap.containsKey(id)) return;
        if (head == tail) {
            historyMap = null;
            head = null;
            tail = null;
            return;
        }
        if (historyMap.get(id) == head) {
            head = historyMap.get(id).next;
            head.prev = null;
        } else if (historyMap.get(id) == tail) {
            tail = historyMap.get(id).prev;
            tail.next = null;
        } else {
            historyMap.get(id).prev.next = historyMap.get(id).next;
            historyMap.get(id).next.prev = historyMap.get(id).prev;
        }
        historyMap.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    } //Т.З. - Реализация метода getHistory должна перекладывать задачи из связного списка в ArrayList для ответа.

    private void linkLast(Task task) {
        //Т.З. - будет добавлять задачу в конец этого списка
        if (tail == null) {
            Node node = new Node(task, null, null);
            this.tail = node;
            this.head = node;
            historyMap.put(task.getId(), node);
        } else {
            Node node = new Node(task, this.tail, null);
            this.tail.next = node;
            this.tail = node;
            historyMap.put(task.getId(), node);
        }
    }

    private List<Task> getTasks() {
        //Т.З. - будет собирать все задачи из него в обычный ArrayList
        List<Task> tasks = new ArrayList<>();
        Node iterator = head;
        while (iterator != null) { //==null!!
            tasks.add((Task) iterator.getTask());
            iterator = iterator.next;
        }
        return tasks;
    }

    private void removeNode(Node node) {
        //Т.З. - В качестве параметра этот метод должен принимать объект Node — узел связного списка — и удалять его.
        if (historyMap.isEmpty()) return;
        if (!historyMap.containsValue(node)) return;
        Task task = (Task) node.getTask(); // Cast expression to task.Task ?? - требует явное приведение типов
        remove(task.getId());
    }

    @Override
    public void printHistory() { //вспомогательный метод, необходим для тестирования getHistory
        for (int i = 0; i < history.size(); i++) {
            System.out.print(history.get(i).getClass() + "  ");
            System.out.println(history.get(i));
        }
    }
}