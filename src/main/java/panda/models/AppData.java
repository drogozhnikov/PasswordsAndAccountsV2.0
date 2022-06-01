package panda.models;

public class AppData {

    //all properties are selected and saved in base
    private String passGenPattern = "Aaaaaaaaaa11111";
    private String theme = "style1.css";
    private int screenWidth = 900;
    private int screenHeight = 700;
    private Owner selectedOwner = new Owner();

    //specials
    private int helloScreenWidth = 500;
    private int helloScreenHeight = 100;
    private boolean hidePass = true;

    public AppData(String passGenPattern, String theme, int screenWidth, int screenHeight, Owner selectedOwner) {
        this.passGenPattern = passGenPattern;
        this.theme = theme;
        this.selectedOwner = selectedOwner;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public AppData() {
    }

    public String getPassGenPattern() {
        return passGenPattern;
    }

    public String getTheme() {
        return theme;
    }

    public Owner getSelectedOwner() {
        return selectedOwner;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getHelloScreenWidth() {
        return helloScreenWidth;
    }

    public int getHelloScreenHeight() {
        return helloScreenHeight;
    }

    public boolean isHidePass() {
        return hidePass;
    }

    public void setHidePass(boolean hidePass) {
        this.hidePass = hidePass;
    }


    public void setPassGenPattern(String passGenPattern) {
        this.passGenPattern = passGenPattern;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setSelectedOwner(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


}
