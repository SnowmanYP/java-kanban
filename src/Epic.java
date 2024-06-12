public class Epic extends Task {
    protected Integer epicID;

    public Integer getEpicID() {
        return epicID;
    }

    public void setEpicID(Integer epicID) {
        this.epicID = epicID;
    }

    public Epic(String taskName, String description, Integer epicID) {
        super(taskName, description);
        this.epicID=epicID;
    }

    @Override
    public String toString(){
        if (this == null) {
            return "Нет такой задачи";
        }
        return "id-'" + id + "' Название задачи-'" + taskName + "' Описание задачи-'"
                + description + "' Статус задачи-'" + status + "' epicID-'" + epicID + "'";
    }
}

