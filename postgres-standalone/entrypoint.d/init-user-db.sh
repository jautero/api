#!/bin/bash
set -e
DB=worldcon75

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    CREATE ROLE $POSTGRES_USER WITH CREATEDB CREATEROLE PASSWORD '$POSTGRES_PASSWORD';

    CREATE DATABASE $DB;
    GRANT ALL PRIVILEGES ON DATABASE $DB TO $POSTGRES_USER;

    \connect $DB

    CREATE TYPE MembershipStatus AS ENUM ('NonMember','Supporter','KidInTow','Child','Youth','FirstWorldcon','Adult');

    CREATE TABLE IF NOT EXISTS People (
        id SERIAL PRIMARY KEY,
        member_number integer NOT NULL,
        first_name text NOT NULL,
        last_name text NOT NULL,
        badge_name text,
        email text,
        city text,
        state text,
        country text,
        controller_id integer,
        membership MembershipStatus NOT NULL,
        can_hugo_nominate bool NOT NULL DEFAULT false,
        can_hugo_vote bool NOT NULL DEFAULT false,
        can_site_select bool NOT NULL DEFAULT false
    );

    CREATE TABLE IF NOT EXISTS PaperPublications (
        id SERIAL PRIMARY KEY,
        people_id integer REFERENCES People(id),
        name text,
        address text,
        country text
    );

    CREATE TABLE IF NOT EXISTS Admins (
        id SERIAL PRIMARY KEY,
        people_id integer REFERENCES People NOT NULL,
        super_admin bool NOT NULL DEFAULT false,
        member_admin bool NOT NULL DEFAULT false,
        hugo_admin bool NOT NULL DEFAULT false
    );

    CREATE TABLE IF NOT EXISTS Transactions (
        id SERIAL PRIMARY KEY,
        target_people_id integer REFERENCES People NOT NULL,
        author_people_id integer REFERENCES People NOT NULL,
        "timestamp" timestamptz NOT NULL,
        author_source text NOT NULL,
        sum money,
        currency char(3),
        membership MembershipStatus,
        can_hugo_nominate bool,
        can_hugo_vote bool,
        can_site_select bool,
        action text NOT NULL,
        parameters jsonb NOT NULL,
        description text NOT NULL
    );

EOSQL