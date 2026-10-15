const taskType = document.getElementById('taskType');
const dynamicLabel = document.getElementById('dynamicLabel');
const dynamicValue = document.getElementById('dynamicValue');
const taskForm = document.getElementById('taskForm');
const taskList = document.getElementById('taskList');

taskType.addEventListener('change', () => {
  if (taskType.value === 'work') {
    dynamicLabel.textContent = 'Hours Left to Deadline';
    dynamicValue.value = '6';
  } else {
    dynamicLabel.textContent = 'Energy Required (1 to 5)';
    dynamicValue.value = '3';
  }
});

async function fetchTasks() {
  try {
    const res = await fetch('/api/tasks');
    const data = await res.json();
    renderTasks(data);
  } catch (err) {
    console.error('Fetch error:', err);
  }
}

function renderTasks(tasks) {
  taskList.innerHTML = '';
  tasks.forEach((task, index) => {
    const card = document.createElement('div');
    card.className = 'bg-[#181818] border border-zinc-800 rounded-xl p-4 flex items-center justify-between';
    
    const isWork = task.deadlineHoursLeft !== undefined;
    card.innerHTML = `
      <div class="flex items-center space-x-4">
        <span class="font-mono text-xs text-zinc-600 font-bold">#0${index + 1}</span>
        <div>
          <div class="flex items-center space-x-2">
            <span class="font-medium text-zinc-200 text-sm">${task.title}</span>
            <span class="text-[10px] px-2 py-0.5 rounded-full ${isWork ? 'bg-indigo-950 text-indigo-300' : 'bg-emerald-950 text-emerald-300'} font-mono uppercase">
              ${isWork ? 'Work' : 'Study'}
            </span>
          </div>
          <p class="text-xs text-zinc-500 mt-1">${task.estimatedMinutes} mins • Priority: ${task.priorityLevel}/5</p>
        </div>
      </div>
      <div class="text-right font-mono">
        <span class="text-xs text-zinc-500 block">Urgency</span>
        <span class="text-sm font-bold text-emerald-400">${task.urgencyScore ? task.urgencyScore.toFixed(2) : '0.00'}</span>
      </div>
    `;
    taskList.appendChild(card);
  });
}

taskForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const type = taskType.value;
  const payload = {
    title: document.getElementById('taskTitle').value,
    duration: parseInt(document.getElementById('taskDuration').value),
    priority: parseInt(document.getElementById('taskPriority').value)
  };

  if (type === 'work') {
    payload.deadlineHours = parseInt(dynamicValue.value);
  } else {
    payload.energy = parseInt(dynamicValue.value);
  }

  await fetch(`/api/tasks/${type}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });

  taskForm.reset();
  fetchTasks();
});

fetchTasks();