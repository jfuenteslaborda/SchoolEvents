-- Base de Datos SchoolEvents
-- Creado por Jose Fuentes Laborda 

-- TABLA 1 app_user    
CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo TEXT,
    date DATE,
    is_admin INTEGER DEFAULT 0
);

-- TABLA 2 Event
CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) DEFAULT 0.00,
    capacity INTEGER,
    date TIMESTAMP WITHOUT TIME ZONE,
    src VARCHAR(250),
    need_payment BOOLEAN DEFAULT FALSE
);

-- TABLA 3 Message
CREATE TABLE message (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    send_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,
    
    CONSTRAINT fk_message_user
        FOREIGN KEY (user_id)
        REFERENCES app_user(id) 
        ON DELETE CASCADE
);

-- TABLA 4 Image
CREATE TABLE image (
    id SERIAL PRIMARY KEY,
    src VARCHAR(255) NOT NULL,
    description TEXT,
    event_id INTEGER NOT NULL,
    
    CONSTRAINT fk_images_event
        FOREIGN KEY (event_id)
        REFERENCES event(id)
        ON DELETE CASCADE
);

-- TABLA 5 Comment
CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,          
    event_id INTEGER NOT NULL,          
    
    CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
        REFERENCES app_user(id) 
        ON DELETE CASCADE,
        
    CONSTRAINT fk_comment_event
        FOREIGN KEY (event_id)
        REFERENCES event(id)
        ON DELETE CASCADE
);

-- TABLA 6 Sign (CORREGIDA)
CREATE TABLE sign (
	id SERIAL PRIMARY KEY, 
    user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    UNIQUE (user_id, event_id), 
    
    CONSTRAINT fk_signs_user
        FOREIGN KEY (user_id)
        REFERENCES app_user(id) 
        ON DELETE CASCADE,
        
    CONSTRAINT fk_signs_event
        FOREIGN KEY (event_id)
        REFERENCES event(id) 
        ON DELETE CASCADE
);


SELECT
    e.id AS event_id,
    e.title,
    e.description,
    e.price,
    e.capacity,
    e.date,
    COUNT(s.user_id) AS assist_num
    FROM
    event e
JOIN
    sign s ON e.id = s.event_id
GROUP BY
    e.id, e.title, e.description, e.price, e.capacity, e.date
ORDER BY
    assist_num DESC
LIMIT 5;






SELECT
    au.id AS user_id,
    au.full_name,
    COUNT(s.id) AS signs_total
FROM
    app_user au
JOIN
    sign s ON au.id = s.user_id
GROUP BY
    au.id, au.full_name
ORDER BY
    signs_total DESC
LIMIT 1;








