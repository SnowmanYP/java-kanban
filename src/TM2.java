import java.util.HashMap;

public class TM2 {
    public static HashMap<Integer, Task> middleTasks = new HashMap<>();

    public static void main(String[] args) {
        TM2 taskManager = new TM2();
        //  HashMap<Integer, Task> middleTasks=new HashMap<>();
        taskManager.addMiddleTask(middleTasks, "Первая задача", "Описание", Status.NEW, Hierarchy.MIDDLE);
        taskManager.addMiddleTask(middleTasks, "Вторая задача", "Описание", Status.IN_PROGRESS, Hierarchy.MIDDLE);
        taskManager.addMiddleTask(middleTasks, "Третья задача", "Описание", Status.DONE, Hierarchy.MIDDLE);

        //taskManager.printAllMiddleTask(middleTasks);

        //taskManager.dellAllMiddleTask(middleTasks);
        //taskManager.printAllMiddleTask(middleTasks);

        //taskManager.getMiddleTask(middleTasks,2);

       /* Task task4=new Task("Четвертая задача", "Описание", Status.NEW, Hierarchy.MIDDLE);
        Task task5=new Task("Пятая задача", "Описание", Status.IN_PROGRESS, Hierarchy.MIDDLE);
        Task task6=new Task("Шестая задача", "Описание", Status.DONE, Hierarchy.MIDDLE);*/

        /*taskManager.newMiddleTask(null,task4);
        taskManager.newMiddleTask(middleTasks,null);
        taskManager.newMiddleTask(middleTasks,task4);
        taskManager.printAllMiddleTask(middleTasks);
        */

        /*taskManager.updateMiddleTask(middleTasks,task6,2);
        taskManager.printAllMiddleTask(middleTasks);*/

        /*taskManager.delMiddleTask(middleTasks,2);
        System.out.println();
        taskManager.printAllMiddleTask(middleTasks);*/
    }


    void addMiddleTask(HashMap<Integer, Task> tasks, String name, String description, Status status, Hierarchy hierarchy) {
        //Добавление задачи - для тестов
        tasks.put(ID.getID(), new Task(name, description, status, hierarchy));

    }
    void printAllMiddleTask(HashMap<Integer, Task> tasks) {  //a. Получение списка всех задач. - вывод в консоль.
        if (tasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : tasks.keySet())
                System.out.println("ID-'" + i + "' Название задачи-'" + tasks.get(i).taskName + "' Описание задачи-'"
                        + tasks.get(i).description + "' Статус задачи-'" + tasks.get(i).status + "' Иерархичность -'"
                        + tasks.get(i).hierarchy + "'");
        }
    }

    void dellAllMiddleTask(HashMap<Integer, Task> tasks) { // b. Удаление всех задач.
        tasks.clear();
    }
    Task getMiddleTask(HashMap<Integer, Task> tasks, Integer id) { //c. Получение по идентификатору.
        if (tasks==null) {System.out.println("Списка задач не существует!"); return null;}
        if (!tasks.containsKey(id)) {
            System.out.println("Нет записи с таким идентификатором!"); return null;}

       /* System.out.println("ID-'" + id + "' Название задачи-'" + tasks.get(id).taskName + "' Описание задачи-'"
                + tasks.get(id).description + "' Статус задачи-'" + tasks.get(id).status + "' Иерархичность -'"
                + tasks.get(id).hierarchy + "'");*/
        return tasks.get(id);
    }

    void newMiddleTask(HashMap<Integer, Task> tasks,Task task){
        //d. Создание. Сам объект должен передаваться в качестве параметра.
        if (tasks==null || tasks.isEmpty()) {System.out.println("Списка задач не существует или список пустой"); return;}
        if (task==null) {
            System.out.println("Такой задачи не существует"); return;
        }
        tasks.put(ID.getID(),task); //?? Нужен ил ненуже ID внутри Task
    }

    void updateMiddleTask(HashMap<Integer, Task> tasks,Task task, Integer id){
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        if (tasks==null || tasks.isEmpty()) {System.out.println("Списка задач не существует или список пустой"); return;}
        if (task==null) {
            System.out.println("Такой задачи не существует"); return;
        }
        if (!tasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
        }
        tasks.replace(id,task);
    }

void delMiddleTask(HashMap<Integer, Task> tasks, Integer id){
    if (tasks==null || tasks.isEmpty()) {System.out.println("Списка задач не существует или список пустой"); return;}
    if (!tasks.containsKey(id)) {
        System.out.println("Нет задачи с таким идентификатором");
}
tasks.remove(id); }
}





