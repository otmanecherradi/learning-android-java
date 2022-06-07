require('dotenv').config();

const env = {
  DATABASE_NAME: process.env.DATABASE_NAME,
  PG_USERNAME: process.env.PG_USERNAME,
  PG_PASSWORD: process.env.PG_PASSWORD,
  PG_HOST: process.env.PG_HOST,
  PG_PORT: process.env.PG_PORT,
};

Object.entries(env).forEach(([name, value]) => {
  if (!value) {
    throw new Error(`${name} is not specified in the .env file!`);
  }
});

module.exports = env;
