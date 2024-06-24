package managers;

import manager.InMemoryTaskManager;
import status.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    // ТЗ - убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    private static InMemoryTaskManager manager = new InMemoryTaskManager();
    private static ArrayList<Task> checklist = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        Task task;
        for (int i = 1; i <= 10; i++) {
            task = new Task("task number " + i, "description of task " + i);
            task.setStatus(randomStatus()); //  и присвоим разные статусы новым задачам случайным образом
            manager.createTask(task); //создаем задачу
            manager.getTask(i); //При вызове этого метода объект добавляется в историю
            checklist.add(task); //Добавляем задачу в контрольный список
        }
    }

    private static final Random random = new Random();

    public static Status randomStatus() { //Генерим случайный статус;
        Status[] directions = Status.values();
        return directions[random.nextInt(directions.length)];
    }

    @Test
    void shouldReturnListEquality() { //Сравниваем историю с контрольным списком
        assertEquals(manager.historyManager.getHistory(),checklist,"Incorrect");
    }
}