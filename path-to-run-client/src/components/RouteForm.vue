<template>
  <div class="route-form">
    <form @submit.prevent="submitForm">
      <div class="address">
        <label for="address">Starting from...</label>
        <input
          name="address"
          v-model.trim="address"
          type="text"
          placeholder="77 West Wacker Dr, Chicago IL"
        >
      </div>
      <div class="distance">
        <label for="distance">I want to run...</label>
        <select name="distance" v-model="distance">
          <option value="1">1 mile</option>
          <option value="2">2 miles</option>
          <option value="3">3 miles</option>
          <option value="4">4 miles</option>
          <option value="5">5 miles</option>
        </select>
      </div>
      <div class="shape">
        <label for="shape">On a route that is...</label>
        <select name="shape" v-model="shape">
          <option value="out-and-back">out and back</option>
          <option value="circular">circular</option>
        </select>
      </div>
      <div class="submit">
        <button>Find Routes!</button>
      </div>
    </form>
    <loading-spinner v-show="routesRequestInProgress"></loading-spinner>
  </div>
</template>

<script>
export default {
  name: 'RouteForm',
  data() {
    return {
      address: '',
      distance: '',
      shape: ''
    };
  }
};
</script>

<style lang="scss" scoped>
.route-form {
  flex-grow: 1;
  padding: 1em;
  .distance {
    margin-top: 2em;
  }
  .shape {
    margin-top: 0.5em;
  }
  .submit {
    margin-top: 2em;
  }
}
</style>
