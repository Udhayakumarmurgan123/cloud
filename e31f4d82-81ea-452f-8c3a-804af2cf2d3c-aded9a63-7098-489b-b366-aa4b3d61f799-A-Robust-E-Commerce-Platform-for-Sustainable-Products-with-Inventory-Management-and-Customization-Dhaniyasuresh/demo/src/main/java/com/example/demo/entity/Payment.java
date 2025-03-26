package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private Double amount;

@Column(nullable = false)
private String paymentMethod;

@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
@JsonBackReference
private User user;

@Column(nullable = false)
private Long memberId;

public Long getId() {
    return id;
    }

    public void setId(Long id) {
        this.id = id;
        }

        public Double getAmount() {
            return amount;
            }

            public void setAmount(Double amount) {
            this.amount = amount;
            }

            public String getPaymentMethod() {
                return paymentMethod;
                }

                public void setPaymentMethod(String paymentMethod) {
                    this.paymentMethod = paymentMethod;
                }

                public User getUser() {
                    return user;
                    }

                    public void setUser(User user) {
                        this.user = user;
                    }

                    public Long getMemberId() {
                        return memberId;
                        }

                        public void setMemberId(Long memberId) {
                            this.memberId = memberId;
                            }
                            }