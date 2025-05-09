CREATE TABLE IF NOT EXISTS service_cristaleria (
  service_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  CONSTRAINT fk_sc_service
    FOREIGN KEY (service_id)
    REFERENCES service(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_sc_product
    FOREIGN KEY (product_id)
    REFERENCES product(id)
    ON DELETE CASCADE,
  PRIMARY KEY (service_id, product_id)
);