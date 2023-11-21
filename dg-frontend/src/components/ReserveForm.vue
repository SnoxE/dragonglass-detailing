<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Zarezerwuj usługę</span>
    </div>

    <div class="mx-auto mt-4 flex flex-col">
      <form action="#" method="post" class="flex flex-col gap-5">
        <!-- Name Input -->
        <div v-for="inputField in inputFields" :key="inputField.id" class="flex flex-col gap-2">
          <label :for="inputField.id">{{ inputField.label }}</label>
          <input
            :id="inputField.id"
            :type="inputField.type"
            :name="inputField.name"
            :placeholder="inputField.placeholder"
            required
            class="h-10 rounded bg-light-gray pl-2"
          />
        </div>

        <div class="flex flex-col gap-2">
          <label for="selector">Wybierz usługę</label>
          <select id="service" name="selector" class="h-10 rounded bg-light-gray pl-2">
            <option v-for="service in services" :key="service" :value="service.value">
              {{ service.name }}
            </option>
          </select>
        </div>
        <div class="flex flex-col gap-2">
          <label for="date">Wybierz datę</label>
          <VueDatePicker
            id="date"
            v-model="date"
            dark
            :enable-time-picker="false"
            :min-time="{ hours: 10, minutes: 0 }"
          />
        </div>
        <div class="mx-auto">
          <button type="submit" class="rounded-md bg-mcl-orange p-2 px-10" @click.prevent="xd">
            Zarezerwuj
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReserveForm',
  data() {
    return {
      inputFields: [
        { id: 'name', type: 'text', name: 'name', label: 'Imię', placeholder: 'Jan' },
        {
          id: 'surname',
          type: 'text',
          name: 'surname',
          label: 'Nazwisko',
          placeholder: 'Kowalski'
        },
        {
          id: 'email',
          type: 'text',
          name: 'email',
          label: 'Email',
          placeholder: 'nazwa@gmail.com'
        }
      ],
      services: [],
      carSizes: [
        { value: 'small', name: 'Małe' },
        { value: 'medium', name: 'Średnie' },
        { value: 'large', name: 'Duże' }
      ]
    }
  },
  async mounted() {
    await this.getServices()
  },
  methods: {
    async getServices() {
      const response = await axios.get('api/services')
      this.services = response.data['content']
      console.log(this.services)
    },
    xd() {}
  }
}
</script>

<script setup>
import axios from 'axios'
import { ref } from 'vue'

const date = ref(new Date())
</script>
