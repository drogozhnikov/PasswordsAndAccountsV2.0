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
        if (dataManager.initCheckAccess(new StringBuilder(input))) {
            viewServicesManager.showPandaScene();
            viewServicesManager.refresh();
        }else{
            logger.warn("Access Denied");
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
