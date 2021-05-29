CREATE TABLE "desk_value"
(
    "id"        serial       NOT NULL,
    "desk_id"   integer      NOT NULL,
    CONSTRAINT "desk_value_pk" PRIMARY KEY ("id")
);

ALTER TABLE "desk_value"
    ADD CONSTRAINT "desk_value_fk_desk" FOREIGN KEY ("desk_id") REFERENCES "desk" ("id");