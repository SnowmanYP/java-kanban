public class SubTask  extends Task{
    Integer epicID;
    public SubTask(String taskName, String description, Status status, Hierarchy hierarchy,Integer epicID){
        super(taskName,description,status,hierarchy);
        this.epicID=epicID;
    }


}
