import { createClient } from '@supabase/supabase-js';

const supabaseUrl = 'https://btbhfssptcdnxmfvpash.supabase.co';
const supabaseKey =
  'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ0Ymhmc3NwdGNkbnhtZnZwYXNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NTEwNzI0OTgsImV4cCI6MTk2NjY0ODQ5OH0.JiXkWrQxGRIbLMik0-8gtzfCFULKuMNdeJHfcES4lHg';
const supabase = createClient(supabaseUrl, supabaseKey);

export { supabase };
