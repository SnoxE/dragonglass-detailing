import { createApp } from 'vue'

import '@/index.css'
import router from '@/router/index.js'
import { plugin, defaultConfig } from '@formkit/vue'
import App from '@/App.vue'

createApp(App).use(router).use(plugin, defaultConfig).mount('#app')
