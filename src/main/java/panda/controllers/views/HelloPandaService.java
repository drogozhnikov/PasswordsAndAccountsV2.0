package panda.controllers.views;

import javafx.application.Platform;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.utils.Utils;
import panda.views.elements.HelloPandaView;

public class HelloPandaService {

    private final DataManager dataManager;
    private final ViewServicesManager viewServicesManager;

    private HelloPandaView helloPandaView;

    public HelloPandaService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
        helloPandaView = new HelloPandaView(this, Utils.getImage(
                dataManager.findPath("panda-clipart.png"))
        );
    }

    public void confirm(String input){
        viewServicesManager.showPandaScene();
        //TODO cryptionControl
    }

    public void cancel(){
        Platform.exit();
        System.exit(0);
    }

    public HelloPandaView getHelloPandaView(){
        return helloPandaView;
    }
}
