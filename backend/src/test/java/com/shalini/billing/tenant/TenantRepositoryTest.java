package com.shalini.billing.tenant;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
public class TenantRepositoryTest {

    @Autowired
    TenantRepository tenantRepository;

    @Test
    void saveTenantWithDefaultsAndFindsItBySlug(){
        Tenant saved = tenantRepository.saveAndFlush(new Tenant("Test Gym","test-gym","ownerEtestgym.com","USD"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getStatus()).isEqualTo(TenantStatus.ACTIVE);
        assertThat(tenantRepository.findBySlug("test-gym")).isPresent();
    }

    @Test
    void rejectsDuplicateSlug() {
        tenantRepository.saveAndFlush(new Tenant("First", "dup-slug", "a@a.com", "USD"));

        assertThatThrownBy(() ->
                tenantRepository.saveAndFlush(new Tenant("Second", "dup-slug", "b@b.com", "USD")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
