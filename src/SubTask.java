public class SubTask extends Task {
    Integer epicID;

    public SubTask(String taskName, String description, Status status, Integer epicID) {
        super(taskName, description, status);
        this.epicID = epicID;
    }
}
