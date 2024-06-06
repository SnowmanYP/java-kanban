import java.util.HashMap;

public class TaskManager {
    public static HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    public static HashMap<Integer, SubTask> subTasks = new HashMap<>();
    public static HashMap<Integer, Task> middleTasks = new HashMap<>();

    public static void main(String[] args) {
// Добавляем три простых задачи для теста уровня SUB
       /* tasks.addMiddleTask("Первая простая задача", "Абракадабра", Status.NEW, Hierarchy.MIDDLE);
        tasks.addMiddleTask("Вторая простая задача", "Описание", Status.IN_PROGRESS, Hierarchy.MIDDLE);
        tasks.addMiddleTask("Третья простая задача", "Описание", Status.DONE, Hierarchy.MIDDLE);
       */
// Тестируем переопределенный equals() и hashCode()
/*System.out.println(tasks.middleTasks.get(1).id+" "+tasks.middleTasks.get(1).hashCode());
tasks.middleTasks.get(2).id=7;
System.out.println(tasks.middleTasks.get(2).id+" "+tasks.middleTasks.get(2).hashCode());
System.out.println(tasks.middleTasks.get(1).equals(tasks.middleTasks.get(2)));*/

//Добавляем задачи уровня EPIC
        addEpicTask("Первая эпическая", "Не битва и не война, просто задача", Status.IN_PROGRESS, Hierarchy.EPIC);
        addEpicTask("Вторая эпическая", "Не просто задача", Status.IN_PROGRESS, Hierarchy.EPIC);
        addEpicTask("Третья эпическая", "Сложная задача", Status.IN_PROGRESS, Hierarchy.EPIC);

// printOneEpicTask(epicTasks.get(4)); //Тестируем метод - работает ++
// printAllEpicTasks(); //Тестируем метод - работает ++
// dellAllEpicTasks(); printAllEpicTasks(); //Тестируем метод - работает ++
// printOneEpicTask(getEpicTask(5)); //Тестируем метод getEpicTask - работает ++

        //Тестируем метод createEpicTask() - работает ++
/*printAllEpicTasks(); System.out.println();
EpicTask newEpicTask=new EpicTask("Тестируем createEpicTask()","Новая задача уровня EPIC",
Status.NEW,Hierarchy.EPIC);
createEpicTask(newEpicTask); printAllEpicTasks(); System.out.println();*/
//Тестируем метод updateEpicTask() - работает ??
/* printAllEpicTasks();
EpicTask newEpicTask=new EpicTask("Тестируем updateEpicTask()","Обновленная задача уровня EPIC",
       Status.NEW,Hierarchy.EPIC);
System.out.println(newEpicTask.id +" " + newEpicTask.epicID); updateEpicTask(newEpicTask, 5);
printAllEpicTasks();*/

//Добавляем подзадачи уровня SUB
        addSubTask("Первая подзадача", "Первая подзадача первой эпической", Status.DONE, Hierarchy.SUB, 1);
        addSubTask("Вторая подзадача", "Вторая подзадача первой эпической", Status.DONE, Hierarchy.SUB, 1);
        addSubTask("Третья подзадача", "1-я подзадача 2-ой эпической", Status.IN_PROGRESS, Hierarchy.SUB, 2);
        addSubTask("Четвертая подзадача", "Третья подзадача первой эпической", Status.DONE, Hierarchy.SUB, 1);

//Тестируем метод dellEpicTask - работает++
        /*printAllEpicTasks();
        printAllSubTasks();
        dellEpicTask(4);
        printAllEpicTasks();
        printAllSubTasks();*/
//Тестируем метод printAllTasksOfOneEpic ++
        //printAllTasksOfOneEpic(4);
//Тестируем метод dellAllEpicTask - работает++
        /*printAllEpicTasks();
        printAllSubTasks();
        dellAllEpicTasks();
        printAllEpicTasks();
        printAllSubTasks();*/
//Снова тестируем updateEpicTask обновление статусов подзадач
    /*printAllEpicTasks();
    System.out.println();
    printAllSubTasks();

    EpicTask newEpicTask=new EpicTask("Тестируем createEpicTask()","Новая задача уровня EPIC",
    Status.DONE,Hierarchy.EPIC);
    updateEpicTask(newEpicTask,1);

    printAllEpicTasks();
    System.out.println();
    printAllSubTasks();*/
        //Тестируем метод createSubTask - работает++
        /*printAllSubTasks();
        printAllEpicTasks();
        System.out.println();
        SubTask sT = new SubTask("Подзадача", "Создана подзадача к эпической", Status.IN_PROGRESS, Hierarchy.SUB, 1);
        createSubTask(sT);
        printAllSubTasks();
        printAllEpicTasks();*/
        //Тестируем метод updateSubTask();

        /*printAllEpicTasks();
        System.out.print("\n");
        printAllSubTasks();
        System.out.print("\n\n");
        SubTask sT = new SubTask("Подзадача", "Обновлена подзадача", Status.DONE, Hierarchy.SUB, 1);
        updateSubTask(sT, 7);
        printAllEpicTasks();
        System.out.print("\n");
        printAllSubTasks();*/

        addMiddleTask("Первая задача", "Описание", Status.DONE, Hierarchy.MIDDLE);
        addMiddleTask("Вторая задача", "Описание", Status.IN_PROGRESS  , Hierarchy.MIDDLE);
        addMiddleTask("Третья задача", "Описание", Status.NEW, Hierarchy.MIDDLE);
        addMiddleTask("Четвертая задача", "Описание", Status.NEW, Hierarchy.MIDDLE);
        Task task=new Task("Обновленная задача", "Обновление", Status.NEW, Hierarchy.MIDDLE);
        updateMiddleTask(task, 10);
        Task task2=new Task("Созданная задача", "Создание", Status.NEW, Hierarchy.MIDDLE);
        createMiddleTask(task2);
        printAllMiddleTask();




    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --EPIC--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void addEpicTask(String name, String description, Status status, Hierarchy hierarchy) {
        if (hierarchy.name().equals("EPIC")) {
            int i = ID.getID();
            epicTasks.put(i, new EpicTask(name, description, status, hierarchy));
            epicTasks.get(i).id = i;
            epicTasks.get(i).epicID = ID.getEpicID();
            System.out.println("Большая задача добавлена.");
        } else {
            System.out.println("У создаваемой задачи уровень " + hierarchy.name() + " требуемый уровень - EPIC");
        }
    }

    public static void printOneEpicTask(EpicTask epicTask) { //Вывод в консоль одной задачи
        if (epicTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("поле id-'" + epicTask.id + "' Название задачи-'" + epicTask.taskName + "' Описание задачи-'"
                + epicTask.description + "' Статус задачи-'" + epicTask.status + "' Иерархичность -'" + epicTask.hierarchy + "' epicID-'" + epicTask.epicID + "'");
    }

    public static void printAllEpicTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (epicTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : epicTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id-'" + epicTasks.get(i).id + "' Название задачи-'"
                        + epicTasks.get(i).taskName + "' Описание задачи-'" + epicTasks.get(i).description
                        + "' Статус задачи-'" + epicTasks.get(i).status + "' Иерархичность -'"
                        + epicTasks.get(i).hierarchy + "' epicID-'" + epicTasks.get(i).epicID + "'");
        }
    }

