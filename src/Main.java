import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.Node;
import status.Status;
import task.Epic;
import task.SubTask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        //InMemoryHistoryManager historyManager=new InMemoryHistoryManager();
        //Создайте две задачи, ...
        Task task1 = new Task("Первая простая задача", "Абракадабра");
        Task task2 = new Task("Вторая простая задача", "Описание");
        manager.createTask(task1);
        manager.createTask(task2);
        //...а также эпик с двумя подзадачами...
        Epic epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
        manager.createEpicTask(epic1);
        SubTask sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", epic1.getId());
        SubTask sub2 = new SubTask("Вторая подзадача", "Вторая подзадача первой эпической", epic1.getId());
        manager.createSubTask(sub1);
        manager.createSubTask(sub2);
        //...и эпик с одной подзадачей.
        Epic epic2 = new Epic("Вторая эпическая", "Не просто задача");
        manager.createEpicTask(epic2);
        SubTask sub3 = new SubTask("Еще одна подзадача", "подзадача для большой задачи", epic2.getId());
        manager.createSubTask(sub3);
        //Распечатайте списки задач, эпиков и подзадач через System.out.println(..)
        System.out.println("\nРаспечатайте списки задач, эпиков и подзадач");
        System.out.println("Обычные задачи");
        manager.printAllTasks();
        System.out.println("Эпики");
        manager.printAllEpicTasks();
        System.out.println("Подзадачи");
        manager.printAllSubTasks();
        //Измените статусы созданных объектов, распечатайте их.
        System.out.println("\nИзмените статусы созданных объектов");
        System.out.println("Меняем статусы простых задач");
        System.out.println("Было");
        manager.printAllTasks();
        Task newTask1 = new Task("Для первой простой", "изменяем статус задачи new->in-progress");
        newTask1.setStatus(Status.IN_PROGRESS);
        newTask1.setId(task1.getId());
        manager.updateTask(newTask1);
        Task newTask2 = new Task("Для второй простой", "изменяем статус задачи new->done");
        newTask2.setStatus(Status.DONE);
        newTask2.setId(task2.getId());
        manager.updateTask(newTask2);
        System.out.println("Стало");
        manager.printAllTasks();
        System.out.println();
        System.out.println("Меняем статусы подзадач");
        SubTask newSubTask1 = new SubTask("Первая подзадача первой эпической", "Меняем статус ", epic1.getId());
        newSubTask1.setStatus(Status.DONE);
        newSubTask1.setId(sub1.getId());
        SubTask newSubTask2 = new SubTask("Вторая подзадача первой эпической", "Меняем статус ", epic1.getId());
        newSubTask2.setStatus(Status.NEW);
        newSubTask1.setId(sub2.getId());
        SubTask newSubTask3 = new SubTask("Первая подзадача второй эпической", "Меняем статус ", epic2.getId());
        newSubTask3.setStatus(Status.IN_PROGRESS);
        newSubTask3.setId(sub3.getId());
        // Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.
        System.out.println("Было");
        System.out.println("Эпик с двумя подзадачами");
        manager.printAllTasksOfOneEpic(epic1.getId());
        System.out.println("Эпик с одной подзадачей");
        manager.printAllTasksOfOneEpic(epic2.getId());
        System.out.println();
        manager.updateSubTask(newSubTask1);
        manager.updateSubTask(newSubTask2);
        manager.updateSubTask(newSubTask3);
        System.out.println("Стало");
        System.out.println("Эпик с двумя подзадачами");
        manager.printAllTasksOfOneEpic(epic1.getId());
        System.out.println("Эпик с одной подзадачей");
        manager.printAllTasksOfOneEpic(epic2.getId());
        //И, наконец, попробуйте удалить одну из задач и один из эпиков.
        /*System.out.println("\nИ, наконец, попробуйте удалить одну из задач и один из эпиков.");
        System.out.println("Было");
        manager.deleteAllTasks(); //Было
        manager.deleteTask(task2.id); //Удаление простой задачи
        System.out.println("Стало");
        manager.deleteAllTasks(); //Стало
        System.out.println("Было");
        manager.printAllEpicTasks(); //Было
        manager.printAllSubTasks();
        manager.deleteEpicTask(epic1.id); //Если удаляем эпик, метод удаляет подзадачи
        System.out.println("Стало");
        manager.printAllEpicTasks(); //Стало
        manager.printAllSubTasks();*/
        //Если удалить все подзадачи, то статус Большой задачи должен быть NEW
       /* System.out.println();
        manager.printAllEpicTasks();
        System.out.println();
        manager.printAllSubTasks();
        System.out.println();
        manager.deleteSubTask(sub3.id);
        System.out.println();
        manager.printAllEpicTasks();
        System.out.println();
        manager.printAllSubTasks();*/

    /*
    Дополнительное задание. Реализуем пользовательский сценарий
    Если у вас останется время, вы можете выполнить дополнительное задание. Реализуйте в классе Main опциональный
    пользовательский сценарий:
    1. Создайте две задачи, эпик с тремя подзадачами и эпик без подзадач.*/ //++
        SubTask sub4 = new SubTask("Третья подзадача", "Третья подзадача первой эпической", epic1.getId());
        manager.createSubTask(sub4);
        Epic epic3 = new Epic("Третий эпик", "Эпик без подзадач");
        manager.createEpicTask(epic3);
        // 2. Запросите созданные задачи несколько раз в разном порядке. //++
        manager.getTask(1);
        manager.getTask(2);
        manager.getEpicTask(3);
        manager.getSubTask(4);
        manager.getSubTask(5);
        manager.getSubTask(8);
        System.out.println(manager.getHistoryManager().getHistory()); //Все тесты пройдены++
        /*manager.getTask(1);
        manager.getTask(2);
        manager.getEpicTask(3);
        manager.getSubTask(5);*/
//    3. После каждого запроса выведите историю и убедитесь, что в ней нет повторов. //++
//      System.out.println(manager.getHistoryManager().getHistory());
//    4. Удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться. //++
//        manager.deleteTask(1);
//        System.out.println(manager.getHistoryManager().getHistory()); //++
//        manager.deleteSubTask(5);
//        System.out.println(manager.getHistoryManager().getHistory()); //++
//    5. Удалите эпик с тремя подзадачами и убедитесь, что из истории удалился как сам эпик, так и все его подзадачи. //++
        int i = epic1.getId();
        manager.printAllTasksOfOneEpic(i);
        System.out.println(manager.getHistoryManager().getHistory());
        manager.deleteEpicTask(i);
        System.out.println(manager.getHistoryManager().getHistory());
//    Обратите внимание, что выполнение этого задания необязательно.
        //Все тесты пройдены++
    }
}