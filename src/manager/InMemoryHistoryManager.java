package manager;

import task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    public List<Task> history = new ArrayList<>();
    public Map<Integer, Node> historyMap = new HashMap<>();

    private Node head = null;
    private Node tail = null;

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
    public List<Task> getHistory(boolean order) {
        List<Task> directOrRevers = new ArrayList<>();
        directOrRevers = getTasks();
        if (!order) {
            Collections.reverse(directOrRevers);
        }
        return directOrRevers;
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
        while (iterator != null) {
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