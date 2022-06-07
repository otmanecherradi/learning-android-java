/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.alterTable('students', (table) => {
    table.string('full_name', 255).nullable().alter();
    table.date('birthday').nullable().alter();
    table.string('cin', 10).nullable().alter();
    table.string('cne', 20).nullable().alter();

    table.string('phone', 15).nullable().alter();

    table.double('bac_mark').nullable().alter();
    table.integer('bac_year').unsigned().nullable().alter();
    table.string('bac_specialty').nullable().alter();
  });
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.alterTable('students', (table) => {
    table.string('full_name', 255).notNullable().alter();
    table.date('birthday').notNullable().alter();
    table.string('cin', 10).notNullable().alter();
    table.string('cne', 20).notNullable().alter();

    table.string('phone', 15).notNullable().alter();

    table.double('bac_mark').notNullable().alter();
    table.integer('bac_year').unsigned().notNullable().alter();
    table.string('bac_specialty').notNullable().alter();
  });
};
