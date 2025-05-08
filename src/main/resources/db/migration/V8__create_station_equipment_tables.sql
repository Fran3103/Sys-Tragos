-- Crear tabla STATION si no existe
CREATE TABLE IF NOT EXISTS station (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    event_id BIGINT,
    CONSTRAINT fk_station_event FOREIGN KEY (event_id) REFERENCES events(id)
);

-- Crear tabla STATION_EQUIPMENT como entidad intermedia con cantidad
CREATE TABLE IF NOT EXISTS station_equipment (
    id SERIAL PRIMARY KEY,
    station_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_stationequipment_station FOREIGN KEY (station_id) REFERENCES station(id) ON DELETE CASCADE,
    CONSTRAINT fk_stationequipment_product FOREIGN KEY (product_id) REFERENCES productos(id)
);