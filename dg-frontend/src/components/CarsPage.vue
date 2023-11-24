<template>
  <div class="min-h-screen bg-dark-mode-gray">
    <div class="mx-auto flex max-w-screen-xl flex-col flex-wrap gap-6 p-4">
      <h1 class="border-b-2 pb-2 text-4xl font-medium text-white">SAMOCHODY</h1>

      <div class="flex flex-col gap-3 text-lg text-white">
        <span>FILTRUJ</span>
      </div>
      <div
        v-for="car in carList"
        :key="car.id"
        class="flex max-w-screen-md rounded-lg border-2 border-transparent text-white hover:border-white"
      >
        <div class="bg flex rounded-l-lg bg-light-gray px-8 py-6 text-xl font-medium">
          <div class="flex flex-col">
            <span> {{ car.make }}</span>
          </div>
        </div>
        <div class="flex justify-between rounded-r-lg bg-dark-gray">
          <div class="my-auto flex flex-col px-10">
            <span> {{ car.production_year }}</span>
            <span class="text-lg"> {{ car.model }}</span>
          </div>
        </div>
        <div class="my-auto flex flex-col px-5">
          <span> {{ titleCase(car.colour.toLowerCase()) }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'OrdersPage',
  data() {
    return {
      items: [
        { id: 1, label: 'Option 1', value: 'option1' },
        { id: 2, label: 'Option 2', value: 'option2' },
        { id: 3, label: 'Option 3', value: 'option3' }
      ],
      carList: [],
      selectedItems: [],
      userId: ''
    }
  },
  async mounted() {
    await this.fetchUserId()
    await this.fetchUserCars()
  },
  methods: {
    async fetchUserId() {
      const response = await axios.get('api/users/user')
      this.userId = response.data['id']
    },
    async fetchUserCars() {
      const response = await axios.get('api/users/' + this.userId + '/cars')
      const carList = response.data['content']

      carList.forEach((car) => {
        this.carList.push(car)
      })
    },
    titleCase(input) {
      var splitInput = input.toLowerCase().split(' ')
      for (var i = 0; i < splitInput.length; i++) {
        splitInput[i] = splitInput[i].charAt(0).toUpperCase() + splitInput[i].substring(1)
      }

      return splitInput.join(' ')
    }
  }
}
</script>
