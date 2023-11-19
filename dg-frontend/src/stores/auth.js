import { defineStore } from 'pinia'
import router from '@/router/index.js'
import axios from 'axios'

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
    async login(email, password) {
      // const response = await fetch('http://localhost:8080/api/token', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   body: JSON.stringify({ email, password })
      // })

      const response = await axios.post('api/token', {
        email: email,
        password: password
      })

      if (response.status == 200) {
        const token = await response.data['token']
        this.user = email
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
