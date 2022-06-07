const env = require('./env');

module.exports = {
  development: {
    client: 'pg',
    connection: {
      database: env.DATABASE_NAME,
      user: env.PG_USERNAME,
      password: env.PG_PASSWORD,
      host: env.PG_HOST,
      port: env.PG_PORT,
    },
    migrations: {
      tableName: 'knex_migrations',
      directory: './migrations',
    },
    seeds: {
      directory: './seeds',
    },
  },

  staging: {
    client: 'pg',
    connection: {
      database: env.DATABASE_NAME,
      user: env.PG_USERNAME,
      password: env.PG_PASSWORD,
      host: env.PG_HOST,
      port: env.PG_PORT,
    },
    migrations: {
      tableName: 'knex_migrations',
      directory: './migrations',
    },
    seeds: {
      directory: './seeds',
    },
  },

  production: {
    client: 'pg',
    connection: {
      database: env.DATABASE_NAME,
      user: env.PG_USERNAME,
      password: env.PG_PASSWORD,
      host: env.PG_HOST,
      port: env.PG_PORT,
    },
    migrations: {
      tableName: 'knex_migrations',
      directory: './migrations',
    },
    seeds: {
      directory: './seeds',
    },
  },
};
