/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('group_subjects', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.integer('progress').unsigned().defaultTo(0).notNullable();

    table.uuid('group_id').references('id').inTable('groups').onDelete('CASCADE').index();
    table.uuid('subject_id').references('id').inTable('subjects').onDelete('CASCADE').index();
    table.uuid('teacher_id').references('id').inTable('teachers').onDelete('CASCADE').index();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table group_subjects enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.group_subjects for select using (true);`);
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('group_subjects');
};
