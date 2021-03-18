package model;

public class Card {
    private  String title;
    private  String label;
    private  String value;

    public Card(String title, String label, String value) {
        this.title = title;
        this.label = label;
        this.value = value;
    }

    public Card(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
