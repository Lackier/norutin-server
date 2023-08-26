ALTER TABLE "public"."users" RENAME COLUMN "phone_number" TO "phone";
ALTER TABLE "public"."users" RENAME COLUMN "name" TO "username";
ALTER TABLE "public"."users" DROP COLUMN "create_dt";
ALTER TABLE "public"."users" DROP COLUMN "uid";
ALTER TABLE users ADD password varchar(255) NOT NULL;


create table roles(
    id serial not null primary key,
    name varchar not null unique
);

insert into roles(id, name)
values (default, 'USER'),
       (default, 'ANONYMOUS'),
       (default, 'PREMIUM');

create table user_roles(
    id serial not null primary key,
    user_id integer not null references users(id),
    role_id integer not null references roles(id)
);