<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Nie masz jeszcze konta?</span>
    </div>

    <form action="" method="post" class="login-form z-2 mx-auto flex w-80 flex-col p-6">
      <div class="mx-auto my-2 flex w-full flex-col gap-4">
        <InputField
          id="email"
          v-model="email"
          class="h-10 rounded bg-light-gray pl-2"
          type="text"
          name="email"
          placeholder="you@gmail.com"
          required
        />
        <InputField
          id="password"
          v-model="password"
          class="h-10 rounded bg-light-gray pl-2"
          type="password"
          name="email"
          placeholder="password"
          required
        />
      </div>
      <button
        type="submit"
        class="submit-btn mx-auto my-6 w-full rounded-lg bg-gray-500 p-2 hover:bg-mcl-orange"
      >
        Zarejestruj
      </button>
      <div class="mx-auto flex">
        <span class="p-1 text-sm">Masz już konto?</span>
        <router-link
          to="/login"
          class="p-1 text-sm underline underline-offset-2 hover:text-mcl-orange"
          >Zaloguj się</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue'

export default {
  name: 'RegisterForm',
  components: { InputField },
  data() {
    return {
      email: '',
      password: ''
    }
  }
}
</script>
