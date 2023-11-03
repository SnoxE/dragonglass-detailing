import { createRouter, createWebHashHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import MenuView from '@/views/MenuView.vue'
import OrdersView from '@/views/OrdersView.vue'
import ReserveView from '@/views/ReserveView.vue'
import { useAuthStore } from '@/stores/auth'

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
  },
  {
    path: '/rezerwuj',
    name: 'reserve',
    component: ReserveView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach(async (to) => {
  const publicPages = ['/login', '/rejestracja', '/', '/oferta']
  const authRequired = !publicPages.includes(to.path)
  const auth = useAuthStore()

  if (authRequired && !auth.user) {
    return '/login'
  }
})

export default router
