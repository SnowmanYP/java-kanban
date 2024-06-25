package managers;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import status.Status;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {
    // ТЗ - убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    private static InMemoryTaskManager manager = new InMemoryTaskManager();
    private static List<Task> checklist = new ArrayList<>(10);

    @BeforeAll
    static void beforeAll() {
        Task task;
        for (int i = InMemoryTaskManager.getID(); i <= 10; i++) {
            task = new Task("task number " + (i+1), "description of task " + (i+1));
            task.setStatus(Status.NEW);
            manager.createTask(task); //создаем задачу - здесь ID увеличивается на единицу, поэтому getTask(i+1)
            manager.getTask(i+1); //При вызове этого метода объект добавляется в историю
            checklist.add(task); //Добавляем задачу в контрольный список
        }
    }

       @Test
    void shouldReturnListEquality() { //Сравниваем историю с контрольным списком
   assertEquals(manager.getHistoryManager().getHistory(),checklist,"Incorrect");
    }
}