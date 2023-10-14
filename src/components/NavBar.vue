<template>
  <header class="fixed w-full border-b border-white flex justify-between items-center">
    <div class="flex px-4 py-3">
      <a :href="url" class="text-white text-xl">
        {{ company }}
      </a>
    </div>

    <nav class="">
      <ul class="flex text-white gap-8">
        <li v-for="menuItem in menuItems" :key="menuItem">
          <a href="" class="flex py-2.5">{{ menuItem }}</a>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
// import ActionButton from '@/components/ActionButton.vue'

export default {
  name: 'NavBar',
  // components: {
  //   ActionButton
  // },
  data() {
    return {
      company: 'Dragonglass Detailing',
      url: 'https://careers.google.com',
      menuItems: ['O NAS', 'OFERTA', 'REZERWUJ', 'ZALOGUJ'],
      isLoggedIn: false
    }
  },
  computed: {},
  methods: {
    loginUser() {
      this.isLoggedIn = true
    }
  }
}
</script>
