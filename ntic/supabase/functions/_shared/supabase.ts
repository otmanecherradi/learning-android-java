import { createClient } from 'https://esm.sh/@supabase/supabase-js@^1.33.1';

export const client = createClient(
  // Supabase API URL - env var exported by default.
  Deno.env.get('SUPABASE_URL') ?? '',
  // Supabase API ANON KEY - env var exported by default.
  Deno.env.get('SUPABASE_ANON_KEY') ?? ''
);

// WARNING: The service role key has admin priviliges and should only be used in secure server environments!
export const admin = createClient(Deno.env.get('SUPABASE_URL') ?? '', Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? '');
