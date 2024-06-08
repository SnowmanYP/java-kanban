
public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        //Создайте две задачи, ...
        Task task1 = new Task("Первая простая задача", "Абракадабра", Status.NEW);
        Task task2 = new Task("Вторая простая задача", "Описание", Status.NEW);
        manager.createMiddleTask(task1);
        manager.createMiddleTask(task2);
        //...а также эпик с двумя подзадачами...
        EpicTask epic1 = new EpicTask("Первая эпическая", "Не битва и не война, просто задача",
                Status.NEW);
        manager.createEpicTask(epic1);
        SubTask sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", Status.NEW,
                epic1.epicID);
        SubTask sub2 = new SubTask("Вторая подзадача", "Вторая подзадача первой эпической", Status.NEW,
                epic1.epicID);
        manager.createSubTask(sub1);
        manager.createSubTask(sub2);
        //...и эпик с одной подзадачей.
        EpicTask epic2 = new EpicTask("Вторая эпическая", "Не просто задача", Status.NEW);
        manager.createEpicTask(epic2);
        SubTask sub3 = new SubTask("Еще одна подзадача", "подзадача для большой задачи", Status.NEW,
                epic2.epicID);
        manager.createSubTask(sub3);
        //Распечатайте списки задач, эпиков и подзадач через System.out.println(..)
        System.out.println("\nРаспечатайте списки задач, эпиков и подзадач");
        System.out.println("Обычные задачи");
        manager.printAllMiddleTask();
        System.out.println("Эпики");
        manager.printAllEpicTasks();
        System.out.println("Подзадачи");
        manager.printAllSubTasks();
        //Измените статусы созданных объектов, распечатайте их.
        System.out.println("\nИзмените статусы созданных объектов");
        System.out.println("Меняем статусы простых задач");
        System.out.println("Было");
        manager.printAllMiddleTask();
        Task newTask1 = new Task("Для первой простой", "изменяем статус задачи new->in-progress",
                Status.IN_PROGRESS);
        manager.updateMiddleTask(newTask1, task1.id);
        Task newTask2 = new Task("Для второй простой", "изменяем статус задачи new->done",
                Status.DONE);
        manager.updateMiddleTask(newTask2, task2.id);
        System.out.println("Стало");
        manager.printAllMiddleTask();
        System.out.println();
        System.out.println("Меняем статусы подзадач");
        SubTask newSubTask1 = new SubTask("Первая подзадача первой эпической", "Меняем статус ",
                Status.DONE, epic1.epicID);
        SubTask newSubTask2 = new SubTask("Вторая подзадача первой эпической", "Меняем статус ",
                Status.NEW, epic1.epicID);
        SubTask newSubTask3 = new SubTask("Первая подзадача второй эпической", "Меняем статус ",
                Status.DONE, epic2.epicID);
        // Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.
        System.out.println("Было");
        System.out.println("Эпик с двумя подзадачами");
        manager.printAllTasksOfOneEpic(epic1.id);
        System.out.println("Эпик с одной подзадачей");
        manager.printAllTasksOfOneEpic(epic2.id);
        System.out.println();
        manager.updateSubTask(newSubTask1, sub1.id);
        manager.updateSubTask(newSubTask2, sub2.id);
        manager.updateSubTask(newSubTask3, sub3.id);
        System.out.println("Стало");
        System.out.println("Эпик с двумя подзадачами");
        manager.printAllTasksOfOneEpic(epic1.id);
        System.out.println("Эпик с одной подзадачей");
        manager.printAllTasksOfOneEpic(epic2.id);
        //И, наконец, попробуйте удалить одну из задач и один из эпиков.
        System.out.println("\nИ, наконец, попробуйте удалить одну из задач и один из эпиков.");
        System.out.println("Было");
        manager.printAllMiddleTask(); //Было
        manager.dellMiddleTask(task2.id); //Удаление простой задачи
        System.out.println("Стало");
        manager.printAllMiddleTask(); //Стало
        System.out.println("Было");
        manager.printAllEpicTasks(); //Было
        manager.printAllSubTasks();
        manager.dellEpicTask(epic1.id); //Если удаляем эпик, метод удаляет подзадачи
        System.out.println("Стало");
        manager.printAllEpicTasks(); //Стало
        manager.printAllSubTasks();
        //Если удалить все подзадачи, то статус Большой задачи должен быть NEW
        System.out.println();
        manager.printAllEpicTasks();
        System.out.println();
        manager.printAllSubTasks();
        System.out.println();
        manager.dellSubTask(sub3.id);
        System.out.println();
        manager.printAllEpicTasks();
        System.out.println();
        manager.printAllSubTasks();
    }
}
