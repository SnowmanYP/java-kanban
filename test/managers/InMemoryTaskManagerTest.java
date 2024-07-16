package managers;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.AfterAll;
import status.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {

    private static InMemoryTaskManager manager;
    private static Epic epic1;
    private static SubTask sub1;
    private static Task task1;

    @BeforeAll
    static void beforeEach() {
        manager = new InMemoryTaskManager();
        task1 = new Task("Первая простая задача", "Абракадабра");
        epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
    }

    @AfterAll
    static void AfterAll() {
        manager.deleteAllSubTasks();
        manager.deleteAllEpicTasks();
        manager.deleteAllTasks();
    }
//ТЗ - проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;

    @Test
    void createAndGetSubTask() { //Тестируем создание и поиск подзадачи
        manager.createEpicTask(epic1); //Нельзя создать подзадачу без эпика, поэтому сначала создаем эпик
        sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", epic1.getId());
        //sub1 = new...нельзя вынести в общий метод, т.к. epic1.getId() - формируется в этом методе
        manager.createSubTask(sub1);
        assertNotNull(manager.getSubTask(sub1.getId()), "Подзадача не найдена");
    }

    @Test
    void createAndGetEpicTask() { //Теститируем создание и поиск эпика
        manager.createEpicTask(epic1);
        manager.printAllEpicTasks();
        assertNotNull(manager.getEpicTask(epic1.getId()), "Большая задача не найдена.");
    }

    @Test
    void createTask() {  //Создание(добавление) и поиск задачи одним тестом
        manager.createTask(task1);
        manager.printAllTasks();
        assertNotNull(manager.getTask(task1.getId()), "Задача не найдеа");
    }
// ТЗ -проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
// id - задачи любого уровня задается при ее создании. В этом коде нет такого понятия - заданный id.
// Поэтому: 1. Невозможно создать такой тест.
//          2. Он не имеет смысла.
//ТЗ - создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер

    @Test
    void shouldReturnEqualityOfTaskVariables() {
        //Принимаем поля в переменные
        // id создается при добавлении(создании) задачи
        String name = task1.getTaskName();
        String description = task1.getDescription();
        Status status = task1.getStatus();
        manager.createTask(task1); // Создаем задачу
        assertEquals(name, manager.getTask(task1.getId()).getTaskName(), "Incorrect");
        assertEquals(description, manager.getTask(task1.getId()).getDescription(), "Incorrect");
        assertEquals(status, manager.getTask(task1.getId()).getStatus(), "Incorrect");
    }
}