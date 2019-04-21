<template>
  <div class="route-form">
    <form @submit.prevent="submitForm">
      <div class="location">
        <label for="location">Starting from...</label>
        <input
          ref="location"
          name="location"
          type="text"
          placeholder="77 West Wacker Dr, Chicago IL"
        >
      </div>
      <div class="distance">
        <label for="distance">I want to run...</label>
        <select
          v-model="distance"
          name="distance"
        >
          <option value="1">
            1 mile
          </option>
          <option value="2">
            2 miles
          </option>
          <option value="3">
            3 miles
          </option>
          <option value="4">
            4 miles
          </option>
          <option value="5">
            5 miles
          </option>
          <option value="6">
            6 miles
          </option>
          <option value="7">
            7 miles
          </option>
          <option value="8">
            8 miles
          </option>
          <option value="9">
            9 miles
          </option>
          <option value="10">
            10 miles
          </option>
        </select>
      </div>
      <div class="shape">
        <label for="shape">On a route that is...</label>
        <select
          v-model="shape"
          name="shape"
        >
          <option value="out-and-back">
            out and back
          </option>
          <option value="circular">
            circular
          </option>
        </select>
      </div>
      <div class="submit">
        <button>Find Routes!</button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'RouteForm',
  data() {
    return {
      distance: '',
      shape: ''
    }
  },
  computed: {
    ...mapGetters([
      'startLocation',
      'startLocationExists',
      'startLocationLat',
      'startLocationLng'
    ])
  },
  /**
   * Init Google Places autocomplete
   */
  mounted() {
    /* eslint-disable-next-line no-undef */
    this.autocomplete = new google.maps.places.Autocomplete(this.$refs.location, {
      types: ['geocode']
    })
    this.autocomplete.setFields(['geometry']) // return only coordinate data
    this.autocomplete.addListener('place_changed', () => {
      this.handleLocationInput(this.autocomplete.getPlace())
    })
  },
  methods: {
    submitForm() {
      const requestParams = {
        startLat: this.startLocationLat,
        startLng: this.startLocationLng,
        distance: this.distance,
        shape: this.shape
      }
      this.getRoutes(requestParams)
    },
    handleLocationInput(place) {
      this.setStartLocation(place)
    },
    ...mapActions([
      'setStartLocation',
      'getRoutes'
    ])
  }
}
</script>

<style lang="scss" scoped>
.route-form {
  width: 18em;
  padding: 1em;
  border-right: 1px solid rgba(0, 0, 0, 0.25);
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
