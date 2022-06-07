const otmane = {
  id: '8ed2056b-5403-4ec2-b835-eb574497962b',
  full_name: 'Otmane Cherradi',
  birthday: '12/22/1998',
  cin: 'k555272',
  cne: 'p131323088',
  phone: '0658248690',
  bac_mark: 11.84,
  bac_year: 2016,
  bac_specialty: 'STE',
  group_id: '',
};
const nouh = {
  id: 'ab0318a7-4b9e-4b06-915c-6481f5fdb1f6',
  full_name: 'Nouh Bakhlakh',
  cin: 'kb179281',
  phone: '0661546024',
  group_id: '',
};
const ismail = {
  id: '385cc75d-4fa7-4348-a22e-407eaa03342e',
  full_name: 'Ismail Er-regoui',
  birthday: '01/26/2000',
  cin: 'kb130761',
  phone: '0700772169',
  group_id: '',
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('students').del();
  const tdi201 = await knex('groups').where('name', '=', '01').andWhere('year', '=', '2').select('*').first();
  const tdi203 = await knex('groups').where('name', '=', '03').andWhere('year', '=', '2').select('*').first();

  const otmaneS = await knex('students')
    .insert({
      ...otmane,
      group_id: tdi201.id,
    })
    .returning('*');
  const ismailS = await knex('students')
    .insert({
      ...ismail,
      group_id: tdi201.id,
    })
    .returning('*');
  const nouhS = await knex('students')
    .insert({
      ...nouh,
      group_id: tdi203.id,
    })
    .returning('*');
};
