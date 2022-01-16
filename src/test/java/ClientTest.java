

import org.junit.Test;

public class ClientTest {

    @Test
    public void testClient() {
        client.Controller controller = new client.Controller();
        client.Client c = new client.Client(controller);
        controller.initialize();
        controller.initBoard(6);
        controller.movePiece(5, 5, 6, 6);
        controller.start(1);
    }
}
