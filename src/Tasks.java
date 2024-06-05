import java.util.HashMap;

public class Tasks {

    public HashMap<Integer, Task> middleTasks = new HashMap<>();

    public void addMiddleTask(String name, String description, Status status, Hierarchy hierarchy) {
        if (hierarchy.name() == "MIDDLE") {
            int i = ID.getID();
            middleTasks.put(i, new EpicTask(name, description, status, hierarchy));
            middleTasks.get(i).id = i;
            System.out.println("Добавлена простая задача.");
        } else {
            System.out.println("У создаваемой задачи уровень " + hierarchy.name() + " требуемый уровень - MIDDLE");
        }
    }

    public void printOneTask(Task task) { //Вывод в консоль одной задачи
        if (task == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("Название задачи-'" + task.taskName + "' поле_id-'" + task.id + "' Описание задачи-'"
                + task.description + "' Статус задачи-'" + task.status + "' Иерархичность -'" + task.hierarchy + "'");
    }

    public void printAllMiddleTask() {  //a. Получение списка всех задач. - вывод в консоль.
        if (middleTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : middleTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле_id-'" + middleTasks.get(i).id + "' Название задачи-'"
                        + middleTasks.get(i).taskName + "' Описание задачи-'" + middleTasks.get(i).description
                        + "' Статус задачи-'" + middleTasks.get(i).status + "' Иерархичность -'"
                        + middleTasks.get(i).hierarchy + "'");
        }
    }

    public void dellAllMiddleTask() { // b. Удаление всех задач.
        middleTasks.clear(); System.out.println("Список подзадач полностью удален!");
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
        if (task.hierarchy.name() == "MIDDLE") {
            int i = ID.getID();
            middleTasks.put(i, task); // Нельзя создать запись с переданным id, id будет присваиться при создании записи
            middleTasks.get(i).id = i;
            System.out.println("Задача успешно создана.");
        } else {
            System.out.println("У создаваемой задачи уровень " + task.hierarchy.name() + " требуемый уровень - EPIC");
        }
    }


    public void updateMiddleTask(Task task, Integer id) {
        // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
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
        middleTasks.replace(id, task);
        System.out.println("Запись обновлена.");
    }

    public void delMiddleTask(Integer id) {  // f. Удаление по идентификатору.
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
