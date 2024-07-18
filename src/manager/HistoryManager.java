package manager;

import task.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    List<Task> getHistory(boolean order);

    void remove(int id);

    public void printHistory();
}
