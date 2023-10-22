<template>
  <nav id="navbar" class="fixed z-10 w-full bg-transparent" @scroll="navScroll()">
    <div class="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between">
      <router-link to="/" class="flex items-center p-4">
        <span class="self-center whitespace-nowrap text-2xl font-semibold dark:text-white"
          >DG Detailing</span
        >
      </router-link>
      <button
        data-collapse-toggle="navbar-default"
        type="button"
        class="inline-flex h-10 w-10 items-center justify-center rounded-lg p-2 text-sm text-white hover:bg-gray-800 focus:outline-none md:hidden"
        @click="toggleMenu()"
      >
        <span class="sr-only">Open main menu</span>
        <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
          <path
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M1 1h15M1 7h15M1 13h15"
          />
        </svg>
      </button>
      <div id="navbar-default" class="hidden w-full p-4 md:block md:w-auto">
        <ul class="mt-0 flex space-x-8 bg-transparent p-0 font-medium">
          <li v-for="url in urls" :key="url.name">
            <NavBarItem :url="url.url">{{ url.name }}</NavBarItem>
          </li>
        </ul>
      </div>
      <div
        id="navbar-mobile"
        class="bg-light-gray min-h-screen w-full translate-x-full bg-opacity-30 backdrop-blur-sm transition-transform duration-300 md:hidden"
      >
        <ul class="flex flex-col pl-10 pt-10 font-medium">
          <li v-for="url in urls" :key="url.name">
            <NavBarItem :url="url.url">{{ url.name }}</NavBarItem>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import NavBarItem from '@/components/NavBarItem.vue'

export default {
  name: 'NavBar',
  components: {
    NavBarItem
  },
  data() {
    return {
      urls: [
        { name: 'O nas', url: '/' },
        { name: 'Oferta', url: '/oferta' },
        { name: 'Rezerwuj', url: '/' },
        { name: 'Zaloguj', url: '/login' },
        { name: 'Zarejestruj', url: '/rejestracja' }
      ]
    }
  },
  methods: {
    toggleMenu() {
      const navbar = document.querySelector('#navbar-mobile')

      navbar.classList.toggle('translate-x-1/4')
      navbar.classList.toggle('translate-x-full')
    },
    navScroll() {
      const navbar = document.querySelector('#navbar')

      navbar.classList.toggle('bg-mcl-orange')
    }
  }
}
</script>
