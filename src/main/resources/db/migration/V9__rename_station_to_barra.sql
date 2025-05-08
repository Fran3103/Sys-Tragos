-- 1️⃣ Eliminar la vieja tabla 'barra' si existe
DROP TABLE IF EXISTS barra CASCADE;

-- 2️⃣ Renombrar la tabla 'station' a 'barra'
ALTER TABLE station RENAME TO barra;

-- 3️⃣ Renombrar tabla intermedia de equipos
ALTER TABLE station_equipments RENAME TO barra_item;

-- 4️⃣ Renombrar columna 'station_id' a 'barra_id'
ALTER TABLE barra_item RENAME COLUMN station_id TO barra_id;