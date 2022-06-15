const groups = [
  {
    name: '01',
    year: '1',
    description: '',
  },
  {
    name: '02',
    year: '1',
    description: '',
  },
  {
    name: '03',
    year: '1',
    description: '',
  },
  {
    name: '04',
    year: '1',
    description: '',
  },
  {
    name: '01',
    year: '2',
    description: '',
  },
  {
    name: '02',
    year: '2',
    description: '',
  },
  {
    name: '03',
    year: '2',
    description: '',
  },
  {
    name: '04',
    year: '2',
    description: '',
  },
];

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('groups').del();

  const tdi = await knex('branches').where('name', '=', 'TDI').select('*').first();
  const gs = await knex('groups')
    .insert(
      groups.map((g) => ({
        ...g,
        branch_id: tdi.id,
      }))
    )
    .returning('*');
};
