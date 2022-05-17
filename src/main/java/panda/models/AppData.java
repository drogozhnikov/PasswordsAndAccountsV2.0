package panda.models;

public class AppData {

    //all properties are selected and saved in base
    private String passGenPattern = "Aaaaaaaaaa11111";
    private String theme = "style1.css";
    private int owner = 1;
    private int screenWidth = 900;
    private int screenHeight = 700;

    //specials
    private int helloScreenWidth = 500;
    private int helloScreenHeight = 100;
    private boolean hidePass = true;

    public AppData(String passGenPattern, String theme, int owner, int screenWidth, int screenHeight) {
        this.passGenPattern = passGenPattern;
        this.theme = theme;
        this.owner = owner;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public AppData(){}

    public String getPassGenPattern() {
        return passGenPattern;
    }

    public String getTheme() {
        return theme;
    }

    public int getOwner() {
        return owner;
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

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


}
