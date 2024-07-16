package managers;

import manager.Managers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    //убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;
    @Test
    void getDefault() {
        assertNotNull(Managers.getDefault(), "TaskManger is not OK");
    }

    @Test
    void getDefaultHistory() {
        assertNotNull(Managers.getDefaultHistory(), "HistoryManager is not OK");
    }
}