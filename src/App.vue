<template>
  <NavBar />
  <HeroSection />
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import HeroSection from '@/components/HeroSection.vue'

export default {
  name: 'App',
  components: {
    NavBar,
    HeroSection
  }
}
</script>
