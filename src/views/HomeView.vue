<template>
  <HeroSection />
  <InfoSection />
</template>

<script>
import HeroSection from '@/components/HeroSection.vue'
import InfoSection from '@/components/InfoSection.vue'

export default {
  name: 'HomeView',
  components: {
    HeroSection,
    InfoSection
  }
}
</script>
