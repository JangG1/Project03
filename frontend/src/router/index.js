import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/Reservation',
    name: 'Reservation',
    component: () => import('../views/Reservation.vue')
  },
  {
    path: '/Calendar',
    name: 'Calendar',
    component: () => import('../views/Calendar.vue')
  },
  {
    path: '/Test',
    name: 'Test',
    component: () => import('../views/Test.vue')
  },
  {
    path: '/Departure/:fromArea/:toArea/:seat/:startDate/:returnDate/:AdultCount/:ChildCount/:InfantCount',
    name: 'Departure',
    component: () => import('../views/Departure.vue'),
    props: true
  },  
  {
    path: '/Arrival/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startDate/:returnDate/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2',
    name: 'Arrival',
    component: () => import('../views/Arrival.vue'),
    props: true
  },
  {
    path: '/Payment/:fromArea/:toArea/:seat/:seatClass1/:seatClass2/:startDate/:returnDate/:AdultCount/:ChildCount/:InfantCount/:startTime1/:arriveTime1/:startPrice/:startTime2/:arriveTime2',
    name: 'Payment',
    component: () => import('../views/Payment.vue'),
    props: true
  },
 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
