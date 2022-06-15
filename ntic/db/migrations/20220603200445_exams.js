/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('exams', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.enum('type', ['cc', 'efm']).notNullable();
    table.date('date').notNullable();

    table.uuid('group_reference_id').references('id').inTable('group_subjects').onDelete('CASCADE').index();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table exams enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.exams for select using (true);`);
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('exams');
};
