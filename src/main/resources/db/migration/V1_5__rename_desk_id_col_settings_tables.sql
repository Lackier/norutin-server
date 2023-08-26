ALTER TABLE "task_type"
    DROP IF EXISTS "desk_id";

ALTER TABLE "task_status"
    DROP IF EXISTS "desk_id";

ALTER TABLE "priority_type"
    DROP IF EXISTS "desk_id";


ALTER TABLE "task_type"
    ADD COLUMN IF NOT EXISTS desk_value_id INTEGER;

ALTER TABLE "task_status"
    ADD COLUMN IF NOT EXISTS desk_value_id INTEGER;

ALTER TABLE "priority_type"
    ADD COLUMN IF NOT EXISTS desk_value_id INTEGER;

ALTER TABLE task_type ALTER COLUMN desk_value_id SET NOT NULL;
ALTER TABLE task_status ALTER COLUMN desk_value_id SET NOT NULL;
ALTER TABLE priority_type ALTER COLUMN desk_value_id SET NOT NULL;


ALTER TABLE "task_type"
    ADD CONSTRAINT "task_type_fk_desk_value" FOREIGN KEY ("desk_value_id") REFERENCES "desk_value" ("id");

ALTER TABLE "task_status"
    ADD CONSTRAINT "task_status_fk_desk_value" FOREIGN KEY ("desk_value_id") REFERENCES "desk_value" ("id");

ALTER TABLE "priority_type"
    ADD CONSTRAINT "priority_type_fk_desk_value" FOREIGN KEY ("desk_value_id") REFERENCES "desk_value" ("id");