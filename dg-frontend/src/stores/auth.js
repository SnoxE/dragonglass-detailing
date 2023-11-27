import { defineStore } from 'pinia'
import router from '@/router/index.js'
import { isLoggedIn } from '@/authStatus.js'
import axios from 'axios'

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => {
    return {
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : null,
      token: localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')) : '',
      returnUrl: '/'
    }
  },
  actions: {
    async login(email, password) {
      const response = await axios.post('api/token', {
        email: email,
        password: password
      })

      if (response.status == 200) {
        const token = await response.data['token']
        localStorage.setItem('user', JSON.stringify(email))
        localStorage.setItem('token', JSON.stringify(token))
        this.user = email
        this.token = token
        isLoggedIn.value = true
        router.push(this.returnUrl || '/')
      }
    },
    logout() {
      this.user = null
      this.token = ''
      localStorage.removeItem('user')
      localStorage.removeItem('token')
    }
  }
})
