CREATE TABLE student
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    course VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_student_email UNIQUE(email),
    CONSTRAINT uk_student_phone UNIQUE(phone)
);