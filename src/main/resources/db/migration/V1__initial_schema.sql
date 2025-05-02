-- V1__initial_schema.sql
CREATE TABLE productos (
  id            BIGSERIAL PRIMARY KEY,
  name          VARCHAR(255) NOT NULL,
  alcohol_type  VARCHAR(255),
  capacity      NUMERIC,
  stock         INT,
  product_type  VARCHAR(255) NOT NULL,
  CONSTRAINT productos_product_type_check
    CHECK (product_type IN (
      'Insumo_Alcoholico',
      'Insumo_No_Alcoholico',
      'Fruta',
      'Hielo',
      'Cristaleria',
      'Equipamiento',
      'Herramientas'
    ))
);