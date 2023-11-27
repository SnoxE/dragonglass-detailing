<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Zarezerwuj usługę</span>
    </div>

    <div class="mx-auto mt-4 flex flex-col">
      <form
        method="post"
        class="login-form z-2 mx-auto flex w-80 flex-col p-6"
        @submit.prevent="registerUser"
      >
        <div class="mx-auto my-2 flex w-full flex-col gap-4">
          <InputField
            id="make"
            v-model="make"
            class="h-10 rounded bg-light-gray pl-2"
            type="text"
            name="make"
            placeholder="Renault"
            required
          />
          <InputField
            id="model"
            v-model="model"
            class="h-10 rounded bg-light-gray pl-2"
            type="text"
            name="model"
            placeholder="Clio 4"
            required
          />
          <InputField
            id="year"
            v-model="year"
            class="h-10 rounded bg-light-gray pl-2"
            type="text"
            name="year"
            placeholder="2014"
            required
          />
          <InputField
            id="colour"
            v-model="colour"
            class="h-10 rounded bg-light-gray pl-2"
            type="text"
            name="colour"
            placeholder="Czarny"
            required
          />
          <label for="selector">Wybierz rozmiar</label>
          <select
            id="selector"
            v-model="size"
            name="selector"
            class="h-10 rounded bg-light-gray pl-2"
          >
            <option v-for="carSize in sizes" :key="carSize">
              {{ carSize }}
            </option>
          </select>
        </div>
        <button
          type="submit"
          class="submit-btn mx-auto my-6 w-full rounded-lg bg-gray-500 p-2 hover:bg-mcl-orange"
        >
          Dodaj
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue'

export default {
  name: 'ReserveForm',
  components: { InputField },
  data() {
    return {
      sizes: ['Małe', 'Średnie', 'Duże'],
      make: '',
      model: '',
      year: '',
      colour: '',
      size: ''
    }
  },
  methods: {}
}
</script>

<script setup>
// import axios from 'axios'
// import { ref } from 'vue'

// const date = ref(new Date())
</script>
