ALTER TABLE responses
DROP COLUMN active;
ALTER TABLE users
DROP COLUMN active;
ALTER TABLE topics
DROP COLUMN active;

alter table users add active tinyint;
update users set active=1;
alter table topics add active tinyint;
update topics set active=1;
alter table responses add active tinyint;
update responses set active=1;