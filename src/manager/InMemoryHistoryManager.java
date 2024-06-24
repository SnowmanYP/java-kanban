package manager;

import task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager{
    private final int CAPACITY = 10; //5 - если тестировать в Main
    private ArrayList<Task> history = new ArrayList<>(CAPACITY);

    @Override
    public void add(Task task) { //должен помечать команды как просмотренные
        if (history.size() == CAPACITY) {
            history.remove(0);
        }
        history.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }

    public void printHistory() { //вспомогательный метод, необходим для тестирования getHistory

        for (int i = 0; i < history.size(); i++) {
            System.out.print(history.get(i).getClass() + "  ");
            System.out.println(history.get(i));
        }
    }
}
