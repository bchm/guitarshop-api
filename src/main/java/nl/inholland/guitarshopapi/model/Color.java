package nl.inholland.guitarshopapi.model;

import javax.persistence.*;

@Entity
public class Color {
    @Id
    private long id;
    @Column
    private String color;

    public Color(){}

    public Color(String color, long id) {
        this.id = id;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn//(name = "guitar_id", nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
