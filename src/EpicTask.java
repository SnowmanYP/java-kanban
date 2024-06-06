public class EpicTask extends Task {
    Integer epicID;

    public EpicTask(String taskName, String description, Status status, Hierarchy hierarchy) {
        super(taskName, description, status, hierarchy);
        //epicID = ID.getEpicID();
    }
}

