<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex p-6 text-2xl">
      <span>Nie posiadasz jeszcze konta?</span>
    </div>

    <form action="" method="post" class="login-form z-2 mx-auto flex w-80 flex-col p-6">
      <div
        v-for="inputField in inputFields"
        :key="inputField.id"
        class="mx-auto my-2 flex w-full flex-col"
      >
        <input
          :id="inputField.id"
          class="h-10 rounded bg-slate-500 pl-2"
          :type="inputField.type"
          :name="inputField.name"
          :placeholder="inputField.placeholder"
          required
        />
      </div>
      <button
        type="submit"
        class="submit-btn mx-auto my-2 w-full rounded-lg bg-slate-600 p-2 hover:bg-mcl-orange"
      >
        Zarejestruj
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data() {
    return {
      inputFields: [
        { id: 'email', type: 'text', name: 'email', placeholder: 'you@gmail.com' },
        { id: 'password', type: 'password', name: 'password', placeholder: 'password' }
      ]
    }
  }
}
</script>
