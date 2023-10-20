<template>
  <div class="min-h-screen bg-gray-900">
    <SeparatorComp />
    <RegisterForm />
  </div>
</template>

<script>
import RegisterForm from '@/components/RegisterForm.vue'
import SeparatorComp from '@/components/SeparatorComp.vue'

export default {
  name: 'RegisterView',
  components: {
    RegisterForm,
    SeparatorComp
  }
}
</script>
