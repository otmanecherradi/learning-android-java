import { json } from 'https://deno.land/x/sift@0.4.0/mod.ts';

export function createResponse(error: any | null = null, data: any | null = null) {
  return json({ status: error ? 'ERROR' : 'SUCCESS', data: error ?? data });
}
