--dropandcreate2.sql

SELECT pg_terminate_backend (pid) FROM pg_stat_activity WHERE datname = 'health_invoice';

DROP DATABASE health_invoice;

CREATE DATABASE health_invoice;
