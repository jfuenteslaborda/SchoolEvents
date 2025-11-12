
-- Base de Datos SchoolEvents
-- Creado por Jose Fuentes Laborda 

-- TABLA 1 Users
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo VARCHAR(255),
    date DATE,
    is_admin BOOLEAN DEFAULT FALSE
);

-- TABLA 2 Events
CREATE TABLE Events (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) DEFAULT 0.00,
    capacity INTEGER,
    date TIMESTAMP WITHOUT TIME ZONE,
    need_payment BOOLEAN DEFAULT FALSE,
    creator_id INTEGER NOT NULL,
    
    CONSTRAINT fk_creator
        FOREIGN KEY (creator_id)
        REFERENCES Users(id)
        ON DELETE CASCADE
);

-- TABLA 3 Messages
CREATE TABLE Messages (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    send_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,
    
    CONSTRAINT fk_message_user
        FOREIGN KEY (user_id)
        REFERENCES Users(id)
        ON DELETE CASCADE
);

-- TABLA 4 Images
CREATE TABLE Images (
    id SERIAL PRIMARY KEY,
    src VARCHAR(255) NOT NULL,
    description TEXT,
    event_id INTEGER NOT NULL,
    
    CONSTRAINT fk_images_event
        FOREIGN KEY (event_id)
        REFERENCES Events(id)
        ON DELETE CASCADE
);

-- TABLA 5 Comments
CREATE TABLE Comments (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,          
    event_id INTEGER NOT NULL,          
    
    CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
        REFERENCES Users(id)
        ON DELETE CASCADE,
        
    CONSTRAINT fk_comment_event
        FOREIGN KEY (event_id)
        REFERENCES Events(id)
        ON DELETE CASCADE
);

-- Tablas de Muchos a Muchos que crean tablan, solo 1

-- TABLA 6 Signs
CREATE TABLE Signs (
    user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (user_id, event_id),
    
    CONSTRAINT fk_signs_user
        FOREIGN KEY (user_id)
        REFERENCES Users(id)
        ON DELETE CASCADE,
        
    CONSTRAINT fk_signs_event
        FOREIGN KEY (event_id)
        REFERENCES Events(id)
        ON DELETE CASCADE
);