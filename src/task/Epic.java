package task;

public class Epic extends Task {
    @Override
    public TasksType getTasksType() {
        return tasksType;
    }

    private TasksType tasksType=TasksType.EPIC;
    public Epic(String taskName, String description) {
        super(taskName, description);
    }
}