<template>
  <div
    class="relative w-full justify-center before:absolute before:inset-0 before:bg-black before:opacity-60 before:content-['']"
  >
    <img src="@/assets/2 bucket Taycan wash edited.jpg" />
  </div>
  <div class="z-5 absolute top-32 flex w-full transform flex-col justify-center md:top-1/4">
    <h1 class="flex justify-center p-4 text-center text-3xl font-bold text-white md:text-6xl">
      Lorem ipsum dolor sit amet <br />
      consectetur adipisicing elit.
    </h1>
    <div class="mt-8 flex w-full justify-center">
      <router-link
        to="/oferta"
        class="prices-btn mx-4 my-2 flex w-36 justify-center rounded-lg border border-solid border-mcl-orange bg-transparent px-6 py-2 font-medium text-white hover:bg-mcl-orange"
      >
        Nasza oferta
      </router-link>
      <router-link
        to="/"
        class="prices-btn mx-4 my-2 flex w-36 justify-center rounded-lg border border-solid border-mcl-orange bg-transparent px-6 py-2 font-medium text-white hover:bg-mcl-orange"
      >
        Rezerwuj
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HeroSection'
}
</script>
