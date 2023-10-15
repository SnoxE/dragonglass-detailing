<template>
  <router-link
    :to="link"
    class="block py-2 px-2 text-white md:bg-transparent md:p-0 hover:underline underline-offset-8"
  >
    <slot />
  </router-link>
</template>

<script>
export default {
  name: 'NavBarItem',
  props: {
    link: {
      type: String,
      required: false,
      default: '#'
    }
  }
}
</script>
