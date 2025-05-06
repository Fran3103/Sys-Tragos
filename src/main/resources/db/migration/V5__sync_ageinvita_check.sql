-- 1) Normaliza filas con valores “huérfanos”
UPDATE events
SET age_invita = 'Jovenes_18_a_25'
WHERE age_invita NOT IN (
  'Jovenes_18_a_25',
  'Adultos_25_mas',
  'Mayor_a_35'
);

-- 2) Suelta el constraint antiguo
ALTER TABLE events
  DROP CONSTRAINT IF EXISTS events_age_invita_check;

-- 3) Añade el nuevo constraint con tus tres valores exactos
ALTER TABLE events
  ADD CONSTRAINT events_age_invita_check
    CHECK (age_invita IN (
      'Jovenes_18_a_25',
      'Adultos_25_mas',
      'Mayor_a_35'
    ));