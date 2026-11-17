package com.shalini.billing.tenant;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    @Column(name = "billing_email", nullable = false, length = 320)
    private String billingEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TenantStatus status  = TenantStatus.ACTIVE;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "default_currency", nullable = false, length = 3)
    private String defaultCurrency = "USD";

    @CreationTimestamp
    @Column(name="created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Tenant() {
        // required by JPA
    }

    public Tenant(String name, String slug, String billingEmail, String defaultCurrency) {
        this.name = name;
        this.slug = slug;
        this.billingEmail = billingEmail;
        this.defaultCurrency = defaultCurrency;
    }

    public void suspend()  { this.status = TenantStatus.SUSPENDED; }
    public void activate() { this.status = TenantStatus.ACTIVE; }

    public UUID getId()               { return id; }
    public String getName()           { return name; }
    public String getSlug()           { return slug; }
    public String getBillingEmail()   { return billingEmail; }
    public TenantStatus getStatus()   { return status; }
    public String getDefaultCurrency(){ return defaultCurrency; }
    public Instant getCreatedAt()     { return createdAt; }
    public Instant getUpdatedAt()     { return updatedAt; }
}
