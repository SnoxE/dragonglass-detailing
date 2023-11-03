import { defineStore } from 'pinia'
import router from '@/router/index.js'

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => {
    return {
      user: null,
      token: '',
      returnUrl: '/'
    }
  },
  actions: {
    async login(username, password) {
      const response = await fetch('http://localhost:8080/token', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      })

      if (response.status == 200) {
        const token = await response.text()
        this.user = username
        this.token = token
        router.push(this.returnUrl || '/')
      }
    },
    logout() {
      this.user = null
      this.token = ''
      router.push('/login')
    }
  }
})
