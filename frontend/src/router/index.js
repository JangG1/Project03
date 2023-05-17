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
    path: '/Reservation',
    name: 'Reservation',
    component: () => import('../views/Reservation.vue')
  },
  {
    path: '/Contact',
    name: 'Contact',
    component: () => import('../views/Contact.vue'),
    props: true
  },
  {
    path: '/Departure',
    name: 'Departure',
    component: () => import('../views/Departure.vue'),
    props: true
  },  
  {
    path: '/Return',
    name: 'Return',
    component: () => import('../views/Return.vue'),
    props: true
  },
  {
    path: '/Payment',
    name: 'Payment',
    component: () => import('../views/Payment.vue'),
    props: true
  },
  {
    path: '/PayModal',
    name: 'PayModal',
    component: () => import('@/components/PayModal.vue'),
    props: true
  },
  {
    path: '/Complete',
    name: 'Complete',
    component: () => import('@/views/Complete.vue'),
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
