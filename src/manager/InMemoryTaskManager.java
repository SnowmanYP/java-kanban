package manager;

import status.Status;
import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    protected HashMap<Integer, Epic> epicTasks = new HashMap<>();
    protected HashMap<Integer, SubTask> subTasks = new HashMap<>();
    protected HashMap<Integer, Task> tasks = new HashMap<>();

    private static int id;

    private HistoryManager historyManager = Managers.getDefaultHistory(); // по ТЗ - статический метод

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        InMemoryTaskManager.id = id;
    }

    protected static int generateId() {
        return ++id;
    }
//~~~~~~~~~~~~~~~~~~~~~~~~/УНИВЕРСАЛЬНЫЕ МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --Task, Epic, SubTask --/~~~~~~~~~~~~~~~~~~~~//

    @Override
    public List<Task> getHistory(boolean order) {
        return historyManager.getHistory(order);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --EPIC--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void addEpicTask(String name, String description, Status status) {
        int i = generateId();
        epicTasks.put(i, new Epic(name, description));
        epicTasks.get(i).setId(i);
        System.out.println("Большая задача добавлена.");
    }

    @Override
    public void printAllEpicTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (epicTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : epicTasks.keySet())
                System.out.println(epicTasks.get(i).toString());
        }
    }

    public List getAllEpicTask() {
        return new ArrayList<>(epicTasks.values());
    }

    @Override
    public void deleteAllEpicTasks() { // b. Удаление всех задач.
        epicTasks.clear();
        deleteAllSubTasks();
        //Если мы удалили все EPIC-задачи значит нужно удалить и все SUB задачи, но не наоборот
        //Подзадачи не существуют в отдельности от EPIC задач
        System.out.println("Список больших задач полностью удален!");
    }

    @Override
    public Epic getEpicTask(Integer id) { //c. Получение по идентификатору.
        if (epicTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!epicTasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        historyManager.add(epicTasks.get(id));
        return epicTasks.get(id);
    }

    @Override
    public void createEpicTask(Epic epicTask) { // d. Создание. Сам объект должен передаваться
        // в качестве параметра.
        if (epicTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (!epicTask.getStatus().equals(Status.NEW)) {
            System.out.println("У создаваемой задачи должен быть статус NEW");
            epicTask.setStatus(Status.NEW);
        }
        int i = generateId();
        epicTasks.put(i, epicTask); // Нельзя создать запись с переданным id, id уникален,
        epicTasks.get(i).setId(i);    // поэтому id будет присвоен при создании записи
        System.out.println("Большая задача создана.");
    }

    @Override
    public void updateEpicTask(Epic epicTask) {
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        if (epicTasks == null || epicTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (epicTask == null) {
            System.out.println("Такой задачи не существует");
            return;
        }
        epicTasks.replace(epicTask.getId(), epicTask);
        System.out.println("Большая задача обновлена");
        //Если у обновленной большой задачи статус NEW все соответствующие подзадачи должны быть NEW
        if (epicTask.getStatus().equals(Status.NEW)) {
            for (SubTask obj : subTasks.values()) {
                if (obj.getEpicID().equals(epicTask.getId())) {
                    obj.setStatus(Status.NEW);
                }
            }
        }
        if (epicTask.getStatus().equals(Status.DONE)) { //все соответствующие подзадачи должны быть DONE
            for (SubTask obj : subTasks.values()) {
                if (obj.getEpicID().equals(epicTask.getId())) {
                    obj.setStatus(Status.DONE);
                }
                System.out.println();
            }
        }
        //А если у обновленной большой задачи статус изменен на IN_PROGRESS, может возникнуть коллизия
        //эпик IN_PROGRESS все подзадачи NEW или DONE - как обрабатывать эту противоречивую логику - непонятно,
        // так как изменение статусов сабтасков при изменении статуса эпика в ТЗ не указано.
        // Возможно, следует запретить менять статус эпиков при вызове этого метода.
    }

    @Override
    public void deleteEpicTask(Integer id) {  // f. Удаление по идентификатору.
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
            if (obj.getEpicID() == epicTasks.get(id).getId()) {
                historyManager.remove(obj.getId());
                subTasks.remove(obj.getId());
            }
        }
        historyManager.remove(id);
        epicTasks.remove(id);
        System.out.println("Большая задача удалена");
    }

    @Override
    public void printAllTasksOfOneEpic(Integer id) { //Дополнительные методы: a. Получение списка всех подзадач
        // определённого эпика.
        System.out.println(epicTasks.get(id).toString());
        System.out.println("Подзадачи:");
        for (SubTask obj : subTasks.values()) {
            if (obj.getEpicID() == epicTasks.get(id).getId()) {
                System.out.println(obj);
            }
        }
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --SUB--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void addSubTask(String name, String description, Status status, Integer epicID) {
        int i = generateId();
        subTasks.put(i, new SubTask(name, description, epicID));
        subTasks.get(i).setId(i);
        subTasks.get(i).setEpicID(epicID);
        System.out.println("Подзадача добавлена");
    }

    @Override
    public void printAllSubTasks() {  //a. Получение списка всех подзадач. - вывод в консоль.
        if (subTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : subTasks.keySet())
                System.out.println(subTasks.get(i).toString());
        }
    }

    public List getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void deleteAllSubTasks() { // b. Удаление всех подзадач.
        subTasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    @Override
    public SubTask getSubTask(Integer id) { //c. Получение подзадачи по уникальному идентификатору
        if (subTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void createSubTask(SubTask subTask) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        //Проверка на соответствие типу
        boolean isEpic = false;
        if (subTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (epicTasks.isEmpty()) {
            System.out.println("Список больших задач пуст. Невозможно создать подзадачу");
            return;
        }
        for (Epic obj : epicTasks.values()) {
            isEpic = false;
            if (obj.getId().equals(subTask.getEpicID())) { //Если ID ecpic задачи совпадает с epicID подзадачи
                if (obj.getStatus().equals(Status.DONE)) { //И Статус эпика DONE
                    System.out.println("Эта большая задача завершена");
                    return;
                }
                int i = generateId();
                subTask.setId(i);
                if (!subTask.getStatus().equals(Status.NEW)) { //или NEW
                    System.out.println("У создаваемой подзадачи должен быть статус NEW");
                    subTask.setStatus(Status.NEW);
                }
                subTasks.put(i, subTask);
                System.out.println("Подзадача создана.");

            } else isEpic = true;
        }
        if (isEpic) {
            System.out.println("Для этой подзадачи нет большой задачи");
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
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
        subTasks.replace(subTask.getId(), subTask);
        for (Epic obj : epicTasks.values()) {
            if (obj.getId().equals(subTask.getEpicID())) {
                epicTaskID = obj.getId();  //id Заголовка
            }
        }
        for (SubTask obj : subTasks.values()) {
            if (obj.getEpicID().equals(subTask.getEpicID())) {
                if (obj.getStatus() != Status.NEW) isNew = false;
                if (obj.getStatus() != Status.DONE) isDone = false;
            }
            if (isNew) epicTasks.get(epicTaskID).setStatus(Status.NEW);
            else if (isDone) epicTasks.get(epicTaskID).setStatus(Status.DONE);
            else epicTasks.get(epicTaskID).setStatus(Status.IN_PROGRESS);
        }
        System.out.println("Подзадача обновлена");
    }

    @Override
    public void deleteSubTask(Integer id) {  // f. Удаление по идентификатору.
        if (subTasks == null || subTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        historyManager.remove(id);
        subTasks.remove(id);
        recalcOfStatus();
        System.out.println("Подзадача удалена");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --Task (обычные задачи)--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void addTask(String name, String description, Status status) {
        int i = generateId();
        tasks.put(i, new Task(name, description));
        tasks.get(i).setId(i);
        System.out.println("Добавлена простая задача.");
    }

    @Override
    public void printAllTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (tasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : tasks.keySet())
                System.out.println(tasks.get(i).toString());
        }
    }

    public List getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks() { // b. Удаление всех задач.
        tasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    @Override
    public Task getTask(Integer id) { //c. Получение по идентификатору.
        if (tasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!tasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public void createTask(Task task) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        if (task == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        int i = generateId();
        tasks.put(i, task);
        tasks.get(i).setId(i);
        System.out.println("Задача успешно создана.");
    }

    @Override
    public void updateTask(Task task) {
        // e. Обновление.
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (task == null) {
            System.out.println("Такой задачи не существует");
            return;
        }
        tasks.replace(task.getId(), task);
        System.out.println("Задача обновлена");
    }

    @Override
    public void deleteTask(Integer id) {  // f. Удаление по идентификатору.
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!tasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
        }
        historyManager.remove(id);
        tasks.remove(id);
        System.out.println("Задача удалена");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Приводит в соответствие статусы Epiс и Sub
    private void recalcOfStatus() {
        for (Epic o : epicTasks.values()) {
            boolean isNew = true;
            boolean isDone = true;
            int count = 0;
            int id = o.getId();
            for (SubTask obj : subTasks.values()) {
                if (o.getId().equals(obj.getEpicID())) {
                    count++;
                    if (obj.getStatus() != Status.NEW) isNew = false;
                    if (obj.getStatus() != Status.DONE) isDone = false;
                }
            }
            if (isNew || count == 0) epicTasks.get(id).setStatus(Status.NEW);
            else if (isDone) epicTasks.get(id).setStatus(Status.DONE);
            else epicTasks.get(id).setStatus(Status.IN_PROGRESS);
        }
    }
}


