import manager.FileBackedTaskManager;
import task.Epic;
import task.SubTask;
import task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("save.csv");
        FileBackedTaskManager backedManager = new FileBackedTaskManager(file);
//~~~~~~~~~~~~~~~~~~~~~~~~~~Запись в файл - полный тест~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Добавляю две задачи ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.printf("%s", "Создаю задачи\n");
        Task task1 = new Task("Первая простая задача", "Абракадабра");
        Task task2 = new Task("Вторая простая задача", "Описание");
        backedManager.createTask(task1);
        backedManager.createTask(task2);
// Добавляю Эпик с двумя подзадачами ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Epic epic1 = new Epic("Первая эпическая", "Не битва и не война - просто задача");
        backedManager.createEpicTask(epic1);
        SubTask sub1 = new SubTask("Первая подзадача", "Первая подзадача первой эпической", epic1.getId());
        SubTask sub2 = new SubTask("Вторая подзадача", "Вторая подзадача первой эпической", epic1.getId());
        backedManager.createSubTask(sub1);
        backedManager.createSubTask(sub2);
//Добавляю Эпик с одной подзадачей ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Epic epic2 = new Epic("Вторая эпическая", "Не просто задача");
        backedManager.createEpicTask(epic2);
        SubTask sub3 = new SubTask("Еще одна подзадача", "подзадача для большой задачи", epic2.getId());
        backedManager.createSubTask(sub3);
        System.out.println("\nСмотрю что получилось");
        backedManager.printAllTasks();
        backedManager.printAllEpicTasks();
        backedManager.printAllSubTasks();
        System.out.println("\nСмотрю файл");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        while (bf.readLine() != null) {
            System.out.println(bf.readLine());
        }
        bf.close();
//~~~~~~~~~~~~~~~~~~~~~~~~~~Восстановление из файла - полный тест~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.println("\nУдаляю все задачи");
        backedManager.deleteAllTasks(); //Удаляем задачи
        backedManager.deleteAllEpicTasks(); //Удаляем эпики, подзадачи также удалятся
        System.out.println("\nПроверяю");
        backedManager.printAllTasks();
        backedManager.printAllEpicTasks();
        backedManager.printAllSubTasks();
        System.out.println("\nВосстанавливаю данные из файла");
        FileBackedTaskManager.loadFromFile(file);
        backedManager.printAllTasks();
        backedManager.printAllEpicTasks();
        backedManager.printAllSubTasks();
        System.out.println("It's Ok!");
        //File.createTempFile(…) - пригодится, но не этой стадии проекта
    }
}