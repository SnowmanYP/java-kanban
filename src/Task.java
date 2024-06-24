import java.util.Objects;

public class Task {
    protected Integer id;
    protected String taskName;
    protected String description;
    protected Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW;
    }

    @Override  // "...При этом две задачи с одинаковым id должны выглядеть для менеджера как одна и та же."
    public boolean equals(Object obj){
    if (this==obj) return true;
    if (obj==null || getClass()!=obj.getClass() && !(obj instanceof Task)) return false;
    return Objects.equals(id,((Task) obj).id);
    }

    @Override
    public int hashCode(){
       return Objects.hash(id);
    }


    @Override
    public String toString(){
        if (this == null) {
            return "Нет такой задачи";
        }
        return "id-'" + id + "' Название задачи-'" + taskName + "' Описание задачи-'"
                + description + "' Статус задачи-'" + status;
    }
}

