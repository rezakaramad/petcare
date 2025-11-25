-- Pet Care


CREATE TABLE IF NOT EXISTS profiles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS foods (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS purchases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    purchase_date DATE NOT NULL,
    quantity INT NOT NULL,            -- e.g., 1 bag, 3 cans
    price DECIMAL(10,2),              -- Optional: price you paid
    store VARCHAR(255),               -- Optional: where you bought it
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS nutrition (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_id BIGINT NOT NULL UNIQUE,
    protein DECIMAL(5,2),
    fat DECIMAL(5,2),
    fiber DECIMAL(5,2),
    moisture DECIMAL(5,2),
    ash DECIMAL(5,2),
    calories INT,
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS heartbeat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,       -- foreign key to pets table
    bpm INT NOT NULL,                 -- heart rate in beats per minute
    measured_at TIMESTAMP NOT NULL,   -- when measurement was taken
    source VARCHAR(50),               -- manual, wearable, vet, etc.
    activity VARCHAR(50),             -- resting, sleeping, active, stressed
    notes VARCHAR(500),               -- optional notes
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vaccinations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    vaccine_name VARCHAR(255) NOT NULL,   -- e.g., "Rabies", "FVRCP", "FeLV"
    manufacturer VARCHAR(255),            -- optional: Pfizer, Nobivac, etc.
    batch_number VARCHAR(100),            -- vaccine batch/lot number (useful)
    date_administered DATE NOT NULL,      -- when the shot was given
    due_date DATE,                        -- next booster date (important)
    vet_name VARCHAR(255),                -- optional: which clinic/vet
    notes VARCHAR(500),                   -- optional: side effects, special notes
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS weights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    weight DECIMAL(5,2) NOT NULL,     -- in kg
    measured_at DATE NOT NULL,
    notes VARCHAR(500),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS feeding_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    meals_per_day INT NOT NULL,         -- e.g., 3 meals
    portions_per_meal INT NOT NULL,     -- e.g., 1 portion per meal
    grams_per_portion INT NOT NULL,     -- e.g., 50g
    start_date DATE NOT NULL,
    end_date DATE,
    notes VARCHAR(255),
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vet_visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    visit_date DATE NOT NULL,
    reason VARCHAR(255),            -- e.g., "Check-up", "Vomiting"
    diagnosis VARCHAR(500),
    treatment VARCHAR(500),
    cost DECIMAL(10,2),
    vet_name VARCHAR(255),
    notes VARCHAR(1000),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS medications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    medication_name VARCHAR(255) NOT NULL,
    dose VARCHAR(50),                 -- e.g., "1 pill", "2 ml"
    frequency VARCHAR(50),            -- e.g., "daily", "weekly"
    start_date DATE NOT NULL,
    end_date DATE,
    notes VARCHAR(500),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS health_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    logged_at TIMESTAMP NOT NULL,
    symptom VARCHAR(255),
    severity VARCHAR(50),        -- mild, moderate, severe
    notes VARCHAR(1000),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS activities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    activity_type VARCHAR(100),     -- sleeping, playing, running
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    notes VARCHAR(500),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
) ENGINE=InnoDB;
