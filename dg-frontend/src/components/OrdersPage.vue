<template>
  <div class="min-h-screen bg-dark-mode-gray">
    <div class="mx-auto flex max-w-screen-xl flex-col flex-wrap gap-6 p-4">
      <h1 class="border-b-2 pb-2 text-4xl font-medium text-white">REZERWACJE</h1>

      <div class="flex flex-col gap-3 text-lg text-white">
        <span>FILTRUJ</span>
        <div>
          <!-- <label for=""></label>
            
          <input type="selector" name="" id="" class="bg-light-gray" /> -->
          <div class="selector">
            <form>
              <div v-for="item in items" :key="item.id" class="checkbox">
                <input
                  :id="`checkbox-${item.id}`"
                  v-model="selectedItems"
                  type="checkbox"
                  :value="item.value"
                />
                <label :for="`checkbox-${item.id}`">{{ item.label }}</label>
              </div>
            </form>
            <div>Selected Items: {{ selectedItems }}</div>
          </div>
        </div>
      </div>
      <div
        v-for="order in orderList"
        :key="order.id"
        class="flex max-w-screen-md rounded-lg border-2 border-transparent text-white hover:border-white"
      >
        <div class="w-1/4 rounded-l-lg bg-light-gray p-6">
          <div class="flex flex-col">
            <span class="mb-2 font-medium">{{ order.status }}</span>
            <span> {{ order.date }}</span>
            <span class="text-xkom-gray"> nr {{ order.id }}</span>
            <span class="mt-2">{{ order.price }} zł</span>
          </div>
        </div>
        <div class="flex w-3/4 justify-between">
          <div class="my-auto flex flex-col p-10">
            <span class="justify-end text-xl font-medium"> {{ order.service }}</span>
            <span class="text-lg"> {{ order.car }}</span>
          </div>
          <div class="">
            <button
              class="m-4 rounded-lg border border-transparent px-4 py-2 font-medium hover:border hover:border-mcl-orange"
            >
              Anuluj
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OrdersPage',
  data() {
    return {
      orderList: [
        {
          id: '0001',
          status: 'Rezerwacja',
          service: 'Mycie Ręczne',
          date: '30.10.2023',
          price: 150,
          car: 'Renault Clio 4'
        },
        {
          id: '0002',
          status: 'Rezerwacja',
          service: 'PPF',
          date: '30.10.2023',
          price: 4000,
          car: 'Renault Clio 4'
        }
      ],
      items: [
        { id: 1, label: 'Option 1', value: 'option1' },
        { id: 2, label: 'Option 2', value: 'option2' },
        { id: 3, label: 'Option 3', value: 'option3' }
      ],
      selectedItems: []
    }
  }
}
</script>
