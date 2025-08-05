package com.tuespotsolutions.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhonePePaymentResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Long amount;
    private String transactionId;

    @Column(name = "paymentUrl", columnDefinition = "TEXT")
    private String paymentUrl;
    @Column(name = "requestString", columnDefinition = "TEXT")
    private String requestString;
    @Lob
    private String xVerify;

    @Column(name = "responseString", columnDefinition = "TEXT")
    private String responseString;

    private String code;

    private String merchantTransactionId;
    private String state;


    private String createdAt;

    private String updatedAt;


    private String utr;
    private String cardNetwork;
    private String accountType;
    private String type;

    private Integer feesContextAmount;
    private String message;
    private String merchantId;
    private String responseCode;
    private Boolean success;
    private String providerReferenceId;
    private String merchantOrderId;
    private String email;
    private String mobileNumber;
    private String name;
    private Long userId;
    private Long planeId;


}
