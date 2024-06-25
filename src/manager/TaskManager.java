package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.List;

public interface TaskManager {

      void printAllEpicTasks();

      void deleteAllEpicTasks();

      Epic getEpicTask(Integer id);

      void createEpicTask(Epic epicTask);

      void updateEpicTask(Epic epicTask);

      void deleteEpicTask(Integer id);

      void printAllTasksOfOneEpic(Integer id);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --SUB--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      void printAllSubTasks();

      void deleteAllSubTasks();

      SubTask getSubTask(Integer id);

      void createSubTask(SubTask subTask);

      void updateSubTask(SubTask subTask);

      void deleteSubTask(Integer id);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --Task (обычные задачи)--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      void printAllTasks();

      void deleteAllTasks();

      Task getTask(Integer id);

      void createTask(Task task);
      void updateTask(Task task);

      void deleteTask(Integer id);

        //~~~~~~~~~~~~~~~~~~~~~~~~/УНИВЕРСАЛЬНЫЕ МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --Task, Epic, SubTask --/~~~~~~~~~~~~~~~~~~~~//

      List<Task> getHistory();
}