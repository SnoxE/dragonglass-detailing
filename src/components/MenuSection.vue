<template>
  <div class="mx-auto flex max-w-screen-xl flex-col flex-wrap p-4">
    <h1 class="p-4 text-5xl font-medium text-white">Oferta</h1>
    <ul class="grid grid-cols-3 gap-6 py-6 md:grid-cols-3">
      <li
        v-for="item in items"
        :key="item.name"
        class="bg-light-gray flex flex-col items-center justify-center rounded-xl"
      >
        <img src="@/assets/White GT3 RS sticker side profile with traffic lights.jpg" alt="" />
        <router-link :to="item.url" class="mx-auto p-4">
          <span class="p-4 text-white">{{ item.name }} </span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'MenuSection',
  data() {
    return {
      items: [
        { name: 'Mycie Ręczne', url: '#' },
        { name: 'Woskowanie', url: '#' },
        { name: 'Korekta Lakieru', url: '#' },
        { name: 'Zabezpieczenie PPF', url: '#' },
        { name: 'Powłoka Ceramiczna', url: '#' },
        { name: 'Czyszczenie Wnętrza', url: '#' },
        { name: 'Przyciemnianie Szyb', url: '#' }
      ]
    }
  }
}
</script>
