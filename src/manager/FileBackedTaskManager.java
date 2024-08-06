package manager;

import exception.ManagerSaveException;
import status.Status;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TasksType;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {

    private File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    public void save() {
        try {
            if (!file.exists()) throw new ManagerSaveException("Создается новый файл");
            if (file.isDirectory()) throw new ManagerSaveException("Файл является директорией");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("id,type,name,status,description,epic");
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                Task task = entry.getValue();
                writer.write("\n" + task.toString());
            }
            for (Map.Entry<Integer, Epic> entry : epicTasks.entrySet()) {
                Task task = entry.getValue();
                writer.write("\n" + task.toString());
            }
            for (Integer key : subTasks.keySet()) {
                Task task = subTasks.get(key);
                writer.write("\n" + task.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ManagerSaveException e) {
            System.out.println(e);
        }
    }

    public Task fromString(String value) {
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

    private <T extends Task> void fromList(T task, List<String> list) {
        task.setId(Integer.parseInt(list.get(0)));
        task.setTasksType(TasksType.valueOf(list.get(1)));
        task.setStatus(Status.valueOf(list.get(3)));
    }

    @Override
    public void createEpicTask(Epic epicTask) {
        super.createEpicTask(epicTask);
        save();
    }

    @Override
    public void createSubTask(SubTask subTask) {
        super.createSubTask(subTask);
        save();
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    public static HashMap<Integer, Task> tasksClone;

    public static FileBackedTaskManager loadFromFile(File file) {
        try {
            if (!file.exists()) throw new ManagerSaveException("Файла не существует");
            if (file.isDirectory()) throw new ManagerSaveException("Файл является директорией");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            FileBackedTaskManager backedTaskManager = new FileBackedTaskManager(file);
            int id = 0;
            Task task;
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                task = backedTaskManager.fromString(line);
                if (task.getClass() == Task.class) {
                    tasks.put(task.getId(), (Task) task);
                    if (task.getId() > InMemoryTaskManager.getId()) {
                        id = task.getId();
                    }
                }
                if (task.getClass() == Epic.class) {
                    epicTasks.put(task.getId(), (Epic) task);
                    if (task.getId() > InMemoryTaskManager.getId()) {
                        id = task.getId();
                    }
                }
                if (task.getClass() == SubTask.class) {
                    subTasks.put(task.getId(), (SubTask) task);
                    if (task.getId() > InMemoryTaskManager.getId()) {
                        id = task.getId();
                    }
                }
                InMemoryTaskManager.setId(id);
            }
        } catch (ManagerSaveException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
