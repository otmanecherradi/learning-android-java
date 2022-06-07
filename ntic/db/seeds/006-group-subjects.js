/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('group_subjects').del();

  const tdi201 = await knex('groups').where('name', '=', '01').andWhere('year', '=', '2').select('*').first();
  const tdi203 = await knex('groups').where('name', '=', '03').andWhere('year', '=', '2').select('*').first();

  const M201 = await knex('subjects').where('name', '=', 'M201').select('*').first();
  const M202 = await knex('subjects').where('name', '=', 'M202').select('*').first();

  const M205 = await knex('subjects').where('name', '=', 'M205').select('*').first();
  const M206 = await knex('subjects').where('name', '=', 'M206').select('*').first();
  const M207 = await knex('subjects').where('name', '=', 'M207').select('*').first();

  const bouchra = await knex('teachers').where('id', '=', '675e67b3-9d25-4bd7-9db2-8c3ee9629b94').select('*').first();

  await knex('group_subjects').insert({
    progress: 100,
    subject_id: M201.id,
    group_id: tdi201.id,
    teacher_id: bouchra.id,
  });

  await knex('group_subjects').insert({
    progress: 100,
    subject_id: M202.id,
    group_id: tdi201.id,
    teacher_id: bouchra.id,
  });

  await knex('group_subjects').insert({
    progress: 100,
    subject_id: M205.id,
    group_id: tdi201.id,
    teacher_id: bouchra.id,
  });

  await knex('group_subjects').insert({
    progress: 75,
    subject_id: M206.id,
    group_id: tdi201.id,
    teacher_id: bouchra.id,
  });
};
