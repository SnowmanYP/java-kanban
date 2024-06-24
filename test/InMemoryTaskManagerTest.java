import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {

    private static InMemoryTaskManager manager;
    private static Epic epic1;
    private static SubTask sub1;
    private static Task task1;

    @BeforeAll
    static void before() {
        manager = new InMemoryTaskManager();
        epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
        sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", 1);
        task1 = new Task("Первая простая задача", "Абракадабра");
    }

//ТЗ - проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
    @Test
    void createAndGetEpicTask() { //Теститируем создание и поиск эпика
        manager.createEpicTask(epic1);
        manager.printAllEpicTasks();
        assertNotNull(manager.getEpicTask(epic1.getId()), "Большая задача не найдена.");
    }

    @Test
    void createAndGetSubTask() { //Тестируем создание и поиск подзадачи
        manager.createEpicTask(epic1); //Нельзя создать подзадачу без эпика, поэтому сначала создаем эпик
        manager.createSubTask(sub1);
        manager.printAllSubTasks();
        assertNotNull(manager.getSubTask(sub1.getId()), "Подзадача не найдена");
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
        String description = task1.description;
        Status status = task1.getStatus();
        manager.createTask(task1); // Создаем задачу
        assertEquals(name, manager.getTask(task1.getId()).getTaskName(), "Incorrect");
        assertEquals(description, manager.getTask(task1.getId()).getDescription(), "Incorrect");
        assertEquals(status, manager.getTask(task1.getId()).getStatus(), "Incorrect");
    }
}