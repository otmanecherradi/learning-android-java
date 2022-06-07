const dbUtils = require('../utils');

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('branches', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.string('name', 255).notNullable();
    table.enum('level', ['TS', 'T']).notNullable();

    table.text('description').nullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table branches enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.branches for select using (true);`);

  await knex.schema.createTable('subjects', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.string('name', 255).unique().notNullable();

    table.text('description').nullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table subjects enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.subjects for select using (true);`);

  await knex.schema.createTable('branch_subjects', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.integer('mass').unsigned().notNullable();

    table.uuid('branch_id').references('id').inTable('branches').onDelete('CASCADE').index();
    table.uuid('subject_id').references('id').inTable('subjects').onDelete('CASCADE').index();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table branch_subjects enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.branch_subjects for select using (true);`);

  await knex.schema.createTable('groups', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.string('name', 255).notNullable();

    table.enum('year', ['1', '2']).notNullable();

    table.uuid('branch_id').references('id').inTable('branches').onDelete('CASCADE').index();

    table.text('description').nullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table groups enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.groups for select using (true);`);
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('groups');

  await knex.schema.dropTable('branch_subjects');
  await knex.schema.dropTable('subjects');
  await knex.schema.dropTable('branches');
};

// curl -L -X POST 'https://btbhfssptcdnxmfvpash.functions.supabase.co/api/users/me' -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ0Ymhmc3NwdGNkbnhtZnZwYXNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NTEwNzI0OTgsImV4cCI6MTk2NjY0ODQ5OH0.JiXkWrQxGRIbLMik0-8gtzfCFULKuMNdeJHfcES4lHg' --data '{"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNjU0ODc1MDUxLCJzdWIiOiI4ZWQyMDU2Yi01NDAzLTRlYzItYjgzNS1lYjU3NDQ5Nzk2MmIiLCJlbWFpbCI6ImNoZXJyYWRpLm90bWFuZUBvZnBwdC1lZHUubWEiLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImZjbVRva2VuIjoiZnFnZlRxRmtSZHllcmF2dTd2WUZ3cTpBUEE5MWJHRDFLajgtRFZRU2doN2NyMF8xVWNUNUJKZlBpd3JkUUZ0eE5GZjdQRndHdkh5N3BTQUYyU05ibXpuQ25Vb09yeFVWV3RuTWVadlZBMHRkMjFvN191bDNfYkxNODdpbjlzejFfdEs5c2ptbDktaWJvTldfdjFJcVdWWV9fWV9ZTHp2Zk1kUSJ9LCJyb2xlIjoiYXV0aGVudGljYXRlZCJ9.Wxd6Zh3xHaXHcGrWN2-Y7JynLfxYr32fG7mbxy27ob8"}'
