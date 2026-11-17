CREATE TABLE tenants (
                         id               UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
                         name             VARCHAR(200) NOT NULL,
                         slug             VARCHAR(50)  NOT NULL,
                         billing_email    VARCHAR(320) NOT NULL,
                         status           VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
                         default_currency CHAR(3)      NOT NULL DEFAULT 'USD',
                         created_at       TIMESTAMPTZ  NOT NULL DEFAULT now(),
                         updated_at       TIMESTAMPTZ  NOT NULL DEFAULT now(),

                         CONSTRAINT uk_tenants_slug     UNIQUE (slug),
                         CONSTRAINT ck_tenants_status   CHECK (status IN ('ACTIVE', 'SUSPENDED')),
                         CONSTRAINT ck_tenants_currency CHECK (default_currency ~ '^[A-Z]{3}$')
    );