public class ID {
    private static int ID;
    private static int epicID;
    static public int getID() {
        ID++;
        return ID;
    }

    static public int getEpicID() {
        epicID++;
        return epicID;
    }


}
