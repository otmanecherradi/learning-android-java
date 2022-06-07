import { client } from '../_shared/supabase.ts';

import { createResponse } from '../_utils/create-response.ts';

export class SchoolService {
  public static async list() {
    const { data, error } = await client.from('schools').select();

    return createResponse(error, data);
  }
}
