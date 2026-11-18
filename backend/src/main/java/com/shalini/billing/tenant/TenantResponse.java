package com.shalini.billing.tenant;

import java.time.Instant;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        String name,
        String slug,
        String billingEmail,
        String defaultCurrency,
        TenantStatus status,
        Instant createdAt
) {
    public static TenantResponse from(Tenant tenant) {
        return new TenantResponse(
                tenant.getId(),
                tenant.getName(),
                tenant.getSlug(),
                tenant.getBillingEmail(),
                tenant.getDefaultCurrency(),
                tenant.getStatus(),
                tenant.getCreatedAt()
        );
    }
}