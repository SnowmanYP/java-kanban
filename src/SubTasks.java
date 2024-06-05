import java.util.HashMap;

public class SubTasks {
    public HashMap<Integer, SubTask> subTasks = new HashMap<>();
    public void addSubTask(String name, String description, Status status, Hierarchy hierarchy, Integer epicID) {
        if (!TaskManager.epicTasks.epicTasks.containsKey(epicID)){ // Из-за этой проверки пришлось сделать
            // static переменные в классе TaskManager
            System.out.println("Для этой подзадачи не существует сложной задачи!");
            return;
        }
        if (hierarchy.name() == "SUB") {
            int i = ID.getID();
            subTasks.put(i, new SubTask(name, description, status, hierarchy, epicID));
            subTasks.get(i).id = i;
            subTasks.get(i).epicID=epicID;
            System.out.println("Подзадача добавлена");
        } else {
            System.out.println("У создаваемой задачи уровень " + hierarchy.name() + " требуемый уровень - SUB");
        }
    }

    public void printOneTask(SubTask subTask) { //Вывод в консоль одной задачи
        if (subTask == null) {
            System.out.println("Нет такой задачи");
            return;
        }
        System.out.println("Название задачи-'" + subTask.taskName + "' поле_id-'" + subTask.id + "' Описание задачи-'"
                + subTask.description + "' Статус задачи-'" + subTask.status + "' Иерархичность -'" + subTask.hierarchy + "'");
    }

    public void printAllSubTasks() {  //a. Получение списка всех задач. - вывод в консоль.
        if (subTasks.isEmpty()) System.out.println("Нет задач в списке");
        else {
            for (Integer i : subTasks.keySet())
                System.out.println("ID_Key-'" + i + "' поле id" + subTasks.get(i).id + "' Название задачи-'"
                        + subTasks.get(i).taskName + "' Описание задачи-'" + subTasks.get(i).description
                        + "' Статус задачи-'" + subTasks.get(i).status + "' Иерархичность -'"
                        + subTasks.get(i).hierarchy + "'");
        }
    }

    public void dellAllSubTasks() { // b. Удаление всех задач.
        subTasks.clear();
        System.out.println("Список подзадач полностью удален!");
    }

    public SubTask getSubTask(Integer id) { //c. Получение по уникальному идентификатору.
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
    /*public EpicTask getEpicTaskEpicID(Integer epicID) { //c.1 Получение по идентификатору принадлежности к большой задаче.
        if (subTasks == null) {
            System.out.println("Списка задач не существует!");
            return null;
        }
        if (!subTasks.containsKey(epicID)) {
            System.out.println("Нет записи с таким идентификатором!");
            return null;
        }
        if (!TaskManager.epicTasks.epicTasks.containsKey(epicID)){ // Из-за этой проверки пришлось сделать
            // static переменные в классе TaskManager
            System.out.println(TaskManager.epicTasks.epicTasks.e);
            System.out.println(TaskManager.epicTasks.epicTasks.containsKey(epicID));
            //TaskManager.epicTasks.epicTasks.containsKey(epicID);
            System.out.println("Для этой подзадачи не существует сложной задачи!");
            return null;} else {}

        return TaskManager.epicTasks.getEpicTask(epicID);
        //subTasks.get(id);
    }*/

    public void createSubTask(SubTask subTask) { // d. Создание. Сам объект должен передаваться в качестве параметра.

        /// Возможно вообще нет больших задач. Проверку. если список больших задач пуст или ссылка==null тогда ничего не
        // делать и вывести предупреждение++
       if (TaskManager.epicTasks.epicTasks.isEmpty()) {
           System.out.println("Невозможно создать подзадачу. Не существует ни одной большой задачи.");
           return;
       }

        /// Нужно сделать проверку, возможно нет большой подзадачи с таким ID - обратится к карте эпик перебрать все поля
        /// пока не найдется с epicID иначе подзадачу добавить некуда, возникает вопрос - а можно сначала создать подзадачу
        /// а потом большую задачу?

        if (subTask == null) {
            System.out.println("Получена пустая ссылка");
            return;
        }
        /*System.out.println(subTask.epicID);
        for (EpicTask eT : TaskManager.epicTasks.){
            System.out.println(eT.epicID);
        }*/


        if (subTask.hierarchy.name() == "SUB") {
            int i = ID.getID();
            subTasks.put(i, subTask); // Нельзя создать запись с переданным id, id будет присваиться при создании записи
            subTasks.get(i).id = i;
            System.out.println("Подзадача создана.");
        } else {
            System.out.println("У создаваемой задачи уровень " + subTask.hierarchy.name() + " требуемый уровень - SUB");
        }
    }
}