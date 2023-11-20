import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'
axios.interceptors.request.use(function (config) {
  const token = localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')) : ''
  config.headers.Authorization = token ? `Bearer ${token}` : ''
  return config
})
