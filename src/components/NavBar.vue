<template>
  <nav class="fixed z-10 w-full bg-transparent">
    <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto">
      <router-link to="/" class="flex items-center p-4">
        <!-- <img src="" class="h-8 mr-3" alt="DGD Logo" /> -->
        <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white"
          >DG Detailing</span
        >
      </router-link>
      <button
        data-collapse-toggle="navbar-default"
        type="button"
        class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-white rounded-lg md:hidden hover:bg-gray-800 focus:outline-none"
        @click="toggleMenu()"
      >
        <span class="sr-only">Open main menu</span>
        <svg class="w-5 h-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
          <path
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M1 1h15M1 7h15M1 13h15"
          />
        </svg>
      </button>
      <div id="navbar-default" class="hidden w-full md:block md:w-auto p-4">
        <ul class="font-medium flex p-0 space-x-8 mt-0 bg-transparent">
          <li v-for="link in links" :key="link.name">
            <NavBarItem :link="link.link">{{ link.name }}</NavBarItem>
          </li>
        </ul>
      </div>
      <div
        id="navbar-mobile"
        class="w-full md:hidden min-h-screen translate-x-full transition-transform duration-300 bg-gray-600 backdrop-blur-sm bg-opacity-30"
      >
        <ul class="font-medium flex flex-col pl-10 pt-10">
          <li v-for="link in links" :key="link">
            <NavBarItem :link="link.link">{{ link.name }}</NavBarItem>
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
      links: [
        { name: 'O nas', link: '/' },
        { name: 'Oferta', link: '/' },
        { name: 'Rezerwuj', link: '/' },
        { name: 'Zaloguj', link: '/login' },
        { name: 'Zarejestruj', link: '/' }
      ]
    }
  },
  methods: {
    toggleMenu() {
      const navbar = document.querySelector('#navbar-mobile')

      navbar.classList.toggle('translate-x-1/4')
      navbar.classList.toggle('translate-x-full')
    }
  }
}
</script>
