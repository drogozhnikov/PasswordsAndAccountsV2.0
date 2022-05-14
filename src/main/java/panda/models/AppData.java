package panda.models;

public class AppData {

    //all properties are selected and saved in base
    private String passGenPattern;
    private String theme;
    private int owner;
    private int screenWidth;
    private int screenHeight;

    public AppData(String passGenPattern, String theme, int owner, int screenWidth, int screenHeight) {
        this.passGenPattern = passGenPattern;
        this.theme = theme;
        this.owner = owner;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

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
