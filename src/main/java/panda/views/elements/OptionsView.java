package panda.views.elements;

import javafx.scene.layout.BorderPane;
import panda.controllers.views.OptionsService;

public class OptionsView extends BorderPane {

    private OptionsService optionsService;

    public OptionsView(OptionsService optionsService){
        this.optionsService = optionsService;
    }


}