    public static void dellAllEpicTasks() { // b. Удаление всех задач.
        epicTasks.clear();
        dellAllSubTasks();
        //Если мы удалили все EPIC-задачи значит нужно удалить и все SUB задачи, но не наоборот
        //Подзадачи не существуют в отдельности от EPIC задач
        System.out.println("Список больших задач полностью удален!");
    }

    public static EpicTask getEpicTask(Integer id) { //c. Получение по идентификатору.
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

    public static void createEpicTask(EpicTask epicTask) { // d. Создание. Сам объект должен передаваться
        // в качестве параметра.
        if (epicTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (epicTask.hierarchy.name().equals("EPIC")) {
            if (!epicTask.status.equals(Status.NEW)) {  //??
                System.out.println("У создаваемой задачи должен быть статус NEW");
                epicTask.status = Status.NEW;
            }
            int i = ID.getID();
            epicTasks.put(i, epicTask); // Нельзя создать запись с переданным id, id уникален,
            epicTasks.get(i).id = i;    // поэтому id будет присвоен при создании записи
            epicTasks.get(i).epicID = ID.getEpicID();
            System.out.println("Большая задача создана.");
        } else {
            System.out.println("У создаваемой задачи уровень " + epicTask.hierarchy.name()
                    + " требуемый уровень - EPIC");
        }
    }


    public static void updateEpicTask(EpicTask epicTask, Integer id) {
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
    }


    public static void dellEpicTask(Integer id) {  // f. Удаление по идентификатору.
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

    public static void printAllTasksOfOneEpic(Integer id) { //Дополнительные методы: a. Получение списка всех подзадач
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

    public static void addSubTask(String name, String description, Status status, Hierarchy hierarchy, Integer epicID) {
            int i = ID.getID();
            subTasks.put(i, new SubTask(name, description, status, hierarchy, epicID));
            subTasks.get(i).id = i;
            subTasks.get(i).epicID = epicID;
            System.out.println("Подзадача добавлена");
    }

    public static void printOneSubTask(SubTask subTask) { //Вывод в консоль одной подзадачи
        if (subTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("поле id-'" + subTask.id + "' Название задачи-'" + subTask.taskName + "' Описание задачи-'"
                + subTask.description + "' Статус задачи-'" + subTask.status + "' Иерархичность -'" + subTask.hierarchy + "' epicID-'" + subTask.epicID + "'");
    }

    public static void printAllSubTasks() {  //a. Получение списка всех подзадач. - вывод в консоль.
        if (subTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : subTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id-'" + subTasks.get(i).id + "' Название задачи-'"
                        + subTasks.get(i).taskName + "' Описание задачи-'" + subTasks.get(i).description
                        + "' Статус задачи-'" + subTasks.get(i).status + "' Иерархичность -'"
                        + subTasks.get(i).hierarchy + "'" + " epicID-'" + subTasks.get(i).epicID + "'");
        }
    }

    public static void dellAllSubTasks() { // b. Удаление всех подзадач.
        subTasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    public static SubTask getSubTask(Integer id) { //c. Получение подзадачи по уникальному идентификатору
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

    public static void createSubTask(SubTask subTask) { // d. Создание. Сам объект должен передаваться в качестве параметра.
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
            if (obj.epicID.equals(subTask.epicID)) {
                if (obj.status.equals(Status.DONE)) {
                    System.out.println("Эта большая задача завершена");
                    return;
                }
                subTask.id = obj.id;
                if (!subTask.status.equals(Status.NEW)) {
                    System.out.println("У создаваемой задачи должен быть статус NEW");
                    subTask.status = Status.NEW;
                    subTasks.put(ID.getID(), subTask);
                    System.out.println("Подзадача создана.");
                } else isEpic = true;
            }
        }
        if (!isEpic) {
            System.out.println("Для этой подзадачи нет большой задачи");
        }

    }

    public static void updateSubTask(SubTask subTask, Integer id) {
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
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
        if (subTask.status.equals(Status.NEW)) { //Если все подзадачи эпика будут NEW и переданная подзадача так же NEW
            // Статус эпика меняется на NEW
            boolean isNew = true; //Предполагем, что все подзадачи будут NEW
            for (SubTask obj : subTasks.values()) {
                if (obj.epicID.equals(subTask.epicID)) { //Если подзадача входит в наш эпик
                    if (obj.status != Status.NEW) { //Предположили - пытаемся опровергнуть
                        isNew = false;
                        System.out.println(isNew);
                        System.out.println(obj.status);
                        System.out.println(obj.epicID);
                    }
                }
            }
            if (isNew) {
                for (EpicTask obj : epicTasks.values()) { //Меняем статус эпик на NEW
                    if (obj.epicID.equals(subTask.epicID)) {
                        obj.status = Status.valueOf("NEW");
                    }
                }
            }
        }
        if (subTask.status.equals(Status.DONE)) { //Если все подзадачи эпика будут DONE и переданная подзадача так же DONE
            // Статус эпика меняется на DONE
            boolean isNew = true; //Предполагем, что все подзадачи будут DONE
            for (SubTask obj : subTasks.values()) {
                if (obj.epicID.equals(subTask.epicID)) { //Если подзадача входит в наш эпик
                    if (obj.status != Status.DONE) { //Предположили - пытаемся опровергнуть
                        isNew = false;
                        System.out.println(isNew);
                        System.out.println(obj.status);
                        System.out.println(obj.epicID);
                    }
                }
            }
            if (isNew) {
                for (EpicTask obj : epicTasks.values()) { //Меняем статус эпик на DONE
                    if (obj.epicID.equals(subTask.epicID)) {
                        obj.status = Status.valueOf("DONE");
                    }
                }
            }
        }
        subTask.id = id;
        subTask.epicID = subTasks.get(id).epicID;
        subTasks.replace(id, subTask);
        System.out.println("Подзадача обновлена");
    }


    public static void dellSubTask(Integer id) {  // f. Удаление по идентификатору.
        if (subTasks == null || subTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!subTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        subTasks.remove(id);
        System.out.println("Подзадача удалена");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/МЕТОДЫ ДЛЯ ЗАДАЧ КЛАССА --MIDDLE (обычные задачи)--/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static void addMiddleTask(String name, String description, Status status, Hierarchy hierarchy) {
        int i = ID.getID();
        middleTasks.put(i, new Task(name, description, status, hierarchy));
        middleTasks.get(i).id = i;
        System.out.println("Добавлена простая задача.");
    }

    public static void printOneTask(Task task) { //Вывод в консоль одной задачи
        if (task == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("Название задачи-'" + task.taskName + "' поле_id-'" + task.id + "' Описание задачи-'"
                + task.description + "' Статус задачи-'" + task.status + "' Иерархичность -'" + task.hierarchy + "'");
    }

    public static void printAllMiddleTask() {  //a. Получение списка всех задач. - вывод в консоль.
        if (middleTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : middleTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле_id-'" + middleTasks.get(i).id + "' Название задачи-'"
                        + middleTasks.get(i).taskName + "' Описание задачи-'" + middleTasks.get(i).description
                        + "' Статус задачи-'" + middleTasks.get(i).status + "' Иерархичность -'"
                        + middleTasks.get(i).hierarchy + "'");
        }
    }

    public static void dellAllMiddleTask() { // b. Удаление всех задач.
        middleTasks.clear(); System.out.println("Список подзадач полностью удален!");
    }

    public static Task getMiddleTask(Integer id) { //c. Получение по идентификатору.
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

    public static void createMiddleTask(Task task) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        if (task == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
            int i = ID.getID();
            middleTasks.put(i, task);
            middleTasks.get(i).id = i;
            System.out.println("Задача успешно создана.");
    }

    public static void updateMiddleTask(Task task, Integer id) {
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
        task.id=id;
        middleTasks.replace(id, task);
        System.out.println("Задача обновлена");
    }

    public static void delMiddleTask(Integer id) {  // f. Удаление по идентификатору.
        if (middleTasks == null || middleTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!middleTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
        }
        middleTasks.remove(id);
    }
}