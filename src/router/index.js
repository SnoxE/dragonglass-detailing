import { createRouter, createWebHashHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import MenuView from '@/views/MenuView.vue'
import OrdersView from '@/views/OrdersView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/rejestracja',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/oferta',
    name: 'menu',
    component: MenuView
  },
  {
    path: '/user/id/rezerwacje',
    name: 'orders',
    component: OrdersView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
