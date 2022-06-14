import { json, serve, validateRequest } from 'https://deno.land/x/sift@0.5.0/mod.ts';
import { summon } from 'https://deno.land/x/summon@0.0.2/mod.ts';

import { corsHeaders } from '../_shared/cors.ts';
import { admin, client } from '../_shared/supabase.ts';

export function createResponse(error: any | null = null, data: any | null = null) {
  return { status: error ? 'ERROR' : 'SUCCESS', data: error ?? data };
}

export class NotificationService {
  public static async send(fcmToken: string, title: string, body: string) {
    console.log('NotificationService.send');
    // fqgfTqFkRdyeravu7vYFwq:APA91bGD1Kj8-DVQSgh7cr0_1UcT5BJfPiwrdQFtxNFf7PFwGvHy7pSAF2SNbmznCnUoOrxUVWtnMeZvVA0td21o7_ul3_bLM87in9sz1_tK9sjml9-iboNW_v1IqWVY__Y_YLzvfMdQ
    const { response, error } = await summon.post(
      'https://fcm.googleapis.com/fcm/send',
      {
        to: fcmToken,
        data: {
          title,
          body,
        },
      },
      {
        headers: {
          'content-type': 'application/json',
          Authorization:
            'key=AAAA7G5G750:APA91bEupudrWWueH-2cQpDN62mlseadAAFB-VgXGgZ1gfyOdkTLHKhwzyyF-JGskUWi8utiCPlAa4cCDzzlSgyGRJ4tMQelfwHs7s39OVLjT9fKG00WSbKBKgsb4PXJWdUKwKGd45K6',
        },
      }
    );

    return createResponse(error, response);
  }
}

export class AuthService {
  public static async signup(email: string, password: string) {
    console.log('AuthService.signup');

    const { data, error } = await admin.auth.api.createUser({
      email: email.trim(),
      password,
      email_confirm: true,
    });

    return createResponse(error, data);
  }

  public static async login(email: string, password: string, fcmToken: string) {
    console.log('AuthService.login');

    const { data, error } = await client.auth.api.signInWithEmail(email.trim(), password);

    if (!error) {
      await admin.auth.api.updateUserById(data?.user?.id as string, {
        user_metadata: {
          fcmToken,
        },
      });
    }

    return createResponse(error, data);
  }

  public static async refreshToken(refreshToken: string) {
    console.log('AuthService.refreshToken');

    const { data, error } = await client.auth.api.refreshAccessToken(refreshToken);

    return createResponse(error, data);
  }

  public static async logout(jwt: string) {
    console.log('AuthService.logout');

    const { error } = await client.auth.api.signOut(jwt);

    return createResponse(error);
  }
}

export class UsersServices {
  public static async me(accessToken: string) {
    console.log('UsersServices.me');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id);
    const { data: teacherData, error: teacherError } = await client.from('teachers').select('*').eq('id', userData?.id);

    if (studentError) {
      return json(createResponse(studentError.message));
    }

    if (teacherError) {
      return json(createResponse(teacherError.message));
    }

    const { data: avatarData, error: avatarError } = await client.storage.from('media').createSignedUrl(`avatars/${userData?.id}`, 60 * 60);

    console.log(`avatars/${userData?.id}`, avatarData, avatarError);

    if (avatarError) {
      return json(createResponse(avatarError.message));
    }

    return createResponse(null, {
      ...userData,
      ...(studentData ? { student: { ...studentData[0], avatar_url: avatarData?.signedURL } } : {}),
      ...(teacherData ? { teacher: { ...teacherData[0], avatar_url: avatarData?.signedURL } } : {}),
    });
  }
}

export class SchoolsService {
  public static async list() {
    console.log('SchoolsService.list');

    const { data, error } = await client.from('schools').select('*');

    return createResponse(error, data);
  }
}

export class BranchesService {
  public static async list() {
    console.log('BranchesService.list');

    const { data, error } = await client.from('branches').select('*');

    return createResponse(error, data);
  }

