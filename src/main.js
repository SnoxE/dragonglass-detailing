import { createApp } from 'vue'

import '@/index.css'
import router from '@/router/index.js'
import App from '@/App.vue'

createApp(App).use(router).mount('#app')
