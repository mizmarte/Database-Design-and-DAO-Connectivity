SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'projects_pair';

DROP DATABASE projects_pair;

CREATE DATABASE projects_pair;