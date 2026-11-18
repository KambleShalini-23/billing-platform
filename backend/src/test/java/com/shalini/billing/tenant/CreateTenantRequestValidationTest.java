package com.shalini.billing.tenant;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTenantRequestValidationTest {


    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private Set<String> invalidFields(CreateTenantRequest request) {
        return validator.validate(request).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    @Test
    void acceptsAValidRequest() {
        var request = new CreateTenantRequest("Acme Gym", "acme-gym", "client1@acmegym.com", "USD");
        assertThat(invalidFields(request)).isEmpty();
    }

    @Test
    void slugAndCurrencyAreOptional() {
        var request = new CreateTenantRequest("Acme Gym", null, "client1@acmegym.com", null);
        assertThat(invalidFields(request)).isEmpty();
    }

    @Test
    void rejectsBlankNameAndBadEmail() {
        var request = new CreateTenantRequest("  ", null, "not-an-email", null);
        assertThat(invalidFields(request)).containsExactlyInAnyOrder("name", "billingEmail");
    }

    @Test
    void rejectsBadSlugs() {
        for (String slug : new String[] {"Acme Gym", "-acme", "acme-", "ab"}) {
            var request = new CreateTenantRequest("Acme Gym", slug, "client1@acmegym.com", "USD");
            assertThat(invalidFields(request)).as("slug '%s'", slug).containsExactly("slug");
        }
    }

    @Test
    void rejectsLowercaseCurrency() {
        var request = new CreateTenantRequest("Acme Gym", null, "client1@acmegym.com", "usd");
        assertThat(invalidFields(request)).containsExactly("defaultCurrency");
    }
}
