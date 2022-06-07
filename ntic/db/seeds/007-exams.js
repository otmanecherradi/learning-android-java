/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('exams').del();

  const tdi201 = await knex('groups').where('name', '=', '01').andWhere('year', '=', '2').select('*').first();

  const M201 = await knex('subjects').where('name', '=', 'M201').select('*').first();
  const M205 = await knex('subjects').where('name', '=', 'M205').select('*').first();

  const bouchra = await knex('teachers').where('id', '=', '675e67b3-9d25-4bd7-9db2-8c3ee9629b94').select('*').first();

  const groupRefM201 = await knex('group_subjects')
    .where('subject_id', '=', M201.id)
    .andWhere('group_id', '=', tdi201.id)
    .andWhere('teacher_id', '=', bouchra.id)
    .select('*')
    .first();

  const groupRefM205 = await knex('group_subjects')
    .where('subject_id', '=', M205.id)
    .andWhere('group_id', '=', tdi201.id)
    .andWhere('teacher_id', '=', bouchra.id)
    .select('*')
    .first();

  await knex('exams').insert({
    type: 'cc',
    date: new Date('10/22/2021'),
    group_reference_id: groupRefM201.id,
  });

  await knex('exams').insert({
    type: 'cc',
    date: new Date('11/29/2021'),
    group_reference_id: groupRefM201.id,
  });

  await knex('exams').insert({
    type: 'efm',
    date: new Date('12/20/2021'),
    group_reference_id: groupRefM201.id,
  });

  await knex('exams').insert({
    type: 'cc',
    date: new Date('03/24/2022'),
    group_reference_id: groupRefM205.id,
  });

  await knex('exams').insert({
    type: 'cc',
    date: new Date('06/13/2022'),
    group_reference_id: groupRefM205.id,
  });
};
