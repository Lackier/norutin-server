--basic entities
CREATE TABLE "task"
(
    "id"           serial       NOT NULL,
    "name"         varchar(255) NOT NULL,
    "type_id"      integer      NOT NULL,
    "priority_id"  integer      NOT NULL,
    "create_dt"    TIMESTAMP    NOT NULL,
    "status_id"    integer      NOT NULL,
    "commit_dt"    TIMESTAMP,
    "done_dt"      TIMESTAMP,
    "done_on_time" BOOLEAN      NOT NULL,
    "desk_id"      integer      NOT NULL,
    "calendar_id"  integer,
    "description"  varchar(255),
    CONSTRAINT "task_pk" PRIMARY KEY ("id")
);

CREATE TABLE "event"
(
    "id"             serial       NOT NULL,
    "name"           varchar(255) NOT NULL,
    "create_dt"      TIMESTAMP    NOT NULL,
    "periodicity_id" integer      NOT NULL,
    "type_id"        integer      NOT NULL,
    "calendar_id"    integer      NOT NULL,
    "commit_dt"      TIMESTAMP    NOT NULL,
    "description"    varchar(255),
    CONSTRAINT "event_pk" PRIMARY KEY ("id")
);

CREATE TABLE "calendar"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "user_id"   integer      NOT NULL,
    CONSTRAINT "calendar_pk" PRIMARY KEY ("id")
);

CREATE TABLE "desk"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "user_id"   integer      NOT NULL,
    CONSTRAINT "desk_pk" PRIMARY KEY ("id")
);

CREATE TABLE "user"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "oid"       varchar(255) NOT NULL,
    CONSTRAINT "user_pk" PRIMARY KEY ("id")
);


--user dictionary entities
CREATE TABLE "task_type"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "desk_id"   integer      NOT NULL,
    CONSTRAINT "task_type_pk" PRIMARY KEY ("id")
);

CREATE TABLE "periodicity_type"
(
    "id"          serial       NOT NULL,
    "name"        varchar(255) NOT NULL,
    "create_dt"   TIMESTAMP    NOT NULL,
    "calendar_id" integer      NOT NULL,
    CONSTRAINT "periodicity_type_pk" PRIMARY KEY ("id")
);

CREATE TABLE "event_type"
(
    "id"          serial       NOT NULL,
    "name"        varchar(255) NOT NULL,
    "create_dt"   TIMESTAMP    NOT NULL,
    "calendar_id" integer      NOT NULL,
    CONSTRAINT "event_type_pk" PRIMARY KEY ("id")
);

CREATE TABLE "task_status"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "desk_id"   integer      NOT NULL,
    CONSTRAINT "task_status_pk" PRIMARY KEY ("id")
);

CREATE TABLE "priority_type"
(
    "id"        serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    "create_dt" TIMESTAMP    NOT NULL,
    "desk_id"   integer      NOT NULL,
    CONSTRAINT "priority_type_pk" PRIMARY KEY ("id")
);


--default system dictionary entities
CREATE TABLE "task_type_def"
(
    "id"   serial       NOT NULL,
    "code" varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT "task_type_def_pk" PRIMARY KEY ("id")
);

CREATE TABLE "priority_type_def"
(
    "id"   serial       NOT NULL,
    "code" varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT "priority_type_def_pk" PRIMARY KEY ("id")
);

CREATE TABLE "periodicity_type_def"
(
    "id"   serial       NOT NULL,
    "code" varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT "periodicy_type_def_pk" PRIMARY KEY ("id")
);

CREATE TABLE "event_type_def"
(
    "id"   serial       NOT NULL,
    "code" varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT "event_type_def_pk" PRIMARY KEY ("id")
);

CREATE TABLE "task_status_def"
(
    "id"   serial       NOT NULL,
    "code" varchar(255) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT "task_status_def_pk" PRIMARY KEY ("id")
);


--constraints
ALTER TABLE "task"
    ADD CONSTRAINT "task_fk_type" FOREIGN KEY ("type_id") REFERENCES "task_type" ("id");
ALTER TABLE "task"
    ADD CONSTRAINT "task_fk_priority" FOREIGN KEY ("priority_id") REFERENCES "priority_type" ("id");
ALTER TABLE "task"
    ADD CONSTRAINT "task_fk_status" FOREIGN KEY ("status_id") REFERENCES "task_status" ("id");
ALTER TABLE "task"
    ADD CONSTRAINT "task_fk_desk" FOREIGN KEY ("desk_id") REFERENCES "desk" ("id");
ALTER TABLE "task"
    ADD CONSTRAINT "task_fk_calendar" FOREIGN KEY ("calendar_id") REFERENCES "calendar" ("id");

ALTER TABLE "event"
    ADD CONSTRAINT "event_fk_periodicity" FOREIGN KEY ("periodicity_id") REFERENCES "periodicity_type" ("id");
ALTER TABLE "event"
    ADD CONSTRAINT "event_fk_type" FOREIGN KEY ("type_id") REFERENCES "event_type" ("id");
ALTER TABLE "event"
    ADD CONSTRAINT "event_fk_calendar" FOREIGN KEY ("calendar_id") REFERENCES "calendar" ("id");

ALTER TABLE "calendar"
    ADD CONSTRAINT "calendar_fk_user" FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "desk"
    ADD CONSTRAINT "desk_fk_user" FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "task_type"
    ADD CONSTRAINT "task_type_type_fk_desk" FOREIGN KEY ("desk_id") REFERENCES "desk" ("id");
ALTER TABLE "priority_type"
    ADD CONSTRAINT "priority_type_fk_desk" FOREIGN KEY ("desk_id") REFERENCES "desk" ("id");
ALTER TABLE "task_status"
    ADD CONSTRAINT "task_status_fk_desk" FOREIGN KEY ("desk_id") REFERENCES "desk" ("id");
ALTER TABLE "periodicity_type"
    ADD CONSTRAINT "periodicity_type_fk_calendar" FOREIGN KEY ("calendar_id") REFERENCES "calendar" ("id");
ALTER TABLE "event_type"
    ADD CONSTRAINT "event_type_fk_calendar" FOREIGN KEY ("calendar_id") REFERENCES "calendar" ("id");


--values for static dictionaries
INSERT INTO task_type_def (code, name)
VALUES ('private', 'private'),
       ('work', 'work'),
       ('family', 'family'),
       ('study', 'study');

INSERT INTO priority_type_def (code, name)
VALUES ('regular', 'regular'),
       ('important', 'important'),
       ('minor', 'minor');

INSERT INTO periodicity_type_def (code, name)
VALUES ('once', 'once'),
       ('daily', 'daily'),
       ('weekly', 'weekly'),
       ('monthly', 'monthly'),
       ('yearly', 'yearly');

INSERT INTO event_type_def (code, name)
VALUES ('once', 'once'),
       ('daily', 'daily'),
       ('weekly', 'weekly');

INSERT INTO task_status_def (code, name)
VALUES ('planned', 'planned'),
       ('in process', 'in process'),
       ('done', 'done'),
       ('failed', 'failed');