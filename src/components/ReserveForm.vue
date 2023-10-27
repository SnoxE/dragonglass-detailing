<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Zarezerwuj usługę</span>
    </div>

    <FormKit id="reserve-form" type="form" submit-label="Rezerwuj">
      <FormKit
        type="text"
        name="name"
        label="Your name"
        placeholder="Jane Doe"
        help="What do people call you?"
        validation="required"
      />
    </FormKit>
  </div>
</template>

<script>
export default {
  name: 'ReserveForm',
  data() {
    return {
      inputFields: [
        { id: 'email', type: 'text', name: 'email', placeholder: 'you@gmail.com' },
        { id: 'password', type: 'password', name: 'password', placeholder: 'password' }
      ]
    }
  }
}
</script>
