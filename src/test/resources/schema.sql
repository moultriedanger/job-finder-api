-- Drop existing tables if present
DROP TABLE IF EXISTS job;

DROP TABLE IF EXISTS company;

-- Company table
CREATE TABLE company (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  company_name VARCHAR(255),
  company_description TEXT,
  company_website VARCHAR(255),
  country_located VARCHAR(255)
);

-- Job table
CREATE TABLE jobs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  job_title VARCHAR(255) NOT NULL,
  job_description TEXT,
  seniority_level VARCHAR(255),
  max_salary DECIMAL(10, 2),
  location VARCHAR(255),
  posting_url VARCHAR(512),
  company_id BIGINT NOT NULL,
  CONSTRAINT fk_job_company FOREIGN KEY (company_id) REFERENCES company (id) ON DELETE CASCADE
);