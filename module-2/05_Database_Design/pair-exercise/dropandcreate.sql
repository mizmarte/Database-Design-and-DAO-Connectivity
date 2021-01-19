dropandcreate.sql



SELECT pg_terminate_backend (pid) FROM pg_stat_activity WHERE datname = 'health_history';

DROP DATABASE health_history;

CREATE DATABASE health_history;
