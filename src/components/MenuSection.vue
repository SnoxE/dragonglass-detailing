<template>
  <div class="mx-auto flex max-w-screen-xl flex-col flex-wrap p-4">
    <h1 class="border-b-2 p-4 pb-1 text-4xl font-medium text-white">OFERTA</h1>
    <ul class="grid grid-cols-1 gap-7 py-7 sm:grid-cols-3 md:grid-cols-3">
      <li
        v-for="item in items"
        :key="item.name"
        class="flex flex-col items-center justify-center rounded-xl bg-light-gray"
      >
        <img src="@/assets/White_GT3_RS_sticker_side_profile_with_traffic_lights.jpg" alt="" />
        <router-link :to="item.url" class="mx-auto p-4">
          <span class="p-4 font-medium text-white">{{ item.name }} </span>
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
