package panda.controllers.views;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.utils.Utils;
import panda.views.elements.HelloPandaView;

public class HelloPandaService {

    private final DataManager dataManager;
    private final ViewServicesManager viewServicesManager;

    private final Logger logger = LoggerFactory.getLogger(HelloPandaService.class);

    private HelloPandaView helloPandaView;

    public HelloPandaService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
        helloPandaView = new HelloPandaView(this, Utils.getImage(
                dataManager.findPath("panda-clipart.png"), 300, 100)
        );
    }

    public void confirm(String input) {
        int result = dataManager.checkAccess(new StringBuilder(input));

        final int accessConfirmed = 1;
        final int accessDenied = -1;
        final int passNotFount = 0;

        switch (result) {
            case accessConfirmed: {
                viewServicesManager.initPandaScene();
                viewServicesManager.showPandaScene();
                break;
            }
            case accessDenied: {
                logger.warn("Access Denied");
                break;
            }
            case passNotFount: {
                logger.warn("Password not exist");
                break;
            }
        }
    }

    public void cancel() {
        Platform.exit();
        System.exit(0);
    }

    public HelloPandaView getHelloPandaView() {
        return helloPandaView;
    }
}
