DO $$
    BEGIN
        IF EXISTS(SELECT *
                  FROM information_schema.columns
                  WHERE table_name='users' and column_name='oid')
        THEN
            ALTER TABLE "public"."users" RENAME COLUMN "oid" TO "uid";
        END IF;
    END $$;