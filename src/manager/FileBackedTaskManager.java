package manager;

import exception.ManagerSaveException;
import task.Epic;
import task.SubTask;
import task.Task;

import java.io.*;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private File file;

    public FileBackedTaskManager(File file) {
        if (file == null) {
            System.out.println("Имя файла == null, Завершение работы программы.");
            System.exit(0);
        }
        this.file = file;
        setId(0);
    }

    public int currentId() {
        return getId();
    }

    private void save() {
        try {
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

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager backedTaskManager = new FileBackedTaskManager(file);
        try {
            if (file == null) throw new ManagerSaveException("Передана пустая ссылка на файл");
            if (!file.exists()) throw new ManagerSaveException("Файла не существует");
            if (file.isDirectory()) throw new ManagerSaveException("Файл является директорией");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int id = 0;
            Task task;
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                task = CSVFormat.fromString(line);
                if (task.getClass() == Task.class) {
                    backedTaskManager.tasks.put(task.getId(), (Task) task);
                    if (task.getId() > InMemoryTaskManager.getId()) {
                        id = task.getId();
                    }
                }
                if (task.getClass() == Epic.class) {
                    backedTaskManager.epicTasks.put(task.getId(), (Epic) task);
                    if (task.getId() > InMemoryTaskManager.getId()) {
                        id = task.getId();
                    }
                }
                if (task.getClass() == SubTask.class) {
                    backedTaskManager.subTasks.put(task.getId(), (SubTask) task);
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
        return backedTaskManager;
    }
}