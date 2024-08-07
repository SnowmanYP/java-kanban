package task;

public class Epic extends Task {
    @Override
    public TasksType getTasksType() {
        return tasksType;
    }

    public Epic(String taskName, String description) {
        super(taskName, description);
        tasksType = TasksType.EPIC;
    }
}