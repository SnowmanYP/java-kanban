package task;

public class SubTask extends Task {
    private Integer epicID;

    @Override
    public TasksType getTasksType() {
        return tasksType;
    }

    private TasksType tasksType=TasksType.SUBTASK;

    public Integer getEpicID() {
        return epicID;
    }

    public void setEpicID(Integer epicID) {
        this.epicID = epicID;
    }

    public SubTask(String taskName, String description, Integer epicID) {
        super(taskName, description);
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "Нет такой задачи";
        }
        //return "id-'" + this.getId() + "' Название задачи-'" + this.getTaskName() + "' Описание задачи-'" + this.getDescription() + "' Статус задачи-'" + this.getStatus() + "' epicID-'" + epicID + "'";
        return getId() +"," + getTasksType() +  "," + getTaskName()   + "," + getStatus()  +"," + getDescription() + "," + getEpicID();
    }
}
