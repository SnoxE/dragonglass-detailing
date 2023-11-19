import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

axios.defaults.baseURL = 'http://localhost:8080/'
axios.interceptors.request.use(function (config) {
  const token = useAuthStore.token
  config.headers.Authorization = token ? `Bearer ${token}` : ''
  return config
})
