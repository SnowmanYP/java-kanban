// Думаю так, задание сквозное,  что дальше к нему довесят, не очень понятно. Поэтому без выпендрежей. Класс Task и два наследника Sub и Epic. enum - статус. основной класс TasksManager. Основная структура данных - три HashMap<> Task, SubTask ,Epic.
//ключ - id задачи
//Класс Main обязателен в финальном проекте? А консольный интерфейс обязателен?

/*Методы для каждого из типа задач(Задача/Эпик/Подзадача):
a. Получение списка всех задач.
b. Удаление всех задач.
c. Получение по идентификатору.
d. Создание. Сам объект должен передаваться в качестве параметра.
e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
f. Удаление по идентификатору.*/
public class Main {
    public static void main(String[] args) {
        Tasks tasks=new Tasks();
        tasks.addMiddleTask("Первая задача", "Описание", Status.NEW, Hierarchy.MIDDLE);
        tasks.addMiddleTask("Вторая задача", "Описание", Status.IN_PROGRESS, Hierarchy.MIDDLE);
        tasks.addMiddleTask("Третья задача", "Описание", Status.DONE, Hierarchy.MIDDLE);

        tasks.printAllMiddleTask();
        tasks.printOneTask(tasks.getMiddleTask(2));
    }
}
