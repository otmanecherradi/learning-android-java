const teachers = [
  {
    id: '675e67b3-9d25-4bd7-9db2-8c3ee9629b94',
    full_name: 'Bouchra El Akel',
  },
];

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('teachers').del();
  const ts = await knex('teachers').insert(teachers).returning('*');
};
