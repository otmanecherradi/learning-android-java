

import { supabase } from './supabase';

/**
 *
 * @param {FormData} formData
 */
async function handleFormSubmission(formData) {
  const date = `${formData.get('date')}`;
  const file = formData.get('file');
  const group = formData.get('group');

  console.log(date, file, group);

  const { error: uploadError } = await supabase.storage.from('media').upload(`schedules/${date}`, file, {
    upsert: true,
  });

  if (uploadError) {
    alert('Error!!  ' + uploadError.message);

    return;
  }

  const { data, error } = await supabase
    .from('schedules')
    .upsert([{ week_start: date, group_id: group, bucket_url: `schedules/${date}` }], { onConflict: 'ignore' });

  if (error) {
    alert('Error!!  ' + error.message);

    return;
  }

  alert('Upload done');
}

async function main() {
  let { data: groups, error } = await supabase.from('groups').select(
    `
    id, name, year,
    branch:branch_id(
      name
    )
    `
  );

  const groupElement = document.querySelector('select#group');

  groups.forEach((group, idx) => {
    const op = document.createElement('option');
    op.value = group.id;
    op.setAttribute('id', group.id);

    op.textContent = `${group.branch.name}${group.year}${group.name}`;

    if (idx === 0) {
      op.setAttribute('selected', 'selected');
    }

    groupElement.append(op);
  });

  const scheduleForm = document.querySelector('form#scheduleForm');
  scheduleForm.addEventListener('submit', (ev) => {
    ev.preventDefault();

    const formData = new FormData(scheduleForm);

    handleFormSubmission(formData);
  });
}

main();
