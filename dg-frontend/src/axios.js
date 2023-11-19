import axios from 'axios'
import { useAuthStore } from '@/stores/auth'

axios.defaults.baseURL = 'http://localhost:8080/'
axios.defaults.headers.common['Authorization'] = 'Bearer' + useAuthStore.token
