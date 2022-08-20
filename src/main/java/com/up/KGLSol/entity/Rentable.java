package com.up.KGLSol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Getter
@Setter
public class Rentable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name ="";

    @Column
    private String description="";

    @Column
    private long price;
    @Column
    private long area;


    @JsonIgnore
    @OneToMany(mappedBy = "rentable")
    private Set<Reservation> reservations;


    public Rentable() {

    }


    public Rentable(String name, String description, long price, long area) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Rentable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", reservations=" + reservations +
                '}';
    }
}
