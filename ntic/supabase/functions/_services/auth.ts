import { client } from '../_shared/supabase.ts';

import { createResponse } from '../_utils/create-response.ts';

export class AuthService {
  public static async login(email: string, password: string, fcmToken: string) {
    const { data, error } = await client.auth.api.signInWithEmail(email.trim(), password);

    console.log(email, error, fcmToken);

    if (!error) {
      const userId = data?.user?.id;
      const intern = await client.from('interns').select('*').eq('id', userId);
      const teacher = await client.from('teachers').select('*').eq('id', userId);

      console.log({ userId, intern, teacher });

      if (intern.count) {
        console.log('added to intern');

        await client.from('interns').update({ fcm: fcmToken }).eq('id', userId);
      }
      if (teacher.count) {
        console.log('added to teacher');
        await client.from('teachers').update({ fcm: fcmToken }).eq('id', userId);
      }
    }

    return createResponse(error, data);
  }

  public static async refreshToken(refreshToken: string) {
    const { data, error } = await client.auth.api.refreshAccessToken(refreshToken);

    return createResponse(error, data);
  }

  public static async logout(jwt: string) {
    const { error } = await client.auth.api.signOut(jwt);

    return createResponse(error);
  }
}
