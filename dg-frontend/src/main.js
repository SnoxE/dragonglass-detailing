import { createApp } from 'vue'

import '@/index.css'
import router from '@/router/index.js'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import App from '@/App.vue'

createApp(App).use(router).component('VueDatePicker', VueDatePicker).mount('#app')