  public static async listSubjects(branchId: string) {
    console.log('BranchesService.listSubjects');

    const { data, error } = await client
      .from('branch_subjects')
      .select(
        `
        subject:subject_id (
          id,
          name,
          description
        ),

        mass
        `
      )
      .eq('branch_id', branchId);

    return createResponse(error, data);
  }

  public static async listGroups(branchId: string) {
    console.log('BranchesService.listGroups');

    const { data, error } = await client
      .from('groups')
      .select(
        `
        branch:branch_id (
          id,
          name,
          description
        ),

        id,
        name,
        year
        `
      )
      .eq('branch_id', branchId);

    return createResponse(error, data);
  }
}

export class SubjectsService {
  public static async list() {
    console.log('SubjectsService.list');

    const { data, error } = await client.from('subjects').select('*');

    return createResponse(error, data);
  }

  public static async get(subjectId: string) {
    console.log('SubjectsService.get');

    const { data, error } = await client.from('subjects').select('*').eq('id', subjectId);

    return createResponse(error, data);
  }
}

export class GroupsService {
  public static async list() {
    console.log('GroupsService.list');

    const { data, error } = await client.from('groups').select('*');

    return createResponse(error, data);
  }

  static async listStudents(accessToken: string) {
    console.log('GroupsService.listStudents');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id).single();

    if (studentError) {
      return json(createResponse(studentError.message));
    }

    const { data, error } = await client.from('students').select('*').eq('group_id', studentData?.group_id);

    return createResponse(error, data);
  }
}

export class ExamsService {
  public static async list(accessToken: string) {
    console.log('ExamsService.list');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id).single();
    if (studentError) {
      return json(createResponse(studentError.message));
    }

    const { data: groupSubjectData, error: groupSubjectError } = await client
      .from('group_subjects')
      .select('*')
      .eq('group_id', studentData.group_id);
    if (groupSubjectError) {
      return json(createResponse(groupSubjectError.message));
    }

    const { data, error } = await client
      .from('exams')
      .select(
        `
        id, 
        type,
        date,
        group_reference:group_reference_id (
          id,
          group:group_id (
            id,
            name,
            year,
            branch:branch_id (
              name
            )
          ),
          subject:subject_id (
            id,
            name
          ),
          teacher:teacher_id (
            id,
            full_name
          )
        )
        `
      )
      .in(
        'group_reference_id',
        groupSubjectData.map((v) => v.id)
      );

    return createResponse(error, data);
  }
}

export class DemandsService {
  public static async list(accessToken: string) {
    console.log('DemandsService.list');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id).single();
    if (studentError) {
      return json(createResponse(studentError.message));
    }

    const { data, error } = await client.from('demands').select('*').eq('student_id', studentData?.id);

    return createResponse(error, data);
  }

  public static async add(accessToken: string, type: string, forDate: string, note: string) {
    console.log('DemandsService.add');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id).single();
    if (studentError) {
      return json(createResponse(studentError.message));
    }

    const [d, m, y] = forDate.split('/');

    const { data, error } = await client.from('demands').insert(
      {
        student_id: studentData?.id,
        type: type.toLowerCase(),
        for_date: new Date(`${y}-${m}-${d}`),
        note,
        state: 'todo',
      },
      { returning: 'minimal' }
    );

    return createResponse(error, data);
  }
}

export class SchedulesService {
  public static async list(accessToken: string) {
    console.log('SchedulesService.list');

    const { data: userData, error: userError } = await client.auth.api.getUser(accessToken);
    if (userError) {
      return json(createResponse(userError.message));
    }

    const { data: studentData, error: studentError } = await client.from('students').select('*').eq('id', userData?.id).single();
    if (studentError) {
      return json(createResponse(studentError.message));
    }

    const { data, error } = await client.from('schedules').select('*').eq('group_id', studentData?.group_id);

    if (error) {
      return json(createResponse(error.message));
    }

    const schedules = [];

    for (let idx = 0; idx < (data as any[]).length; idx += 1) {
      const schedule = (data as any[])[idx];

      const { data: scheduleData } = await client.storage.from('media').createSignedUrl(schedule?.bucket_url, 60 * 60);

      schedules.push({
        ...schedule,
        schedule_url: scheduleData?.signedURL,
      });
    }

    return createResponse(null, schedules);
  }
}

