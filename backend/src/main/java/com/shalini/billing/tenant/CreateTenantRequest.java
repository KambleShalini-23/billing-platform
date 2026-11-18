package com.shalini.billing.tenant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateTenantRequest(
        @NotBlank
        @Size(max = 200)
        String name,

        @Pattern(regexp = "^[a-z0-9](?:[a-z0-9-]{1,48}[a-z0-9])$",
                message = "must be 3-50 lowercase letters, digits or hyphens, and cannot start or end with a hyphen")
        String slug,

        @NotBlank
        @Email
        @Size(max = 320)
        String billingEmail,

        @Pattern(regexp = "^[A-Z]{3}$",
                message = "must be a 3-letter ISO currency code, e.g. USD")
        String defaultCurrency
) {
}
