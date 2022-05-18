package com.msdelivery.entity;

import com.msdelivery.enums.ParcelStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "parcels")
@Entity
public class Parcel implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parcel_sending_time")
    private LocalDateTime parcelSendingTime;
    @Column(name = "parcel_approximate_acceptance_time")
    private LocalDateTime parcelApproximateAcceptanceTime;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ParcelStatus status;
    @Column(name = "customer_username")
    private String customerUsername;
    @Column(name = "courier_username")
    private String courierUsername;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


    public Parcel() {
    }

    public Parcel(Builder builder) {
        this.id = builder.id;
        this.parcelSendingTime = builder.parcelSendingTime;
        this.parcelApproximateAcceptanceTime = builder.parcelApproximateAcceptanceTime;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.status = builder.status;
        this.customerUsername = builder.customerUsername;
        this.courierUsername = builder.courierUsername;
        this.order = builder.order;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getParcelSendingTime() {
        return parcelSendingTime;
    }

    public LocalDateTime getParcelApproximateAcceptanceTime() {
        return parcelApproximateAcceptanceTime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public ParcelStatus getStatus() {
        return status;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public Order getOrder() {
        return order;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private LocalDateTime parcelSendingTime;
        private LocalDateTime parcelApproximateAcceptanceTime;
        private Double longitude;
        private Double latitude;
        private ParcelStatus status;
        private String customerUsername;
        private String courierUsername;
        private Order order;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setParcelSendingTime(LocalDateTime parcelSendingTime) {
            this.parcelSendingTime = parcelSendingTime;
            return this;
        }

        public Builder setParcelApproximateAcceptanceTime(LocalDateTime parcelApproximateAcceptanceTime) {
            this.parcelApproximateAcceptanceTime = parcelApproximateAcceptanceTime;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setStatus(ParcelStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCustomerUsername(String customerUsername) {
            this.customerUsername = customerUsername;
            return this;
        }

        public Builder setCourierUsername(String courierUsername) {
            this.courierUsername = courierUsername;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Parcel build() {
            return new Parcel(this);
        }

        public static Parcel.Builder of(Parcel parcel) {
            return new Builder()
                    .setId(parcel.getId())
                    .setParcelSendingTime(parcel.getParcelSendingTime())
                    .setParcelApproximateAcceptanceTime(parcel.getParcelApproximateAcceptanceTime())
                    .setLongitude(parcel.getLatitude())
                    .setLatitude(parcel.getLatitude())
                    .setStatus(parcel.getStatus())
                    .setCustomerUsername(parcel.getCustomerUsername())
                    .setCourierUsername(parcel.getCourierUsername())
                    .setOrder(parcel.getOrder());
        }
    }
}
