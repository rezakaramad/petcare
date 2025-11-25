--  Pet Care - Mock Data

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE activities;
TRUNCATE TABLE health_logs;
TRUNCATE TABLE medications;
TRUNCATE TABLE vet_visits;
TRUNCATE TABLE feeding_plans;
TRUNCATE TABLE weights;
TRUNCATE TABLE vaccinations;
TRUNCATE TABLE heartbeat;
TRUNCATE TABLE purchases;
TRUNCATE TABLE nutrition;
TRUNCATE TABLE foods;
TRUNCATE TABLE profiles;

SET FOREIGN_KEY_CHECKS = 1;

-- 1) PROFILES
INSERT INTO profiles (id, name) VALUES
  (1, 'Maviz');


-- 2) FOODS
INSERT INTO foods (name, brand, category) VALUES
  ('Chicken Soup', 'Royal Canin', 'wet'),
  ('Dry Kibble', 'Acana', 'dry'),
  ('Tuna Treats', 'Cosma', 'treat');


-- 3) PURCHASES  
-- Note: Maviz = profile_id = 1  
--       Foods inserted in order → 1 = Soup, 2 = Kibble, 3 = Treats
INSERT INTO purchases (profile_id, food_id, purchase_date, quantity, price, store) VALUES
  (1, 1, '2025-01-05', 12, 19.99, 'PetWorld Malmö'),
  (1, 2, '2025-01-10', 1, 34.50, 'PetWorld Malmö'),
  (1, 3, '2025-01-12', 5, 7.99, 'Zooplus');


-- 4) NUTRITION (Guaranteed Analysis)
INSERT INTO nutrition (food_id, protein, fat, fiber, moisture, ash, calories) VALUES
  (1, 8.0, 3.5, 0.5, 82.0, 2.0, 90),   -- Chicken Soup
  (2, 35.0, 15.0, 4.0, 10.0, 8.0, 380), -- Dry Kibble
  (3, 45.0, 20.0, 1.0, 18.0, 6.0, 420); -- Tuna Treats


-- 5) HEARTBEAT LOGS
INSERT INTO heartbeat (profile_id, bpm, measured_at, source, activity, notes) VALUES
  (1, 140, '2025-01-15 08:00:00', 'manual', 'resting', 'Normal morning reading'),
  (1, 160, '2025-01-15 18:00:00', 'manual', 'active', 'Playing with toys'),
  (1, 150, '2025-01-16 09:00:00', 'manual', 'resting', NULL);


-- 6) VACCINATIONS
INSERT INTO vaccinations (
    profile_id, vaccine_name, manufacturer, batch_number,
    date_administered, due_date, vet_name, notes
) VALUES
  (1, 'Rabies', 'Nobivac', 'RBX-2024-11A', '2024-06-01', '2025-06-01', 'Dr. Sara', 'Annual booster'),
  (1, 'FVRCP', 'Purevax', 'FV-2024-88C', '2024-06-01', '2025-06-01', 'Dr. Sara', 'No reactions');


-- 7) WEIGHTS
INSERT INTO weights (profile_id, weight, measured_at, notes) VALUES
  (1, 4.80, '2024-12-01', 'Healthy weight'),
  (1, 4.95, '2025-01-01', 'Slight increase'),
  (1, 5.00, '2025-02-01', 'Stable and healthy');


-- 8) FEEDING PLANS
-- Maviz feeding schedule:
-- - Chicken Soup: 3× daily, 1 portion, 50g
-- - Dry Kibble:   4× daily, 2 portions, 10g
INSERT INTO feeding_plans (
    profile_id, food_id, meals_per_day, portions_per_meal, 
    grams_per_portion, start_date, notes
) VALUES
  (1, 1, 3, 1, 50, '2025-01-01', 'Soup feeding schedule'),
  (1, 2, 4, 2, 10, '2025-01-01', 'Dry food routine');


-- 9) VET VISITS
INSERT INTO vet_visits (
    profile_id, visit_date, reason, diagnosis, treatment, cost,
    vet_name, notes
) VALUES
  (1, '2024-12-10', 'Check-up', 'Healthy', NULL, 45.00,
      'Dr. Sara', 'Vaccines updated'),
  (1, '2025-01-20', 'Sneezing', 'Mild cold', 'Antibiotics', 75.00,
      'Dr. Lucas', 'Recovering well');


-- 10) MEDICATIONS
INSERT INTO medications (
    profile_id, medication_name, dose, frequency, start_date, end_date, notes
) VALUES
  (1, 'Amoxicillin', '1ml', 'daily', '2025-01-20', '2025-01-25',
      'For cold symptoms');


-- 11) HEALTH LOGS
INSERT INTO health_logs (
    profile_id, logged_at, symptom, severity, notes
) VALUES
  (1, '2025-01-20 08:00:00', 'Sneezing', 'mild', 'Started yesterday'),
  (1, '2025-01-21 08:30:00', 'Sneezing', 'mild', 'Improving with meds');


-- 12) ACTIVITIES
INSERT INTO activities (
    profile_id, activity_type, start_time, end_time, notes
) VALUES
  (1, 'sleeping', '2025-01-15 10:00:00', '2025-01-15 12:30:00', NULL),
  (1, 'playing', '2025-01-15 17:00:00', '2025-01-15 17:45:00', 'High energy'),
  (1, 'running', '2025-01-16 19:00:00', '2025-01-16 19:10:00', 'Chasing laser toy');
