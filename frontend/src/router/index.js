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
  }
  ,
  {
    path: '/Departure/:fromArea/:toArea/:seat/:bothWay',
    name: 'Departure',
    component: () => import('../views/Departure.vue'),
    props: true
  },
 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
