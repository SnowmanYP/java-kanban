package manager;

import status.Status;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TasksType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CSVFormat {

    public static Task fromString(String value) {
        if (value.isBlank()) {
            System.exit(0);
        }
        String[] items = value.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(items));
        try {
            Integer.parseInt(list.get(0));
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.exit(0);
        }  //Проверяем релевантность типа номера строки, (совпадает с ID)
        if (!Stream.of(TasksType.values()).anyMatch(e -> e.name().equals(list.get(1)))) {
            System.exit(0);
        }
        //Проверяем тип задачи
        if (!Stream.of(Status.values()).anyMatch(e -> e.name().equals(list.get(3)))) {
            System.exit(0);
        }
        //Проверяем соответствие статуса задачи - списку
        if (list.get(1).equals(TasksType.SUBTASK.name())) {
            try {
                Integer.parseInt(list.get(5));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
                System.exit(0);                          //Если номер Эпика отсутствует в подзадаче
            } catch (NumberFormatException e) {               //Если номер Эпика в подзадаче не число
                System.out.println(e);
                System.exit(0);
            }
        }
        Task task = null;
        Epic epic = null;
        SubTask subTask = null;
        if (list.get(1).equals(TasksType.TASK.name())) {
            task = new Task(list.get(2), list.get(4));
            fromList(task, list);
        }
        if (list.get(1).equals(TasksType.EPIC.name())) {
            epic = new Epic(list.get(2), list.get(4));
            fromList(epic, list);
        }
        if (list.get(1).equals(TasksType.SUBTASK.name())) {
            subTask = new SubTask(list.get(2), list.get(4), Integer.parseInt(list.get(5)));
            fromList(subTask, list);
        }
        if (task != null) return task;
        else if (epic != null) return epic;
        else return subTask;
    }

    private static <T extends Task> void fromList(T task, List<String> list) {
        task.setId(Integer.parseInt(list.get(0)));
        task.setTasksType(TasksType.valueOf(list.get(1)));
        task.setStatus(Status.valueOf(list.get(3)));
    }
}
