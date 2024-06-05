import java.awt.print.Book;
import java.util.Objects;

public class Task {
    Integer id;
    String taskName;
    String description;
    Status status; //Статус задачи
    Hierarchy hierarchy; //Тип задачи

//        public Task(){}
        public Task(String taskName, String description, Status status, Hierarchy hierarchy) {
        //this.id=id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.hierarchy = hierarchy;
    }

    @Override  // "...При этом две задачи с одинаковым id должны выглядеть для менеджера как одна и та же."
    public boolean equals(Object obj){
    if (this==obj) return true;
    if (obj==null || getClass()!=obj.getClass()) return false;
    //Task otherTask=(Task) obj;
    return Objects.equals(id,((Task) obj).id);
    }

    @Override
    public int hashCode(){
       return Objects.hash(id);
    }
}
