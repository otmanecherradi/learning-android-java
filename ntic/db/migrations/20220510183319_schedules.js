const dbUtils = require('../utils');

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('schedules', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.date('week_start').unique().notNullable();
    table.text('bucket_url').unique().notNullable();

    table.uuid('group_id').references('id').inTable('groups').onDelete('CASCADE').index();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table schedules enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.schedules for select using (true);`);
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('schedules');
};
