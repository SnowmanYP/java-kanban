import java.util.HashMap;

public class EpicTasks {
    public HashMap<Integer, EpicTask> epicTasks = new HashMap<>();

    public void addEpicTask(String name, String description, Status status, Hierarchy hierarchy) {
        if (hierarchy.name() == "EPIC") {
            int i = ID.getID();
            epicTasks.put(i, new EpicTask(name, description, status, hierarchy));
            epicTasks.get(i).id = i;
            System.out.println("Большая задача добавлена.");
        } else {
            System.out.println("У создаваемой задачи уровень " + hierarchy.name() + " требуемый уровень - EPIC");
        }
    }

    public void printOneTask(EpicTask epicTask) { //Вывод в консоль одной задачи
        if (epicTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("Название задачи-'" + epicTask.taskName + "' поле_id-'" + epicTask.id + "' Описание задачи-'"
                + epicTask.description + "' Статус задачи-'" + epicTask.status + "' Иерархичность -'" + epicTask.hierarchy + "'");
    }

    public void printAllEpicTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (epicTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : epicTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id" + epicTasks.get(i).id + "' Название задачи-'"
                        + epicTasks.get(i).taskName + "' Описание задачи-'" + epicTasks.get(i).description
                        + "' Статус задачи-'" + epicTasks.get(i).status + "' Иерархичность -'"
                        + epicTasks.get(i).hierarchy + "'");
        }
    }

    public void dellAllEpicTasks() { // b. Удаление всех задач.
        epicTasks.clear();
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

    public void createEpicTask(EpicTask epicTask) { // d. Создание. Сам объект должен передаваться в качестве параметра.
        if (epicTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        if (epicTask.hierarchy.name() == "EPIC") {
            int i = ID.getID();
            epicTasks.put(i, epicTask); // Нельзя создать запись с переданным id, id будет присваиться при создании записи
            epicTasks.get(i).id = i;
            System.out.println("Большая задача создана.");
        } else {
            System.out.println("У создаваемой задачи уровень " + epicTask.hierarchy.name() + " требуемый уровень - EPIC");
        }
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
        epicTasks.replace(id, epicTask); //...Вместе с главной задачей нужно обновить и подзадачи!!! вызвать методы из
        //SubTask
        System.out.println("Запись обновлена.");
    }

    public void delEpicTask(Integer id) {  // f. Удаление по идентификатору.
        if (epicTasks == null || epicTasks.isEmpty()) {
            System.out.println("Списка задач не существует или список пустой");
            return;
        }
        if (!epicTasks.containsKey(id)) {
            System.out.println("Нет задачи с таким идентификатором");
            return;
        }
        epicTasks.remove(id);  /// .... Вместе с главной задачей нужно удалить и подзадачи!!!-- вызвать методы из
        //SubTask
        System.out.println("Задача удалена");
    }
}