serve({
  '/api/auth/signup': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['email', 'password'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { email, password } = body as { email: string; password: string };
    if (!email || !password) {
      return json(createResponse('email & password are required'));
    }

    const user = await AuthService.signup(email, password);

    return json(user);
  },

  '/api/auth/login': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['email', 'password', 'fcm_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { email, password, fcm_token: fcmToken } = body as { email: string; password: string; fcm_token: string };
    if (!email || !password || !fcmToken) {
      return json(createResponse('email & password & fcm_token are required'));
    }
    const response = await AuthService.login(email, password, fcmToken);

    return json(response);
  },

  '/api/auth/refresh': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['refresh_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { refresh_token: refreshToken } = body as { refresh_token: string };
    if (!refreshToken) {
      return json(createResponse('refresh_token is required'));
    }
    const response = await AuthService.refreshToken(refreshToken);

    return json(response);
  },

  '/api/auth/logout': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await AuthService.logout(accessToken);

    return json(response);
  },

  '/api/users/me': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await UsersServices.me(accessToken);

    return json(response);
  },

  '/api/users/me/group/students/list': async (req, con, params) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await GroupsService.listStudents(accessToken);

    return json(response);
  },

  '/api/schools/list': async () => {
    const response = await SchoolsService.list();

    return json(response);
  },

  '/api/branches/list': async () => {
    const response = await BranchesService.list();

    return json(response);
  },

  '/api/branches/:branchId/subjects/list': async (req, con, params) => {
    const branchId = (params as any).branchId as string;
    const response = await BranchesService.listSubjects(branchId);

    return json(response);
  },

  '/api/branches/:branchId/groups/list': async (req, con, params) => {
    const branchId = (params as any).branchId as string;
    const response = await BranchesService.listGroups(branchId);

    return json(response);
  },

  '/api/subjects/list': async () => {
    const response = await SubjectsService.list();

    return json(response);
  },

  '/api/subjects/:subjectId/get': async (req, con, params) => {
    const subjectId = (params as any).subjectId as string;
    const response = await SubjectsService.get(subjectId);

    return json(response);
  },

  '/api/exams/list': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await ExamsService.list(accessToken);

    return json(response);
  },

  '/api/demands/add': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token', 'type', 'for_date', 'note'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const {
      access_token: accessToken,
      type,
      for_date: forDate,
      note,
    } = body as { access_token: string; type: string; for_date: string; note: string };
    if (!accessToken || !type || !forDate || !note) {
      return json(createResponse('access_token & type & for_date & note are required'));
    }
    const response = await DemandsService.add(accessToken, type, forDate, note);

    return json(response);
  },

  '/api/demands/list': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await DemandsService.list(accessToken);

    return json(response);
  },

  '/api/schedules/list': async (req) => {
    const { error, body } = await validateRequest(req, {
      POST: {
        body: ['access_token'],
      },
    });

    if (error) {
      return json(createResponse(error.message));
    }

    const { access_token: accessToken } = body as { access_token: string };
    if (!accessToken) {
      return json(createResponse('access_token is required'));
    }
    const response = await SchedulesService.list(accessToken);

    return json(response);
  },

  404: () => {
    return json(createResponse('not found'), { status: 404 });
  },
});

console.log(`API server running`);

// curl -L -X POST 'https://btbhfssptcdnxmfvpash.functions.supabase.co/api/auth/signup' -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ0Ymhmc3NwdGNkbnhtZnZwYXNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NTEwNzI0OTgsImV4cCI6MTk2NjY0ODQ5OH0.JiXkWrQxGRIbLMik0-8gtzfCFULKuMNdeJHfcES4lHg' --data '{"email":"erregoui.ismail@ofppt-edu.ma","password":"password"}'
