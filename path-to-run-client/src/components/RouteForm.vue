<template>
  <div class="route-form">
    <form @submit.prevent="submitForm">
      <div class="location">
        <label for="location">Starting from...</label>
        <input
          name="location"
          ref="location"
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
  </div>
</template>

<script>
export default {
  name: 'RouteForm',
  data() {
    return {
      startLat: '',
      startLng: '',
      distance: '',
      shape: ''
    };
  },
  methods: {
    submitForm() {
      console.log('Form submitted with data:');
      console.log({
        startLat: this.startLat,
        startLng: this.startLng,
        distance: this.distance,
        shape: this.shape
      });
    },
    handleLocationInput(place) {
      this.startLat = place.geometry.location.lat();
      this.startLng = place.geometry.location.lng();
    }
  },
  /**
   * Init Google Places autocomplete
   */
  mounted() {
    this.autocomplete = new google.maps.places.Autocomplete(this.$refs.location, {
      types: ['geocode']
    });
    this.autocomplete.setFields(['geometry']); // return only coordinate data
    this.autocomplete.addListener('place_changed', () => {
      this.handleLocationInput(this.autocomplete.getPlace());
    });
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
    text-align: center;
    margin-top: 2em;
  }
}
</style>
