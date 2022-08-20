package com.up.KGLSol.entity;


import com.up.KGLSol.exception.ReservationIllegalException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentStart;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentEnd;

    @ManyToOne

    @JoinColumn (name = "client_id")
    private Client client;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn (name = "rent_id")
    private Rentable rentable;


    public Reservation(Date reservationFrom, Date reservationTo, Client client, Rentable rentable) {

        this.rentStart = reservationFrom;
        this.rentEnd = reservationTo;
        this.client = client;
        this.rentable = rentable;
    }

    public Reservation() {

    }

    public Reservation(Client client, Rentable rentable) {
        this.client = client;
        this.rentable = rentable;
    }

    public String parseDateToString(Date date){
        System.out.println(date.toString());
        return date.toString().substring(0,10);
    }



    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", rentable=" + rentable.getName() +
                '}';
    }


    public void checkReservationAllow(Set<Reservation> reservations) throws ReservationIllegalException {
        if (reservations == null){
            return;
        }

        if (rentStart.compareTo(rentEnd) > 0){
            throw new ReservationIllegalException("You cannot reserve in past");
        }
        for (Reservation reservation: reservations) {
            System.out.println("This.rent start :  "  + this.rentStart);
            System.out.println("Outer.rent start :  "  + reservation.rentStart);
            if (Objects.equals(reservation.getId(), this.id)){
                break;
            }
            // 1 2 1
            if (reservation.rentStart.compareTo(rentStart) <= 0 && reservation.rentEnd.after(rentStart)){
                throw new ReservationIllegalException("This term is not allow");
            }
            if (rentStart.before(reservation.rentStart) && rentEnd.after(reservation.rentStart)){
                throw new ReservationIllegalException("This term is not allow");
            }
        }
    }
}

