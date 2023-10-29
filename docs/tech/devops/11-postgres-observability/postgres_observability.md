# Postgres observability

Queries

```sql
--
SELECT datname, usename, state, wait_event_type, wait_event, query
FROM pg_stat_activity
WHERE state = 'active' AND wait_event IS NOT NULL;

--
SELECT relname, seq_scan, seq_tup_read, idx_scan, n_tup_ins, n_tup_upd, n_tup_del, n_tup_hot_upd
FROM pg_stat_user_tables
WHERE relname = 'zone';

--
SELECT relname, relkind, pg_size_pretty (pg_relation_size ('foo')) FROM pg_class;

```

monitor

![pg_stat_monitor.jpg](_img%2Fpg_stat_monitor.jpg)
