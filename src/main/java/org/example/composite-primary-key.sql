-- 1. Create the Parent Table (Course Sections)
-- This table uses a Composite Primary Key
CREATE TABLE course_sections (
                                 course_id VARCHAR(10),
                                 section_id INT,
                                 semester VARCHAR(20),
                                 room_number VARCHAR(10),
                                 PRIMARY KEY (course_id, section_id, semester)
);

-- 2. Create the Child Table (Student Enrollments)
-- This table uses a Composite Foreign Key to link to the parent
CREATE TABLE enrollments (
                             enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
                             student_name VARCHAR(100),
                             c_id VARCHAR(10),
                             s_id INT,
                             sem VARCHAR(20),

    -- Defining the Composite Foreign Key
    -- It MUST reference all parts of the parent's primary key
                             CONSTRAINT fk_course_section
                                 FOREIGN KEY (c_id, s_id, sem)
                                     REFERENCES course_sections(course_id, section_id, semester)
);

-- 3. Insert Sample Data
INSERT INTO course_sections VALUES ('CS101', 1, 'Fall_2026', 'Room_A');
INSERT INTO course_sections VALUES ('CS101', 2, 'Fall_2026', 'Room_B');
INSERT INTO course_sections VALUES ('MATH200', 1, 'Spring_2026', 'Room_C');

-- This works because the combination exists in the parent table
INSERT INTO enrollments (student_name, c_id, s_id, sem)
VALUES ('Vivek', 'CS101', 1, 'Fall_2026');

-- This will FAIL because 'CS101', Section 3 does not exist
-- INSERT INTO enrollments (student_name, c_id, s_id, sem)
-- VALUES ('Alice', 'CS101', 3, 'Fall_2026');



SELECT e.student_name, e.c_id, c.room_number
FROM enrollments e
         JOIN course_sections c ON e.c_id = c.course_id
    AND e.s_id = c.section_id
    AND e.sem = c.semester;