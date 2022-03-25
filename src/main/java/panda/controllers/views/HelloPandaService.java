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
                dataManager.findPath("panda-clipart.png"), 300, 100)
        );
    }

    public void confirm(String input){
        boolean getAccess = false;
        if(input.equals("admin")){
            getAccess = true;
        }
        //TODO cryptionControl
        if(getAccess){
            viewServicesManager.showPandaScene();
        }
    }

    public void cancel(){
        Platform.exit();
        System.exit(0);
    }

    public HelloPandaView getHelloPandaView(){
        return helloPandaView;
    }
}
