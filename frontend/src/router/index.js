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
    path: '/Tes12t',
    name: 'Test',
    component: () => import('../views/Test.vue')
  }
  ,
  {
    path: '/Modal',
    name: 'Modal',
    component: () => import('../views/Modal.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
