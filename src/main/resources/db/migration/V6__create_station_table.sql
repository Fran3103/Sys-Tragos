-- Crear tabla STATION
CREATE TABLE IF NOT EXISTS station (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    event_id BIGINT,
    CONSTRAINT fk_station_event FOREIGN KEY (event_id) REFERENCES events(id)
);

-- Crear tabla intermedia STATION_EQUIPMENTS (ManyToMany con PRODUCT)
CREATE TABLE IF NOT EXISTS station_equipments (
    station_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (station_id, product_id),
    CONSTRAINT fk_station FOREIGN KEY (station_id) REFERENCES station(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES productos(id)
);

-- Verificar si la columna 'event_id' ya existe en BARRA antes de agregarla
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'barra' AND column_name = 'event_id'
    ) THEN
        ALTER TABLE barra ADD COLUMN event_id BIGINT;
    END IF;
END
$$;

-- Intentar agregar la clave foránea si no existe
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.table_constraints
        WHERE constraint_name = 'fk_barra_event'
    ) THEN
        ALTER TABLE barra ADD CONSTRAINT fk_barra_event FOREIGN KEY (event_id) REFERENCES events(id);
    END IF;
END
$$;
