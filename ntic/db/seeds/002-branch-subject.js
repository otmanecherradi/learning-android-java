const branches = [
  {
    name: 'TDI',
    level: 'TS',
    description: 'Technique de developement informatique',
  },
  {
    name: 'TRI',
    level: 'TS',
    description: 'Technique de reseau informatique',
  },
  {
    name: 'INFO',
    level: 'TS',
    description: 'Infographie',
  },
];

const subjects = [
  {
    name: 'EGTS202',
    description: 'Français',
  },
  {
    name: 'EGTS203',
    description: 'Anglais technique',
  },
  {
    name: 'EGTS204',
    description: "Culture d'entreprise",
  },
  {
    name: 'EGTS205',
    description: 'Compétences comportementales et social',
  },
  {
    name: 'M201',
    description: 'Bases de données',
  },
  {
    name: 'M202',
    description: "Développement d'application client serveur",
  },
  {
    name: 'M203',
    description: 'Développement web coté client',
  },
  {
    name: 'M204',
    description: 'Développement web coté serveur',
  },
  {
    name: 'M205',
    description: "Développement d'application mobiles",
  },
  {
    name: 'M206',
    description: 'Projet de fin de formation',
  },
  {
    name: 'M207',
    description: 'Intégration au milieu de travail',
  },
];

const branch_subjects = [
  { s: 0, mh: 60 },
  { s: 1, mh: 35 },
  { s: 2, mh: 50 },
  { s: 3, mh: 30 },
  { s: 4, mh: 110 },
  { s: 5, mh: 100 },
  { s: 6, mh: 100 },
  { s: 7, mh: 100 },
  { s: 8, mh: 105 },
  { s: 9, mh: 45 },
  { s: 10, mh: 168 },
];

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('branches').del();
  await knex('branches').insert(branches);

  await knex('subjects').del();
  const ss = await knex('subjects').insert(subjects).returning('*');

  await knex('branch_subjects').del();
  const tdi = await knex('branches').where('name', '=', 'TDI').select('*').first();

  for (let idx = 0; idx < branch_subjects.length; idx++) {
    const bs = branch_subjects[idx];

    await knex('branch_subjects').insert({
      branch_id: tdi.id,
      subject_id: ss[bs.s].id,
      mass: bs.mh,
    });
  }
};
