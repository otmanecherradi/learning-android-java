const schools = [
  {
    name: "Institut Spécialisé dans les Métiers de l'Offshoring et les Nouvelles Technologies de l'information et de la Communication ISMONTIC",
    lat: 35.74369444,
    lng: -5.846444444,
    url: 'https://www.ofppt.ma/fr/etablissements/institut-specialise-dans-les-metiers-de-loffshoring-et-les-nouvelles-technologies',
  },
  {
    name: 'Centre de Qualification Professionnelle Confection',
    lat: 35.77244444,
    lng: -5.799138889,
    url: 'https://www.ofppt.ma/fr/etablissements/centre-de-qualification-professionnelle-confection-tanger',
  },
  {
    name: 'Institut Spécialisé de Technologie Appliquée de Textile - Confection - Tanger',
    lat: 35.743102,
    lng: -5.845343,
    url: 'https://www.ofppt.ma/fr/etablissements/institut-specialise-de-technologie-appliquee-de-textile-confection-tanger',
  },
  {
    name: 'Institut Spécialisé de Technologie Appliquée Ibn Marhal - Tanger',
    lat: 35.756,
    lng: -5.846777778,
    url: 'https://www.ofppt.ma/fr/etablissements/institut-specialise-de-technologie-appliquee-ibn-marhal-tanger',
  },
];

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
  await knex('schools').del();
  await knex('schools').insert(schools);
};
