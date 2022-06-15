const dbUtils = require('../utils');

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('students', (table) => {
    table.uuid('id').references('id').inTable('auth.users').onDelete('CASCADE').primary();

    table.string('full_name', 255).notNullable();
    table.date('birthday').notNullable();
    table.string('cin', 10).notNullable();
    table.string('cne', 20).notNullable();

    table.string('phone', 15).notNullable();

    table.double('bac_mark').notNullable();
    table.integer('bac_year').unsigned().notNullable();
    table.string('bac_specialty').notNullable();

    table.uuid('group_id').references('id').inTable('groups').onDelete('CASCADE').index();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.schema.createTable('teachers', (table) => {
    table.uuid('id').references('id').inTable('auth.users').onDelete('CASCADE').primary();

    table.string('full_name', 255).notNullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('teachers');
  await knex.schema.dropTable('students');
};
