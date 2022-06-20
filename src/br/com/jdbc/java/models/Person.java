package br.com.jdbc.java.models;

public class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

}
