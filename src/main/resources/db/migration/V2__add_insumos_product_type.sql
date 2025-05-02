-- V2__add_insumos_product_type.sql

ALTER TABLE productos
  DROP CONSTRAINT IF EXISTS productos_product_type_check;

ALTER TABLE productos
  ADD CONSTRAINT productos_product_type_check
  CHECK (product_type IN (
     'Insumo_Alcoholico',
        'Insumo_No_Alcoholico',
        'Fruta',
        'Hielo',
        'Cristaleria',
        'Equipamiento',
        'Herramientas',
        'Insumos';
  ));


