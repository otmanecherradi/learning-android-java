const dbUtils = require('../utils');

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('demands', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.uuid('student_id').references('id').inTable('students').onDelete('CASCADE').index();

    table.enum('type', ['marks', 'inscription']).notNullable();
    table.enum('state', ['todo', 'inProgress', 'done']).notNullable();

    table.date('for_date').nullable();

    table.text('note').nullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('demands');
};
