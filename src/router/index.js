import { createRouter, createWebHashHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import MenuView from '@/views/MenuView.vue'

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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
