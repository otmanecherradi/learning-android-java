/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = async function (knex) {
  await knex.schema.createTable('schools', (table) => {
    table.uuid('id').defaultTo(knex.raw('(uuid_generate_v4())')).primary();

    table.string('name', 255).unique().notNullable();
    table.decimal('lat', 20, 10).notNullable();
    table.decimal('lng', 20, 10).notNullable();

    table.text('url').nullable();

    table.text('description').nullable();

    table.timestamps(true, true);
    table.timestamp('deleted_at').nullable().defaultTo(null);
  });

  await knex.raw('alter table schools enable row level security;');
  await knex.raw(`create policy "Read Only to all users" on public.schools for select using (true);`);
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = async function (knex) {
  await knex.schema.dropTable('schools');
};
