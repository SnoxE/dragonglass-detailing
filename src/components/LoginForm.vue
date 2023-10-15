<template>
  <div class="justify-center text-white">
    <div class="">
      <span>Witaj z powrotem</span>
    </div>

    <form action="" method="post" class="login-form z-10 flex flex-col">
      <div class="flex flex-col">
        <input id="username" type="text" name="username" required />
      </div>
      <div class="flex flex-col">
        <input id="password" type="password" name="password" required />
      </div>
      <button type="submit" class="submit-btn">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'LoginForm'
}
</script>
