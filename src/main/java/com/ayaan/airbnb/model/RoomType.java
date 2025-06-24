// package com.ayaan.airbnb.model;

// import org.springframework.stereotype.Component;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// @EqualsAndHashCode(callSuper = false)
// @Component
// @Entity
// @Table(name = "room_type")
// public class RoomType {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "room_type_id")
//     private Integer roomTypeId;
//     @Column(name = "room_type")
//     private String roomType;
//     @Column(name = "quantity")
//     private Integer quantity;
//     @ManyToOne
//     @JoinColumn(name = "amenities_id")
//     private Amenities amenities;
// }