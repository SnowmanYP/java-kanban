import org.junit.jupiter.api.Test;

class EpicTest {

    @Test
    void addEpicToYourSelf(){ // проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;
        TaskManager taskManager=new InMemoryTaskManager();
        Epic epic1 = new Epic("Первая эпическая", "Не битва и не война, просто задача");
        taskManager.createTask(epic1);
        //Добавить подзадачу в эпик можно только созданием подзадачи - пробуем:
        // taskManager.createSubTask(epic1); //java: incompatible types: Epic cannot be converted to SubTask
        //Невозможно построить тест для проверки, код не компилируется
        //....
    }
}