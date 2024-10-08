package task;

public class SubTask extends Task {
    private Integer epicID;

    @Override
    public TasksType getTasksType() {
        return tasksType;
    }

    public Integer getEpicID() {
        return epicID;
    }

    public void setEpicID(Integer epicID) {
        this.epicID = epicID;
    }

    public SubTask(String taskName, String description, Integer epicID) {
        super(taskName, description);
        this.epicID = epicID;
        tasksType = TasksType.SUBTASK;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "Нет такой задачи";
        }
        return getId() + "," + getTasksType() + "," + getTaskName() + "," + getStatus() + "," + getDescription()
                + "," + getEpicID();
    }
}
