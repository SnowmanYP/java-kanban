package tasks;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @BeforeEach
    void beforeEach(){
        TaskManager taskManager=new InMemoryTaskManager();
    }

    @Test
    void compareTasks() { //Сравниваем две задачи с одинаковым id
        Task task1 = new Task("Первая простая задача", "Абракадабра");
        task1.setId(1); //id Присваивается только при создании, вызове метода createTask
        Task task2 = new Task("Вторая простая задача", "Описание");
        task2.setId(1); // поэтому id устанавливается принудительно
        assertEquals(task1,task2,"Задачи не совпадают");
    }

    @Test
    void compareTaskHeirs() { //Сравниваем двух наследников Task с совпадающим id
        Epic epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
        epic1.setId(7);
        SubTask sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", epic1.getId());
        sub1.setId(7);
        assertEquals(epic1,sub1,"Задачи не совпадают");
    }
}