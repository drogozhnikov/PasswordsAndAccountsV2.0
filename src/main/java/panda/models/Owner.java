package panda.models;

public class Owner implements Comparable<Owner>{

    private int id = 1;
    private String name = "all";

    public Owner(){}

    public Owner(int id, String ownerName) {
        this.id = id;
        this.name = ownerName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Owner o) {
        return this.id - o.id;
    }
}
