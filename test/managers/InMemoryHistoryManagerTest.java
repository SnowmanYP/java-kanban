package managers;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.*;
import status.Status;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    // ТЗ - убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.

    private static List<Task> checklist = new ArrayList<>(10);


       @Test
    void shouldReturnListEquality() { //Сравниваем историю с контрольным списком
           Task task;
           InMemoryTaskManager manager = new InMemoryTaskManager();
           for (int i = InMemoryTaskManager.getID(); i <= 10; i++) {
               task = new Task("task number " + (i+1), "description of task " + (i+1));
               task.setStatus(Status.NEW);
               manager.createTask(task); //создаем задачу - здесь ID увеличивается на единицу, поэтому getTask(i+1)
               manager.getTask(i+1); //При вызове этого метода объект добавляется в историю
               checklist.add(task); //Добавляем задачу в контрольный список
           }
        assertEquals(manager.getHistoryManager().getHistory(),checklist,"Incorrect");

    }
    //manager.getHistoryManager().getHistory() - теперь обращается к новой структуре данных.

    @Test
    void shouldReturnMyTests(){
        // Все в одном тесте
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Epic epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
        manager.createEpicTask(epic1);
        manager.getEpicTask(epic1.getId());
            assertNotNull(manager.getHistoryManager().getHistory());
            assertTrue(manager.getHistoryManager().getHistory().contains(epic1));
        SubTask sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", epic1.getId());
        SubTask sub2 = new SubTask("Вторая подзадача", "Вторая подзадача первой эпической", epic1.getId());
        SubTask sub4 = new SubTask("Третья подзадача","Третья подзадача первой эпической",  epic1.getId());
        manager.createSubTask(sub1);
        manager.getSubTask(sub1.getId());
            assertTrue(manager.getHistoryManager().getHistory().contains(sub1));
        manager.createSubTask(sub2);
        manager.getSubTask(sub2.getId());
        manager.createSubTask(sub4);
        manager.getSubTask(sub4.getId());
        //Создали epic с тремя подзадачами
            assertFalse(manager.getHistoryManager().getHistory().isEmpty(),"Истории не существует");
        manager.deleteEpicTask(epic1.getId()); //После удаления эпика должны удалиться подзадачи и сам эпик
        // , а также история
            assertTrue(manager.getHistoryManager().getHistory().isEmpty(),"История не удалена");
    }
    //Все сложные тесты делались в main
}

