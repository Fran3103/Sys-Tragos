-- Agregar columnas nuevas si no existen
ALTER TABLE productos ADD COLUMN IF NOT EXISTS incidence DOUBLE PRECISION;
ALTER TABLE productos ADD COLUMN IF NOT EXISTS capacity_per_box INTEGER;