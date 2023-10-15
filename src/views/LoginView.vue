<template>
  <div class="bg-gray-900 min-h-screen">
    <SeparatorComp />
    <LoginForm class="" />
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'
import SeparatorComp from '@/components/SeparatorComp.vue'

export default {
  name: 'LoginView',
  components: {
    LoginForm,
    SeparatorComp
  }
}
</script>
