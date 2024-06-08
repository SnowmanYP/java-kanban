import java.util.HashMap;

public class TaskManager {
    public HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    public HashMap<Integer, SubTask> subTasks = new HashMap<>();
    public HashMap<Integer, Task> middleTasks = new HashMap<>();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --EPIC--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void addEpicTask(String name, String description, Status status) {
        int i = ID.getID();
        epicTasks.put(i, new EpicTask(name, description, status));
        epicTasks.get(i).id = i;
        epicTasks.get(i).epicID = ID.getEpicID();
        System.out.println("Большая задача добавлена.");
    }

    public void printOneEpicTask(EpicTask epicTask) { //Вывод в консоль одной задачи
        if (epicTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("поле id-'" + epicTask.id + "' Название задачи-'" + epicTask.taskName + "' Описание задачи-'"
                + epicTask.description + "' Статус задачи-'" + epicTask.status + "' epicID-'" + epicTask.epicID + "'");
    }

    public void printAllEpicTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (epicTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : epicTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id-'" + epicTasks.get(i).id + "' Название задачи-'"
                        + epicTasks.get(i).taskName + "' Описание задачи-'" + epicTasks.get(i).description
                        + "' Статус задачи-'" + epicTasks.get(i).status + "' epicID-'" + epicTasks.get(i).epicID + "'");
        }
    }

    public void dellAllEpicTasks() { // b. Удаление всех задач.
        epicTasks.clear();
        dellAllSubTasks();
        //Если мы удалили все EPIC-задачи значит нужно удалить и все SUB задачи, но не наоборот
        //Подзадачи не существуют в отдельности от EPIC задач
        System.out.println("Список больших задач полностью удален!");
    }

    public EpicTask getEpicTask(Integer id) { //c. Получение по идентификатору.
        if (epicTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!epicTasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        return epicTasks.get(id);
    }

    public void createEpicTask(EpicTask epicTask) { // d. Создание. Сам объект должен передаваться
        // в качестве параметра.
        if (epicTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (!epicTask.status.equals(Status.NEW)) {
            System.out.println("У создаваемой задачи должен быть статус NEW");
            epicTask.status = Status.NEW;
        }
        int i = ID.getID();
        epicTasks.put(i, epicTask); // Нельзя создать запись с переданным id, id уникален,
        epicTasks.get(i).id = i;    // поэтому id будет присвоен при создании записи
        epicTasks.get(i).epicID = ID.getEpicID();
        System.out.println("Большая задача создана.");
    }


    public void updateEpicTask(EpicTask epicTask, Integer id) {
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        if (epicTasks == null || epicTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (epicTask == null) {
            System.out.println("Такой задачи не существует");
            return;
        }
        if (!epicTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        epicTask.id = id;
        epicTask.epicID = epicTasks.get(id).epicID;
        epicTasks.replace(id, epicTask); //id и epicID не изменяются
        System.out.println("Большая задача обновлена");
        if (epicTask.status.equals(Status.NEW)) {//Если у обновленной большой задачи статус NEW
            //все соответствующие подзадачи должны быть NEW
            for (SubTask obj : subTasks.values()) {
                if (obj.epicID.equals(epicTask.epicID)) {
                    obj.status = Status.NEW;
                }
            }
        }
        if (epicTask.status.equals(Status.DONE)) {//Если у обновленной большой задачи статус DONE
            //все соответствующие подзадачи должны быть DONE
            for (SubTask obj : subTasks.values()) {
                if (obj.epicID.equals(epicTask.epicID)) {
                    obj.status = Status.DONE;
                }
                System.out.println();
            }
        }
        //А если у обновленной большой задачи статус изменен на IN_PROGRESS, может возникнуть коллизия
        //эпик IN_PROGRESS все подзадачи NEW или DONE - как обрабатывать эту противоречивую логику - непонятно,
        // так как изменение статусов сабтасков при изменении статуса эпика в ТЗ не указано.
        // Возможно, следует запретить менять статус эпиков при вызове этого метода.
    }


    public void dellEpicTask(Integer id) {  // f. Удаление по идентификатору.
        if (epicTasks == null || epicTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!epicTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        /// .... Вместе с главной задачей нужно удалить и подзадачи!!!
        HashMap<Integer, SubTask> copy = new HashMap<>(subTasks); // Создаем копию, это поможет в дальнейшем
        //избежать ConcurrentModificationException
        for (SubTask obj : copy.values()) {
            if (obj.epicID == epicTasks.get(id).epicID) {
                subTasks.remove(obj.id);
            }
        }
        epicTasks.remove(id);
        System.out.println("Большая задача удалена");
    }

    public void printAllTasksOfOneEpic(Integer id) { //Дополнительные методы: a. Получение списка всех подзадач
        // определённого эпика.
        printOneEpicTask(epicTasks.get(id));
        System.out.println("Подзадачи:");
        for (SubTask obj : subTasks.values()) {
            if (obj.epicID == epicTasks.get(id).epicID) {
                printOneSubTask(obj);
            }
        }
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --SUB--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void addSubTask(String name, String description, Status status, Integer epicID) {
        int i = ID.getID();
        subTasks.put(i, new SubTask(name, description, status, epicID));
        subTasks.get(i).id = i;
        subTasks.get(i).epicID = epicID;
        System.out.println("Подзадача добавлена");
    }

    public void printOneSubTask(SubTask subTask) { //Вывод в консоль одной подзадачи
        if (subTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("поле id-'" + subTask.id + "' Название задачи-'" + subTask.taskName + "' Описание задачи-'"
                + subTask.description + "' Статус задачи-'" + subTask.status + "' epicID-'" + subTask.epicID + "'");
    }

    public void printAllSubTasks() {  //a. Получение списка всех подзадач. - вывод в консоль.
        if (subTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : subTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id-'" + subTasks.get(i).id + "' Название задачи-'"
                        + subTasks.get(i).taskName + "' Описание задачи-'" + subTasks.get(i).description
                        + "' Статус задачи-'" + subTasks.get(i).status + "'" + " epicID-'" + subTasks.get(i).epicID
                        + "'");
        }
    }

    public void dellAllSubTasks() { // b. Удаление всех подзадач.
        subTasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    public SubTask getSubTask(Integer id) { //c. Получение подзадачи по уникальному идентификатору
        if (subTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        return subTasks.get(id);
    }

    public void createSubTask(SubTask subTask) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        boolean isEpic = false;
        if (subTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (epicTasks.isEmpty()) {
            System.out.println("Список больших задач пуст. Невозможно создать подзадачу");
            return;
        }
        for (EpicTask obj : epicTasks.values()) {
            isEpic = false;
            if (obj.epicID.equals(subTask.epicID)) { //Если epicId совпадают у подзадачи и эпической задачи
                if (obj.status.equals(Status.DONE)) { //И Статус эпика DONE
                    System.out.println("Эта большая задача завершена");
                    return;
                }
                int i = ID.getID();
                subTask.id = i;
                if (!subTask.status.equals(Status.NEW)) {
                    System.out.println("У создаваемой подзадачи должен быть статус NEW");
                    subTask.status = Status.NEW;
                }
                subTasks.put(i, subTask);
                System.out.println("Подзадача создана.");

            } else isEpic = true;
        }
        if (isEpic) {
            System.out.println("Для этой подзадачи нет большой задачи");
        }
    }


    public void updateSubTask(SubTask subTask, Integer id) {
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        int epicTaskID = -1;
        boolean isNew = true;
        boolean isDone = true;
        if (subTasks == null || subTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (subTask == null) {
            System.out.println("Такой задачи не существует");
            return;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        subTask.id = id;
        subTask.epicID = subTasks.get(id).epicID;
        subTasks.replace(id, subTask);
        for (EpicTask obj : epicTasks.values()) {
            if (obj.epicID.equals(subTask.epicID)) {
                epicTaskID = obj.id;  //id Заголовка
            }
        }
        for (SubTask obj : subTasks.values()) {
            if (obj.epicID.equals(subTask.epicID)) {
                if (obj.status != Status.NEW) isNew = false;
                if (obj.status != Status.DONE) isDone = false;
            }
            if (isNew) epicTasks.get(epicTaskID).status = Status.NEW;
            else if (isDone) epicTasks.get(epicTaskID).status = Status.DONE;
            else epicTasks.get(epicTaskID).status = Status.IN_PROGRESS;
        }
        System.out.println("Подзадача обновлена");
    }


    public void dellSubTask(Integer id) {  // f. Удаление по идентификатору.
        if (subTasks == null || subTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        subTasks.remove(id);
        recalcOfStatus();
        System.out.println("Подзадача удалена");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --MIDDLE (обычные задачи)--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void addMiddleTask(String name, String description, Status status) {
        int i = ID.getID();
        middleTasks.put(i, new Task(name, description, status));
        middleTasks.get(i).id = i;
        System.out.println("Добавлена простая задача.");
    }

    public void printOneTask(Task task) { //Вывод в консоль одной задачи
        if (task == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("Название задачи-'" + task.taskName + "' поле_id-'" + task.id + "' Описание задачи-'"
                + task.description + "' Статус задачи-'" + task.status + "'");
    }

    public void printAllMiddleTask() {  //a. Получение списка всех задач. - вывод в консоль.
        if (middleTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : middleTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле_id-'" + middleTasks.get(i).id + "' Название задачи-'"
                        + middleTasks.get(i).taskName + "' Описание задачи-'" + middleTasks.get(i).description
                        + "' Статус задачи-'" + middleTasks.get(i).status + "'");
        }
    }

    public void dellAllMiddleTask() { // b. Удаление всех задач.
        middleTasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    public Task getMiddleTask(Integer id) { //c. Получение по идентификатору.
        if (middleTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!middleTasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        return middleTasks.get(id);
    }

    public void createMiddleTask(Task task) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        if (task == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        int i = ID.getID();
        middleTasks.put(i, task);
        middleTasks.get(i).id = i;
        System.out.println("Задача успешно создана.");
    }

    public void updateMiddleTask(Task task, Integer id) {
        // e. Обновление.
        if (middleTasks == null || middleTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (task == null) {
            System.out.println("Такой задачи не существует");
            return;
        }
        if (!middleTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        task.id = id;
        middleTasks.replace(id, task);
        System.out.println("Задача обновлена");
    }

    public void dellMiddleTask(Integer id) {  // f. Удаление по идентификатору.
        if (middleTasks == null || middleTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!middleTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
        }
        middleTasks.remove(id);
        System.out.println("Задача удалена");
    }

    //Приводит в соответствие статусы Epiс и Sub
    public void recalcOfStatus() {
        for (EpicTask o : epicTasks.values()) {
            boolean isNew = true;
            boolean isDone = true;
            int count = 0;
            int id = o.id;
            for (SubTask obj : subTasks.values()) {
                if (o.epicID.equals(obj.epicID)) {
                    count++;
                    if (obj.status != Status.NEW) isNew = false;
                    if (obj.status != Status.DONE) isDone = false;
                }
            }
            if (isNew || count == 0) epicTasks.get(id).status = Status.NEW;
            else if (isDone) epicTasks.get(id).status = Status.DONE;
            else epicTasks.get(id).status = Status.IN_PROGRESS;
        }
    }
}