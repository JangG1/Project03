import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: "/LoginModal",
    name: "LoginModal",
    component: () => import("@/components/LoginModal.vue"),
  },
  {
    path: "/Callback",
    name: "Callback",
    component: () => import("@/views/Callback.vue"),
  },
  {
    path: '/Reservation',
    name: 'Reservation',
    component: () => import('../views/Reservation.vue')
  },
  {
    path: '/Test',
    name: 'Test',
    component: () => import('../views/Test.vue')
  },
  {
    path: '/Departure/:fromArea/:toArea/:seat/:startYear/:startMonth/:startDay/:startWeek/:returnYear/:returnMonth/:returnDay/:returnWeek/:AdultCount/:ChildCount/:InfantCount',
    name: 'Departure',
    component: () => import('../views/Departure.vue'),
    props: true
  },  
  {
    path: '/Arrival/:flight1/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startYear/:startMonth/:startDay/:startWeek/:returnYear/:returnMonth/:returnDay/:returnWeek/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2',
    name: 'Arrival',
    component: () => import('../views/Arrival.vue'),
    props: true
  },
  {
    path: '/Payment/:flight1/:flight2/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startYear/:startMonth/:startDay/:startWeek/:returnYear/:returnMonth/:returnDay/:returnWeek/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2',
    name: 'Payment',
    component: () => import('../views/Payment.vue'),
    props: true
  },
  {
    path: '/PayModal/:flight1/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startYear/:startMonth/:startDay/:startWeek/:returnYear/:returnMonth/:returnDay/:returnWeek/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2/:korName/:engName/:gender/:birthday/:addPassKorName/:addPassEngName/:addPassGender/:addPassBirthday',
    name: 'PayModal',
    component: () => import('@/components/PayModal.vue'),
    props: true
  },
  {
    path: '/Complete/:flight1/:flight2/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startYear/:startMonth/:startDay/:startWeek/:returnYear/:returnMonth/:returnDay/:returnWeek/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2/:korName/:engName/:gender/:birthday/:addPassKorName/:addPassEngName/:addPassGender/:addPassBirthday',
    name: 'Complete',
    component: () => import('@/views/Complete.vue'),
    props: true
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
