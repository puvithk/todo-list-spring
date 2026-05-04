// ===== VARIABLES =====
const months = ["January","February","March","April","May","June","July","August","September","October","November","December"];

const taskTitle = document.getElementById('title')
const taskDescription = document.getElementById('description')
const taskPriority = document.getElementById('priority')
const taskDueDate = document.getElementById('due-date')
const taskTime = document.getElementById('time')

const formData  =  [taskTitle , taskDescription , taskPriority , taskDueDate , taskTime]

const todayTaskContainer = document.getElementById('todo-list-today');
const weekTaskContainer = document.getElementById('todo-list-week');
const monthTaskContainer = document.getElementById('todo-list-month');

const todaysProgress = document.getElementById('today-progress')
const weeksProgress = document.getElementById('week-progress')
const monthProgress = document.getElementById('month-progress')

const todayPercentage = document.getElementById('todays-precentage')
const weekPercentage = document.getElementById('week-precentage')
const monthPercentage = document.getElementById('month-precentage')

const todayCompleted = document.getElementById('today-completed')
const todayPending = document.getElementById('today-pending')
const weekCompleted = document.getElementById('week-completed')
const weekPending = document.getElementById('week-pending')
const monthCompleted = document.getElementById('month-completed')
const monthPending = document.getElementById('month-pending')

const taskHeaderProgressMonth = document.getElementById('task-progress-month')
const taskHeaderProgressDay = document.getElementById('task-progress-today')
const taskHeaderProgressWeek = document.getElementById('task-progress-week')

const previousButton = document.getElementById('prev')
const nextButton = document.getElementById('next')

const CATAGORY = {
    TODAY :'today' ,
    MONTH : 'month' ,
    WEEK : 'week'
}

// ===== STATE =====
let today = new Date()
let todayTask = []
let weekTask =[]
let monthTask = []

// ===== WEEK CALC =====
const getFullweekStartAndEnd = (date) => {
    const start = date.getDate() - date.getDay();
    let end = start + 6;

    return {
        startDate: new Date(date.getFullYear(), date.getMonth(), start),
        endDate: new Date(date.getFullYear(), date.getMonth(), end)
    };
};

// ===== DIVIDE TASKS =====
const getAllTaskAndDivide = () => {
    let weekDetails = getFullweekStartAndEnd(today);

    todayTask = [];
    weekTask = [];
    monthTask = [];

    let filteredTask = allTask.filter((element) => {
        const taskDate = new Date(element.schedule.date);

        if(element.schedule.type === CATAGORY.TODAY){
            return taskDate.toDateString() === today.toDateString();
        }
        if(element.schedule.type === CATAGORY.WEEK){
            return taskDate >= weekDetails.startDate &&
                   taskDate <= weekDetails.endDate;
        }
        if(element.schedule.type === CATAGORY.MONTH){
            return taskDate.getFullYear() === today.getFullYear() &&
                   taskDate.getMonth() === today.getMonth();
        }
    });

    filteredTask.forEach((element) => {
        if(element.schedule.type === CATAGORY.TODAY){
            todayTask.push(element);
        } else if(element.schedule.type === CATAGORY.WEEK){
            weekTask.push(element);
        } else {
            monthTask.push(element);
        }
    });
};

// ===== TASK CARD =====
const todoCard = ({ id, title, time, priority , status  }) => {
    const div = document.createElement('div');
    div.className = 'todo-card';

    if(status) div.classList.add('checked');

    div.innerHTML = `
        <div>
            <p>${title}</p>
            <p>${time}</p>
            <p>${priority}</p>
        </div>
    `;

    return div;
};

// ===== RENDER =====
//const UpdateTodaysTodo = () => {
//    todayTaskContainer.innerHTML = '';
//    todayTask.forEach(t => {
//        todayTaskContainer.append(todoCard({
//            id: t.id,
//            title: t.content.title,
//            time: t.schedule.time,
//            priority: t.priority.level,
//            status: t.status.completed
//        }));
//    });
//};
//
//const UpdateWeekTodo = () => {
//    weekTaskContainer.innerHTML = '';
//    weekTask.forEach(t => {
//        weekTaskContainer.append(todoCard({
//            id: t.id,
//            title: t.content.title,
//            time: t.schedule.date + " " + t.schedule.time,
//            priority: t.priority.level,
//            status: t.status.completed
//        }));
//    });
//};
//
//const UpdateMonthTodo = () => {
//    monthTaskContainer.innerHTML = '';
//    monthTask.forEach(t => {
//        monthTaskContainer.append(todoCard({
//            id: t.id,
//            title: t.content.title,
//            time: t.schedule.date + " " + t.schedule.time,
//            priority: t.priority.level,
//            status: t.status.completed
//        }));
//    });
//};

// ===== PROGRESS =====
const findAllPercentage = ()=>{
    let d = todayTask.filter(t=>t.status.completed).length
    let w = weekTask.filter(t=>t.status.completed).length
    let m = monthTask.filter(t=>t.status.completed).length

    return {
        dayPercentage : todayTask.length ? (d/todayTask.length)*100 : 0,
        weekPercentage : weekTask.length ? (w/weekTask.length)*100 : 0,
        monthPercentage : monthTask.length ? (m/monthTask.length)*100 : 0,
        d,w,m
    }
}

const UpdateProgress = ()=>{
    const p = findAllPercentage()

    todaysProgress.style.width = p.dayPercentage + "%"
    weeksProgress.style.width = p.weekPercentage + "%"
    monthProgress.style.width = p.monthPercentage + "%"

    todayPercentage.innerText = Math.round(p.dayPercentage) + "%"
    weekPercentage.innerText = Math.round(p.weekPercentage) + "%"
    monthPercentage.innerText = Math.round(p.monthPercentage) + "%"

    todayCompleted.innerText = p.d
    weekCompleted.innerText = p.w
    monthCompleted.innerText = p.m

    todayPending.innerText = todayTask.length - p.d
    weekPending.innerText = weekTask.length - p.w
    monthPending.innerText = monthTask.length - p.m
}

// ===== CALENDAR =====
const renderCalender = (date)=>{
    const days = document.getElementById('day')
    days.innerHTML = ''

    const startDay = new Date(date.getFullYear(), date.getMonth(), 1).getDay()
    const endDay = new Date(date.getFullYear(), date.getMonth()+1, 0).getDate()

    for(let i=0;i<startDay;i++){
        days.innerHTML += `<div></div>`
    }

    for(let i=1;i<=endDay;i++){
        days.innerHTML += `<div>${i}</div>`
    }
}

// ===== NAVIGATION =====
previousButton.addEventListener('click', () => {
    today = new Date(today.getFullYear(), today.getMonth() - 1, 1)
    refreshAll()
})

nextButton.addEventListener('click', () => {
    today = new Date(today.getFullYear(), today.getMonth() + 1, 1)
    refreshAll()
})

// ===== REFRESH =====
const refreshAll = () => {
    getAllTaskAndDivide();
    renderCalender(today);
    UpdateTodaysTodo();
    UpdateWeekTodo();
    UpdateMonthTodo();
    UpdateProgress();
}

// ===== INIT =====
window.addEventListener("load", () => {
    refreshAll();
});