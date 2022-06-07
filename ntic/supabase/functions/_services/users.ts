import { client } from '../_shared/supabase.ts';

import { createResponse } from '../_utils/create-response.ts';

export class UsersService {
  public static async list() {
    const { data, error } = await client.from('profiles').select();

    return createResponse(error, data);
  }
}
