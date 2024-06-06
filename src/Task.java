import java.util.Objects;

public class Task {
    Integer id;
    String taskName;
    String description;
    Status status;

        public Task(String taskName, String description, Status status) {
        this.taskName = taskName;
        this.description = description;
        this.status = status;
    }

    @Override  // "...При этом две задачи с одинаковым id должны выглядеть для менеджера как одна и та же."
    public boolean equals(Object obj){
    if (this==obj) return true;
    if (obj==null || getClass()!=obj.getClass()) return false;
    return Objects.equals(id,((Task) obj).id);
    }

    @Override
    public int hashCode(){
       return Objects.hash(id);
    }
}
